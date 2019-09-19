package array;

/**
 * 给定一个数组，求该数组所有子数组中最大值减最小值等于num的子数组的数量
 * @author xuyh
 * @date 2019/9/19
 */
public class AllLessNumSubArray {
    public static int getNum(int[] arr, int num) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isValid(arr, i, j, num)) {
                    res++;
                }
            }
        }
        return res;
    }

    public static boolean isValid(int[] arr, int lo, int hi, int num) {
        int min = arr[lo];
        int max = arr[lo];
        for (int i = lo + 1; i < hi + 1; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max - min <= num;
    }
}
