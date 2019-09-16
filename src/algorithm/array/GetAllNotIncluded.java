package array;

import util.BaseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuyh
 * @description: 找出所有在B数组但不在A数组中的元素, A数组升序
 * @date 2019/9/16
 */
public class GetAllNotIncluded {
    public static List<Integer> getAllNotIncluded(int[] A, int[] B) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            int lo = 0;
            int hi = A.length - 1;
            boolean contains = false;
            while (lo <= hi) {
                int mid = lo + ((hi - lo) >> 1);
                if (A[mid] == B[i]) {
                    contains = true;
                    break;
                }
                if (A[mid] > B[i]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            if (!contains) {
                res.add(B[i]);
            }
        }
        return res;
    }

    public static List<Integer> comparator(int[] A, int[] B) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < B.length; i++) {
            boolean contains = false;
            for (int j = 0; j < A.length; j++) {
                if (A[j] == B[i]) {
                    contains = true;
                    continue;
                }
            }
            if (!contains) {
                res.add(B[i]);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int testTime = 30000;
        int sortedArrayMaxSize = 300;
        int unsortedArrayMaxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] A = BaseUtil.generateRandomArray(sortedArrayMaxSize, maxValue);
            int[] B = BaseUtil.generateRandomArray(unsortedArrayMaxSize, maxValue);
            Arrays.sort(A);
            List<Integer> res1 = getAllNotIncluded(A, B);
            List<Integer> res2 = comparator(A, B);
            if (!BaseUtil.isEqual(res1, res2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
