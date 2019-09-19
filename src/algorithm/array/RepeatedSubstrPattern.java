package array;

/**
 * 给你一个非空字符串，判断它能否通过重复它的某一个子串若干次（两次及以上）得到。
 * 字符串由小写字母组成，并且它的长度不会超过10000。
 * 例如：输入abab ,返回true；输入aba,返回false
 *
 * @author: xuyh
 * @create: 2019/9/17
 **/
public class RepeatedSubstrPattern {
    public static void main(String[] args) {
        String str = "abcabc";
        System.out.println(repeatedSubstrPattern(str));
        System.out.println(repeatedSubstrPattern1(str));
        System.out.println(repeatedSubstrPattern2(str));
        System.out.println(repeatedSubstrPattern3(str));
        System.out.println(repeatedSubstrPattern4(str));
        System.out.println(repeatedSubstrPattern5(str));
    }

    /**
     * 首先一个字符串t重复N次后得到重复字符串s， 那么s = N * t，那么这样的重复字符串截取t后得到的字符串也是重复字符串。
     * 然后得到s1 = s+s，现在的s1 = 2N * t，有2N个t组成。
     * 现在把s1的前后减去一个字符，那么前后的两个t就不再作为重复子字符串而存在了，此时s1相当于(2N-2)*t，
     * 如果此时s1中依然能够找到s，说明s完全由t组成。
     *
     * @param str
     * @return
     */
    public static boolean repeatedSubstrPattern(String str) {
        return new String((str + str).toCharArray(), 1, str.length() * 2 - 2).contains(str);
    }

    //正则表达式
    public static boolean repeatedSubstrPattern1(String str) {
        return str.matches("(\\w+)\\1+");
    }

    public static boolean repeatedSubstrPattern2(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        int len = s.length();
        char lastc = s.charAt(len - 1);
        //从中间位置查找最后一个字符在前面出现的索引
        int l = s.lastIndexOf(lastc, len / 2 - 1) + 1;
        for (; l > 0; l = s.lastIndexOf(lastc, l - 2) + 1) {
            //是否能整除
            if (len % l == 0) {
                String p = s.substring(0, l);
                boolean res = true;
                for (int i = l; i < len; i += l) {
                    if (!s.substring(i, i + l).equals(p)) {
                        res = false;
                        break;
                    }
                }
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean repeatedSubstrPattern3(String str) {
        if (str == null || str.length() < 2) {
            return false;
        }
        int len = str.length();
        for (int i = 1; i < len; i++) {
            if (len % i == 0) {
                String sub = str.substring(0, i);
                for (int j = i; j < len; j += i) {
                    if (!str.substring(j, j + i).equals(sub)) break;
                    if (j >= len - i) return true;
                }
            }
        }
        return false;
    }

    public static boolean repeatedSubstrPattern4(String str) {
        int len = str.length();
        for (int i = len / 2; i >= 1; i--) {
            if (len % i == 0) {
                boolean pass = true;
                for (int j = len / i; j >= 1; j--) {
                    if (!str.substring(0, i).equals(str.substring(i * (j - 1), i * j))) {
                        pass = false;
                        break;
                    }
                }
                if (pass) {
                    return true;
                }
            }
        }
        return false;
    }

    //kmp解法
    public static boolean repeatedSubstrPattern5(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        int len = s.length();
        int[] next = new int[len];
        next[0] = -1;
        int m = -1;
        for (int i = 1; i < len; i++) {
            while (m >= 0 && s.charAt(i) != s.charAt(m + 1)) {
                m = next[m];
            }
            if (s.charAt(i) == s.charAt(m + 1)) {
                m++;
            }
            next[i] = m;
        }
        int lenSub = len - next[len - 1] - 1;
        return lenSub != len && len % lenSub == 0;
    }
}
