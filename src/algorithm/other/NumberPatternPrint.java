package other;

/**
 * @author xuyh
 * @date 2019/4/6
 */
public class NumberPatternPrint {
    //打印如下格式的数字
    //1
    //3*2
    //4*5*6
    //10*9*8*7
    //11*12*13*14*15
    public static void numberPatternPrint(int line){
        int num=0;//一直递增的值
        int printNum=0;//每行打印值
        for (int i=1;i<=line;i++){
            if(i%2==1){//确定每行开始的值，奇数行就是num+1,偶数行就是num+行数
                printNum=num+1;
            }else{
                printNum=num+i;
            }
            for(int col=1;col<=i;col++){
                num++;//num一直全局递增
                if(i%2==1){//奇数行数字递增,偶数行数字递减
                    System.out.print(printNum++);
                }else{
                    System.out.print(printNum--);
                }
                if(i==col){
                    System.out.println();
                }else {
                    System.out.print("*");
                }
            }
        }
    }

    public static void main(String[] args) {
        numberPatternPrint(7);
    }

}
