package array;

/**
 * 二分查找
 * @author xuyh
 * @date 2019/4/28
 */
public class BinarySearch {
    //循环实现
    public static int binarySearch(int[] arr,int obj){
        if(arr==null||arr.length==0){
            return -1;
        }
        int start=0;
        int end=arr.length-1;
        while(start<= end){
            int mid=(start+end)>>>1;
            if(arr[mid]==obj){
                return mid;
            }else if(arr[mid]<obj){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return -(start+1);
    }

    //递归实现
    private static int binarySearch1(int[] arr,int obj,int low,int high) {
        if(low<=high){
            int mid=(low+high)>>>1;
            if(arr[mid]==obj){
                return mid;
            }else if(arr[mid]<obj){
                return binarySearch1(arr,obj,mid+1,high);
            }else{
                return binarySearch1(arr,obj,low,mid-1);
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        int[] arr= new int[]{2,3,4,5,6,7,8,20,43,54};
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch1(arr,20,0,arr.length-1));
    }
}
