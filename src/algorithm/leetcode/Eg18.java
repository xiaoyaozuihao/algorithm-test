package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 四数之和
 *
 * @author xuyh
 * @date 2019/10/31
 */
public class Eg18 {
    public static void main(String[] args) {
        Eg18 eg18 = new Eg18();
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(eg18.fourSum(nums, target));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        //如果数组长度小于4则返回空List
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        //减枝
        if (nums.length == 4) {
            if (nums[0] + nums[1] + nums[2] + nums[3] == target) {
                res.add(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
                return res;
            }
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //减枝
            //如果nums[i]加上最后三个数都小于target，说明nums[i]太小，continue；
            if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) {
                continue;
            }
            //减枝
            //如果nums[i]加上最后三个数都大于target,说明已经没有符合条件的值了，break；
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            //固定第二个元素
            for (int j = i + 1; j < len - 2; j++) {
                //去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                //减枝
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                //减枝
                if (nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < target) {
                    continue;
                }
                //双指针移动
                int start = j + 1;
                int end = len - 1;
                while (start < end) {
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum > target) {
                        //去重加指针移动
                        while (start < end && nums[end] == nums[--end]) ;
                    } else if (sum < target) {
                        while (start < end && nums[start] == nums[++start]) ;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        while (start < end && nums[start] == nums[++start]) ;
                        while (start < end && nums[end] == nums[--end]) ;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum1(int[] nums, int target) {
        if (nums.length < 4 || nums == null) return new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) continue;
                int tmp = nums[i] + nums[j];
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    if ((tmp + nums[left] + nums[right]) == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if ((tmp + nums[left] + nums[right]) < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
