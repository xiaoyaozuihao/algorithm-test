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

}
