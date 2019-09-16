package leetcode;

/**
 * @author xuyh
 * @description: 寻找旋转数组中的最小值，存在重复元素
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * @date 2019/9/11
 */
public class Eg154 {
    public static void main(String[] args) {
        Eg154 eg154=new Eg154();
        int[] nums={2,4,5,6,7,0,1};
        System.out.println(eg154.findMin(nums));
        System.out.println(eg154.findMin2(nums));
        System.out.println(eg154.findMin3(nums));
    }

    public int findMin(int[] nums) {
        if(nums==null||nums.length==0){
            throw new RuntimeException("empty array");
        }
        int lo=0,hi=nums.length-1,mid=0;
        while(lo<hi){
            mid=lo+((hi-lo)>>1);
            if(nums[mid] > nums[hi]){
                lo=mid+1;
            }else if(nums[mid]<nums[hi]){
                hi=mid;
            }else{
                hi--;
            }
        }
        return nums[lo];
    }


    public int findMin1(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == nums[l] || nums[m] == nums[r] || nums[r] == nums[l]) {
                int res = nums[l];
                while (l <= r) {
                    res = Math.min(res, nums[l++]);
                }
                return res;
            }
            if (nums[m] < nums[l]) {
                r = m;
            } else if (nums[l] > nums[r]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return Math.min(nums[l], nums[r]);
    }

    /**
     * 不允许有重复元素
     * @param nums
     * @return
     */
    public int findMin2(int[] nums){
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = (left+right) >>> 1;
            if (nums[mid] > nums[mid+1]) {
                return nums[mid+1];
            } else {
                if (nums[mid] < nums[0]) {
                    right = mid;
                } else {
                    left = mid+1;
                }
            }
        }
        return nums[0];
    }

    public int findMin3(int[] nums){
        if(nums==null||nums.length==0){
            throw new RuntimeException("empty array");
        }
        if(nums.length==1){
            return nums[0];
        }
        int lo=0,hi=nums.length-1,mid=0;
        if(nums[hi]>nums[0]){
            return nums[0];
        }
        while(lo<hi){
            mid=lo+((hi-lo)>>1);
            if(nums[mid]>nums[mid+1]){
                return nums[mid+1];
            }
            if(nums[mid-1]>nums[mid]){
                return nums[mid];
            }
            if(nums[mid]>nums[0]){
                lo=mid+1;
            }else{
                hi=mid-1;
            }
        }
        throw new RuntimeException("empty array");
    }
}
