package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 求众数升级版，要求列出超过n/3的所有数
 *
 * @author xuyh
 * @date 2019/10/22
 */
public class Eg229 {
    public static void main(String[] args) {
        Eg229 eg229 = new Eg229();
        int[] nums = {3, 2, 3};
        System.out.println(eg229.majorityElement(nums));
    }

    //暴力法，排序后计算个数
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        int candidate = nums[0];
        for (int num : nums) {
            if (num == candidate) {
                count++;
            } else {
                if (count > n / 3) {
                    res.add(candidate);
                }
                candidate = num;
                count = 1;
            }
        }
        if (count > n / 3) {
            res.add(candidate);
        }
        return res;
    }

    public List<Integer> majorityElement1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int candidate1 = nums[0], candidate2 = nums[0], count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
                continue;
            }
            if (num == candidate2) {
                count2++;
                continue;
            }
            if (count1 == 0) {
                candidate1 = num;
                count1++;
                continue;
            }
            if (count2 == 0) {
                candidate2 = num;
                count2++;
                continue;
            }
            count1--;
            count2--;
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            }
        }
        if (count1 > n / 3) {
            res.add(candidate1);
        }
        if (count2 > n / 3) {
            res.add(candidate2);
        }
        return res;
    }
}
