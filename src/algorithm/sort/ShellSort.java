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
        int num=arr.length/2;
        int i,j,temp;
        while(num>=1){
            for(i=num;i<arr.length;i++){
                temp=arr[i];
                j=i-num;
                while(j>=0&&arr[j]>temp){
                    arr[j+num]=arr[j];
                    j-=num;
                }
                arr[j+num]=temp;
            }
            num/=2;
        }
    }

    public static void main(String[] args) {
        BaseUtil.testTemplate("sort.ShellSort","shellSort");
    }
}
