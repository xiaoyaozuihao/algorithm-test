package binaryTree;

import java.util.List;

/**
 * 一个部门的同事由上下级关系组成一颗多叉树，每个人都有一定的活跃度，某次聚会需要活跃度
 * 有个规则是如果某个人的直属上级来，则他本人一定不来，求如何安排能使聚会的活跃度最大
 *
 * @author xuyh
 * @date 2019/9/26
 */
public class MaxHappy {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 8 }, { 1, 9 }, { 1, 10 } };
        System.out.println(maxHappy(matrix));
    }

    public static class Node {
        private int happyRate;
        private List<Node> nexts;

        public Node(int happyRate, List<Node> nexts) {
            this.happyRate = happyRate;
            this.nexts = nexts;
        }
    }

    public static class ReturnData {
        private int comeHappyRate;
        private int notComeHappyRate;

        public ReturnData(int comeHappyRate, int notComeHappyRate) {
            this.comeHappyRate = comeHappyRate;
            this.notComeHappyRate = notComeHappyRate;
        }
    }

    public static int getMaxHappyRate(Node node) {
        ReturnData data = process(node);
        return Math.max(data.comeHappyRate, data.notComeHappyRate);
    }

    private static ReturnData process(Node node) {
        int comeHappyRate = node.happyRate;
        int notComeHappyRate = 0;
        for (Node next : node.nexts) {
            ReturnData nextData = process(next);
            comeHappyRate += nextData.notComeHappyRate;
            notComeHappyRate += Math.max(nextData.comeHappyRate, nextData.notComeHappyRate);
        }
        return new ReturnData(comeHappyRate, notComeHappyRate);
    }

    public static int maxHappy(int[][] matrix) {
        int[][] dp = new int[matrix.length][2];
        boolean[] visited = new boolean[matrix.length];
        int root = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == matrix[i][0]) {
                root = i;
            }
        }
        process(matrix, dp, visited, root);
        return Math.max(dp[root][0], dp[root][1]);
    }

    public static void process(int[][] matrix, int[][] dp, boolean[] visited, int root) {
        visited[root] = true;
        dp[root][1] = matrix[root][1];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == root && !visited[i]) {
                process(matrix, dp, visited, i);
                dp[root][1] += dp[i][0];
                dp[root][0] += Math.max(dp[i][1], dp[i][0]);
            }
        }
    }

}
