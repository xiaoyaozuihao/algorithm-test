package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
//        BaseUtil.testTemplate("util.Test","sort");
    }

    public static void sort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if(l<r){
            BaseUtil.swap(arr,l+(int)Math.random()*(r-l+1),r);
            int[] p = partition(arr, l, r);
            quickSort(arr,l,p[0]-1);
            quickSort(arr,p[1]+1,r);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less=l-1;
        int more=r;
        while(l<more){
            if(arr[l]<arr[r]){
                BaseUtil.swap(arr,++less,l++);
            }else if(arr[l]>arr[r]){
                BaseUtil.swap(arr,--more,l);
            }else {
                l++;
            }
        }
        BaseUtil.swap(arr,more,r);
        return new int[]{less+1,more};
    }
}
