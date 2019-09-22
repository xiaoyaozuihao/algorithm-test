package array;

import util.BaseUtil;

import java.util.LinkedList;

/**
 * 给定一个数组，求该数组所有子数组中最大值减最小值小于等于num的子数组的数量
 * 利用滑动窗口最大值和最小值巧妙解答
 * @author xuyh
 * @date 2019/9/19
 */
public class AllLessNumSubArray {
    public static void main(String[] args) {
//        int[] arr = getRandomArray(30);
        int[] arr={8,2,3,4,7,2,8,0,6,2,0,4,0,2,1,1,1,4,2,9,7,3,5,1,2,8,1,1,5,4};
        int num = 5;
        BaseUtil.printArray(arr);
        System.out.println(getNum(arr, num));
        System.out.println(getSubArrayNum(arr,num));
    }

    /**
     * 加速原理：
     * 如果一个子数组L到R达标，则它内部的所有子数组都达标，因为向内收缩时，如果max变小，或者min变大，都会导致max-min变得更小；
     * 同理如果一个子数组不达标，则包含这个子数组的子数组都不达标，因为向外扩的时候，如果max变大，或者min变小都会导致max-min变得更大。
     * 时间复杂度O(n)
     * @return
     */
    public static int getSubArrayNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> max = new LinkedList<>();
        LinkedList<Integer> min = new LinkedList<>();
        int j = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            //获取以i开始到j的子数组中的最大值和最小值
            for (; j < arr.length; j++) {
                //使用滑动窗口维护窗口内的最大值和最小值
                while (!max.isEmpty() && arr[max.peekLast()] <= arr[j]) {
                    max.pollLast();
                }
                while (!min.isEmpty() && arr[min.peekLast()] >= arr[j]) {
                    min.pollLast();
                }
                min.addLast(j);
                max.addLast(j);
                //j到达能到达的最大位置，则从i~j形成的子数组均是达标子数组。
                if (arr[max.peekFirst()] - arr[min.peekFirst()] > num) {
                    break;
                }
            }
            //查看下标是否过期
            if (min.peekFirst() == i) {
                min.pollFirst();
            }
            if (max.peekFirst() == i) {
                max.pollFirst();
            }
            //一次性获取以i为起始的所有达标的子数组的数量
            //下次循环以i+1位置作为起始，j的值继续从上一次的位置往后走，走到窗口内最大值减最小值大于目标值则停止。
            //则又可以一次性获取以i+1位置作为起始的所有达标子数组的数量
            res += j - i;
            //打印达标的子数组的父集
//            int[] copyArr = new int[j-i];
//            System.arraycopy(arr,i,copyArr,0,j-i);
//            System.out.println(Arrays.toString(copyArr));
        }
        return res;
    }

    //暴力法，O(n^3)，穷举所有子数组，看每一个子数组是否都达标
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

    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }
}
