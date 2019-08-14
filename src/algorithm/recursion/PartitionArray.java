package recursion;

import util.BaseUtil;

/**
 * 给定一个值num和一个数组，把小于等于num的数放在数组左边，大于num的数放在数组右边
 *
 * @author xuyh
 * @date 2019/4/6
 */
public class PartitionArray {
    //单指针解法，时间复杂度较差
    public static void comparator(int[] arr, int l, int r, int p) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int less = l-1;
        while(l <= r ) {
            if (arr[l] <= p) {
                BaseUtil.swap(arr, ++less, l++);
            }
        }
    }

    //双指针解法,交换次数少
    public static void partitionArray(int[] arr, int l, int r, int p) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int less = l;
        int more = r;
        while (less < more) {
            while (arr[less] <= p) {
                if (++less > more) {
                    break;
                }
            }
            while (arr[more] > p) {
                if (--more < less) {
                    break;
                }
            }
            if (less < more) {
                BaseUtil.swap(arr, less, more);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = BaseUtil.generateRandomArray(10, 20);
            BaseUtil.printArray(arr);
            int[] array = BaseUtil.copyArray(arr);
            comparator(arr, 0, arr.length - 1, 3);
            BaseUtil.printArray(arr);
            partitionArray(array, 0, arr.length - 1, 3);
            BaseUtil.printArray(array);
        }
    }
}
