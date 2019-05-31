package recursion;

/**
 * @author xuyh
 * @date 2019/5/27
 */
public class Fibonacci {

    public static long fibonacci(int n){
        if(n==1||n==2){
            return 1L;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(8));
    }
}
