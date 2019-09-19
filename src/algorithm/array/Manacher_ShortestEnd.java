package array;

/**
 * 在原字符串的基础上添加一个最小的字符串，使之成为回文串
 *
 * @author xuyh
 * @date 2019/9/19
 */
public class Manacher_ShortestEnd {
    public static void main(String[] args) {
        String str="abc123321c";
        System.out.println(shortestEnd(str));
    }

    public static String shortestEnd(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] chars = manacherString(str);
        int[] pArr = new int[chars.length];
        int pr = -1;
        int index = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i < chars.length; i++) {
            pr = pr > i ? Math.min(pArr[2 * index - i], pr - index) : 1;
            while (i + pArr[i] < chars.length && i - pArr[i] > -1) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > pr) {
                pr = i + pArr[i];
                index = i;
            }
            if (pr == chars.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        //原串长度减去包含最后一个字符在内的最大回文串的长度（maxContainsEnd-1）
        // 就是需要加在原串后面的字符串的长度。
        char[] res = new char[str.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            //将chars数组中的奇数位的数（跳过#符号）逆序就得到了使原串成为回文串需要的最小的字符串的长度
            res[res.length - 1 - i] = chars[2 * i + 1];
        }
        return String.valueOf(res);
    }

    public static char[] manacherString(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }
}
