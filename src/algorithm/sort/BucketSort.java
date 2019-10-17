package sort;

import util.BaseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();
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
//            Collections.sort(bucketArr.get(i));
            insertSort(bucketArr.get(i));
            for(int j=0;j<bucketArr.get(i).size();j++){
                arr[index++]=bucketArr.get(i).get(j);
            }
        }
    }

    private static void insertSort(List<Integer> bucketList)  {
        if(bucketList==null||bucketList.size()<2){
            return ;
        }
        for(int i=1;i<bucketList.size();i++){
            int temp=bucketList.get(i);
            int j=i-1;
            while(j>=0&&bucketList.get(j)>temp){
                bucketList.set(j+1,bucketList.get(j));
                j--;
            }
            bucketList.set(j+1,temp);
        }
    }


    public static void main(String[] args) {
        BaseUtil.testSortTemplate("BucketSort","bucketSort");
    }
}
