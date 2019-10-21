package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 求众数
 *
 * @author xuyh
 * @date 2019/10/21
 */
public class Eg169 {
    //暴力法
    public int majorityElement(int[] nums) {
        int majorityCount = nums.length / 2;
        for (int num : nums) {
            int count = 0;
            for (int ele : nums) {
                if (ele == num) {
                    count++;
                }
            }
            if (count > majorityCount) {
                return num;
            }
        }
        return -1;
    }

    //map存储次数
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) != null) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        Map.Entry<Integer, Integer> res = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (res == null || res.getValue() < entry.getValue()) {
                res = entry;
            }
        }
        return res.getKey();
    }

    //map优化解法
    public int majorityElement11(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxNum = 0, maxCount = 0;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
            if (count > maxCount) {
                maxCount = count;
                maxNum = num;
            }
        }
        return maxNum;
    }

    //排序，直接返回n/2索引的数字
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //随机化，因为众数占了一半以上
    public int majorityElement3(int[] nums) {
        int majorityCount = nums.length / 2;
        while (true) {
            int candidateIndex = (int) (Math.random() * nums.length);
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[candidateIndex] == nums[i]) {
                    count++;
                }
            }
            if (count > majorityCount) {
                return nums[candidateIndex];
            }
        }
    }

    //Boyer-Moore 摩尔投票法，算法严格执行了 n 次循环
    //选定一个候选数，制定一个计数器，遍历数组，如果有和候选数相等的，计数器加1，否则减1
    //计数器为0时，更换候选数
    public int majorityElement4(int[] nums) {
        int count = 0;
        int candidate = -1;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    //位运算法，由于众数数组中出现次数大于n/2 ，
    // 那么众数对应的二进制每一个为1的位数出现的次数一定大于n/2，由此可以得出众数
    public int majorityElement5(int[] nums) {
        int result = 0, k = nums.length >> 1;
        for (int j = 0; j < 32; j++) {
            int count = 0;
            for (int num : nums) {
                count += num >> j & 1;
                if (count > k) {
                    result += 1 << j;
                    break;
                }
            }
        }
        return result;
    }
}
