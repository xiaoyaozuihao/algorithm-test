package dp;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * n个项目，花费和收益各有不同，问只做k个项目，初始资金为w，如何能获取最大利益
 * @author xuyh
 * @date 2019/5/24
 */
public class IPO {
    public static class Node{
        public int p;
        public int c;
        public Node(int p,int c){
            this.p=p;
            this.c=c;
        }
    }
    public static class MinCostComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c-o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p-o1.p;
        }
    }

    public static int findMaximizedCapital(int k,int w,int[] capital,int[] profit){
        Node[] nodes=new Node[profit.length];
        for(int i=0;i<profit.length;i++){
            nodes[i]=new Node(profit[i],capital[i]);
        }
        PriorityQueue<Node> minCost=new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfit=new PriorityQueue<>(new MaxProfitComparator());
        for(int i=0;i< nodes.length;i++){
            minCost.add(nodes[i]);
        }
        for(int i=0;i<k;i++){
            while(!minCost.isEmpty()&&minCost.peek().c<=w){
                maxProfit.add(minCost.poll());
            }
            if(maxProfit.isEmpty()){
                return w;
            }
            w+=maxProfit.poll().p;
        }
        return w;
    }

    public static void main(String[] args) {
        int k=5,w=1000;
        int[] capital=new int[]{300,200,700,600,800,1200};
        int[] profit=new int[]{60,45,80,35,100,200};
        System.out.println(findMaximizedCapital(k, w, capital, profit));
    }
}
