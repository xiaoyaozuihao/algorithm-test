import java.util.ArrayList;
import java.util.Collections;

/**
 * 桶排序
 * @author xuyh
 * @date 2019/4/20
 */
public class BucketSort {
    public static void bucketSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max,arr[i]);
            min= Math.min(min,arr[i]);
        }
        //桶数
        int bucketNum=(max-min)/arr.length+1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i=0;i<bucketNum;i++){
            bucketArr.add(new ArrayList<>());
        }
        for(int j=0;j<arr.length;j++){
            int num=(arr[j]-min)/arr.length;
            bucketArr.get(num).add(arr[j]);
        }
        int index=0;
        for(int i=0;i<bucketNum;i++){
            Collections.sort(bucketArr.get(i));
            for(int j=0;j<bucketArr.get(i).size();j++){
                arr[index++]=bucketArr.get(i).get(j);
            }
        }
    }

    public static void main(String[] args) {
        BaseUtil.testTemplate("BucketSort","bucketSort");
    }
}
