package array;

/**
 * 已知一个整型数组arr，数组长度为size且size大于2，arr有size-1种可以划分成左右两部分的方案
 * 每一种划分下，左部分都有最大值记为max_left，右部分都有最大值记为max_right。
 * 求|max_left-max_right|(左部分最大值与左部分最大值之差的绝对值)，最大是多少？
 * 要求：时间复杂度为O(N)，额外空间复杂度O(1)。
 *
 * @author xuyh
 * @date 2019/11/5
 */
public class MaxABSBetweenLeftAndRight {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 2, 1, 6, 7, 8, 0};
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
        System.out.println(maxABS3(arr));
    }

    //时间复杂度是O(N^2)的暴力法
    public static int maxABS1(int[] arr) {
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        //划分成左右两部分的数组至少有一个值，第一层循环就是划分的位置
        for (int i = 0; i < arr.length - 1; i++) {
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j <= i; j++) {
                maxLeft = Math.max(maxLeft, arr[j]);
            }
            maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++) {
                maxRight = Math.max(maxRight, arr[j]);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        return res;
    }

    //时间复杂度是O(N) 空间复杂度是O(N),利用数组进行加速,特别注意边界问题
    public static int maxABS2(int[] arr) {
        int[] lArr = new int[arr.length];
        int[] rArr = new int[arr.length];
        lArr[0] = arr[0];
        rArr[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            lArr[i] = Math.max(lArr[i - 1], arr[i]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            rArr[i] = Math.max(rArr[i + 1], arr[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            res = Math.max(res, Math.abs(lArr[i] - rArr[i + 1]));
        }
        return res;
    }

    public static int maxABS3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }
}
