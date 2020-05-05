package dp;

/**
 * 给定一个整数数组，有正有负有0，求累加和为小于等于aim的子数组的最大长度
 *
 * @author: xuyh
 * @create: 2019/10/7
 **/
public class LongestSubarrayLessSumAwesomeSolution {
    public static void main(String[] args) {
        boolean succeed = true;
        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (getMaxLength(arr, k) != getMaxLength1(arr, k)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "hooray!" : "oops!");
    }

    //时间复杂度O(N)
    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //准备累加和数组，存放当前位置到数组最后位置的最小累加和
        int[] sums = new int[arr.length];
        sums[arr.length - 1] = arr[arr.length - 1];
        //存放最小累加和对应的索引
        int[] ends = new int[arr.length];
        ends[arr.length - 1] = arr.length - 1;
//        HashMap<Integer, Integer> ends = new HashMap<>();
//        ends.put(arr.length - 1, arr.length - 1);
        //从倒数第二个数开始向前推
        for (int i = arr.length - 2; i >= 0; i--) {
            //下一个位置的最小累加和为负数，就加上，更新结构
            if (sums[i + 1] < 0) {
                sums[i] = arr[i] + sums[i + 1];
                ends[i] = ends[i + 1];
//                ends.put(i, ends.get(i + 1));
            } else {
                //否则就是当前数
                sums[i] = arr[i];
                ends[i] = i;
//                ends.put(i, i);
            }
        }
        //当前能扩展到的右边界的下一个位置
        int end = 0;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            //如果右边界的下一个位置不越界且累加上下一个位置的最小累加和小于等于aim
            // 则能向右扩，那么就一直扩，直到累加和大于aim，更新右边界
            while (end < arr.length && sum + sums[end] <= aim) {
                sum += sums[end];
                end = ends[end] + 1;
//                cur = ends.get(cur) + 1;
            }
            //如果右边界下一个位置索引大于当前索引，就减去当前值可能会使累加和变小，继续后续的循环
            // 否则说明向右扩不动，所以sum不变
            sum -= end > i ? arr[i] : 0;
            //更新最长子数组的长度
            res = Math.max(end - i, res);
            end = Math.max(end, i + 1);

            //另一种写法
//            if(end>i){
//                sum -= arr[i];
//            }else {
//                end=i+1;
//            }
        }
        return res;
    }

    //时间复杂度O（N*logN）
    public static int getMaxLength1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        //计算出每个位置的累加和，保留每个位置左侧累加和的最大值
        //因为我们只关心某个累加和大于等于aim最早出现的位置
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        //假如0~i的累加和为sum[0-i],那么求以i结尾的累加和小于等于aim的最长子数组的长度
        //可以转化求sum[0-i]-aim这个值累加和最早出现在i之前的什么位置就可以
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            //由于累加和最大值数组升序，所有可以用二分查找找出累加和最早大于等于sum-aim出现的位置
            pre = getLessIndex(h, sum - aim);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    private static int getLessIndex(int[] arr, int num) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (arr[mid] >= num) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }
}
