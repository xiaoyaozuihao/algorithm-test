package dp;

import java.util.PriorityQueue;

/**
 * @author xuyh
 * @date 2019/5/24
 */
public class LessMoney {
    public static int lessMoney(int[] arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<arr.length;i++){
            pq.add(arr[i]);
        }
        int sum=0;
        int cur=0;
        while(pq.size()>1){
            cur=pq.poll()+pq.poll();
            pq.add(cur);
            sum+=cur;
        }
        return sum;
    }
}
