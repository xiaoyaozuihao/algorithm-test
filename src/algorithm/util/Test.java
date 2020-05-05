package util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        int times = 1000000;
        boolean succeed = true;
        for (int i = 0; i < times; i++) {
            int[] arr = BaseUtil.generateRandomArray(100, 100);
            int[] arrCopy = BaseUtil.copyArray(arr);
            if (mostEor(arrCopy) != comparator(arr)) {
                succeed = false;
                BaseUtil.printArray(arr);
                break;
            }
        }
        System.out.println(succeed ? "nice" : "fuck");
    }

    public static int mostEor(int[] arr) {
        if(arr==null||arr.length==0){
            return 0;
        }
        int xor=0;
        int[] dp=new int[arr.length];
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<arr.length;i++){
            xor ^= arr[i];
            if(map.containsKey(xor)){
                int pre = map.get(xor);
                dp[i]=pre==-1?1:dp[pre]+1;
            }
            if(i>0){
                dp[i]=Math.max(dp[i-1],dp[i]);
            }
            map.put(xor,i);
        }
        return dp[dp.length-1];
    }
    public static int comparator(int[] arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        int eor=0;
        int[] eors=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            eor ^= arr[i];
            eors[i]=eor;
        }
        int[] dp=new int[arr.length];
        dp[0]=eors[0]==0?1:0;
        for(int i=1;i<arr.length;i++){
            dp[i]=eors[i]==0?1:0;
            for(int j=0;j<i;j++){
                if((eors[i]^eors[j])==0){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            dp[i]=Math.max(dp[i-1],dp[i]);
        }
        return dp[dp.length-1];
    }

}