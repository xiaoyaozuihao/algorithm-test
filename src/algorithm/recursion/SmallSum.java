package recursion;

import util.BaseUtil;

/**
 * 小和问题:在一个数组中，每个数左边比当前数小的数累加起来，叫做这个数组的小和。
 * 运用的解法正是归并排序。
 * @author xuyh
 * @date 2019/4/6
 */
public class SmallSum {

    public static int smallSum(int[] arr){
        if(arr==null||arr.length<2){
            return 0;
        }
        return mergeSort(arr,0,arr.length-1);

    }
    public static int mergeSort(int[] arr,int l,int r){
        if(l==r){
            return 0;
        }
        int mid = l+((r-l)>>1);
        return mergeSort(arr,l,mid)+mergeSort(arr,mid+1,r)+merge(arr,l,mid,r);
    }

    public static int merge(int[] arr,int l,int mid,int r){
        int[] help=new int[r-l+1];
        int i=0;
        int p1=l;
        int p2=mid+1;
        int res=0;
        while(p1<=mid&&p2<=r){
            res+=arr[p1]<arr[p2]?(r-p2+1)*arr[p1]:0;//计算小和以右侧为基准，所以一定要保证右侧的角标先达到最大
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
//            help[i++]=arr[p1]>arr[p2]?arr[p2++]:arr[p1++];
            // 错误写法一定要注意：看似和上面的写法等效，但是在比较arr[p1]和arr[p2]相等的时候，会使得p1的脚标先越界，从而少计算了小和。
        }
        while(p1<=mid){
            help[i++]=arr[p1++];
        }
        while(p2<=r){
            help[i++]=arr[p2++];
        }
        for(i=0;i<help.length;i++){
            arr[l+i]=help[i];
        }
        return res;
    }

    //相对正确的但时间复杂度不好的方法
    public static int comparator(int[] arr){
        if(arr==null||arr.length<2){
            return 0;
        }
        int res=0;
        for(int i=1;i<arr.length;i++){
            for(int j=0;j<i;j++){
                res+=arr[j]<arr[i]?arr[j]:0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{2,3,6,5,7,3,2};
        int testTimes=10000;
        int maxsize=1000;
        int maxValue=100;
        boolean succeed=true;
        for(int i=0;i<testTimes;i++){
            int[] array = BaseUtil.generateRandomArray(maxsize, maxValue);
            int[] array1 = BaseUtil.copyArray(array);
            int[] array2 = BaseUtil.copyArray(array);
            if(comparator(array1)!=smallSum(array2)){
                succeed=false;
                BaseUtil.printArray(arr);
                break;
            }
        }
        System.out.println(succeed?"nice":"no");
    }
}
