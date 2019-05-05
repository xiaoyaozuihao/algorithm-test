package other;

import util.BaseUtil;

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

    public static void rotateMatrix1(int[][] matrix){
        int row=matrix.length;
        for(int layer=0;layer<row/2;layer++){
            rotateLayer(matrix,layer,row);
        }
    }

    private static void rotateLayer(int[][] m, int offset, int row) {
        for(int pos=0;pos<row-1;pos++){
            int temp=m[offset][offset+pos];
            m[offset][offset+pos]=m[row-1-pos-offset][offset];
            m[row-1-pos-offset][offset]=m[row-offset-1][row-1-pos-offset];
            m[row-offset-1][row-1-pos-offset]=m[offset+pos][row-offset-1];
            m[offset+pos][row-offset-1]=temp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        BaseUtil.printMatrix(matrix);
//        rotateMatrix(matrix);
//        BaseUtil.printMatrix(matrix);
        rotateMatrix1(matrix);
        BaseUtil.printMatrix(matrix);
    }
}
