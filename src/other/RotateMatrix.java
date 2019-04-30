/**
 * 旋转矩阵,矩阵为正方形
 * @author xuyh
 * @date 2019/4/25
 */
public class RotateMatrix {
    public static void rotateMatrix(int[][] matrix){
        int tr=0;
        int tc=0;
        int dr=matrix.length-1;
        int dc=matrix[0].length-1;
        while(tr<dr){
            rotateMatrix(matrix,tr++,tc++,dr--,dc--);
        }

    }

    private static void rotateMatrix(int[][] matrix, int tr, int tc, int dr, int dc) {
        int times=dc-tc;
        int tmp;
        for(int i=0;i!=times;i++){
            tmp=matrix[tr][tc+i];
            matrix[tr][tc+i]=matrix[dr-i][tc];
            matrix[dr-i][tc]=matrix[dr][dc-i];
            matrix[dr][dc-i]=matrix[tr+i][dc];
            matrix[tr+i][dc]=tmp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        BaseUtil.printMatrix(matrix);
        rotateMatrix(matrix);
        BaseUtil.printMatrix(matrix);
    }
}
