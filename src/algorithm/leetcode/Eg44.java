package leetcode;

/**
 * 正则匹配，*表示0个或多个任意字符，?表示一个任意字符
 * @author xuyh
 * @date 2019/10/9
 */
public class Eg44 {
    public static void main(String[] args) {
        String s = "aa";
        String p = "*";
        Eg44 eg44 = new Eg44();
        System.out.println(eg44.isMatch(s, p));
    }

    //dp倒推
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        for (int i = slen; i >= 0; i--) {
            for (int j = plen; j >= 0; j--) {
                if (i == slen && j == plen) {
                    dp[i][j] = true;
                    continue;
                } else if (i == slen) {
                    dp[i][j] = p.charAt(j) == '*' && dp[i][j + 1];
                } else if (j != plen) {
                    if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else if (p.charAt(j) == '*') {
                        dp[i][j] = dp[i][j + 1] || dp[i + 1][j];
                    }
                }
            }
        }
        return dp[0][0];
    }

    //dp正推
    public boolean isMatch1(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j < p.length() + 1; j++) {
            dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //leetCode最优解
    public boolean isMatchBest(String s, String p) {
        int i = 0, j = 0, match = 0, starIdx = -1;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIdx = j;
                match = i;
                j++;
            } else if (starIdx != -1) {
                j = starIdx + 1;//每次不匹配时，退回到上个*之后重新开始匹配
                match++;
                i = match;
            } else
                return false;
        }
        while (j < p.length() && p.charAt(j) == '*')
            j++;
        return j == p.length();
    }
}
