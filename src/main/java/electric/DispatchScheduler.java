package electric;

import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class DispatchScheduler {
     /**
      * 生成一天内最优的充电和放电指令列表
      * @param context  配置上下文
      * @return 充放电时间段
     */
    public static List<DispatchCommand> dispatch(DispatchContext context) {
        // ##### 1. 加载配置
        log.info("充放电最大收益,本月DispatchContext配置:"+ JSONObject.toJSONString(context));
        TouDTO touDTO = context.getTouDTO();
        DeviceSpecDTO deviceSpecDTO = context.getDeviceSpecDTO();
        // todo 充电宝容量暂未用到，考量设备损耗会用到吧
        double capacity = deviceSpecDTO.getCapacity();

        // ##### 2. 计算一天充放电时间段,找到最低和最高电价的时段
        Set<TouPriceDTO> prices = touDTO.getPrices();
        List<TouPeriodDTO> periods = new ArrayList<>(touDTO.getPeriods());
        List<Pair<TouPeriodDTO,TouPeriodDTO>> list = findChargePeriods(prices, periods);
        if(null == list) {
            // todo 需自定义系统错误号;严重错误,需要大盘显示,加入报警处理。
            throw new RuntimeException("充放电最大收益异常,错误号10000");
        }
        log.info("充放电最大收益,periods:"+ JSONObject.toJSONString(periods));

        // ##### 3. 生成充放电指令
        List<DispatchCommand> commands = new ArrayList<>();
        // 标准功率
        double standardPower = deviceSpecDTO.getStandardPower();
        for (Pair<TouPeriodDTO,TouPeriodDTO> pair : list) {
            // 生成充电指令
            commands.add(new DispatchCommand(pair.getKey().getStartTime(), pair.getKey().getEndTime(), standardPower));
            // 生成放电指令
            commands.add(new DispatchCommand(pair.getValue().getStartTime(), pair.getValue().getEndTime(), -standardPower));
        }
        log.info("充放电最大收益,充放电指令:"+ JSONObject.toJSONString(commands));
        return commands;
    }

    /**
     * 返回充放电时间段
     * 1.根据配置时间段的开始时间升序
     * 2.排序后的时间遍历后生成俩Map
     *   2.1. Map<序号,电价>用于计算最大收益的充放电时段
     *   2.2. Map<序号,时间段>与排序后段充电时间段Map,查找最终的时间段
     * 3.给出计算后充放电最大收益时间段
     * @param prices
     * @param periods
     * @return
     */
    private static List<Pair<TouPeriodDTO,TouPeriodDTO>> findChargePeriods(Set<TouPriceDTO> prices, List<TouPeriodDTO> periods) {
        if (null == prices || null == periods || prices.size() == 0 || periods.size() == 0) {
            return null;
        }
        try {
            // 按时间顺序排序时段
            periods.sort(Comparator.comparing(x -> x.getStartTime()));
            List<Pair<TouPeriodDTO, TouPeriodDTO>> result = new ArrayList<>();

            Map<String, TouPeriodDTO> allPeriods = new HashMap<>();
            List<Map<String, Double>> list = new ArrayList<>();
            for (int i = 0; i < periods.size(); i++) {
                TouPeriodDTO touPeriodDTO = periods.get(i);
                TouState state = touPeriodDTO.getState();
                TouPriceDTO touPriceDTO = prices.stream().filter(x -> x.getState() == state).findFirst().orElse(null);
                // 找不到说明配置不对,直接异常捕获；todo 自定义异常明确错误原因
                list.add(createMap(i, touPriceDTO.getPrice()));
                allPeriods.put(Integer.toString(i), touPeriodDTO);
            }
            // 取最大充放电收益时间段序号
            List<List<Integer>> increasingSubArrays = findIncreasingSubArrays(list);
            log.debug("充放电最大收益,序号", JSONObject.toJSON(increasingSubArrays));
            for (List<Integer> increasingSubArray : increasingSubArrays) {
                //充电时间, 放电时间
                result.add(new Pair(allPeriods.get(increasingSubArray.get(0).toString()), allPeriods.get(increasingSubArray.get(1).toString())));
            }
            return result;
        } catch (Exception e) {
            log.error("充放电最大收益,计算充放电时间段异常" + e.getStackTrace());
            return null;
        }
    }

    /**
     * 创建包含键值对(k, v)的HashMap
     *
     * @param k 键
     * @param v 值
     * @return 包含键值对(k, v)的HashMap
     */
    private static Map<String, Double> createMap(int k, double v) {
        Map<String, Double> map = new HashMap<>();
        map.put("key", (double) k); // 将k转换为Double类型
        map.put("value", v);
        return map;
    }

    /**
     * 找到给定列表中的所有递增子数组，并只保留每个子数组的第一个和最后一个值
     *
     * @param maps 包含键值对(k, v)的列表
     * @return 仅保留每个递增子数组的第一个和最后一个值的列表,返回值仅为充放电时间段对应段序号值
     */
    public static List<List<Integer>> findIncreasingSubArrays(List<Map<String, Double>> maps) {
        List<List<Integer>> result = new ArrayList<>();

        // 如果输入列表为空或长度为0，直接返回空结果
        if (null == maps || maps.isEmpty()) {
            return result;
        }
        // 当前递增子数组
        List<Map<String, Double>> currentSubarray = new ArrayList<>();
        // 初始化当前子数组为第一个元素
        currentSubarray.add(maps.get(0));

        // 遍历列表中的每个元素
        for (int i = 1; i < maps.size(); i++) {
            // 如果当前元素的value值大于前一个元素的v值，则将当前元素添加到当前子数组
            if (maps.get(i).get("value") > maps.get(i - 1).get("value")) {
                currentSubarray.add(maps.get(i));
            } else {
                // 如果当前子数组的长度大于1，则将其第一个和最后一个元素添加到结果列表中
                findStartAndEndOrder(result, currentSubarray);
                // 重置当前子数组为当前元素
                currentSubarray.clear();
                currentSubarray.add(maps.get(i));
            }
        }

        // 最后检查当前子数组是否长度大于1，如果是则将其第一个和最后一个元素添加到结果列表中
        findStartAndEndOrder(result, currentSubarray);

        return result;
    }

    /**
     * 仅取序号
     * @param result
     * @param currentSubarray
     */
    private static void findStartAndEndOrder(List<List<Integer>> result, List<Map<String, Double>> currentSubarray) {
        if (currentSubarray.size() > 1) {
            List<Integer> subarray = new ArrayList<>();
            // 添加第一个元素,序号转化
            subarray.add((currentSubarray.get(0).get("key").intValue()));
            // 添加最后一个元素
            subarray.add(currentSubarray.get(currentSubarray.size() - 1).get("key").intValue());
            result.add(subarray);
        }
    }
}
