package leetcode;

/**
 * 判断一个数字是否是回文
 *
 * @author xuyh
 * @date 2019/10/28
 */
public class Eg9 {
    //时间复杂度 O(log10(x))
    //反转一半的数字
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x /= 10;
        }
        return x == reverseNum || x == reverseNum / 10;
    }

    //数字全部反转
    public boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        }
        return x == reverse(x);
    }

    private int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    //反转总位数的一半
    public boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        }
        int digit = (int) (Math.log(x) / Math.log(10) + 1); //总位数，以10为底的对数的求法
        int revert = 0;
        int pop = 0;
        //倒置右半部分
        for (int i = 0; i < digit / 2; i++) {
            pop = x % 10;
            revert = revert * 10 + pop;
            x /= 10;
        }
        //偶数
        if (digit % 2 == 0 && x == revert) {
            return true;
        }
        //奇数情况 x 除以 10 去除 1 位
        if (digit % 2 != 0 && x / 10 == revert) {
            return true;
        }
        return false;
    }
}
