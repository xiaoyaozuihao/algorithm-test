package leetcode;

import util.BaseUtil;

/**
 * 删除排序数组中的重复项
 *
 * @author xuyh
 * @date 2019/10/16
 */
public class Eg26 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 4, 5, 5, 6};
        int[] nums1 = BaseUtil.copyArray(nums);
        Eg26 eg26 = new Eg26();
        System.out.println(eg26.removeDuplicate(nums));
        BaseUtil.printArray(nums);
        System.out.println(eg26.removeDuplicate1(nums1));
        BaseUtil.printArray(nums1);
    }

    public int removeDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //重复元素的个数
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[i - count] = nums[i];
            } else {
                count++;
            }
        }
        return n - count;
    }

    //双指针
    public int removeDuplicate1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[j - 1]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}
