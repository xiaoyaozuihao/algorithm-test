package leetcode;

/**
 * 反转整数
 *
 * @author xuyh
 * @date 2019/10/25
 */
public class Eg7 {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        Eg7 eg7 = new Eg7();
        System.out.println(eg7.reverse1(2147483646));
    }

    //避免反转后溢出，直接用long接收
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
            if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
                return 0;
            }
        }
        return (int) res;
    }
    //不用long的做法,int最大值和最小值最后一位分别是7和8，所以判断最后一位的数值大小即可

    /**
     * 如果 rev > intMax / 10 ，那么没的说，此时肯定溢出了。
     * 如果 rev == intMax / 10 = 2147483647 / 10 = 214748364 ，
     * 此时 rev * 10 就是 2147483640 如果 pop 大于 7 ，那么就一定溢出了。
     * 但是!!!如果假设 pop 等于 8，那么意味着原数 x 是 8463847412 了，输入的是 int ，
     * 而此时是溢出的状态，所以不可能输入，所以意味着 pop 不可能大于 7 ，
     * 也就意味着 rev == intMax / 10 时不会造成溢出。
     * intMin同理
     *
     * @param x
     * @return
     */
    public int reverse1(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
//            if (res > Integer.MAX_VALUE / 10
//                    || (res == Integer.MAX_VALUE / 10 && pop > 7)
//                    || res < Integer.MIN_VALUE / 10
//                    || (res == Integer.MIN_VALUE / 10 && pop < -8)) {
//                return 0;
//            }
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10){
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }
}
