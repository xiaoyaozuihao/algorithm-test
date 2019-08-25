package binaryTree;

/**
 * 折纸问题：
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2次，
 * 压出折痕后展开，此时有三条折痕，从上到下依次是下折痕,下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向。例如 :
 * N = 1时,打印 : 　down
 * N = 2时,打印 : 　down down up
 * @author: xuyh
 * @create: 2019/8/25
 **/
public class PaperFolding {
    public static void main(String[] args) {
        int N=4;
        printAllFolds(N);
    }
    public static void printAllFolds(int N){
        printPaperFolding(1,N,true);
    }
    public static void printPaperFolding(int index,int N,boolean down){
        if(index>N){
            return;
        }
        printPaperFolding(index+1,N,true);
        System.out.println(down?"down ":"up ");
        printPaperFolding(index+1,N,false);

    }
}
