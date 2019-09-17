package recursion;

/**
 * 数字字符串转换为字母组合的种数
 * 数字可以转换为字母，例如1->A,2->B...26->Z
 * 例如：1111可以转换为  AAAA,AAL,LAA,LAL,LL,一共5种。
 * 0没有对应的字母，不可转换
 *
 * @author xuyh
 * @date 2019/9/17
 */
public class NumsOfNumToLetter {
    public static int num1(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    //递归有两个分支，p(i+1)和p(p+2)，一共n层递归，所有时间复杂度为O(2^n),空间复杂度O(n)
    private static int process(char[] chars, int i) {
        if (i == chars.length) {
            return 1;
        }
        if (chars[i] == '0') {
            return 0;
        }
        int res = process(chars, i + 1);
        if (i + 1 < chars.length && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
            res += process(chars, i + 2);
        }
        return res;
    }

    //O(n)的时间复杂度，空间复杂度O(1)
    public static int num2(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int cur = chars[chars.length - 1] == '0' ? 0 : 1;
        int next = 1;
        int tmp = 0;
        for (int i = chars.length - 2; i >= 0; i--) {
            if (chars[i] == '0') {
                next = cur;
                cur = 0;
            } else {
                tmp = cur;
                if ((chars[i] - '0') * 10 - chars[i + 1] - '0' < 27) {
                    cur += next;
                }
                next = tmp;
            }
        }
        return cur;
    }

    public static int num3(String str) {
        if(str==null||str.equals("")){
            return 0;
        }
        char[] chs = str.toCharArray();
        return numProcess(chs,0);
    }

    public static int numProcess(char[] chs, int i) {
        if (i == chs.length) {
            return 1;
        }
        if (chs[i] == '0') {
            return 0;
        }
        if (chs[i] == '1') {
            int res = numProcess(chs, i + 1);
            if (i + 1 < chs.length) {
                res += numProcess(chs, i + 2);
            }
            return res;
        }
        if (chs[i] == '2') {
            int res = numProcess(chs, i + 1);
            if (i + 1 < chs.length
                    && (chs[i + 1] >= '0' && chs[i + 1] <= '6')) {
                res += numProcess(chs, i + 2);
            }
            return res;
        }
        return numProcess(chs, i + 1);
    }

    public static void main(String[] args) {
        String str = "111122";
        System.out.println(num1(str));
        System.out.println(num2(str));
        System.out.println(num3(str));
    }
}
