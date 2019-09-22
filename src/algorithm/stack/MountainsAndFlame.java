package stack;

import java.util.Stack;

/**
 * 给一个数组，代表环形的山
 * 1、每座山上会放烽火，相邻可看见烽火
 * 2、不相邻的山峰，两条路中其中一条路上的烽火都不大于他们之间的最小值，就能看见
 * 返回能相互看见的山峰有多少对？
 * <p>
 * 解析1：如果没有重复值的时候，则直接O(1)出结果。
 * 证明：如果一座山，则为0对，两座山，则为1对，如果n座山，则为（2*n-3）对。
 * 利用小数去找大数，找出环形中的最大值和次大值，i在这两值之间。从i向两侧找，则一定有两对。
 * 所以最高和次高之间一共有（n-2）*2对，再加上最高和次高这一对，所有一共有（2*n-3）对。
 * 解析2：有重复值的情况就比较麻烦，利用单调栈，重复值叠加到一起。
 *
 * @author: xuyh
 * @create: 2019/9/21
 **/
public class MountainsAndFlame {
    public static void main(String[] args) {
        int[] arr={2,3,5,4,6,5,4,6};
        System.out.println(communications(arr));
    }

    //定义栈中存放的数据结构，value代表值大小，times代表出现的次数
    public static class Pair {
        private int value;
        private int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int maxIndex = 0;
        //获取最大值下标
        for (int i = 1; i < arr.length; i++) {
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }
        int value = arr[maxIndex];//最大值
        int index = nextIndex(arr.length, maxIndex);
        long res = 0L;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(value));//以最大值为底
        while (index != maxIndex) {
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) {
                int times = stack.pop().times;
                //重复值计算对数Cn(下标)2(上标)以及左右都有最大值，再加上2*times
                res += getInternalSum(times) + 2 * times;
            }
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;//相等值次数累加
            } else {
                stack.push(new Pair(value));
            }
            index = nextIndex(arr.length, index);
        }
        //一圈完毕后，计算栈中剩余的
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res += getInternalSum(times);
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {//栈中还剩2个及以上时
                    res += times;
                } else {
                    //栈中还剩一个时，即判断最大值的个数。
                    res += stack.peek().times > 1 ? times : 0;
                }
            }
        }
        return res;
    }

    //从n个中随意选择2个，Cn(下标)2(上标)
    private static long getInternalSum(int n) {
        return n == 1 ? 0L : n * (n - 1) / 2L;
    }

    //在一个环形数组中，下一个的索引
    private static int nextIndex(int size, int index) {
        return index < size - 1 ? index + 1 : 0;
    }
}
