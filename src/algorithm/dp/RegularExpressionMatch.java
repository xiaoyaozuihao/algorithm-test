package dp;

/**
 * 正则匹配问题，字符串A只有字母，字符串B包含（字母.*）三种符号，问字符串B能否匹配出字符串A
 * leetCode 第十题
 *
 * @author: xuyh
 * @create: 2019/10/7
 **/
public class RegularExpressionMatch {
    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch0(str, exp));
        System.out.println(isMatch1(str, exp));
        System.out.println(isMatchDP(str, exp));
        System.out.println(isMatchLeetCodeBest(str, exp));
    }

    public static boolean isMatchLeetCodeBest(String s, String p) {
        if (s == null || p == null) return false;
        /**
         * 这里把p字符串放在行变量上是为了验证p字符串对s的各终结点的子串的匹配情况
         * 整个dp二维数组的行变量从0开始到p的各个字符，列变量从0开始到s的各个字符
         * 如：对于s = "aab"  p = "a.*b" 组成的二维数组如下
         *       0  a  .  *  b
         *     0
         *     a
         *     a
         *     b
         */
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //第一个，0，0点肯定为true
        dp[0][0] = true;
        //先初始化第一行，第一行因为要对空字符串匹配，所以只能由*号做删除才能匹配
        for (int i = 0; i < p.length(); i++) {
            //模式串有效性验证
            if ((i == 0 || p.charAt(i - 1) == '*') && p.charAt(i) == '*') {
                return false;
            }
            //*号可以删除前一个元素，所以查看在不包括前一个元素的情况下，能否匹配。
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = dp[0][i - 1];
            }
        }
        /**
         * 因为前面的初始化都是相当于空字符串操作的，所以这里我们遍历的开始还是从每个字符串的第一个开始。
         */
        for (int i = 0; i < s.length(); i++) {
            char sC = s.charAt(i);
            for (int j = 0; j < p.length(); j++) {
                char pC = p.charAt(j);
                //如果此时的p字符串所在字符与s当前位置字符相等或是‘.’，那么p当前位置能否匹配s当前位置取决于p的上一个位置能否匹配s的上个位置
                if (pC == sC || pC == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                    //如果此时p字符串当前位置是'*'，因为'*'的效果是可以把上个字符复制-1到n次，换而言之，'*'既可以把上个字符删掉，
                } else if (pC == '*') {
                    if (p.charAt(j - 1) == sC) {
                        //上个字符与此字符相等， 且上个位置与这个位置匹配
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i + 1][j - 1] || dp[i][j];
                    } else if (p.charAt(j - 1) == '.') {
                        //且上个字符是 '.'   那么有两种情况可以匹配
                        // 1，p的上上个位置与s的此位置匹配  那么'*'的含义直接删掉'.'就可匹配
                        // 2，p的上个位置与s的上个
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i + 1][j - 1] || dp[i][j + 1];
                        if (i >= 1) {
                            dp[i + 1][j + 1] |= dp[i - 1][j - 1];
                        }
                    } else {
                        //且上个字符不是'.'  那么必须上个位置与上个位置匹配
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //官方暴力法
    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        //只有长度大于 2 的时候，才考虑 *
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            //两种情况
            //pattern 直接跳过两个字符。表示 * 前边的字符出现 0 次
            //pattern 不变，例如 text = aa ，pattern = a*，第一个 a 匹配，
            // 然后 text 的第二个 a 接着和 pattern 的第一个 a 进行匹配。表示 * 用前一个字符替代。
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    //官方dp,假设 text 的长度是 T，pattern 的长度是 P ，时间和空间复杂度都是 O（TP）。
    public static boolean isMatch1(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (first_match && dp[i + 1][j]);
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    //dp优化空间复杂度，空间复杂度：主要用了两个数组进行轮换，O（P）
    public boolean isMatch4(String text, String pattern) {
        // 多一维的空间，因为求 dp[len - 1][j] 的时候需要知道 dp[len][j] 的情况，
        // 多一维的话，就可以把 对 dp[len - 1][j] 也写进循环了
        boolean[][] dp = new boolean[2][pattern.length() + 1];
        dp[text.length() % 2][pattern.length()] = true;
        // 从 len 开始减少
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length(); j >= 0; j--) {
                if (i == text.length() && j == pattern.length()) continue;
                boolean first_match = (i < text.length() && j < pattern.length()
                        && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i % 2][j] = dp[i % 2][j + 2] || first_match && dp[(i + 1) % 2][j];
                } else {
                    dp[i % 2][j] = first_match && dp[(i + 1) % 2][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    //另类递归
    public static boolean isMatch0(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) && process(s, e, 0, 0);
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

    public static boolean process(char[] str, char[] exp, int si, int ei) {
        //表达式exp已经消耗完，是否能配出来取决于str是否也消耗完
        if (ei == exp.length) {
            return si == str.length;
        }
        //表达式还有字符，如果是最后一个字符，或者当前的下一个字符不是'*'，则要分情况讨论
        //1.str已经没有字符了，那么直接返回false，否则继续向后考查
        //2.如果表达式当前字符和字符串当前字符相等或者表达式字符为'.',则当前字符能配出来，
        //那么继续考查双方的下一个字符
        if (ei + 1 == exp.length || exp[ei + 1] != '*') {
            return si != str.length && (exp[ei] == str[si] || exp[ei] == '.')
                    && process(str, exp, si + 1, ei + 1);
        }
        //表达式还有字符，str也有字符，且表达式的下一个字符是'*',则比较当前字符是否匹配
        //匹配的话，则将表达式当前字符加上下一个字符可以认为是0个字符，从ei+2开始尝试匹配
        //匹配出的话就直接返回true,否则将之认为是1个字符，2个字符，3个字符...依次向后尝试匹配
        while (si != str.length && (exp[ei] == str[si] || exp[ei] == '.')) {
            if (process(str, exp, si, ei + 2)) {
                return true;
            }
            si++;
        }
        //当前字符不匹配且表达式的下一个字符是'*'，则表达式当前字符加上下一个字符可以认为是0个字符
        //于是从ei+2开启继续向下匹配
        return process(str, exp, si, ei + 2);
    }

    //另类递归改动态规划
    public static boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        boolean[][] dp = initDPMap(s, e);
        //从倒数第一行，倒数第三列开始向前推
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                //暴力方法的改写
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (!dp[i][j]) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    //初始化dp数组
    public static boolean[][] initDPMap(char[] str, char[] exp) {
        int slen = str.length;
        int elen = exp.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        //由baseCase知终止位置为true，最后一列除终止位置都是false
        dp[slen][elen] = true;
        //最后一行的情况是str已经没有了，exp还有字符，那么如果是*，则配不出来
        //如果是字符*，则可以匹配，如果*字符*，配不出来，如果是字符*字符*...的格式，则可以匹配
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (exp[j] != '*' && exp[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                //当不满足条件时，则剩下的都为false
                break;
            }
        }
        if (slen > 0 && elen > 0) {
            //倒数第二列的情况分析，如果表达式最后一个字符和字符串最后一个字符能匹配，则为true
            //除了这个位置外倒数第二列的其他位置都是false，因为表达式只剩最后一个字符，字符串却有超过一个以上的字符。
            if ((exp[elen - 1] == '.' || str[slen - 1] == exp[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }

    //leetCode解法，正向dp
    public boolean isMatchLeetCode(String s, String p) {
        int ls = s.length(), lp = p.length();
        boolean[][] dp = new boolean[ls + 1][lp + 1];
        dp[0][0] = true;
        for (int j = 2; j <= lp; j++)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        for (int i = 1; i <= ls; i++) {
            for (int j = 1; j <= lp; j++) {
                int m = i - 1, n = j - 1;
                if (p.charAt(n) == '*')
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j] &&
                            (s.charAt(m) == p.charAt(n - 1) || p.charAt(n - 1) == '.');
                else if (s.charAt(m) == p.charAt(n) || p.charAt(n) == '.')
                    dp[i][j] = dp[i - 1][j - 1];
            }
        }
        return dp[ls][lp];
    }

}
