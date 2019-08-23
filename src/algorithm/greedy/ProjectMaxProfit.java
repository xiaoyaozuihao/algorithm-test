package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * n个项目，花费和收益各有不同，问只做k个项目，初始资金为w，如何能获取最大利益
 *
 * @author xuyh
 * @date 2019/5/24
 */
public class ProjectMaxProfit {
    public static class Project {
        public int profit;
        public int cost;

        public Project(int profit, int cost) {
            this.profit = profit;
            this.cost = cost;
        }
    }

    /**
     * @param k      总共做k个项目
     * @param w      初始资金w
     * @param cost   项目花费
     * @param profit 项目收益
     * @return int 最大收益
     * @description 找到最大化的收益的投资方式
     * @author xuyh
     * @create 2019/8/23 17:13
     */
    public static int findMaximizedProfit(int k, int w, int[] cost, int[] profit) {
        Project[] projects = new Project[profit.length];
        for (int i = 0; i < cost.length; i++) {
            projects[i] = new Project(profit[i], cost[i]);
        }
        PriorityQueue<Project> minCost = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        PriorityQueue<Project> maxProfit = new PriorityQueue<>((p1, p2) -> p2.profit - p1.profit);
        for (int i = 0; i < projects.length; i++) {
            minCost.add(projects[i]);
        }
        for (int i = 0; i < k; i++) {
            while (!minCost.isEmpty() && minCost.peek().cost <= w) {
                maxProfit.add(minCost.poll());
            }
            if (maxProfit.isEmpty()) {
                return w;
            }
            w += maxProfit.poll().profit;
        }
        return w;
    }

    public static void main(String[] args) {
        int k = 5, w = 1000;
        int[] capital = new int[]{300, 200, 700, 600, 800, 1200};
        int[] profit = new int[]{60, 45, 80, 35, 100, 200};
        System.out.println(findMaximizedProfit(k, w, capital, profit));
    }
}
