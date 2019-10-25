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
        System.out.println(longestPalindrome0("aaababadd"));
        System.out.println(longestPalindrome1("aaababadd"));
        System.out.println(longestPalindrome2("aaababadd"));
        System.out.println(longestPalindromeBest("aaababadd"));
        System.out.println(longestPalindromeManacher("aaababadd"));
    }

    //暴力法，判断每个子串是否是回文
    public static String longestPalindrome0(String s) {
        String res = "";
        int max = 0;
        int len = s.length();
        //以每个位置开始的子串是否是回文
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String tmp = s.substring(i, j);
                if (isPalindrome(tmp) && tmp.length() > max) {
                    res = tmp;
                    max = tmp.length();
                }
            }
        }
        return res;
    }

    private static boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    //暴力法优化
    public static String longestPalindrome(String s) {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                //当s.charAt(i) == s.charAt(j)成立的时候，dp[i][j] 的值由 dp[i + 1][j - l] 决定，
                //这一点也不难思考：当左右边界字符串相等的时候，整个字符串是否是回文就完全由“原字符串去掉左右边界”的子串是否回文决定。
                //但是这里还需要再多考虑一点点：“原字符串去掉左右边界”的子串的边界情况。
                //1、当原字符串的元素个数为 3 个的时候，如果左右边界相等，那么去掉它们以后，
                // 只剩下 1 个字符，它一定是回文串，故原字符串也一定是回文串；
                //2、当原字符串的元素个数为 2 个的时候，如果左右边界相等，那么去掉它们以后，
                // 只剩下 0 个字符，显然原字符串也一定是回文串。
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

    //继续优化空间复杂度
    public static String longestPalindrome1(String s) {
        int n = s.length();
        String res = "";
        boolean[] P = new boolean[n];
        //倒推
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

    //循环条件不一样的dp
    public static String longestPalindromeDp(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        //判断以每个位置为结尾的字符串是否是回文
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    //中心扩展算法
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

    //中心扩散法另一种实现
    String res = "";
    public String longestPalindrome3(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return res;
    }

    private void helper(String s, int lo, int hi) {
        while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) {
            lo--;
            hi++;
        }
        String cur = s.substring(lo + 1, hi);
        if (cur.length() > res.length()) {
            res = cur;
        }
    }

    //leetCode最优解
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
        //跳过相等元素
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

    //马拉车算法
    public static String longestPalindromeManacher(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] chars = manacherString(str);
        int[] parr = new int[chars.length];
        int r = -1;
        int index = -1;
        int max = 0;
        int maxCenter = 0;
        for (int i = 0; i < chars.length; i++) {
            parr[i] = r > i ? Math.min(parr[2 * index - i], r - i) : 1;
            while (i + parr[i] < chars.length && i - parr[i] > -1) {
                if (chars[i + parr[i]] == chars[i - parr[i]]) {
                    parr[i]++;
                } else {
                    break;
                }
            }
            if (i + parr[i] > r) {
                r = i + parr[i];
                index = i;
            }
            if (parr[i] > max) {
                max = parr[i] - 1;
                maxCenter = i;
            }
        }
        int start = (maxCenter - max) / 2;
        return str.substring(start, start + max);
    }

    private static char[] manacherString(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[2 * str.length() + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    //另一种实现，不需要判断边界
    public String longestPalindromeManacher1(String str) {
        String t = preProcess(str);
        int n = t.length();
        int[] p = new int[n];
        int c = 0, r = 0;
        int max = 0;
        int maxCenter = 0;
        for (int i = 1; i < n - 1; i++) {
            int iMirror = 2 * c - i;
            p[i] = r > i ? Math.min(r - i, p[iMirror]) : 0;
            while (t.charAt(i + p[i] + 1) == t.charAt(i - p[i] - 1)) {
                p[i]++;
            }
            if (i + p[i] > r) {
                r = i + p[i];
                c = i;
            }
            if (p[i] > max) {
                max = p[i];
                maxCenter = i;
            }
        }
        int start = (maxCenter - max) / 2;
        return str.substring(start, start + max);
    }

    private String preProcess(String str) {
        int n = str.length();
        //首尾字符不同，可以有效控制循环边界
        if (n == 0) {
            return "^$";
        }
        String res = "^";
        for (int i = 0; i < n; i++) {
            res += "#" + str.charAt(i);
        }
        res += "#$";
        return res;
    }
}
