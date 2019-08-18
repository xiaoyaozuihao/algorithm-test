package other;

/**
 * 之字形打印矩阵
 *
 * @author xuyh
 * @date 2019/4/27
 */
public class ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix) {
        int tr=0;
        int tc=0;
        int dr=0;
        int dc=0;
        int endR=matrix.length-1;
        int endC=matrix[0].length-1;
        boolean fromUp=false;
        while(tr!=endR+1){
            printLevel(matrix,tr,tc,dr,dc,fromUp);
            tr=tc==endC?tr+1:tr;
            tc=tc==endC?tc:tc+1;
            //特别注意下面两个语句的顺序，先算dc，再算dr，因为判断条件是dr==endR.
            dc=dr==endR?dc+1:dc;
            dr=dr==endR?dr:dr+1;
            fromUp=!fromUp;
        }
    }

    private static void printLevel(int[][] matrix, int tr, int tc, int dr, int dc,boolean fromUp) {
        if(fromUp){
            while(tr!=dr+1){
                System.out.print(matrix[tr++][tc--]+" ");
            }
        }else{
            while(dc!=tc+1){
                System.out.print(matrix[dr--][dc++]+" ");
            }
        }
    }

    //偶数行顺序，奇数行倒序打印
    public static int[] printMatrix(int[][] mat, int n, int m) {
        int[] arr = new int [n*m];
        //hang记录你的行数，count记录的是你返回的字符数组的长度
        int hang = 0;
        int count = 0;
        //偶数行顺序，奇数行倒序
        for(int i = 0; i < mat.length; i++ ){
            for(int j = 0; j < mat[i].length; j++){
                //判断数奇数行还是偶数行
                if(hang%2 == 0){
                    arr[count++] = mat[i][j];
                }else{
                    arr[count++] = mat[i][m-1-j];
                }
            }
            //写完了一行，行数需要+1
            hang++;
        }
        return arr;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },{13,14,15,16} };

        printMatrixZigZag(matrix);
    }
}
