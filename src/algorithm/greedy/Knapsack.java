package greedy;

/**
 * 背包问题,背包容量有限，问将若干价值不等的东西装入背包，使背包价值最大。
 *
 * @author xuyh
 * @date 2019/5/28
 */
public class Knapsack {
    public static int maxValue(int[] weights, int[] values, int bag) {
        return process(weights, values, 0, 0, bag);
    }

    public static int process(int[] weights, int[] values, int i, int alreadyWeight, int bag) {
        if (alreadyWeight > bag) {
            return 0;
        }
        if (i == weights.length) {
            return 0;
        }
        return Math.max(
                process(weights, values, i + 1, alreadyWeight, bag),
                values[i] + process(weights, values, i + 1, alreadyWeight + weights[i], bag));
    }

    public static int knapsack(int[] weights, int[] values, int bag) {
        int[][] dp = new int[weights.length + 1][bag + 1];
        for (int i = weights.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + weights[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], values[i] + dp[i + 1][j + weights[i]]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] c = {3, 2, 4, 7};
        int[] p = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(knapsack(c, p, bag));
        System.out.println(maxValue(c, p, bag));
    }
}
