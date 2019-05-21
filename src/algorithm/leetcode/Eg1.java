package leetcode;

import util.BaseUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyh
 * @date 2019/5/16
 */
public class Eg1 {
    //暴力破解
    public static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]==target-nums[i]){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum1(int[] nums,int target){
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            if(map.get(target-nums[i])!=null&&map.get(target-nums[i])!=i){
                return new int[]{i,map.get(target-nums[i])};
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums,int target){
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i!=nums.length;i++){
            int complement=target-nums[i];
            if(map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    //leetCode最优解法
    public static int[] twoSum3(int[] nums,int target){
        int index;
        int indexArrayMax=2047;
        int[] indexArrays=new int[indexArrayMax+1];
        int diff;
        for(int i=1;i<nums.length;i++){
            diff=target-nums[i];
            //i=0时索引无效,所以单独处理
            if(diff==nums[0]){
                return new int[]{0,i};
            }
            index=diff&indexArrayMax;
            if(indexArrays[index]!=0){
                return new int[]{indexArrays[index],i};
            }
            indexArrays[nums[i]&indexArrayMax]=i;
        }

        return new int[2];
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4};
        arr=twoSum3(new int[]{2047,0, 800001, 800000, 7,2,2048}, 4095);
//        arr=twoSum3(new int[]{2047,546666, 800001, 1898, 7,2,2048}, 548564);
        BaseUtil.printArray(arr);
    }
}
