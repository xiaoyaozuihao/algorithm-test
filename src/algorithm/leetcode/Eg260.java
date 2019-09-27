package leetcode;

import java.util.Arrays;

/**
 * 一个数组中所有数只有两个数出现1次，其他数都出现两次，找出这两个数
 * @author xuyh
 * @date 2019/9/26
 */
public class Eg260 {
    public static void main(String[] args) {
        int[] nums={1,2,1,3,2,5};
        Eg260 eg260=new Eg260();
        int[] arr = eg260.singleNumber(nums);
        System.out.println(Arrays.toString(arr));
    }

    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int diff = 0;
        for (int i : nums) {
            diff ^= i;
        }
        diff &= (-diff);
        int[] res = new int[2];
        for (int i : nums) {
            if ((i & diff) == 0) {
                res[0] ^= i;
            } else {
                res[1] ^= i;
            }
        }
        return res;
    }
}
