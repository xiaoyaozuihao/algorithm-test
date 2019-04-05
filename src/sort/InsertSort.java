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
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0;j--){
                if(arr[j]>arr[j+1]){
                    BaseUtil.swap(arr,j,j+1);
                }
            }
        }
    }
    public static void insertSort2(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i=1;i<arr.length;i++){
            int temp=arr[i];
            int j;
            for(j=i-1;j>=0;j--){
                if(arr[j]>temp){
                    arr[j+1]=arr[j];
                }
            }
            arr[j+1]=temp;
        }
    }

}
