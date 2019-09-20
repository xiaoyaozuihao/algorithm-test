package array;

import util.BaseUtil;

import java.util.LinkedList;

/**
 * 滑动窗口最大值
 *
 * @author xuyh
 * @date 2019/9/19
 */
public class SlidingWindowMaxArray {
    public static void main(String[] args) {
        int[] arr = {2,5,4,3,6,8,9,3,1};
        BaseUtil.printArray(arr);
        int[] maxWindow = getMaxWindow(arr, 3);
        BaseUtil.printArray(maxWindow);
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> max = new LinkedList<>();
        //一共会产生多少个窗口
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!max.isEmpty() && arr[max.peekLast()] <= arr[i]) {
                max.pollLast();
            }
            max.addLast(i);
            //头结点下标过期弹出
            if (max.peekFirst() == i - w) {
                max.pollFirst();
            }
            if(i>=w-1){
                res[index++]=arr[max.peekFirst()];
            }
        }
        return res;
    }
}
