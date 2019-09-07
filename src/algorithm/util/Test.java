package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
    }

    public static int findOneLessValue(int[] arr){
        if(arr==null||arr.length==0){
            return -1;
        }
        if(arr.length==1||arr[0]<arr[1]){
            return 0;
        }
        if(arr[arr.length-1]<arr[arr.length-2]){
            return arr.length-1;
        }
        int left=1;
        int right=arr.length-2;
        while(left<right){
            int mid=left+((right-left)>>1);
            if(arr[mid]>arr[mid-1]){
                right=mid-1;
            }else if(arr[mid]>arr[mid+1]){
                left=mid+1;
            }else{
                return mid;
            }
        }
        return left;
    }
}

