package dp;

/**
 * 现有n1+n2种面值的硬币，其中前n1种为普通币，可以取任意枚，
 * 后n2中为纪念币，每种最多只能取一枚，每种硬币有一个面值，问能用多少种方法拼出m的面值
 *
 * @author: xuyh
 * @create: 2020/4/2
 **/
public class MoneyWays {
    public static int moneyWays(int[] arbitrary, int[] onlyOne, int money) {
        if (money < 0) {
            return 0;
        }
        if ((arbitrary == null || arbitrary.length == 0) && (onlyOne == null || onlyOne.length == 0)) {
            return money == 0 ? 1 : 0;
        }
        int[][] dpArb = getDpArb(arbitrary, money);
        int[][] dpOne = getDpOne(onlyOne, money);
        if (dpArb == null) {
            return dpOne[dpOne.length - 1][money];
        }
        if (dpOne == null) {
            return dpArb[dpArb.length - 1][money];
        }
        int res = 0;
        for (int i = 0; i <= money; i++) {
            res += dpArb[dpArb.length - 1][i] * dpOne[dpOne.length - 1][money - i];
        }
        return res;
    }

    //普通币
    public static int[][] getDpArb(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        //创建dp，初始化第一行和第一列
        int[][] dp = new int[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; arr[0] * i <= money; i++) {
            dp[0][arr[0] * i] = 1;
        }
        //一个普遍位置的算法,填表格
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp;
    }

    //纪念币
    public static int[][] getDpOne(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] dp = new int[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= money) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
            }
        }
        return dp;
    }
}
