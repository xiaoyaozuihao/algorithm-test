package sort;

import util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 归并排序
 * @author xuyh
 * @date 2019/4/2
 */
public class MergeSort {
    public static void mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        sortProcess(arr,0,arr.length-1);
    }

    private static void sortProcess(int[] arr, int L, int R) {
        if(L==R){
            return;
        }
        int mid = L+((R-L)>>1);//二分求中位数，这种写法可以避免越界
        //拆成两个子过程递归
        sortProcess(arr,L,mid);
        sortProcess(arr,mid+1,R);
        //对子过程进行排序
        merge(arr,L,mid,R);
    }
    public static  void merge(int[] arr,int L,int mid,int R){
        //定义一个辅助数组,长度为原数组的长度
        int[] help=new int[R-L+1];
        int i=0;
        //定义两个指针，一个是左侧头指针，一个是右侧头指针
        int p1=L;
        int p2=mid+1;
        //定义不越界条件
        while(p1<=mid&&p2<=R){
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];//哪个小就拷贝到辅助数据，同时指针都+1
        }
        //其中一个到达边界，将另一半的数据直接拷贝到help数组之后
        while(p1<=mid){
            help[i++]=arr[p1++];
        }
        while(p2<=R){
            help[i++]=arr[p2++];
        }
        //将help数组的元素拷贝回原数组
        for(int j=0;j<help.length;j++){
            arr[L+j]=help[j];
        }
    }

    /**
     * 另一种方式的merge
     * @param arr
     * @param helper
     * @param l
     * @param mid
     * @param r
     */
    public static void merge1(int[] arr,int[] helper,int l,int mid,int r){
        for (int i=l;i<=r;i++){
            helper[i]=arr[i];
        }
        int i=l,j=mid+1;
        for(int k=l;k<=r;k++){
            if (i>mid){
                arr[k]=helper[j++];
            }else if (j>r){
                arr[k]=helper[i++];
            }else if(helper[i]<=helper[j]){
                arr[k]=helper[i++];
            }else{
                arr[k]=helper[j++];
            }
        }
    }

    public static void main(String[] args) {
        BaseUtil.testTemplate("sort.MergeSort","mergeSort");
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("4");
        list2.add("6");
        list2.add("8");


        list1.removeAll(list2);
        list1.stream().forEach(str-> System.out.println(str));
    }
}
