package sort;

import util.BaseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuyh
 * @date 2019/4/19
 */
public class TestSort {
    public static void main(String[] args) {
        BaseUtil.testSortTemplate("sort.TestSort", "sort");
//        int [] arr=new int[]{16,0,25,28,9,12,-29,26,26,-10,12,10,11,-11,-28,24,-3,-22,-7,-22,20,-2,27,-16,25,-14,-15,20,-3,5,6,-9,26,-2,-18,-9,4,14};
//        sort(arr);
//        BaseUtil.printArray(arr);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max= Arrays.stream(arr).max().getAsInt();
        int min=Arrays.stream(arr).min().getAsInt();
        int bucketNum=(max-min)/arr.length+1;
        List<List<Integer>> bucket=new ArrayList<>(bucketNum);
        for(int i=0;i<bucketNum;i++){
            bucket.add(new ArrayList<>());
        }
        for(int i=0;i<arr.length;i++){
            int num=(arr[i]-min)/arr.length;
            bucket.get(num).add(arr[i]);
        }
        int index=0;
        for(int i=0;i<bucketNum;i++){
            insertion(bucket.get(i));
//            Collections.sort(bucket.get(i));
            for(int j=0;j<bucket.get(i).size();j++){
                arr[index++]=bucket.get(i).get(j);
            }
        }

    }

    private static void insertion(List<Integer> bucket) {
        for(int j=1;j<bucket.size();j++){
            int temp=bucket.get(j);
            int k=j-1;
            while(k>=0&&bucket.get(k)>temp){
                bucket.set(k+1,bucket.get(k--));
            }
            bucket.set(k+1,temp);
        }

    }
}
