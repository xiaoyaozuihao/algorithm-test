package other;

import util.BaseUtil;

import java.util.Arrays;

/**
 * 获取数组中两数的最大差值
 *
 * @author xuyh
 * @date 2019/4/20
 */
public class MaxGap {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        if (min == max) {
            return 0;
        }
        int[] mins = new int[len + 1];
        int[] maxs = new int[len + 1];
        boolean[] hasNum = new boolean[len + 1];
        int bid;
        for (int i = 0; i < len; i++) {
            bid = bucket(arr[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(arr[i], mins[bid]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(arr[i], maxs[bid]) : arr[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        for (int i = 1; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    private static int bucket(int num, int len, int min, int max) {
        return (num - min) * len / (max - min);
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        Arrays.sort(arr);
        int gap = 0;
        for (int i = 1; i < arr.length; i++) {
            gap = Math.max(arr[i] - arr[i - 1], gap);
        }
        return gap;
    }

    public static void main(String[] args) {
        int times = 10000;
        boolean succeed = true;
        for (int i = 0; i < times; i++) {
            int[] array = BaseUtil.generateRandomArray(1000, 1000);
            int[] array1 = BaseUtil.copyArray(array);
            if (maxGap(array) != comparator(array1)) {
                succeed = false;
                BaseUtil.printArray(array);
                break;
            }
        }
        System.out.println(succeed ? "nice" : "no");
    }
}
