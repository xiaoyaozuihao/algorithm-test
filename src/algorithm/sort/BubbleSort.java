package sort;

import util.BaseUtil;

/**
 * 冒泡排序
 *
 * @author xuyh
 * @date 2019/3/31
 */
public class BubbleSort {
    //冒泡排序外层循环每次会选出一个最大值或最小值，n个数需要进行n-1次循环
    //内层循环相邻两个数进行比较，n个数需要进行n-i次比较，大值向后移动或小值向前移动，
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //外层递减实现
        for (int e = arr.length - 1; e > 0; e--) {
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    BaseUtil.swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void bubbleSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //外层递增实现
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    BaseUtil.swap(arr, j, j + 1);
                }
            }
        }
    }

    //冒泡排序的优化
    //假如从开始的第一对到结尾的最后一对，相邻的元素之间都没有发生交换的操作，
    //这意味着右边的元素总是大于等于左边的元素，此时的数组已经是有序的了，我们无需再对剩余的元素重复比较下去了。
    public static void bubbleSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    BaseUtil.swap(arr, j, j + 1);
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
