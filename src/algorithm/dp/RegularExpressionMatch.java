package dp;

/**
 * 正则匹配问题，字符串A只有字母，字符串B包含（字母.*）三种符号，问字符串B能否匹配出字符串A
 *
 * @author: xuyh
 * @create: 2019/10/7
 **/
public class RegularExpressionMatch {
    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        str = "aa";
        exp = "a*a*";
        System.out.println(isMatch0(str, exp));
        System.out.println(isMatch1(str, exp));
        System.out.println(isMatchDP(str, exp));
        System.out.println(isMatchLeetCodeBest(str, exp));
    }

    //官方暴力法
    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    //官方dp
    public static boolean isMatch1(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
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

    public static boolean isMatch0(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) && process(s, e, 0, 0);
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

    public static boolean isMatchLeetCodeBest(String s, String p) {
        if (s == null || p == null) return false;
        /**
         * 整个dp二维数组的行变量从0开始到p的各个字符，列变量从0开始到s的各个字符
         * 如：对于s = "aab"  p = "a.*b" 组成的二维数组如下
         *       0  a  .  *  b
         *     0
         *     a
         *     a
         *     b
         */
        /**
         * 这里把p字符串放在行变量上是为了验证p字符串对s的各终结点的子串的匹配情况
         */
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //第一个，0，0点肯定为true
        dp[0][0] = true;
        //先初始化第一行，第一行因为要对空字符串匹配，所以只能由*号做删除才能匹配
        for (int i = 0; i < p.length(); i++) {
            if ((i == 0 || p.charAt(i - 1) == '*') && p.charAt(i) == '*') {
                return false;
            }
            if (p.charAt(i) == '*') {
                /**
                 *  ‘*’号可以删除前一个元素，所以查看在不包括前一个元素的情况下，能否匹配。
                 */
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
                if (pC == sC || pC == '.') {
                    /**
                     * 如果此时的p字符串所在字符与s当前位置字符相等，那么p当前位置能否匹配s当前位置取决于p的上一个位置能否匹配s的上个位置
                     * 如果此时 p 字符串当前字符是'.'，因为'.'的效果是可以替换任意字符，所以也可以类比到-》此时字符与s当前字符相等
                     */
                    /**
                     * 假设此时 i = 0  j = 0  此时的情况如下：
                     *         0  a  .  *  b
                     *      0  @
                     *      a  F ￥
                     *      a  F
                     *      b  F
                     *
                     *    注：此时 ￥ 处的值取决于 @ 处的值
                     */
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (pC == '*') {
                    /**
                     * 如果此时p字符串当前位置是'*'，因为'*'的效果是可以把上个字符复制-1到n次，换而言之，'*'既可以把上个字符删掉，
                     * 也可以在上个字符后面再复制任意个上个字符。
                     * 那么此时可发生的操作有三种情况：
                     *  上个位置不是'.'，此时这个'*'就重复零个p字符串上个位置字符即可匹配
                     *      图示如下：假设此时 i = 1  j = 1
                     *         0  a  *  .  b
                     *      0  T
                     *      a  F
                     *      a  F  @  $
                     *      b  F
                     *      注：此时$是否匹配取决于@的值
                     *  上个位置是'.'，此时只需要判断p的上个位置能否匹配s的上个位置。因为上个位置是'.',所以p.上个位置与s.的上个位置能否匹配取决于
                     *               p的上上个位置与s的上上个位置能否匹配，也就是第二种情况。
                     *      图示如下：假设此时 i = 2  j = 2
                     *         0  a  .  *  b
                     *      0  T
                     *      a  F
                     *      a  F     @
                     *      b  F        $
                     *      注：此时$是否匹配取决于@的值
                     */
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

    public boolean isMatchLeetCode0(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();
        boolean[][] array = new boolean[len1 + 1][len2 + 1];
        array[len1][len2] = true;
        for (int i = len1; i >= 0; i--) {
            for (int j = len2; j >= 0; j--) {
                if (i == len1 && j == len2) {//前面已经判断过
                    continue;
                }
                boolean isMatch = i < len1 && j < len2 && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j + 1 < len2 && p.charAt(j + 1) == '*') {
                    array[i][j] = array[i][j + 2] || (isMatch && array[i + 1][j]);
                } else {
                    array[i][j] = isMatch && array[i + 1][j + 1];
                }
            }
        }
        return array[0][0];
    }

    //leetCode解法
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

    //leetCode暴力解法
    public boolean isMatchLeetCode1(String s, String p) {
        return match(s, p, 0, 0);
    }

    private boolean match(String s, String p, int i, int j) {
        int ls = s.length();
        int lp = p.length();
        if (j == lp) return i == ls;
        boolean isMatch = i < ls && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));
        if (j < lp - 1 && p.charAt(j + 1) == '*') {
            if (isMatch)
                return match(s, p, i + 1, j) || match(s, p, i, j + 2);
            return match(s, p, i, j + 2);
        }
        if (isMatch)
            return match(s, p, i + 1, j + 1);
        return false;
    }
}
