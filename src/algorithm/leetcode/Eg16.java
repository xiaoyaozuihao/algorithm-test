package leetcode;

import java.util.Arrays;

/**
 * 三数之和，最接近target
 *
 * @author xuyh
 * @date 2019/10/31
 */
public class Eg16 {
    //暴力
    public int threeSumClosest(int[] nums, int target) {
        int sub = Integer.MAX_VALUE;
        int res = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int abs = Math.abs(target - sum);
                    if (sub > abs) {
                        sub = abs;
                        res = sum;
                    }
                }
            }
        }
        return res;
    }

    //双指针解法
    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int sub = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(sum - target) < sub) {
                    sub = Math.abs(sum - target);
                    res = sum;
                }
                if (sum > target) {
                    hi--;
                } else {
                    lo++;
                }
            }
        }
        return res;
    }

    //leetcode best solution,双指针的优化写法
    public int threeSumClosestBest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int cur = nums[i];
            //输入数组具有唯一解
            //排完序之后如果前三个数之和大于target，当前值大于target，
            //整个数组升序，所以后面的三数之和一定大于等于res,即与target的差值会越来越大，所以直接返回
            if (res > target && cur > target) {
                break;
            }
            //跳过相等元素
            if (i > 0 && cur == nums[i - 1]) {
                continue;
            }
            int index1 = i + 1;
            int index2 = nums.length - 1;
            //当前元素和其后连续两个数的和大于等于target，记做较小值，比较更新
            if (nums[index1] + nums[index1 + 1] + cur >= target) {
                int min = nums[index1] + nums[index1 + 1] + cur;
                if (Math.abs(min - target) < Math.abs(res - target)) {
                    res = min;
                }
                continue;
            }
            //当前元素和末尾倒数两个数的和小于等于target，记做较大值，比较更新
            if (nums[index2] + nums[index2 - 1] + cur <= target) {
                int max = nums[index2] + nums[index2 - 1] + cur;
                if (Math.abs(max - target) < Math.abs(res - target)) {
                    res = max;
                }
                continue;
            }
            while (index1 < index2) {
                int sum = nums[index1] + nums[index2] + cur;
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum < target) {
                    while (index1 < index2 && nums[index1] == nums[index1++ + 1]) ;
                } else if (sum > target) {
                    while (index1 < index2 && nums[index2] == nums[index2-- - 1]) ;
                } else {
                    return sum;
                }
            }
        }
        return res;
    }

    //另一种写法，更好理解
    /**
     * 优化1：元素重复问题。nums = [1,1,1,2,3] target = 7，那么最终的结果应该是 6，遍历的时候 nums[i]会重复的等于 1 这个数，
     *        但是其实之前 nums[i] 等于 1 已经遍历过了，后面的遍历都属于无用的遍历。所以可以添加去重操作
     * 优化2：超越界限的问题。nums = [-3,-1,3,4,5],假设i = 0，left = 1，right = 4，那么每次left和right之间都有许多元素，
     *        那么left和right之间的元素之和肯定也有一个最小值和一个最大值。那么移动指针的情况下，
     *        nums[left] + nums[right] 的最小值肯定为nums[left] + nums[left + 1]，同理最大值肯定为nums[right]+nums[right-1].
     *        如果 target 的值比 nums[i] + nums[left] + nums[left + 1] 的值还小，那么双指针无论怎么取，
     *        最后都会取到 nums[i] + nums[left] + nums[left + 1]，同理target比这个值大的情况。
     *        所以可以增加一个判断，满足条件的情况下就可以直接取值，而不需要双指针一步步的判断来进行取值，减少了双指针的移动。
     * 优化3：三数之和等于 target 的问题。三数之和等于 target 的情况，此时直接返回结果即可，
     *        不需要在进行之后的循环，因为不可能有数比他自己更接近自己了。
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest3(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left != right) {
                int min = nums[i] + nums[left] + nums[left + 1];
                if (target < min) {
                    if (Math.abs(result - target) > Math.abs(min - target))
                        result = min;
                    break;
                }
                int max = nums[i] + nums[right] + nums[right - 1];
                if (target > max) {
                    if (Math.abs(result - target) > Math.abs(max - target))
                        result = max;
                    break;
                }
                int sum = nums[i] + nums[left] + nums[right];
                // 判断三数之和是否等于target
                if (sum == target)
                    return sum;
                if (Math.abs(sum - target) < Math.abs(result - target))
                    result = sum;
                if (sum > target) {
                    right--;
                    while (left != right && nums[right] == nums[right + 1])
                        right--;
                } else {
                    left++;
                    while (left != right && nums[left] == nums[left - 1])
                        left++;
                }
            }
            while (i < nums.length - 2 && nums[i] == nums[i + 1])
                i++;
        }
        return result;
    }
}
