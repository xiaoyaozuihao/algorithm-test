package array;

import util.BaseUtil;

/**
 * 查询某数组中第K小的数，通常用堆来做，O(n*logn)
 * BFPRT是5位大牛研究的算法，利用荷兰国旗的思想，以及partition过程中划分值选择的改良。O(n)
 *
 * @author xuyh
 * @date 2019/9/19
 */
public class BFPRT {
    public static void main(String[] args) {
//        int[] arr = {6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9};
        int[] arr = {6, 9, 1, 3, 4, 10, 12, 5, 6, 11, 13, 8, 19, 7, 2, 5, 6};
        arr = new int[]{2, 4, 6, 53, 7, 8, 1};
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
        BaseUtil.printArray(getMinKByHeap(arr, 5));
        BaseUtil.printArray(getMinKByBFPRT(arr, 5));
    }

    public static int[] getMinKByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int minKth = getMinKth(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        //没填满数组，就用第k小的数进行填充
        while (index != res.length) {
            res[index++] = minKth;
        }
        return res;
    }

    private static int getMinKth(int[] arr, int k) {
        int[] copyArray = BaseUtil.copyArray(arr);
        return select(copyArray, 0, copyArray.length - 1, k - 1);
    }

    private static int select(int[] arr, int lo, int hi, int k) {
        if (lo == hi) {
            return arr[lo];
        }
        //将整个数组分为n/5大小，不足的自成一组，获取分组的中位数，得到一个n/5大小的数组，再获取此数组的中位数作为划分值
        //就可以将整个数组分为规模相等的两部分，然后利用荷兰国旗思想进行划分
        int pivot = medianOfMedians(arr, lo, hi);
        int[] pivotRange = partition(arr, lo, hi, pivot);
        if (k >= pivotRange[0] && k <= pivotRange[1]) {
            return arr[k];
        } else if (k < pivotRange[0]) {
            return select(arr, lo, pivotRange[0] - 1, k);
        } else {
            return select(arr, pivotRange[1] + 1, hi, k);
        }
    }

    private static int[] partition(int[] arr, int lo, int hi, int pivot) {
        int less = lo - 1;
        int more = hi + 1;
        while (lo < more) {
            if (arr[lo] < pivot) {
                BaseUtil.swap(arr, lo++, ++less);
            } else if (arr[lo] > pivot) {
                BaseUtil.swap(arr, lo, --more);
            } else {
                lo++;
            }
        }
        //返回等于区域的左右边界
        return new int[]{less + 1, more - 1};
    }

    private static int medianOfMedians(int[] arr, int lo, int hi) {
        int num = hi - lo + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int begin = lo + i * 5;
            int end = begin + 4;
            mArr[i] = getMedian(arr, begin, Math.min(end, hi));
        }
        //在中位数数组中找中位数，即k/2小的数
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    private static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i < end + 1; i++) {
            for (int j = i; j > begin && arr[j - 1] > arr[j]; j--) {
                BaseUtil.swap(arr, j - 1, j);
            }
        }
    }

    //使用堆来获取数组中前k小的数
    public static int[] getMinKByHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int[] kheap = new int[k];
        //先取k个数放到大根堆中
        for (int i = 0; i < k; i++) {
            heapInsert(kheap, arr[i], i);
        }
        //从之后的元素中找到符合的数
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < kheap[0]) {
                kheap[0] = arr[i];
                heapify(kheap, 0, k);
            }
        }
        return kheap;
    }

    private static void heapify(int[] arr, int i, int heapSize) {
        int left = 2 * i + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[i] ? largest : i;
            if (largest == i) {
                break;
            }
            BaseUtil.swap(arr, largest, i);
            i = largest;
            left = 2 * i + 1;
        }
    }

    private static void heapInsert(int[] heap, int value, int i) {
//        heap[i] = value;
//        while (i != 0) {
//            int parent = (i - 1) / 2;
//            if (heap[i] > heap[parent]) {
//                BaseUtil.swap(heap, parent, i);
//                i = parent;
//            } else {
//                break;
//            }
//        }
        while (heap[i] > heap[(i - 1) / 2]) {
            BaseUtil.swap(heap, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }
}
