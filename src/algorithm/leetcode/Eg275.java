package leetcode;

/**
 * H指数，前提是已排好序，需要O(logN)时间复杂度
 *
 * @author xuyh
 * @date 2019/10/22
 */
public class Eg275 {
    public static void main(String[] args) {
        Eg275 eg274 = new Eg275();
        int[] citations = {0, 1, 3, 5, 7};
        System.out.println(eg274.hIndex(citations));
        int[] citations1={0,1,3,3,3,4,5,6};
        System.out.println(eg274.hIndex1(citations1));
        System.out.println(eg274.hIndex11(citations1));
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        int lo = 0, hi = n, mid = 0;
        while (lo < hi) {
            //奇数求的是中位数，偶数求的是下中位数，hi=n会使得中位数偏向右边界
            mid = lo + (hi - lo) / 2;
            //如果下中位数满足条件，那么右边界就来到中位数位置，继续寻找更小的满足条件的mid值，最终lo和hi相等退出循环
            if (citations[mid] >= n - mid) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return n - lo;
    }

    public int hIndex1(int[] citations) {
        int n = citations.length;
        int lo = 0, hi = n - 1, mid = 0;
        while (lo <= hi) {
            //hi=n-1会使得中位数偏向左边界，即偶数会获得上中位数
            mid = lo + (hi - lo) / 2;
            if (citations[mid] == n - mid) {
                return n - mid;
            } else if (citations[mid] > n - mid) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return n - lo;
    }

    //不同的理解方式,需要多判断一个边界
    //核心是我们要返回选取的区间的长度，而我们选取的区间要满足区间中的数大于等于所在区间的长度
    public int hIndex11(int[] citations) {
        int len = citations.length;
        // 特判
        if (len == 0 || citations[len - 1] == 0) {
            return 0;
        }
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            // 比长度小，就得去掉该值
            if (citations[mid] < len - mid) {
                left = mid + 1;
            } else {
                // 比长度大是满足的，我们应该继续让 mid 往左走去尝试看有没有更小的 mid 值
                // 可以满足 mid 对应的值大于等于从 [mid, len - 1] 的长度
                right = mid;
            }
        }
        return len - left;
    }

    public int hIndex2(int[] citations) {
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            if (h <= citations[i]) {
                return h;
            }
        }
        return 0;
    }
}
