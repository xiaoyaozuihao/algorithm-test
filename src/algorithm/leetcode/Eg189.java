package leetcode;

import util.BaseUtil;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * @author xuyh
 * @date 2019/10/16
 */
public class Eg189 extends BaseUtil {
    public static void main(String[] args) {
        Eg189 eg189 = new Eg189();
        eg189.testTemplate(10000, 100, 100);
    }

    @Override
    protected void invokeMethod(int[] arr) {
        rotate2_1(arr, 3);
    }

    @Override
    protected void invokeComparator(int[] arr) {
        rotate(arr, 3);
    }

    //暴力法，旋转k次，每次旋转一个元素
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 1) {
            return;
        }
        int temp, pre;
        for (int i = 0; i < k; i++) {
            pre = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = pre;
                pre = temp;
            }
        }
    }

    //使用额外数组
    public void rotate1(int[] nums, int k) {
        int[] help = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            help[(i + k) % nums.length] = help[i];
        }
        //拷贝回原数组
        for (int i = 0; i < help.length; i++) {
            nums[i] = help[i];
        }
    }

    //环状替换
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 1) {
            return;
        }
        //减少循环次数
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int cur = start;
            int pre = nums[start];
            do {
                int next = (cur + k) % nums.length;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            } while (start != cur);
        }
    }

    //环形替换的另一种写法
    public void rotate2_1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || (k %= nums.length) == 0) {
            return;
        }
        int length = nums.length;
        int start = 0;
        int i = 0;
        int cur = nums[i];
        int cnt = 0;
        while (cnt++ < length) {
            i = (i + k) % length;
            int t = nums[i];
            nums[i] = cur;
            if (i == start) {
                ++start;
                ++i;
                cur = nums[i];
            } else {
                cur = t;
            }
        }
    }

    //反转数组
    public void rotate3(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 1) {
            return;
        }
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
