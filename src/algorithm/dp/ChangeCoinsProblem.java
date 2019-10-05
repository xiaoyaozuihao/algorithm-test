package dp;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 换钱的方法数
 * 给定数组arr，所有的数均为正数且没有重复，每个值代表一种面值的货币，每种面值的货币可以使用任意张
 * 给定一个整数代表要找的钱数，求换钱有多少种方法。
 *
 * @author xuyh
 * @date 2019/9/17
 */
public class ChangeCoinsProblem {
    public static void main(String[] args) {
        int[] coins = {10, 5, 1, 25};
        int aim = 2000;
        stopWatch("changeCoinsDp",coins,aim);
        stopWatch("changeCoinsDp1",coins,aim);
        stopWatch("changeCoinsMap",coins,aim);
        stopWatch("changeCoinsMap1",coins,aim);
        stopWatch("changeCoins",coins,aim);
        stopWatch("changeCoinsViolence",coins,aim);
    }

    public static void stopWatch(String methodName, int[] coins, int aim) {
        try {
            long start = System.currentTimeMillis();
            Class<?> clazz = Class.forName("dp.ChangeCoinsProblem");
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod(methodName, int[].class, int.class);
            Object invoke = method.invoke(obj, coins, aim);
            System.out.println(invoke);
            long end = System.currentTimeMillis();
            System.out.println(methodName + " cost time : " + (end - start) + "(ms)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dp优化解法
     *
     * @param arr
     * @param aim
     * @return
     */
    public int changeCoinsDp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * dp算法，未优化
     *
     * @param arr
     * @param aim
     * @return
     */
    public int changeCoinsDp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    //最短代码
    public int changeCoins(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int i = 0; arr[0] * i <= aim; i++) {
            dp[arr[0] * i] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

    //利用map数组进行加速
    public int changeCoinsMap1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Integer[][] map = new Integer[arr.length + 1][aim + 1];
        return process3(arr, 0, aim, map);
    }

    private int process3(int[] arr, int i, int aim, Integer[][] map) {
        int res = 0;
        if (i == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            Integer mapValue;
            for (int j = 0; arr[i] * j <= aim; j++) {
                mapValue = map[i + 1][aim - arr[i] * j];
                if (mapValue != null) {
                    res += mapValue;
                } else {
                    res += process3(arr, i + 1, aim - arr[i] * j, map);
                }
            }
        }
        map[i][aim] = res;
        return res;
    }

    /**
     * 换钱方法数，利用map结合进行加速
     *
     * @param arr
     * @param aim
     * @return
     */
    public int changeCoinsMap(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        //使用hashMap进行缓存
        HashMap<String, Integer> map = new HashMap<>();
        return processMap(arr, 0, aim, map);
    }

    private int processMap(int[] arr, int i, int aim, HashMap<String, Integer> map) {
        int res = 0;
        if (i == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int j = 0; arr[i] * j <= aim; j++) {
                int nextAim = aim - arr[i] * j;
                Integer num = map.get((i + 1) + "_" + nextAim);
                if (num != null) {
                    res += num;
                } else {
                    res += processMap(arr, i + 1, nextAim, map);
                }
            }
        }
        map.put(i + "_" + aim, res);
        return res;
    }

    /**
     * 换钱数暴力解法
     *
     * @param arr
     * @param aim
     * @return
     */
    public int changeCoinsViolence(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        //正向遍历
        return process1(arr, 0, aim);
        //逆向遍历
//        return process2(arr,arr.length-1,aim);
    }

    private int process1(int[] arr, int i, int aim) {
        int res = 0;
        if (i == arr.length) {
            //递归到越界时，目标值等于0说明有一种方式尝试成功
            res = aim == 0 ? 1 : 0;
        } else {
            //j代表每种面值的张数，面值*张数需要小于目标值，就递归进行尝试。
            for (int j = 0; arr[i] * j <= aim; j++) {
                res += process1(arr, i + 1, aim - arr[i] * j);
            }
        }
        return res;
    }

    private int process2(int[] arr, int i, int aim) {
        int res = 0;
        if (i == -1) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int j = 0; arr[i] * j <= aim; j++) {
                res += process2(arr, i - 1, aim - arr[i] * j);
            }
        }
        return res;
    }
}
