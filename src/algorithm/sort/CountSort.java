package sort;

import util.BaseUtil;

/**
 * 计数排序，需要占用大量空间，它仅适用于数据比较集中的情况，比如 [0~100]，[10000~19999] 这样的数据。
 *
 * @author xuyh
 * @date 2019/4/13
 */
public class CountSort {
    //不能实现稳定性
    public static void countSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            min = Math.min(array[i], min);
            max = Math.max(array[i], max);
        }
        //如果数组中的数不集中，会浪费大量的桶
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] - min]++;
        }
        for (int j = 0, i = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                //注意：桶的索引存放的是真正的数-最小的数，所以还原时+最小的数
                array[i++] = j + min;
            }
        }
    }

    //可以保证稳定性
    public static void countSort1(int[] arr) {//未能实现稳定排序
        if (arr == null || arr.length < 2) {
            return;
        }
//        int min=Integer.MAX_VALUE;
//        int max=Integer.MIN_VALUE;
//        for(int i=0;i<arr.length;i++){
//            min= Math.min(arr[i],min);
//            max= Math.max(arr[i],max);
//        }
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i] - min]++;
        }
        for (int j = 1; j < bucket.length; j++) {
            //累加是为了计算每个桶内的数在排好序的数组中的终止索引
            bucket[j] = bucket[j - 1] + bucket[j];
        }
        //辅助数组
        int[] res = new int[arr.length];
        //原数组逆序遍历，保证稳定性
        for (int i = arr.length - 1; i >= 0; i--) {
            int pos = --bucket[arr[i] - min];
            res[pos] = arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res[i];
        }
    }

    public static void main(String[] args) {
        BaseUtil.testSortTemplate("sort.CountSort", "countSort1");
//        int[] arr = {379,589,693};
//        countSort1(arr);
//        BaseUtil.printArray(arr);
    }
}
