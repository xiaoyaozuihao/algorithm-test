package recursion;

import java.math.BigInteger;

/**
 * 阶乘
 * @author xuyh
 * @date 2019/5/27
 */
public class Factorial {
    public static long factorial(int n){
        if(n==1){
            return 1L;
        }
        return (long)n*factorial(n-1);
    }

    public static long factorial1(int n){
        long res=1L;
        for(int i=2;i<=n;i++){
            res*=i;
        }
        return res;
    }

    public static BigInteger factorial2(int n){
        BigInteger res=new BigInteger("1");
        for(long i=2;i<=n;i++){
            res=res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(factorial(17));
        System.out.println(factorial1(23));
        System.out.println(factorial2(26));
    }
}
