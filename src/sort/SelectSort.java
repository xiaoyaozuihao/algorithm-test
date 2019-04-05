/**
 * @author xuyh
 * @date 2019/3/31
 */
public class SelectSort {
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //选择排序，每次都选出最小值放到数组的前面
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j]<arr[minIndex]){
                    minIndex=j;
                }

            }
            if(i!=minIndex){
                swap(arr,i,minIndex);
            }
        }
    }

    public static void swap(int[] arr,int i,int j) {
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
}
