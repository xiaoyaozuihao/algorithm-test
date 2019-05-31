package util;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static int[] sort(int[] arr){
        if(arr==null||arr.length<2){
            return arr;
        }
        int num=arr.length/2;
        int i,j,temp;
        while(num>=1){
            for(i=num;i<arr.length;i++){
                temp=arr[i];
                j=i-num;
                while(j>=0&&arr[j]>temp){
                    arr[j+num]=arr[j];
                    j-=num;
                }
                arr[j+num]=temp;
            }
            num/=2;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 2, 2};
        int[] ints = sort(arr);
        BaseUtil.printArray(ints);
    }
}
