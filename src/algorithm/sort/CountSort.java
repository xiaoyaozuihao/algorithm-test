package sort;

import util.BaseUtil;

/**
 * 计数排序，需要占用大量空间，它仅适用于数据比较集中的情况，比如 [0~100]，[10000~19999] 这样的数据。
 * @author xuyh
 * @date 2019/4/13
 */
public class CountSort {

    public static void countSort(int[] array){
        if(array==null||array.length<2){
            return;
        }
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<array.length;i++){
            min=Math.min(array[i],min);
            max=Math.max(array[i],max);
        }
        //如果数组中的数不集中，会浪费大量的桶
        int[] bucket=new int[max-min+1];
        for(int i=0;i< array.length;i++){
            bucket[array[i]-min]++;
        }
        for(int j=0,i=0;j<bucket.length;j++){
            while(bucket[j]-->0){
                //注意：桶的索引存放的是真正的数-最小的数，所以还原时+最小的数
                array[i++]=j+min;
            }
        }
    }

    public static int[] countSort1(int[] arr){//未能实现稳定排序
        if(arr==null||arr.length<2){
            return arr;
        }
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            min=Math.min(arr[i],min);
            max= Math.max(arr[i],max);
        }
        int[] bucket=new int[max-min+1];
        for(int i=0;i<arr.length;i++){
            bucket[arr[i]-min]++;
        }
        for(int j=1;j<bucket.length;j++){
            bucket[j]=bucket[j-1]+bucket[j];
        }
        int[] res=new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){
            int pos=--bucket[arr[i]-min];
            res[pos]=arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
//        BaseUtil.testTemplate("CountSort","countSort1");
        int[] arr = {3,2, 4,2, 1, 2, 2};
        int[] ints = countSort1(arr);
        BaseUtil.printArray(ints);
    }
}
