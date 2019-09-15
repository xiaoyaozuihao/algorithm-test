package array;

/**
 * 旋转有序数组的二分查找
 * @author xuyh
 * @date 2019/5/28
 */
public class RotateArrayBinSearch {
    public static void main(String[] args) {
    }
    //可以解决重复元素问题
    public static int binarySearch(int[] nums,int target){
        if(nums==null||nums.length==0){
            return -1;
        }
        int l=0,r=nums.length-1,mid;
        while(l<=r){
            mid=l+((r-l)>>1);
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]<nums[l]){//右侧有序
                if(target>nums[mid]&&target<=nums[r]){
                    l=mid+1;
                }else{
                    r=mid-1;
                }
            }else if(nums[mid]>nums[l]){//左侧有序
                if(target>=nums[l]&&target<nums[mid]){
                    r=mid-1;
                }else{
                    l=mid+1;
                }
            }else{
                l++;
            }
        }
        return -1;
    }

    //旋转有序的数组二分查找，例如7654123,无重复元素
    public static int bSearch(int[] arr,int x){
        if(arr==null||arr.length==0){
            return -1;
        }
        return bSearch(arr,arr.length,x);
    }

    private static int bSearch(int[] arr, int len, int x) {
        int l=0,r=len-1,mid;
        while(l<=r){
            mid=(l+r)>>1;
            if(arr[mid]==x){
                return mid;
            }
            if(arr[mid]<arr[r]){//右侧有序
                if(x>arr[mid]&&x<=arr[r]){
                    l=mid+1;
                }else{
                    r=mid-1;
                }
            }else{//左侧有序
                if(x<arr[mid]&&x>=arr[l]){
                    r=mid-1;
                }else{
                    l=mid+1;
                }
            }
        }
        return -1;
    }

    //旋转数组不存在重复元素，最简洁的版本
    public static int bSearch1(int[] nums,int target){
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (( nums[0]> target) ^ (nums[0] > nums[mid]) ^ (target > nums[mid])) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo == hi && nums[lo] == target ? lo : -1;
    }

    public static int bSearch2(int[] nums,int target){
        // 边界值
        if(nums== null || nums.length == 0){
            return -1;
        }
        // 1.确定最小值
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if(nums[mid] > nums[hi]){
                lo = mid + 1;
            }else{
                hi = mid;
            }
        }
        int minimumIndex = lo;
        // 2.是否找到
        if(nums[minimumIndex] == target){
            return minimumIndex;
        }

        // 3.确定target在哪一段
        int realLow = -1;
        int realHigh = -1;
        if(target > nums[nums.length - 1]){
            realLow = 0;
            realHigh = minimumIndex -1;
        }else{
            realLow = minimumIndex;
            realHigh = nums.length - 1;
        }
        // 4. 开始真正的二分查找
        int result = -1;
        while(realLow <= realHigh){
            int realMid = (realLow + realHigh)/2;
            if(nums[realMid] == target){
                result = realMid;
                break;
            }
            if(target > nums[realMid]){
                realLow = realMid + 1;
            }else{
                realHigh = realMid - 1;
            }
        }
        return result;
    }

}
