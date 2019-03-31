/**
 * 测试排序
 * @author xuyh
 * @date 2019/3/31
 */
public class TestSort {
    public static void main(String[] args) {
        int[] arr={2,1,5,7,3,4};
        BubbleSort.bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i+",");
        }
    }
}
