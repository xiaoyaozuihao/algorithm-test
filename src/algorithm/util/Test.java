package util;

import java.util.LinkedList;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
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
        int[] res = new int[arr.length - w + 1];
        LinkedList<Integer> list = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!list.isEmpty() && arr[list.peekLast()] <= arr[i]) {
                list.pollLast();
            }
            list.addLast(i);
            if (list.peekFirst() == i - w) {
                list.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[list.peekFirst()];
            }
        }
        return res;
    }
}