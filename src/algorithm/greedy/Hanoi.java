package greedy;

/**
 * 汉诺塔问题，打印最优移动路径
 * @author xuyh
 * @date 2019/5/27
 */
public class Hanoi {

    public static void hanoi(int n){
        if(n>0){
            func(n,n,"left","mid","right");
        }
    }
    public static void func(int rest, int down, String from, String help, String to) {
        if (rest == 1) {
            System.out.println("move " + down + " from " + from + " to " + to);
        } else {
            func(rest - 1, down - 1, from, to, help);
            func(1, down, from, help, to);
            func(rest - 1, down - 1, help, from, to);
        }
    }

    public static void func(int n,String from,String help,String to){
        if(n==1){
            System.out.println("move 1" +" from "+from+" to "+to);
        }else{
            func(n-1,from,to,help);
            System.out.println("move "+n+" from " +from +" to "+to);
            func(n-1,help,from,to);
        }
    }

    public static void main(String[] args) {
        hanoi(3);
        System.out.println();
        func(3,"left","mid","right");

    }
}
