package leetcode;

/**
 * 盛最多的水的容器
 *
 * @author xuyh
 * @date 2019/10/29
 */
public class Eg11 {
    //暴力法
    public int maxArea(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                res = Math.max(res, Math.min(height[j], height[i]) * (j - i));
            }
        }
        return res;
    }

    //双指针
    public int maxArea1(int[] height) {
        int lo = 0;
        int hi = height.length - 1;
        int v = 0;
        int res = 0;
        while (lo < hi) {
            if (height[lo] < height[hi]) {
                v = height[lo] * (hi - lo);
                lo++;
            } else {
                v = height[hi] * (hi - lo);
                hi--;
            }
            if (v > res) {
                res = v;
            }
        }
        return res;
    }
}
