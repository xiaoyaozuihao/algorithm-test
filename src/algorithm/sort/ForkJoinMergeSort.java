package sort;

import util.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author xuyh
 * @description: forkJoin归并排序
 * @date 2019/8/13
 */
public class ForkJoinMergeSort {
    public static void main(String[] args) {
        int[] arrays = ArrayUtils.getIntArrays();
        int[] arrays1=Arrays.copyOf(arrays,arrays.length);
        int[] arrays2=Arrays.copyOf(arrays,arrays.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyMergeSort myMergeSort = new MyMergeSort(arrays);
        long start = System.currentTimeMillis();
        forkJoinPool.submit(myMergeSort);
        long end = System.currentTimeMillis();
        System.out.println("forkJoinMergeSort耗时："+(end-start)+"ms");
        long l1 = System.currentTimeMillis();
        mergeSort(arrays1);
        long l2 = System.currentTimeMillis();
        System.out.println("普通mergeSort耗时："+(l2-l1)+"ms");
        long t1 = System.currentTimeMillis();
        Arrays.sort(arrays2);
        long t2 = System.currentTimeMillis();
        System.out.println("java库函数耗时："+(t2-t1)+"ms");
    }
    public static void mergeSort(int[] arr){
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
        int i=0;
        int p1=l;
        int p2=mid+1;
        while(p1<=mid&&p2<=r){
            help[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
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
    }

    static class MyMergeSort extends RecursiveTask<int[]> {
        private int[] source;

        public MyMergeSort(int[] source) {
            this.source = source;
        }

        @Override
        protected int[] compute() {
            int length = source.length;
            if (length > 2) {
                int mid = length / 2;
                MyMergeSort task1 = new MyMergeSort(Arrays.copyOf(source, mid));
                MyMergeSort task2 = new MyMergeSort(Arrays.copyOfRange(source, mid, length));
                invokeAll(task1,task2);
                int[] merge = joinInts(task1.join(), task2.join());
                return merge;
            }else {
                if(length==1||source[0]<=source[1]){
                    return source;
                }else{
                    int[] target=new int[length];
                    target[0]=source[1];
                    target[1]=source[0];
                    return target;
                }
            }
        }

        private int[] joinInts(int[] join1, int[] join2) {
            int p1 = 0;
            int p2 = 0;
            int[] merge = new int[join1.length + join2.length];
            if (merge.length == 0)
                return null;
            for (int i = 0; i < merge.length; i++) {
                if (join1.length == p1) {
                    merge[i] = join2[p2++];
                    continue;
                } else if (join2.length == p2) {
                    merge[i] = join1[p1++];
                    continue;
                }
                if (join1[p1] <= join2[p2]) {
                    merge[i]=join1[p1++];
                } else {
                    merge[i]=join2[p2++];
                }
            }
            return merge;
        }
    }
}
