package leetcode;

/**
 * 最长回文字符串
 *
 * @author xuyh
 * @date 2019/10/11
 */
public class Eg5 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aaababadd"));
        System.out.println(longestPalindrome1("aaababadd"));
        System.out.println(longestPalindrome2("aaababadd"));
        System.out.println(longestPalindromeBest("aaababadd"));
    }

    public static String longestPalindrome(String s) {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                //当s.charAt(i) == s.charAt(j)成立的时候，dp[i][j] 的值由 dp[i + 1][j - l] 决定，
                //这一点也不难思考：当左右边界字符串相等的时候，整个字符串是否是回文就完全由“原字符串去掉左右边界”的子串是否回文决定。
                //但是这里还需要再多考虑一点点：“原字符串去掉左右边界”的子串的边界情况。
                //1、当原字符串的元素个数为 33 个的时候，如果左右边界相等，那么去掉它们以后，
                // 只剩下 11 个字符，它一定是回文串，故原字符串也一定是回文串；
                //2、当原字符串的元素个数为 22 个的时候，如果左右边界相等，那么去掉它们以后，
                // 只剩下 00 个字符，显然原字符串也一定是回文串。
                //把上面两点归纳一下，只要 s[l + 1, r - 1] 至少包含两个元素，就有必要继续做判断，
                // 否则直接根据左右边界是否相等就能得到原字符串的回文性。而“s[l + 1, r - 1] 至少包含两个元素”
                // 等价于 l + 1 < r - 1，整理得 l - r < -2，或者 r - l > 2。
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]); //j - i 代表长度减去 1
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    //优化空间复杂度
    public static String longestPalindrome1(String s) {
        int n = s.length();
        String res = "";
        boolean[] P = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                P[j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || P[j - 1]);
                if (P[j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static String longestPalindromeBest(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] range = new int[2];
        char[] sArr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            i = findLongest(sArr, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] sArr, int low, int[] range) {
        int high = low;
        while (high < sArr.length - 1 && sArr[high + 1] == sArr[low]) {
            high++;
        }
        int temp = high;
        while (low > 0 && high < sArr.length - 1 && sArr[low - 1] == sArr[high + 1]) {
            low--;
            high++;
        }
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return temp;
    }
}
