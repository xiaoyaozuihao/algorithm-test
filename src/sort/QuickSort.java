/**
 * 快速排序
 * @author xuyh
 * @date 2019/4/7
 */
public class QuickSort {

    public static void quickSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[] arr,int l, int r){
        if(l<r){//这个限制条件是防止partition过程出错
            //经典快排总拿最后一个数进行划分，就和数据状况有关系。
            //随机快排：生成一个子区间长度（r-l+1）的随机数,将它与最右边的数进行交换
            // 可以避免数据状况导致快排时间复杂度增大的问题
            BaseUtil.swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);//partition过程返回等于区域的左右边界
            quickSort(arr,l,p[0]-1);//从l到等于区域的左边界的前一个位置
            quickSort(arr,p[1]+1,r);//从右边界的后一个位置到r
        }
    }
    public static int[] partition(int[] arr,int l,int r){
//        int less=l-1;
//        int more=r+1;
//        int target=arr[r];//与荷兰国旗的问题类似，选定区域的最后一个值就是待比较的数
//        while(l<more){
//            if(arr[l]<target){
//                BaseUtil.swap(arr,l++,++less);
//            }else if(arr[l]>target){
//                BaseUtil.swap(arr,l,--more);
//            }else{
//                l++;
//            }
//        }

        //节约变量写法
        int less=l-1;
        int more=r;//设定大于区域的边界就是r
        while(l<more){
            if(arr[l]<arr[r]){
                BaseUtil.swap(arr,l++,++less);
            }else if(arr[l]>arr[r]){
                BaseUtil.swap(arr,l,--more);
            }else{
                l++;
            }
        }
        BaseUtil.swap(arr,more,r);//将子区间最右侧的值与大于区域的第一个值进行交换
        return new int[]{less+1,more};
    }

    public static void main(String[] args) {
        BaseUtil.testTemplate("QuickSort","quickSort");
    }
}
