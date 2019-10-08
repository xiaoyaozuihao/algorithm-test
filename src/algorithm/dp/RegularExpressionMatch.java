package dp;

/**
 * 正则匹配问题，字符串A只有字母，字符串B包含（字母.*）三种符号，问字符串B能否匹配出字符串A
 *
 * @author: xuyh
 * @create: 2019/10/7
 **/
public class RegularExpressionMatch {
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
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) ? process(s, e, 0, 0) : false;
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

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp));
        System.out.println(isMatchDP(str, exp));
    }
}
