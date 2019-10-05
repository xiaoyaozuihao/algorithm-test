package dp;

import util.BaseUtil;

/**
 * 在一个全是正整数的数组中求累加和为aim的最长子数组的长度
 *
 * @author: xuyh
 * @create: 2019/10/5
 **/
public class LongestSumSubArrayLengthInPositiveArray {
    public static void main(String[] args) {
        int k = 15;
        int[] arr = generatePositiveArray(10);
        System.out.println(getMaxLength(arr, k));
        BaseUtil.printArray(arr);
    }

    public static int[] generatePositiveArray(int size) {
        int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int L = 0, R = 0;
        int sum = arr[0];
        int len = 0;
        while (R < arr.length) {
            if (sum == aim) {
                len = Math.max(len, R - L + 1);
                sum -= arr[L++];
            } else if (sum < aim) {
                R++;
                if (R == arr.length) {
                    break;
                }
                sum += arr[R];
            } else {
                sum -= arr[L++];
            }
        }
        return len;
    }
}
