package greedy;

/**
 * 矩形方阵，从左上角走到右下角，求最短路径
 * @author xuyh
 * @date 2019/5/27
 */
public class MinPath {
    public static int minPath1(int[][] matrix){
        return process1(matrix,matrix.length-1,matrix[0].length-1);
    }

    private static int process1(int[][] matrix, int r, int c) {
        int res=matrix[r][c];
        if(r==0&&c==0){
            return res;
        }
        if(r==0&&c!=0){
            return res+process1(matrix,r,c-1);
        }
        if(r!=0&&c==0){
            return res+process1(matrix,r-1,c);
        }
        return res+Math.min(process1(matrix,r,c-1),process1(matrix,r-1,c));
    }

    public static int minPath(int[][] matrix){
        if(matrix==null||matrix.length==0){
            return 0;
        }
        int row=matrix.length;
        int col=matrix[0].length;
        int[][] dp=new int[row][col];
        dp[0][0]=matrix[0][0];
        for(int i=1;i<row;i++){
            dp[i][0]=dp[i-1][0]+matrix[i][0];
        }
        for(int i=1;i<col;i++){
            dp[0][i]=dp[0][i-1]+matrix[0][i];
        }
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                dp[i][j]= Math.min(dp[i-1][j],dp[i][j-1])+matrix[i][j];
            }
        }
        return dp[row-1][col-1];
    }

    public static int[][] generateRandomMatrix(int rowSize,int colSize){
        if(rowSize<0||colSize<0){
            return null;
        }
        int[][] res=new int[rowSize][colSize];
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                res[i][j]=(int)(Math.random()*10);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));
        System.out.println(minPath(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath(m));
    }
}
