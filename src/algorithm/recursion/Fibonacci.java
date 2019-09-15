package recursion;

/**
 * 斐波那契数列求第n项的值
 *
 * @author xuyh
 * @date 2019/5/27
 */
public class Fibonacci {
    //递归
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1L;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    //变量循环
    public static long fabonacci1(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1L;
        }
        long a = 1, b = 1, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    //数组缓存
    public static long fibonacci2(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1L;
        }
        long[] fibo = new long[n + 1];
        fibo[0] = 0;
        fibo[1] = fibo[2] = 1L;
        for (int i = 3; i <= n; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
        return fibo[n];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(8));
    }
}
