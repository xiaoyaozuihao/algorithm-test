/**
 * 归并排序
 *
 * @author xuyh
 * @date 2019/4/2
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        sortProcess(arr, 0, arr.length - 1);
    }

    private static void sortProcess(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = (L + R) >> 1;
        sortProcess(arr, L, mid);
        sortProcess(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1<=mid){
            help[i++]=arr[p1++];
        }
        while(p2<=r){
            help[i++]=arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l+j]=help[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 6, 7, 3, 5};
        mergeSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
