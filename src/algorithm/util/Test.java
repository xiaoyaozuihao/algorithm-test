package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void sort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        bucketSort(arr);
    }

    private static void bucketSort(int[] arr) {
        int min=Arrays.stream(arr).min().getAsInt();
        int max= Arrays.stream(arr).max().getAsInt();
        int bucketNum=(max-min)/arr.length+1;
        List<List<Integer>> bucketArr=new ArrayList<>(bucketNum);
        for(int i=0;i<bucketNum;i++){
            bucketArr.add(new ArrayList<>());
        }
        for(int i=0;i<arr.length;i++){
            int targetBucket=(arr[i]-min)/arr.length;
            bucketArr.get(targetBucket).add(arr[i]);
        }
        for(int i=0,j=0;j<bucketArr.size();j++){
            insertSort(bucketArr.get(j));
            for(int h=0;h<bucketArr.get(j).size();h++){
                arr[i++]=bucketArr.get(j).get(h);
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
        BaseUtil.testTemplate("util.Test","sort");
    }
}
