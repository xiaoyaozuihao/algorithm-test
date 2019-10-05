package bit;

import util.BaseUtil;

/**
 * 给定一个数组，求子数组的最大异或和
 *
 * @author: xuyh
 * @create: 2019/10/3
 **/
public class MaxEor {
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = BaseUtil.generateRandomArray(maxSize, maxValue);
            int res = maxEor(arr);
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

    public static class Node {
        //二进制数只有0，1两条路
        private Node[] nexts = new Node[2];
    }

    public static class NumTrie {
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                //获取每一位上的数
                int path = (num >> i) & 1;
                //有没有通往这个数的路，没有就新建
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                //一直向后走，直到把int的32位全部插入到前缀树中
                cur = cur.nexts[path];
            }
        }

        public int maxEor(int num) {
            Node cur = head;
            int res = 0;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                //选择要走的路，如果是符号位，则期望走与符号位相同的路，否则不是符号位，期望走相反的路，才能使整个异或结果最大
                int best = i == 31 ? path : (path ^ 1);
                //期望走的路是否存在，不存在就走相反的路
                best = cur.nexts[best] != null ? best : (best ^ 1);
                //设置返回值res相应位上的最优值
                res |= (best ^ path) << i;
                cur = cur.nexts[best];
            }
            return res;
        }
    }

    public static int maxEor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        int max = Integer.MIN_VALUE;
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            //前缀树中存储0到i的所有的异或结果
            eor ^= arr[i];
            max = Math.max(max, numTrie.maxEor(eor));
            numTrie.add(eor);
        }
        return max;
    }

    //n^2的时间复杂度
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        //枚举出以每个位置开头的所有子数组的异或和，比较最大值
        for (int i = 0; i < arr.length; i++) {
            int eor = 0;
            for (int j = i; j < arr.length; j++) {
                //i到j全部异或的结果。
                eor ^= arr[j];
                max = Math.max(eor, max);
            }
        }
        return max;
    }

    public static int maxEor1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            //0到i的异或结果
            eor ^= arr[i];
            max = Math.max(eor, max);
            for (int j = 1; j <= i; j++) {
                //j到i的异或结果
                int curEor = eor ^ dp[j - 1];
                max = Math.max(max, curEor);
            }
            dp[i] = eor;
        }
        return max;
    }

    //暴力解法，n^3的时间复杂度
    public static int maxEorViolence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        //枚举出以每个位置结束的所有子数组，计算每个子数组的异或和
        //整个范围遍历
        for (int i = 0; i < arr.length; i++) {
            //从0到i位置形成的子数组
            for (int j = 0; j <= i; j++) {
                int res = 0;
                //子数组的每一个元素全部异或
                for (int k = j; k <= i; k++) {
                    res ^= arr[k];
                }
                max = Math.max(max, res);
            }
        }
        return max;
    }
}
