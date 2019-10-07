package dp;

/**
 * 有1到N到位置，机器人开始停留在M位置，每次走一步，一共走P步
 * 规定如何在1位置，则只能向右走，如果在N位置，则只能向左走，问最终停留在K位置的可能性有多少种。
 *
 * @author: xuyh
 * @create: 2019/10/5
 **/
public class RobotWays {
    /**
     * @param n 一共有1-N个位置
     * @param m 当前停留的位置
     * @param p 走过的步数
     * @param k 最终停留的位置
     * @return
     */
    public static int robotWaysDp(int n, int m, int p, int k) {
        if (n < 2 || m < 1 || m > n || p < 0 || k < 1 || k > n) {
            return 0;
        }
        //变量为m和p，m变化范围1~n，p变化范围0~p
        int[][] dp = new int[n + 1][p + 1];
        //根据baseCase得来
        dp[k][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= p; j++) {
                //暴力递归直接改写
                if (i == 1) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else if (i == n) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1];
                }
            }
        }
        return dp[m][p];
    }

    /**
     * @param N 一共有1-N个位置
     * @param M 当前停留的位置
     * @param P 走过的步数
     * @param K 最终停留的位置
     * @return
     */
    public static int robotWays(int N, int M, int P, int K) {
        if (N < 2 || M < 1 || M > N || P < 0 || K < 1 || K > N) {
            return 0;
        }
        if (P == 0) {
            return M == K ? 1 : 0;
        }
        int res = 0;
        if (M == 1) {
            res = robotWays(N, M + 1, P - 1, K);
        } else if (M == N) {
            res = robotWays(N, M - 1, P - 1, K);
        } else {
            res = robotWays(N, M - 1, P - 1, K) + robotWays(N, M + 1, P - 1, K);
        }
        return res;
    }
}
