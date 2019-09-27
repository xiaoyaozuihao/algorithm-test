package bit;

/**
 * Calculate XOR from 1 to n
 *
 * @author xuyh
 * @date 2019/9/24
 */
public class CalculateXor {
    public static void main (String[] args) {
        int n = 10;
        System.out.println(calculateXor(n));
    }

    public static int calculateXor(int n) {
        if (n % 4 == 0) {
            return n;
        }
        if (n % 4 == 1) {
            return 1;
        }
        if (n % 4 == 2) {
            return n + 1;
        }
        return 0;
    }
}
