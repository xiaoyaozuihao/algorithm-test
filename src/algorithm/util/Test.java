package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                res[i][j] = (int) (Math.random() * 10);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath(m));
    }

    public static int minPath1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static int minPath(int[][] matrix) {
        return process(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    private static int process(int[][] matrix, int i, int j) {
        int res = matrix[i][j];
        if (i == 0 && j == 0) {
            return res;
        }
        if (i == 0) {
            return res + process(matrix, i, j - 1);
        }
        if (j == 0) {
            return res + process(matrix, i - 1, j);
        }
        return res + Math.min(process(matrix, i, j - 1), process(matrix, i - 1, j));
    }
}