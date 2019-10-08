package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        String str = "abcccdefwg";
        String exp = "ab.*d.*e.*g";
        System.out.println(isMatch(str, exp));
        System.out.println(isMatchDp(str, exp));
    }

    public static boolean isValid(char[] s, char[] e) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] strs = str.toCharArray();
        char[] exps = exp.toCharArray();
        return isValid(strs, exps) && process(strs, exps, 0, 0);
    }

    public static boolean process(char[] strs, char[] exps, int si, int ei) {
        if (ei == exps.length) {
            return si == strs.length;
        }
        if (ei + 1 == exps.length || exps[ei + 1] != '*') {
            return si != strs.length && (exps[ei] == strs[si] || exps[ei] == '.')
                    && process(strs, exps, si + 1, ei + 1);
        }
        while (si != strs.length && (exps[ei] == strs[si] || exps[ei] == '.')) {
            if (process(strs, exps, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(strs, exps, si, ei + 2);
    }

    public static boolean isMatchDp(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] strs = str.toCharArray();
        char[] exps = exp.toCharArray();
        if (!isValid(strs, exps)) {
            return false;
        }
        boolean[][] dp = initDpMap(strs, exps);
        for (int i = strs.length - 1; i >= 0; i--) {
            for (int j = exps.length - 2; j >= 0; j--) {
                if (exps[j + 1] != '*') {
                    dp[i][j] = (exps[j] == strs[i] || exps[j] == '.') && dp[i + 1][j + 1];
                } else {
                    int si=i;
                    while (si!=strs.length&&(exps[j] == strs[i] || exps[j] == '.')) {
                        if(dp[si][j+2]){
                            dp[i][j]= true;
                            break;
                        }
                        si++;
                    }
                    if(!dp[i][j]) {
                        dp[i][j] = dp[si][j+2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    private static boolean[][] initDpMap(char[] strs, char[] exps) {
        int slen = strs.length;
        int elen = exps.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        for (int i = elen - 2; i > -1; i -= 2) {
            if (exps[i] != '*' && exps[i + 1] == '*') {
                dp[slen][i] = true;
            } else {
                break;
            }
        }
        if (exps[elen - 1] == '.' || exps[elen - 1] == strs[slen - 1]) {
            dp[slen - 1][elen - 1] = true;
        }
        return dp;
    }
}