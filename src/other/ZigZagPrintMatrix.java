/**
 * 之字形打印矩阵
 *
 * @author xuyh
 * @date 2019/4/27
 */
public class ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix) {
        int ar = 0;
        int ac = 0;
        int br = 0;
        int bc = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (ar != endR + 1) {
            printLevel(matrix, ar, ac, br, bc, fromUp);
            ar = ac == endC ? ar + 1 : ar;
            ac = ac == endC ? ac : ac + 1;
            bc = br == endR ? bc + 1 : bc;
            br = br == endR ? br : br + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    private static void printLevel(int[][] matrix, int ar, int ac, int br, int bc, boolean fromUp) {
        if (fromUp) {
            while (ar != br + 1) {
                System.out.print(matrix[ar++][ac--] + " ");
            }
        } else {
            while(br!=ar-1){
                System.out.print(matrix[br--][bc++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };

        printMatrixZigZag(matrix);
    }
}
