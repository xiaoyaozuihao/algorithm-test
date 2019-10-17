package leetcode;

import util.BaseUtil;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * @author xuyh
 * @date 2019/10/17
 */
public class Eg41 {
    public static void main(String[] args) {
        Eg41 eg41 = new Eg41();
        int[] nums = {2};
        System.out.println(eg41.findFirstMissingPositiveBest(nums));
    }

    //官方题解，将原数组当成bitmap使用。巧妙使用元素符号作为存在与否的判断
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        //只有一个数，返回2
        boolean contains = false;
        //判断1是否存在
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        //用1替换负数，0和大于n的数
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }
        //使用索引和数字符号作为检查器
        //例如nums[1]是负数，说明数组中出现了数字1
        //nums[2]是正数，说明数组中没有数字2
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            //当读到数字 a 时，替换第 a 个元素的符号。
            //注意重复元素：只能改变一次符号。由于没有下标 n ，使用下标 0 的元素保存是否存在数字 n。
            if (a == n) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[a] = -Math.abs(nums[a]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        if (nums[0] > 0) {
            return n;
        }
        return n + 1;
    }

    //官方版本求绝对值及索引存放的地方进行改进
    public int findFirstMissingPositive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        boolean contains = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int tmp = nums[i] < 0 ? -nums[i] : nums[i];
            if (nums[tmp - 1] > 0) {
                nums[tmp - 1] *= -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    //桶排序原理
    public int findFirstMissingPositive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            //这里需要用while一直交换，知道当前位置的值不能交换为止
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                BaseUtil.swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    //leetcode最优解
    public int findFirstMissingPositiveBest(int[] nums) {
        int len = nums.length;
        boolean[] res = new boolean[nums.length + 1];
        for (int i = 0; i < len; i++) {
            if (nums[i] >= 1 && nums[i] <= len + 1) {
                res[nums[i] - 1] = true;
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (!res[i]) {
                return i + 1;
            }
        }
        return 1;
    }
}
