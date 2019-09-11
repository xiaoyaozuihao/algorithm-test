package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
    }

    public static int binarySearch(int[] arr,int target){
        if(arr==null||arr.length==0){
            return -1;
        }
        int lo=0,hi=arr.length-1,mid;
        while(lo<=hi){
            mid=lo+((hi-lo)>>1);
            if(arr[mid]==target){
                return mid;
            }
            if(arr[mid]<arr[lo]){//右侧有序
                if(target>arr[mid]&&target<=arr[hi]){
                    lo=mid+1;
                }else{
                    hi=mid-1;
                }
            }else if(arr[mid]>arr[lo]){//左侧有序
                if(target>=arr[lo]&&target<arr[mid]){
                    hi=mid-1;
                }else{
                    lo=mid+1;
                }

            }else{
                lo++;
            }
        }
        return -1;
    }
}

