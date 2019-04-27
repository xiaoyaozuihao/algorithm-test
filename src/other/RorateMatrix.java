/**
 * 旋转矩阵,矩阵为正方形
 * @author xuyh
 * @date 2019/4/27
 */
public class RorateMatrix {

    public static void rorateMatrix(int[][] matrix){
        int tr=0;
        int tc=0;
        int dr=matrix.length-1;
        int dc=matrix[0].length-1;
        while(tr<dr){
            rotateEdge(matrix,tr++,tc++,dr--,dc--);
        }
    }

    private static void rotateEdge(int[][] m, int tr, int tc, int dr, int dc) {
        int times=dc-tc;
        for(int i=0;i<times;i++){
            int temp=m[tr][tc+i];
            m[tr][tc+i]=m[dr-i][tc];
            m[dr-i][tc]= m[dr][dc-i];
            m[dr][dc-i] = m[tr+i][dc];
            m[tr+i][dc]=temp;
        }
    }

    public static void printDoubleArray(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                System.out.format("%-4s",matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{1,2,3,4,},{5,6,7,8,},{9,10,11,12},{13,14,15,16}};
        printDoubleArray(matrix);
        rorateMatrix(matrix);
        printDoubleArray(matrix);
    }
}
