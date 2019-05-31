package greedy;

/**
 * 奶牛问题
 * @author xuyh
 * @date 2019/5/27
 */
public class Cow {
    public static int cowNum(int n){
        if(n<0){
            return 0;
        }
        if(n==1||n==2||n==3){
            return n;
        }
        return cowNum(n-1)+cowNum(n-3);
    }

    public static int cowNum1(int n){
        if(n<0){
            return 0;
        }
        if(n==1||n==2||n==3){
            return n;
        }
        int res=3;
        int pre=2;
        int prepre=1;
        int tmp1=0;
        int tmp2=0;
        for(int i=4;i<=n;i++){
            tmp1=res;
            tmp2=pre;
            res=res+prepre;
            pre=tmp1;
            prepre=tmp2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(cowNum(7));
        System.out.println(cowNum1(12));
    }
}
