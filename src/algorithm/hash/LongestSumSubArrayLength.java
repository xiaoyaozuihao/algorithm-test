package hash;

import util.BaseUtil;

import java.util.HashMap;

/**
 * 给定一个数组，一个目标值，求数组中能累加出目标值的最长子串
 *
 * @author: xuyh
 * @create: 2019/9/22
 **/
public class LongestSumSubArrayLength {
    public static void main(String[] args) {
        int[] arr = BaseUtil.generateRandomArray(10, 10);
        int maxLen = getMaxLen(arr, 8);
        System.out.println(maxLen);
    }

    public static int getMaxLen(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - aim)) {
                maxLen = Math.max(i - map.get(sum - aim), maxLen);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}
