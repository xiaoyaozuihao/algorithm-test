package array;

/**
 * manacher算法，解决最大回文字符串问题
 * 利用回文数组中的对称点进行加速
 *
 * @author xuyh
 * @date 2019/9/18
 */
public class Manacher {
    public static void main(String[] args) {
        String str = "dabcbae";
        System.out.println(maxPalindromeLength(str));
        System.out.println(maxPalindromeLen(str));
    }

    /**
     * 获取最大回文串长度
     *
     * @param str
     * @return
     */
    public static int maxPalindromeLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = manacherString(str);
        //回文半径数组
        int[] pArr = new int[chars.length];
        //回文右边界
        int pr = -1;
        //当前回文右边界对应的中心点
        int index = -1;
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            //如果在遍历过程中回文右边界在i右边，则当前的回文半径可以由其对称点求出。
            //求出对称点的回文半径以及右边界到我的距离，取这两个的较小值就是以我为中心最大的回文半径。
            //如果i在边界右侧，那么半径就是我自己。
            pArr[i] = pr > i ? Math.min(pArr[2 * index - i], pr - i) : 1;
            //判断边界，右边区域没越界，左边区域也没越界，就向外扩。
            while (i + pArr[i] < chars.length && i - pArr[i] > -1) {
                //如果对应的字符相等，说明还可以向外扩
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            //当前索引加上半径大于最大回文右边界，就更新右边界和中心点。
            if (i + pArr[i] > pr) {
                pr = i + pArr[i];
                index = i;
            }
            //记录最大回文半径,含#号
            max = Math.max(max, pArr[i]);
        }
        //最大回文半径-1就是原始字符串的最大回文串长度
        return max - 1;
    }

    /**
     * 添加占位符,当字符串长度是偶数时也可以算出最长回文子串。
     *
     * @param str
     * @return
     */
    public static char[] manacherString(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    //动态规划
    public static int maxPalindromeLen(String str) {
        int len = str.length();
        //pal[i,j]表示str[i,j]是否是回文串
        boolean[][] pal = new boolean[len][len];
        int max = 0;
        for (int i = 0; i < len; i++) {//i作为终点
            for (int j = i; j >= 0; j--) {
                if (str.charAt(j) == str.charAt(i) && (i - j < 2 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;
                    max = Math.max(max, i - j + 1);
                }
            }
        }
        return max;
    }
}
