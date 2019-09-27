package dp;

import util.BaseUtil;

import java.util.HashMap;

/**
 * 给定一个整型数组arr，其中可能有正有负有零。
 * 你可以随意把整个数组切成若干个不相容的子数组，求子数组中所有元素异或后为0的最多的子数组数量，即最优切分方式
 *
 * @author: xuyh
 * @create: 2019/9/22
 **/
public class MostEOR {
    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 10;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = BaseUtil.generateRandomArray(maxSize, maxValue);
            int res = mostEOR(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                BaseUtil.printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
    public static int mostEOR(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //子数组的数量
        int ans = 0;
        //异或和
        int xor = 0;
        int[] mosts = new int[arr.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {//存在异或和，这是当前位置之前最后一次出现异或和为零的位置
                int pre = map.get(xor);
                //取出异或和为零的位置，如果是-1，说明第一个元素组成的子数组达标，否则就用之前的数量加1.
                mosts[i] = pre == -1 ? 1 : (mosts[pre] + 1);
            }
            if (i > 0) {//比较i-1位置和i位置的值，取最大
                mosts[i] = Math.max(mosts[i - 1], mosts[i]);
            }
            //每次都更新异或和位置，map中存放最后一次异或和为零的位置
            map.put(xor, i);
            ans = Math.max(ans, mosts[i]);
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //定义异或和数组，存储到当前位置之前所有数的异或和结果
        int[] eors = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            //记录每个位置与前面位置的异或和
            eor ^= arr[i];
            eors[i] = eor;
        }
        //dp数组，记录到某个位置时其前面的异或和为0的子数组最大数量。
        //所以计算到最后一个位置就是最终的答案
        int[] mosts = new int[arr.length];
        //如果第一个位置异或和为0，则达标的子数组数量为1.
        mosts[0] = arr[0] == 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            //如果异或和数组的某个位置为0，说明从当前位置到第二个位置的所有数异或和为0。
            mosts[i] = eors[i] == 0 ? 1 : 0;
            //枚举所有子数组
            for (int j = 0; j < i; j++) {
                //一个数异或同一个数两次，等于原数。
                // 这个异或操作等效于从j+1到i位置的异或和结果，如果为零，子数组数量加1,全局取最大
                if ((eors[i] ^ eors[j]) == 0) {
                    mosts[i] = Math.max(mosts[i], mosts[j] + 1);
                }
            }
            //i位置所在的最后一个子数组所有元素能不能异或后为0的决策
            mosts[i] = Math.max(mosts[i], mosts[i - 1]);
        }
        return mosts[mosts.length - 1];
    }
}
