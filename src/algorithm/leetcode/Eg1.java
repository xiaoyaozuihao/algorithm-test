package leetcode;

import util.BaseUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author xuyh
 * @date 2019/5/16
 */
public class Eg1 {
    //暴力破解
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer divide = map.get(target - nums[i]);
            if (divide != null && divide != i) {
                return new int[]{i, divide};
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i != nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    //leetCode最优解法
    public static int[] twoSum3(int[] nums, int target) {
        int indexArrayMax = 2047;//等于 2^11-1,所有位都是1
        int[] indexArrays = new int[indexArrayMax + 1];
        int index;
        int diff;
        for (int i = 1; i < nums.length; i++) {
            diff = target - nums[i];
            //i=0时索引无效,所以单独处理
            if (diff == nums[0]) {
                return new int[]{0, i};
            }
            //对差值&2047相当于将此差值映射到数组的某个位置，如果这个位置不为0，说明存在直接返回
            //实际是取一个数的低11位，所以需要考虑hash冲突问题
            index = diff & indexArrayMax;
            if (indexArrays[index] != 0) {
                return new int[]{indexArrays[index], i};
            }
            //将当前数&2047放入数组中，记录当前数的坐标
            indexArrays[nums[i] & indexArrayMax] = i;
        }
        throw new IllegalArgumentException("no two sum value");

//        int indexArrayMax = 2047;
//        int[] indexArray = new int[indexArrayMax + 1];
//        int index = 0;
//        for (int i = 0; i < arr.length; i++) {
//            index = indexArray[(aim - arr[i]) & indexArrayMax];
//            if (index != 0) {
//                return new int[]{i, index-1};
//            }
//            indexArray[arr[i] & indexArrayMax] = i + 1;
//        }
//        return null;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4};
//        arr = twoSum3(new int[]{2047,4095,6193}, 8240);
        arr=twoSum3(new int[]{2047,546666, 800001, 1898, 7,2,2048}, 548564);
        BaseUtil.printArray(arr);
    }
}
