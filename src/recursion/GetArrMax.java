/**
 * 查找数组最大值,分治策略
 * master公式 :T(N)=a*T(N/b)+O(n^d)
 * ①当d<logb a时，时间复杂度为O(n^(logb a)) ，例如a=b=2,d=0 ,则logb a=1>d ,所以时间复杂度为O(n)
 * ②当d=logb a时，时间复杂度为O((n^d)*logn)
 * ③当d>logb a时，时间复杂度为O(n^d)
 * @author xuyh
 * @date 2019/4/2
 */
public class GetArrMax {

    public static int getMax(int[] arr){
        if(arr==null||arr.length==0){
            throw new ArithmeticException("数组为空");
        }
        int max=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return max;
    }

    public static int getArrMax1(int[] arr,int L,int R){
        if(arr==null||arr.length==0){
            return -1;
        }
        if(L==R){
            return arr[L];
        }
        int mid = L+((R-L)>>1);
        int LMax = getArrMax1(arr, L, mid);
        int RMax = getArrMax1(arr,mid+1,R);
        return Math.max(LMax,RMax);
    }

    public static void main(String[] args) {
        int times=10000;
        boolean succeed=true;
        for(int i=0;i<times;i++){
            int[] array = BaseUtil.generateRandomArray(10000, 1000);
            int[] array1 = BaseUtil.copyArray(array);
            int[] array2 = BaseUtil.copyArray(array);
            int max1 = getMax(array1);
            int max2 = getArrMax1(array2, 0, array2.length - 1);
            if(max1!=max2){
                succeed=false;
                BaseUtil.printArray(array);
                break;
            }
        }
        System.out.println(succeed?"nice":"no");

    }
}
