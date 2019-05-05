package sort;

import util.BaseUtil;

/**
 * @author xuyh
 * @date 2019/3/31
 */
public class InsertSort {
    public static void insertSort(int[] arr){
        if(arr==null||arr.length<2){
            return ;
        }
        //插入排序就是将待排序的元素插入到已排好序的子序列中。
        for(int i=1;i<arr.length;i++){//从第二个元素一直遍历到末尾
            for(int j=0;j<i;j++){//对已排好序的序列进行扫描，将要排序元素插入进去
                if(arr[j]>arr[i]){
                    BaseUtil.swap(arr,j,i);
                }
            }
        }
    }
    public static void insertSort1(int[] arr){
        if(arr==null||arr.length<2){
            return ;
        }
        for(int i=1;i<arr.length;i++){//外层循环定义遍历次数n-1
            for(int j=i-1;j>=0;j--){//内层循环从目标元素的前一个元素向前遍历，比较并交换
                if(arr[j]>arr[j+1]){
                    BaseUtil.swap(arr,j,j+1);
                }
            }
        }
    }
    //此版本减少交换次数
    public static void insertSort2(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i=1;i< arr.length;i++){
            int temp=arr[i];
            int j;
            //如果前面的元素大于目标值，就向后移动一位，同时指针减一
            //如果前面的元素小于等于目标值，跳出循环，由于每次循环减1，所以是j+1和temp交换
            for(j=i-1;j>=0&&arr[j]>temp;j--){
                arr[j+1]=arr[j];
            }
            arr[j+1]=temp;//如果第一次就跳出循环，实际上位置没动。
        }

        //while循环版本
//        for(int i=1;i<arr.length;i++){
//            int temp=arr[i];
//            int j =i-1;
//            while (j>=0&&arr[j]>temp){
//                arr[j+1]=arr[j];
//                j--;
//            }
//            arr[j+1]=temp;
//        }
    }

}
