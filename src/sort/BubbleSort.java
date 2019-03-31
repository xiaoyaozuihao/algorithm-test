/**
 * 冒泡排序
 * @author xuyh
 * @date 2019/3/31
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr){
        if(arr==null||arr.length<2){
            return ;
        }
        for (int end = arr.length-1; end >0; end--) {
            for (int i = 0; i < end; i++) {
                if(arr[i]>arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
        }
//        for (int i = 0; i < arr.length-1; i++) {
//
//        }
    }

    private static void swap(int[] arr,int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
