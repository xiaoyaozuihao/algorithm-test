package sort;

import util.BaseUtil;

/**
 * 希尔排序,插入排序的进阶版
 * @author xuyh
 * @date 2019/5/31
 */
public class ShellSort {
    public static void shellSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
//        int incr=arr.length/2;
//        int i,j,temp;
//        while(incr>=1){
//            for(i=incr;i<arr.length;i++){
//                temp=arr[i];
//                j=i-incr;
//                while(j>=0&&arr[j]>temp){
//                    arr[j+incr]=arr[j];
//                    j-=incr;
//                }
//                arr[j+incr]=temp;
//            }
//            incr/=2;
//        }
        int incr,i,j,temp;
        for(incr=arr.length/2;incr>=1;incr/=2){
            for(i=incr;i<arr.length;i++){
                temp=arr[i];
                j=i-incr;
                while(j>=0&&arr[j]>temp){
                    arr[j+incr]=arr[j];
                    j-=incr;
                }
                arr[j+incr]=temp;
            }
        }
    }

    public static void main(String[] args) {
        BaseUtil.testSortTemplate("sort.ShellSort","shellSort");
    }
}
