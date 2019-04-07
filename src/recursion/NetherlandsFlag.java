/**
 * 荷兰国旗问题，给定一个数和一个数组，小于该数的放数组左边，等于放中间，大于放右边
 * @author xuyh
 * @date 2019/4/6
 */
public class NetherlandsFlag {
    public static int[] partition(int[] arr,int l,int r,int p){
        int less=l-1;//定义小于区域，为左边界的前一个位置，表示还不存在
        int more=r+1;//定义大于区域，为右边界的后一个位置
        while(l<more){//当l与大于区域重合时，循环结束
            if(arr[l]<p){//从数组左边界开始循环，如果小于p,就和小于区域的下一个数进行交换，其实第一次就是自己和自己交换，然后l+1;
                BaseUtil.swap(arr,++less,l++);
            }else if(arr[l]>p){//如果大于p,就和大于区域的前一个数进行交换，l不动，继续进行比较
                BaseUtil.swap(arr,--more,l);
            }else{//相等情况不进行操作，l直接往后移动一位
                l++;
            }
        }
        return new int[]{less+1,more-1};//返回的是等于区域的左边界和右边界,如果没有相等区域，返回的值是错误的
    }


    public static void main(String[] args) {
        int[] arr = BaseUtil.generateRandomArray(10, 20);
        BaseUtil.printArray(arr);
        int[] partition = partition(arr, 0, arr.length - 1, 4);
        BaseUtil.printArray(arr);
        System.out.println(partition[0]);
        System.out.println(partition[1]);
    }

}
