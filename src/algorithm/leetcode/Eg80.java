package leetcode;

import util.BaseUtil;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * @author xuyh
 * @date 2019/10/16
 */
public class Eg80 {
    public static void main(String[] args) {
        Eg80 eg80 = new Eg80();
        int[] nums = {2, 2, 2, 3, 3, 3, 4, 5, 6};
        System.out.println(eg80.removeDuplicate(nums));
        BaseUtil.printArray(nums);
    }

    //跳过一个元素进行比较，即可保证最多出现两次
    public int removeDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 1;
        for (int j = 2; j < nums.length; j++) {
            if (nums[j] != nums[i - 1]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    //通用解法，解法中的2可以是任何一个数
    public int removeDuplicate1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num != nums[i - 2]) {
                nums[i] = num;
                i++;
            }
        }
        return i;
    }
}
