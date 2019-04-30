/**
 * 打印杨辉三角形
 *
 * @author xuyh
 * @date 2019/4/21
 */
public class YanghuiTriangle {
    public static void main(String[] args) {
        int rows = 10;
        for (int i = 0; i < rows; i++) {
            int number = 1;
            //打印空格字符串
            System.out.format("%" + (rows - i) * 2 + "s", "");
            for (int j = 0; j <= i; j++) {
                System.out.format("%4d", number);//右对齐打印
                number = number * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }

    //左对齐打印
    public static void yanghui() {
        int rows = 12;
        for (int i = 1; i <= rows; i++) {
            int num = 1;
            if (i != rows) {//不是最后一行，打印每行前面的空格
                System.out.format("%" + (rows - i) * 2 + "s", "");
            }
            //打印每行的数字，行数和数字的个数相同
            for (int j = 1; j <= i; j++) {
                System.out.format("%-4d", num);//左对齐，不足四位，右边补空格，超出四位，原样输出
                num = num * (i - j) / j;
            }
            System.out.println();
        }
    }


    //递归法
    public static void yanghuiTriangle(int row) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print(num(i + 1, j + 1) + "   ");
            }
            System.out.println();
        }
    }
    public static int num(int x, int y) {
        if (y == 1 || y == x) {
            return 1;
        }
        return num(x - 1, y - 1) + num(x - 1, y);
    }
}
