/**
 * 逆序对问题
 * 设 A 为一个有 n 个数字的有序集 (n>1)，其中所有数字各不相同。
 * 如果存在正整数 i, j 使得 1 ≤ i < j ≤ n 而且 A[i] > A[j]，
 * 则 <A[i], A[j]> 这个有序对称为 A 的一个逆序对，也称作逆序数。
 * @author xuyh
 * @date 2019/4/6
 */
public class InversePairs {
    private static int[] help;//算法需要的额外空间
    private static int count;//定义逆序对的数量

    public static void inversePairs(int[] arr){
        if(arr==null||arr.length<2){
            return ;
        }
        help=new int[arr.length];
        mergeSort(arr,0,arr.length-1);
    }
    public static void mergeSort(int[] arr,int l,int r){
        if(l==r){
            return;
        }
        int mid=l+((r-l)>>1);
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int i=0;
        int p1=l;
        int p2=mid+1;
        while (p1<=mid&&p2<=r){
            if(arr[p1]>arr[p2]){//说明左边的元素都大于右边的元素，形成逆序对
                for(int j=p1;j<=mid;j++){//左边最小的都大于右边，所以左边到mid为止的数都大于右边，循环打印所有结果
                    System.out.println("<"+arr[j]+","+arr[p2]+">");
                }
                count+=(mid-p1+1);
                help[i++]=arr[p2++];
            }else{
                help[i++]=arr[p1++];
            }
        }
        while(p1<=mid){
            help[i++]=arr[p1++];
        }
        while (p2<=r){
            help[i++]=arr[p2++];
        }
        for(i=0;i<r-l+1;i++){
            arr[l+i]=help[i];
        }
    }

    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        while (sc.hasNext()){
//            String[] split = sc.nextLine().split(",");
//            int[] num=new int[split.length];
//            for(int i=0;i<split.length;i++){
//                num[i]=Integer.parseInt(split[i]);
//            }
//            inversePairs(num);
//            System.out.println(count);
//        }
//        sc.close();
//        int[] array = BaseUtil.generateRandomArray(10, 10);
        int[] array=new int[]{1,-1,10,4,-6,1};
        BaseUtil.printArray(array);
        inversePairs(array);
        BaseUtil.printArray(array);
    }
}
