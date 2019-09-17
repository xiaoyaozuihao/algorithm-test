package array;

/**
 * 向一个字符串后面添加一个最短的字符串，使新的字符串包含两个原始串，并且开头位置不能一样。
 *
 * @author xuyh
 * @date 2019/9/17
 */
public class KMP_ShortestHaveTwice {
    public static void main(String[] args) {
        String str = "abcaabcc";
        System.out.println(getShortestString(str));
    }

    public static String getShortestString(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        if (str.length() == 1) {
            return str + str;
        }
        if (str.length() == 2) {
            return str.charAt(0) == str.charAt(1) ? str + str.charAt(0) : str + str;
        }
        int endNext = endNextLength(str);
        return str + str.substring(endNext);
    }

    public static int endNextLength(String str) {
        int[] next = new int[str.length() + 1];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cur = 0;
        while (pos < next.length) {
            if (str.charAt(pos - 1) == str.charAt(cur)) {
                next[pos++] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                next[pos++] = 0;
            }
        }
        return next[str.length()];
    }
}
