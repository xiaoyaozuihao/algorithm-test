package array;

/**
 * 二分查找
 *
 * @author xuyh
 * @date 2019/4/28
 */
public class BinarySearch {
    //循环实现
    public static int binarySearch(int[] arr, int obj) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (arr[mid] == obj) {
                return mid;
            } else if (arr[mid] < obj) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -(start + 1);
    }

    //编程珠玑推荐的二分写法
    public static int binarySearch0(int[] arr, int obj) {
        int left = -1, right = arr.length, mid;
        while (left + 1 != right) {
            mid = left + (right - left) / 2;
            if (arr[mid] < obj) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (right >= arr.length || arr[right] != obj) {
            right = -1;
        }
        return right;
    }

    //递归实现
    private static int binarySearch1(int[] arr, int obj, int low, int high) {
        if (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] == obj) {
                return mid;
            } else if (arr[mid] < obj) {
                return binarySearch1(arr, obj, mid + 1, high);
            } else {
                return binarySearch1(arr, obj, low, mid - 1);
            }
        }
        return -1;
    }

    //单调不减序列,找第一次出现的位置
    //为什么 while 里的条件是 <，而不是 <=。一方面是我们想在循环外部判断最终的 left 位置是否是目标值，
    // 另一方面是如果循环条件允许 left = right，那么最后 mid = left = right，如果该处正好是目标值，
    // 那么 right 将始终等于 mid，不会再左移，就会陷入死循环。
    public static int binarySearchFirst(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left < right) {
            //取上中位数，mid会偏向左边界，right=mid可以确保右边界往左移动
            mid = left + (right - left) / 2;
            if (arr[mid] < target) left = mid + 1;
            else right = mid;
        }
        if (arr[left] == target) return left;
        else return -1;
    }

    //单调不减序列,找最后一次出现的位置
    public static int binarySearchLast(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left < right) {
            //加1操作取下中位数，mid会偏向右边界，因此 left = mid 可以确保左边界往右移动，缩小查找范围
            mid = left + (right - left + 1) / 2;
            if (arr[mid] > target) right = mid - 1;
            else left = mid;
        }
        if (arr[left] == target) return left;
        else return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 5, 6, 7, 8, 20, 43, 54};
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch1(arr, 20, 0, arr.length - 1));
    }
}
