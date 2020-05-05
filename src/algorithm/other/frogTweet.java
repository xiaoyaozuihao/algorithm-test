package other;

/**
 * 青蛙鸣叫问题
 * 给一个字符串s，表示不同青蛙发出的蛙鸣声（字符串“croak”）的组合，由于同一时间可以有多只青蛙叫，所以s中会
 * 混合多个“croak”，请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
 * 注意：要发出蛙鸣“croak”，青蛙必须依序输出c，r，o，a，k这5个字母，如果没有输出全部5个字母，那么它就不会发出声音。
 * 如果字符串s不是由若干有效的“croak”字符混合而成，请返回-1。
 *
 * @author: xuyh
 * @create: 2020/4/24
 **/
public class frogTweet {
    public static int minFrogNum(String s) {
        if (s == null) {
            return -1;
        }
        char[] chars = s.toCharArray();
        if (chars.length % 5 != 0 || chars[0] != 'c' || chars[chars.length - 1] != 'k') {
            return -1;
        }
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'c') {
                if (k > 0) {
                    k--;
                } else {
                    res++;
                }
                c++;
            } else if (chars[i] == 'r') {
                r++;
                c--;
            } else if (chars[i] == 'o') {
                o++;
                r--;
            } else if (chars[i] == 'a') {
                a++;
                o--;
            } else if (chars[i] == 'k') {
                k++;
                a--;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) {
                break;
            }
        }
        if (c != 0 || r != 0 || o != 0 || a != 0) {
            return -1;
        }
        return res;
    }

    public static int minFrogNum1(String s) {
        if (s == null) {
            return -1;
        }
        char[] chars = s.toCharArray();
        if (chars.length % 5 != 0 || chars[0] != 'c' || chars[chars.length - 1] != 'k') {
            return -1;
        }
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int res = 0;
        for (char ch : chars) {
            switch (ch) {
                case 'c':
                    c++;
                    break;
                case 'r':
                    r++;
                    if (r > c) {
                        return -1;
                    }
                    break;
                case 'o':
                    o++;
                    if (o > r) {
                        return -1;
                    }
                    break;
                case 'a':
                    a++;
                    if (a > r) {
                        return -1;
                    }
                    break;
                case 'k':
                    res = Math.max(res, c);
                    c--;
                    r--;
                    o--;
                    a--;
                    break;
            }
        }
        if (c + r + o + a > 0) {
            return -1;
        }
        return res;
    }
}
