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
//        for (int i = 1; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        buildMaxHeap(arr);
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
            //注意当右节点越界，最大要取左节点。下面这种写法就有问题，在写这种表达式的时候要特别注意
//            int largest = left + 1 < heapSize && arr[left + 1] < arr[left] ? left: left+1;
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

    //这种方式建立大根堆时间复杂度是n*log(n)
    //log1+log2+log3+...+log(n-1),最终化简是n*log(n)
    private static void heapInsert(int[] arr, int i) {//就是和父节点进行比较
        while (arr[i] > arr[(i - 1) / 2]) {//此循环的时间复杂度只和堆的高度有关，所以为logN
            BaseUtil.swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    //总的时间计算为：s = 2^( i - 1 ) * ( k - i )；其中 i 表示第几层，
    // 2^( i - 1) 表示该层上有多少个元素，( k - i) 表示子树上要下调比较的次数。
    //S = n - log(n) -1，所以时间复杂度为：O(n)
    private static void buildMaxHeap(int[] arr) {
        //从最后一个节点array.length-1的父节点（array.length-1-1）/2开始，直到根节点0，反复调整堆
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            adjustDownToUp(arr, i);
        }
    }

    //log2+log3+…+log(n-1)+log(n)≈log(n!),可以证明log(n!)和nlog(n)是同阶函数,时间复杂度为O(nlogn)
    private static void adjustDownToUp(int[] arr, int i) {
        int temp = arr[i];
        //j为初始化为节点i的左孩子，沿节点较大的子节点向下调整
        int j = 2 * i + 1;
        while (j < arr.length) {
            if (j < arr.length - 1 && arr[j] < arr[j + 1]) {
                j++;
            }
            if (temp >= arr[j]) {
                break;
            }
            arr[i] = arr[j];
            i = j;
            j = 2 * i + 1;
        }
        arr[i] = temp;
//            for (int j = 2 * i + 1; j < arr.length; j = 2 * i + 1) {
//                //取节点较大的子节点的下标
//                if (j < arr.length - 1 && arr[j] < arr[j + 1]) {
//                    j++;
//                }
//                //父节点 >=左右子节点中较大者，调整结束
//                if (temp >= arr[j]) {
//                    break;
//                }
//                arr[i] = arr[j];//将左右子结点中较大值arr[j]调整到双亲节点上
//                i = j;//【关键】修改i值，以便继续向下调整
//            }
//            arr[i] = temp;//被调整的结点的值放人最终位置
    }

    public static void main(String[] args) {
        BaseUtil.testSortTemplate("sort.HeapSort", "heapSort");
    }
}
