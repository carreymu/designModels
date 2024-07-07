package electric;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class IncreasingSubarrays {
    /**
     * 找到给定列表中的所有递增子数组
     *
     * @param maps 包含键值对(k, v)的列表
     * @return 所有递增子数组的列表
     */
    public static List<List<Map<String, Double>>> findIncreasingSubArrays(List<Map<String, Double>> maps) {
        List<List<Map<String, Double>>> result = new ArrayList<>();

        // 如果输入列表为空或长度为0，直接返回空结果
        if (maps == null || maps.isEmpty()) {
            return result;
        }

        List<Map<String, Double>> currentSubarray = new ArrayList<>();
        // 初始化当前子数组为第一个元素
        currentSubarray.add(maps.get(0));

        for (int i = 1; i < maps.size(); i++) {
            // 如果当前元素的v值大于前一个元素的v值，则将当前元素添加到当前子数组
            if (maps.get(i).get("v") > maps.get(i - 1).get("v")) {
                currentSubarray.add(maps.get(i));
            } else {
                // 如果当前子数组的长度大于1，则将其添加到结果列表中
                if (currentSubarray.size() > 1) {
                    result.add(new ArrayList<>(currentSubarray));
                }
                // 重置当前子数组为当前元素
                currentSubarray.clear();
                currentSubarray.add(maps.get(i));
            }
        }
        // 最后检查当前子数组是否长度大于1，如果是则将其添加到结果列表中
        if (currentSubarray.size() > 1) {
            result.add(currentSubarray);
        }

        return result;
    }

    /**
     * 找到给定列表中的所有递增子数组，并只保留每个子数组的第一个和最后一个值
     *
     * @param maps 包含键值对(k, v)的列表
     * @return 仅保留每个递增子数组的第一个和最后一个值的列表,返回值仅为充放电时间段对应段序号值
     */
    public static List<List<Integer>> findIncreasingSubarrays(List<Map<String, Double>> maps) {
        List<List<Integer>> result = new ArrayList<>(); // 用于存储最终结果的列表

        // 如果输入列表为空或长度为0，直接返回空结果
        if (maps == null || maps.isEmpty()) {
            return result;
        }
        // 当前递增子数组
        List<Map<String, Double>> currentSubarray = new ArrayList<>();
        // 初始化当前子数组为第一个元素
        currentSubarray.add(maps.get(0));

        // 遍历列表中的每个元素
        for (int i = 1; i < maps.size(); i++) {
            // 如果当前元素的v值大于前一个元素的v值，则将当前元素添加到当前子数组
            if (maps.get(i).get("v") > maps.get(i - 1).get("v")) {
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
            subarray.add((currentSubarray.get(0).get("k").intValue()));
            // 添加最后一个元素
            subarray.add(currentSubarray.get(currentSubarray.size() - 1).get("k").intValue());
            result.add(subarray);
        }
    }

    public static void main(String[] args) {
        List<Map<String, Double>> list1 = new ArrayList<>();
        list1.add(createMap(0, 0.31d));
        list1.add(createMap(1, 0.76d));
        list1.add(createMap(2, 0.92d));
        list1.add(createMap(3, 1.2d));
        list1.add(createMap(4, 0.92d));
        list1.add(createMap(5, 0.76d));
        list1.add(createMap(6, 0.92d));
        list1.add(createMap(7, 0.76d));
        list1.add(createMap(8, 0.31d));

        List<Map<String, Double>> list2 = new ArrayList<>();
        list2.add(createMap(0, 0.31d));
        list2.add(createMap(1, 0.76d));
        list2.add(createMap(2, 0.92d));
        list2.add(createMap(3, 0.76d));
        list2.add(createMap(4, 0.92d));
        list2.add(createMap(5, 0.76d));
        list2.add(createMap(6, 0.92d));
        list2.add(createMap(7, 0.76d));
        list2.add(createMap(8, 0.31d));

        System.out.println("List 1: " + findIncreasingSubarrays(list1));
        System.out.println("List 2: " + findIncreasingSubarrays(list2));
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
        map.put("k", (double) k); // 将k转换为Double类型
        map.put("v", v);
        return map;
    }
}
