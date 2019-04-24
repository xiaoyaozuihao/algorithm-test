/**
 * 打印矩阵螺旋顺序
 * @author xuyh
 * @date 2019/4/24
 */
public class PrintMatrixSpiralOrder {
    public static void spiralOrderPrint(int[][] matrix){
        int tr=0;
        int tc=0;
        int dr=matrix.length-1;
        int dc=matrix[0].length-1;
        while(tr<=dr&&tc<=dc){
            printEdge(matrix,tr++,tc++,dr--,dc--);
        }
    }

    private static void printEdge(int[][] m, int tr, int tc, int dr, int dc) {
        if(tr==dr){
            for(int i=tc;i<=dc;i++){
                System.out.print(m[tr][i]+" ");
            }
        }else if(tc==dc){
            for(int i=tr;i<=dr;i++){
                System.out.print(m[i][tc]+" ");
            }
        }else{
            int curC=tc;
            int curR=tr;
            while(curC!=dc){
                System.out.print(m[tr][curC]+" ");
                curC++;
            }
            while(curR!=dr){
                System.out.print(m[curR][dc]+" ");
                curR++;
            }
            while(curC!=tc){
                System.out.print(m[dr][curC]+" ");
                curC--;
            }
            while(curR!=tr){
                System.out.print(m[curR][tc]+" ");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
//        int[][] matrix=new int[][]{{1,2,3},{8,9,4},{7,6,5}};
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);
    }
}
