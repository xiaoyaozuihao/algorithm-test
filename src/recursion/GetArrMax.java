/**
 * @author xuyh
 * @date 2019/4/2
 */
public class GetArrMax {

    public static int getMax(int[] arr,int L,int R){
        if(arr==null||arr.length==0){
            throw new ArithmeticException("数组为空");
        }
        if(L==R){
            return arr[L];
        }
        int mid=(L+R)/2;
        int maxLeft = getMax(arr, L, mid);
        int maxRight = getMax(arr, mid+1, R);
        return Math.max(maxLeft,maxRight);
    }

    public static void main(String[] args) {
        int[] arr={};
        int max = getMax(arr, 0, arr.length - 1);
        System.out.println(max);
    }
}
