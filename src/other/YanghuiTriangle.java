/**
 * 打印杨辉三角形
 *
 * @author xuyh
 * @date 2019/4/21
 */
public class YanghuiTriangle {
    public static void main(String[] args) {
        int rows = 12;
        for (int i = 1; i <= rows; i++) {
            int num = 1;
            //打印空格字符串
            if (i != rows) {
                System.out.format("%" + (rows - i) * 2 + "s", "");
            }
            for (int j = 1; j <= i; j++) {
                System.out.format("%-4d", num);
                num = num * (i - j) / j;
            }
            System.out.println();
        }
        calculate(rows);
    }

    public static int num(int x,int y){
        if(y==1||y==x){
            return 1;
        }
        return num(x-1,y-1)+num(x-1,y);
    }

    public static void calculate(int row){
        for(int i=0;i<row;i++){
            for(int j=0;j<row-i;j++){
                System.out.print(" ");
            }
            for(int j=0;j<i;j++){
                System.out.print(num(i+1,j+1)+"   ");
            }
            System.out.println();
        }
    }
}
