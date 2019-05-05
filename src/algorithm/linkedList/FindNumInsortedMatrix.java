package linkedList;

/**
 * 在排好序的矩阵中寻找目标数
 *
 * @author xuyh
 * @date 2019/5/2
 */
public class FindNumInsortedMatrix {
    public static boolean isContain(int[][] matrix, int k) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (k == matrix[row][col]) {
                return true;
            } else if (k < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(isContain(matrix, 14));
    }
}
