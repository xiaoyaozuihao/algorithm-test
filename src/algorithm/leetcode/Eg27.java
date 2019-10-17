package leetcode;

import util.BaseUtil;

/**
 * 移除元素
 *
 * @author xuyh
 * @date 2019/10/16
 */
public class Eg27 {
    public static void main(String[] args) {
        Eg27 eg27 = new Eg27();
        int[] nums = {3, 2, 2, 3};
        int[] nums1 = BaseUtil.copyArray(nums);
        int val = 2;
        System.out.println(eg27.removeElement(nums, val));
        BaseUtil.printArray(nums);
        System.out.println(eg27.removeElement1(nums1, val));
        BaseUtil.printArray(nums1);
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public int removeElement1(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[--n];
            } else {
                i++;
            }
        }
        return n;
    }
}
