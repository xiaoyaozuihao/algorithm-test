package array;

/**
 * 在数组中找到局部最小的位置
 * 如果arr长度为1，则arr[0]最小；
 * 如果arr长度n>1，若arr[n-1]<arr[n-2],则arr[n-1]局部最小；
 * 如果0<i<n-1,arr[i]<arr[i-1]且arr[i]<arr[i+1]，则arr[i]为局部最小。
 * @author xuyh
 * @date 2019/5/5
 */
public class FindOneLessValueIndex {
    public static int getLessIndex(int[] array){
        if(array==null|| array.length==0){
            return -1;
        }
        if(array.length==1||array[0]<array[1]){
            return 0;
        }
        if(array[array.length-1]<array[array.length-2]){
            return array.length-1;
        }
        int left=1;
        int right= array.length-2;
        int mid=0;
        while(left<right){
            mid=(left+right)/2;
            if(array[mid]>array[mid-1]){
                right=mid-1;
            }else if(array[mid]>array[mid+1]){
                left=mid+1;
            }else{
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{6,5,3,4,6,7,8};
        System.out.println(getLessIndex(arr));
    }

}
