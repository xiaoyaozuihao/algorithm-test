/**
 * @author xuyh
 * @date 2019/3/31
 */
public class SelectSort {
    public static void selectSort(int[] arr) {
        //选择排序，每次都选出最小值放到数组的前面
        if(arr==null||arr.length<2){
            return ;
        }
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[minIndex]){
                    minIndex=j;
                }
            }
            if(i!=minIndex){
                BaseUtil.swap(arr,i,minIndex);
            }
        }
    }

}
