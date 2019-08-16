package sort;

import util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author xuyh
 * @description: 归并排序进阶版
 * @date 2019/8/14
 */
public class MergeSortAdvance {

    public static void main(String[] args) {
        Integer[] arr=ArrayUtils.getIntegerArrays();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for(int i=0;i<10;i++){
            long start1 = System.currentTimeMillis();
            MergeSortTask.sort(arr,forkJoinPool);
            long end1 = System.currentTimeMillis();
            System.out.println("forkJoinMergeSort耗时："+(end1-start1));
        }
    }

    public static <T extends Comparable<? super T>> T[] sort(T[] arr){
        if(arr==null||arr.length<2){
            return null;
        }
        T[] helper =(T[]) Array.newInstance(arr[0].getClass(), arr.length);
        mergesort(arr,helper,0,arr.length-1);
        return arr;
    }

    private static <T extends Comparable<? super T>> void mergesort(T[] arr, T[] help, int l, int r) {
        if(l==r){
            return;
        }
        int mid=l+((r-l)>>1);
        mergesort(arr,help,l,mid);
        mergesort(arr,help,mid+1,r);
        merge(arr,help,l,mid,r);
    }

    private static <T extends Comparable<? super T>> void merge(T[] arr, T[] helper, int l, int mid, int r) {
        for (int i=l;i<=r;i++){
            helper[i]=arr[i];
        }
        int i=l,j=mid+1;
        for(int k=l;k<=r;k++){
            if (i>mid){
                arr[k]=helper[j++];
            }else if (j>r){
                arr[k]=helper[i++];
            }else if(isLess(helper[i], helper[j])){
                arr[k]=helper[i++];
            }else{
                arr[k]=helper[j++];
            }
        }
    }

    private static <T extends Comparable<? super T>> boolean isLess(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private static class MergeSortTask<T extends Comparable<? super T>> extends RecursiveAction {
        private static final long serialVersionUID = -749935388568367268L;
        private final T[] arr;
        private final T[] helper;
        private final int l;
        private final int r;

        public static <T extends Comparable<? super T>> void sort(T[] a,ForkJoinPool pool) {
            T[] helper = (T[])Array.newInstance(a[0].getClass() , a.length);
            pool.execute(new MergeSortTask<>(a, helper, 0, a.length-1));
        }

        public MergeSortTask(T[] arr, T[] helper, int l, int r){
            this.arr = arr;
            this.helper = helper;
            this.l = l;
            this.r = r;
        }

        @Override
        protected void compute() {
            if(l==r){
                return ;
            }
            int mid=l+((r-l)>>1);
            MergeSortTask<T> task1 = new MergeSortTask<>(arr, helper, l, mid);
            MergeSortTask<T> task2 = new MergeSortTask<>(arr, helper, mid+1, r);
            invokeAll(task1,task2);
            merge(arr,helper,l,mid,r);
        }

        private void merge(T[] a, T[] helper, int lo, int mid, int hi){
            for (int i=lo;i<=hi;i++){
                helper[i]=a[i];
            }
            int i=lo,j=mid+1;
            for(int k=lo;k<=hi;k++){
                if (i>mid){
                    a[k]=helper[j++];
                }else if (j>hi){
                    a[k]=helper[i++];
                }else if(isLess(helper[i], helper[j])){
                    a[k]=helper[i++];
                }else{
                    a[k]=helper[j++];
                }
            }
        }

        private boolean isLess(T a, T b) {
            return a.compareTo(b) < 0;
        }
    }
}
