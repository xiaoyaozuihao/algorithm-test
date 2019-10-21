package leetcode;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * @author xuyh
 * @date 2019/10/21
 */
public class Eg134 {
    //简洁写法
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int curTank = 0;
        int startStation = 0;
        for (int i = 0; i < gas.length; i++) {
            totalTank += gas[i] - cost[i];
            curTank += gas[i] - cost[i];
            if (curTank < 0) {
                startStation = i + 1;
                curTank = 0;
            }
        }
        return totalTank < 0 ? -1 : startStation;
    }

    //暴力法尝试
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        //考虑从每一个点出发能否回到该点
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            //当前剩余油量能否到达下一个点
            while (remain - cost[j] >= 0) {
                //减去花费的再加上新的点的补给
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                //能再次回到出发点
                if (j == i) {
                    return i;
                }
            }
        }
        return -1;
    }

    //优化1，记录从某个点开始能最远到达的点，下次直接跳到该点
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int[] farIndex = new int[n];
        for (int i = 0; i < n; i++) {
            //初始值设为-1，代表没考虑过这个点
            farIndex[i] = -1;
        }
        int[] farRemain = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0) {
                //到达下个点后的剩余油量
                remain = remain - cost[j];
                j = (j + 1) % n;
                //判断有没有考虑过这个点
                if (farIndex[j] != -1) {
                    //加上当时最远能到达的点的剩余油量，直接跳到那个点，起到加速
                    remain += farRemain[j];
                    j = farIndex[j];
                } else {
                    //否则加上当前点的补给
                    remain += gas[j];
                }
                if (j == i) {
                    return i;
                }
            }
            //记录最远到达的点
            farIndex[i] = j;
            //记录当前点的剩余
            farRemain[i] = remain;
        }
        return -1;
    }

    //优化2，如果从i开始最远到达的点是j，那么i+1到j之间的所有点都不能绕一圈
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0) {
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                if (j == i) {
                    return i;
                }
            }
            //最远距离绕到了i之前，则i之后的所有点都不可能绕一圈了
            if (j < i) {
                return -1;
            }
            //i跳到j，外层循环i++，所以相当于跳到了j+1
            i = j;
        }
        return -1;
    }
}
