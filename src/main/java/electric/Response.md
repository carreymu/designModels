# 综述

分页接口只是凭逻辑完成,未代码验证;
充放电功能经过idea验证。

若有疑问请及时联系我。

---
# 一、分页接口

## 目标
在token支持的情况下完成分页接口的开发。

## 大致思路

由于需要适配MetricXxxQuery,MetricYyyQuery,MetricZzzQuery多种查询,且看起来三种查询非常相似,因此可以通过泛型+函数式接口延迟实现分页功能的方式统一起来。

## 代码实现部分
### 1.方法主体
```java
/**
 * 自动分页查询方法，通过重试机制确保获取所有数据。
 *
 * @param listMetricsFunction 分页查询函数，接受查询参数并返回包含数据的响应
 * @param query 初始查询参数,最好有区分每次请求的标识
 * @param maxRetries 最大重试次数,为了避免网络波动引起失败查询,具体情况请根据需求入参
 * @param <TQuery> 查询参数类型，必须继承自 BaseMetericQuery
 * @param <TDTO> 数据传输对象类型
 * @return 查询结果列表
 */
public static <TQuery extends BaseMetericQuery, TDTO> List<TDTO> autoPaged(
        Function<TQuery, RpcResponse<List<TDTO>>> listMetricsFunction,
        TQuery query,
        int maxRetries) {
    // 存储所有查询结果
    List<TDTO> allData = new ArrayList<>();
    // 分页令牌，初始为 null
    byte[] token = null;
    // 当前重试次数
    int retries = 0;
    // 当有分页令牌且未超过最大重试次数时继续查询
    do {
        log.info("autoPaged执行查询,入参:" + query);
        try {
            if (token != null) {
                // 设置查询的分页令牌
                query.setToken(token);
            }
            // 执行分页查询
            RpcResponse<List<TDTO>> response = listMetricsFunction.apply(query);

            if (response.getSuccess()) {
                // todo 上线初期请使用info级别,方便查问题
                log.debug("autoPaged执行返回数据:" + JSONObject.toJSONString(response.getData()));
                allData.addAll(response.getData());
                token = response.getToken();
                retries = 0;
            } else {
                // 如果失败间隔随机时间内重试,大于最大次数终止查询
                retries++;
                Thread.sleep(ThreadLocalRandom.current().nextInt(500));
                if (retries > maxRetries) {
                    break;
                    //throw new RuntimeException("autoPaged执行到最大重试次数");
                }
            }
        } catch (Exception e) {
            log.error("autoPaged执行失败,输入参数" + JSONObject.toJSONString(query) + ",重试" + retries + "次");
        }
    } while (token != null && retries <= maxRetries);

    return allData;
}
```
### 2.调用示例
```java
public static void main(String[] args) {
    // 创建查询参数
    MetricXxxQuery query = new MetricXxxQuery();
    // 最大重试次数
    int maxRetries = 3; 

    // 调用 autoPaged 方法，执行分页查询
    List<MetricXxxDTO> results = MetricQueryPager.autoPaged(
        MetricService::listMetricsXxx, // 分页查询函数
        query, // 查询参数
        maxRetries // 最大重试次数
    );

    // 打印查询结果
    results.forEach(System.out::println);
}

// 示例服务调用类
class MetricService {
    /**
     * 模拟的分页查询方法
     *
     * @param query 查询参数
     * @return 分页查询响应
     */
    public static RpcResponse<List<MetricXxxDTO>> listMetricsXxx(MetricXxxQuery query) {
        // 模拟服务调用，返回 RpcResponse
        return new RpcResponse<>();
    }
}
```

### 3.测试用例

一般写单元测试,调用示例中做多case覆盖即可,不多论述。

## 可能的设计优化点
仅限个人代码习惯及个人YY,仅供讨论:
- RpcResponse.data(暂且这么写吧,下同)建议区分List<T>集合类型和单对象类型,或者直接都使用List<T>,单对象直接用集合返回;返回类型对于调用端友好,无需判断。
- RpcResponse.data查询分页最好给个页数和总记录数字段,代码可以再次优化,可以指定查询页。如果DB允许,另外还可以多线程分段取数。
- RpcResponse.token可以放到请求的header里,由框架统一处理。
- RpcResponse最好加个字段responseCode和errorMsg,网络请求的Code和代码错误返回的Code区分开,errorMsg用来显示错误原因。
- 日志格式等请适配公司要求。

# 二、单日最大收益指令
## 目标
由于按月每天的电价都不相同,因此阶梯电价的配置需要从配置文件中载入。在每天谷段充电,峰/尖段放点采可以达到最大收益的目的。

## 大致思路
### 1. 算法思路
本题可以简化为给定一个数组，找出其连续的子数组。例如:[0,1,2,3,2,1,2,1,0]其连续子数组就是[[0,1,2,3],[1,2]];然后找出每个子数组最大及最小值即可。
在此问题基础上给给定的数组加上序号即可。
### 2. 解决问题思路
找到每天充放电时段也就是找出每个连续电价最低和最高的时段，然后取其差值。结合题目实际情况,思路如下：
- **加载配置电价时段配置**：从 `DispatchContext` 中获取电价和时间段配置以及设备参数。
- **明确充放电时段**：根据电价配置最便宜时段充电，最贵时段放电取出成对的充放电时间段。
- **生成充放电调度指令**：根据充电宝的容量和标准功率生成具体的充放电指令。

## 代码实现部分
### 1.方法主体
#### 1.1. 主体部分 DispatchScheduler
```java
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
        // todo 充电宝容量暂未用到
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
```
#### 1.2. 其他pojo
```java
//######## DispatchContext
@Data
public class DispatchContext {
    private TouDTO touDTO;
    private DeviceSpecDTO deviceSpecDTO;
    public DispatchContext(TouDTO touDTO, DeviceSpecDTO deviceSpecDTO) {
        this.touDTO  = touDTO;
        this.deviceSpecDTO = deviceSpecDTO;
    }
}
//####### TouDTO
@Data
public class TouDTO {
    private LocalDate now;
    private Set<TouPeriodDTO> periods;
    private Set<TouPriceDTO> prices;
    public TouDTO(LocalDate now, Set<TouPeriodDTO> periods, Set<TouPriceDTO> prices) {
        this.now = now;
        this.periods = periods;
        this.prices = prices;
    }
}
//####### TouPeriodDTO
@Data
public class TouPeriodDTO {
    private TouState state;
    private LocalTime startTime;
    private LocalTime endTime;
    public TouPeriodDTO(TouState state, LocalTime startTime, LocalTime endTime) {
        this.state = state;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

//####### TouPriceDTO
@Data
public class TouPriceDTO {
    private TouState state;
    private double price;

    public TouPriceDTO(TouState state, double price) {
        this.state = state;
        this.price = price;
    }
}
//####### TouState
public enum TouState {
    OFF_PEAK,
    SHOULDER,
    PEAK,
    CRITICAL_PEAK
}
//####### DeviceSpecDTO
@Data
public class DeviceSpecDTO {
    private double capacity;
    private double standardPower;

    public DeviceSpecDTO(double capacity, double standardPower) {
        this.capacity = capacity;
        this.standardPower = standardPower;
    }
}
//####### DispatchCommand
@Data
public class DispatchCommand {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Double power;

    public DispatchCommand(LocalTime startTime, LocalTime endTime, Double power) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.power = power;
    }

    @Override
    public String toString() {
        return "DispatchCommand{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", power=" + power +
                '}';
    }
} 

```

### 2.调用示例
```java
public static void main(String[] args) {
    testPeriods();
}

public static void testPeriods(){
    // 创建时段配置
    Set<TouPeriodDTO> periods = new HashSet<>();
    periods.add(new TouPeriodDTO(TouState.OFF_PEAK, LocalTime.of(0, 0), LocalTime.of(6, 0)));
    periods.add(new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(6, 0), LocalTime.of(8, 0)));
    periods.add(new TouPeriodDTO(TouState.PEAK, LocalTime.of(8, 0), LocalTime.of(12, 0)));
    periods.add(new TouPeriodDTO(TouState.CRITICAL_PEAK, LocalTime.of(12, 0), LocalTime.of(14, 0)));
    periods.add(new TouPeriodDTO(TouState.PEAK, LocalTime.of(14, 0), LocalTime.of(15, 0)));
    periods.add(new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(15, 0), LocalTime.of(18, 0)));
    periods.add(new TouPeriodDTO(TouState.PEAK, LocalTime.of(18, 0), LocalTime.of(21, 0)));
    periods.add(new TouPeriodDTO(TouState.SHOULDER, LocalTime.of(21, 0), LocalTime.of(22, 0)));
    periods.add(new TouPeriodDTO(TouState.OFF_PEAK, LocalTime.of(22, 0), LocalTime.of(23, 59)));

    // 创建电价配置
    Set<TouPriceDTO> prices = new HashSet<>();
    prices.add(new TouPriceDTO(TouState.OFF_PEAK, 0.31));
    prices.add(new TouPriceDTO(TouState.SHOULDER, 0.76));
    prices.add(new TouPriceDTO(TouState.PEAK, 0.92));
    prices.add(new TouPriceDTO(TouState.CRITICAL_PEAK, 1.20));

    // 创建每日峰谷时段与电价配置
    TouDTO touDTO = new TouDTO(LocalDate.now(), periods, prices);

    // 创建设备参数
    DeviceSpecDTO deviceSpecDTO = new DeviceSpecDTO(100.0, 50.0);

    // 创建调度上下文
    DispatchContext context = new DispatchContext(touDTO, deviceSpecDTO);

    // 获取单日最优充放电指令列表
    List<DispatchCommand> commands = DispatchScheduler.dispatch(context);

    // 打印结果
    commands.forEach(System.out::println);
}
```
### 3.打印结果：
```text
DispatchCommand{startTime=00:00, endTime=06:00, power=50.0}
DispatchCommand{startTime=12:00, endTime=14:00, power=-50.0}
DispatchCommand{startTime=15:00, endTime=18:00, power=50.0}
DispatchCommand{startTime=18:00, endTime=21:00, power=-50.0}
```

## 测试用例
一般写单元测试,调用示例中做多case覆盖即可,不多论述。

## 可能的优化点
仅限个人代码习惯及个人YY,仅供讨论:
- 由于是示例代码，整体性不够，因此涉及到系统统一错误码,日志系统格式等需根据公司要求修正,代码中todo我都有备注。
- 设备损耗,过冲,过放,总容量都是需要加入代码考量都问题，本次仅限解决最基础最大收益问题。
- 代码中涉及的系统建设包括公司系统大盘(记录异常数),系统异常监控,日志系统,调用链监控等。
- 在计算最大收益时间段时可以通过用Map<开始时间,电价>和Map<开始时间,TouPeriodDTO>(如果有更好的办法可以降低复杂度洗耳恭听)
- 代码仅考量单实体部署，多线程方式调用还需深入code review。
- 一般上线前代码会多留log之后再移除,方便查问题,示例代码我只是在关键点打了log。