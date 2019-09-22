package dp;

import util.BaseUtil;

import java.util.HashMap;

/**
 * 求一个数组中异或和为0的子数组的最大数量
 *
 * @author: xuyh
 * @create: 2019/9/22
 **/
public class MostEOR {
    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 300;
        int maxValue = 100;
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
                //取出异或和为零的位置，如果是-1，说明第一个元素组成的子数组就是，否则就用之前的数量加1.
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
        int[] eors = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            eors[i] = eor;
        }
        int[] mosts = new int[arr.length];
        mosts[0] = arr[0] == 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            mosts[i] = eors[i] == 0 ? 1 : 0;
            for (int j = 0; j < i; j++) {
                if ((eors[i] ^ eors[j]) == 0) {
                    mosts[i] = Math.max(mosts[i], mosts[j] + 1);
                }
            }
            mosts[i] = Math.max(mosts[i], mosts[i - 1]);
        }
        return mosts[mosts.length - 1];
    }
}
