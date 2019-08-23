package greedy;

import java.util.PriorityQueue;

/**
 * 输入一个数组，返回将整个数组之和切分为每个元素所花费的最小代价。例如：[10,20,30],数组之和为60
 * 如果将60拆分为10，50，花费60，将50拆分为20，30，花费50，总花费110；
 * 如果将60拆分成30，30，花费60，将30拆分为10，20，花费30，总花费90；
 * 所以最小代价为90.
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
        int cur;
        while(pq.size()>1){
            cur=pq.poll()+pq.poll();
            pq.add(cur);
            sum+=cur;
        }
        return sum;
    }
}
