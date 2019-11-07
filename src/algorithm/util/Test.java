package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 53, 7, 8, 1};
        bubbleSort(arr);
        BaseUtil.printArray(arr);
    }

    public static void bubbleSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    BaseUtil.swap(arr,j,j+1);
                }
            }
        }
    }
}


