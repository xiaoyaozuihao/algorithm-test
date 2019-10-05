package dp;

/**
 * 给定一个整形数组，求是否能累加出目标值。
 * @author xuyh
 * @date 2019/5/28
 */
public class SumProblem {
    public static boolean moneyProblem(int[] arr, int aim) {
        return process(arr, 0, 0, aim);
    }

    private static boolean process(int[] arr, int i, int sum, int aim) {
        if (sum == aim) {
            return true;
        }
        if (arr.length == i) {
            return false;
        }
        return process(arr, i + 1, sum, aim) || process(arr, i + 1, sum + arr[i], aim);
    }

    public static boolean moneyProblemDp(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (aim >= j + arr[i]) {
                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 25,1};
        int aim = 15;
        System.out.println(moneyProblem(arr, aim));
        System.out.println(moneyProblemDp(arr, aim));
    }
}
