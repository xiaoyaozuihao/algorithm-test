import java.util.Arrays;

/**
 * 测试排序
 * @author xuyh
 * @date 2019/3/31
 */
public class TestSort {
    public static void main(String[] args) {
        int[] arr={2,1,5,7,3,4};
        BubbleSort.bubbleSort(arr);
        printArr(arr);
        SelectSort.selectSort(arr);
        printArr(arr);
        InsertSort.insertSort(arr);
        printArr(arr);
        InsertSort.insertSort1(arr);
        printArr(arr);
        Arrays.sort(arr);

    }
    public static void printArr(int[] arr){
        StringBuilder sb=new StringBuilder();
        sb.append("[ ");
        for (int i : arr) {
            sb.append(i+" ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
