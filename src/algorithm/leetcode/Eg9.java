package leetcode;

/**
 * 判断一个数字是否是回文
 *
 * @author xuyh
 * @date 2019/10/28
 */
public class Eg9 {
    //时间复杂度 O(log10(n))
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
}
