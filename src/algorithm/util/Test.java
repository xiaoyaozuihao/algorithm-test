package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void sort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        sortProcess(arr,0,arr.length-1);

    }

    private static void sortProcess(int[] arr, int l, int r) {
        if(l==r){
            return;
        }
        int mid=l+((r-l)>>1);
        sortProcess(arr,l,mid);
        sortProcess(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help=new int[r-l+1];
        int p1=l;
        int p2=mid+1;
        int index=0;
        while(p1<=mid&&p2<=r){
            help[index++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while(p1<=mid){
            help[index++]=arr[p1++];
        }
        while(p2<=r){
            help[index++]=arr[p2++];
        }
        for(index=0;index<help.length;index++){
            arr[l+index]=help[index];
        }
    }

    public static void main(String[] args) {
        BaseUtil.testTemplate("util.Test","sort");
    }
}
