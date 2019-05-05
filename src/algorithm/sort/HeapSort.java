package sort;

import util.BaseUtil;

/**
 * 堆排序
 *
 * @author xuyh
 * @date 2019/4/7
 */
public class HeapSort {

    //1.将数组转换成大根堆
    //2.弹出堆顶元素，将最后一个元素放到堆顶，进行heapify操作。
    //3.循环第二步，弹出的元素就是排好序的
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //循环数据，将之变成大根堆,将每次循环的数添加到堆的最后，然后进行heapInsert的操作。
        for (int i = 1; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        //形成大根堆之后，每次从堆顶取出最大值，即排好一个数，然后进行heapify的操作
        BaseUtil.swap(arr, 0, --heapSize);//第一次将第一个元素和最后一个交换，即堆顶和堆的最后一个元素交换
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);//每次都从堆顶向下做heapify操作
            BaseUtil.swap(arr, 0, --heapSize);//每次循环都进行此交换操作，且数组长度减1，代表已排好一个数
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;//目标节点左侧子节点
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            BaseUtil.swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void heapInsert(int[] arr, int i) {//就是和父节点进行比较
        while (arr[i] > arr[(i - 1) / 2]) {//此循环的时间复杂度只和堆的高度有关，所以为logN
            BaseUtil.swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    public static void main(String[] args) {
        BaseUtil.testTemplate("HeapSort", "heapSort");
    }
}
