package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在递增排序的数组中，找到和为指定值的两个元素。若存在多对这样的元素，则找到任意一对即可。
 *
 * @author xuyh
 * @date 2019/9/17
 */
public class GetNumbersWithSumInArray {
    public static void main(String[] args) {
        int[] array={1,2,3,4,6,7,8,9,11};
        int sum=10;
        System.out.println(Arrays.toString(getNumbersWithSum(array,sum)));
        System.out.println(getNumbersWithSum1(array,sum));
    }

    //找到一对即可
    public static int[] getNumbersWithSum(int[] array, int sum) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo < hi) {
            int curSum = array[lo] + array[hi];
            if (curSum == sum) {
                return new int[]{array[lo], array[hi]};
            } else if (curSum < sum) {
                lo++;
            } else {
                hi--;
            }
        }
        return null;
    }

    //找到所有
    public static List<List<Integer>> getNumbersWithSum1(int[] array, int sum) {
        List<List<Integer>> res=new ArrayList<>();
        int lo = 0;
        int hi = array.length - 1;
        while (lo < hi) {
            int curSum = array[lo] + array[hi];
            if (curSum == sum) {
                List<Integer> list = new ArrayList<>();
                list.add(array[lo]);
                list.add(array[hi]);
                res.add(list);
                lo++;
                continue;
            } else if (curSum < sum) {
                lo++;
            } else {
                hi--;
            }
        }
        return res;
    }

    /**
     * 输入一个正数sum，打印出所有和为sum的连续正数序列(序列中最少2个数字)。
     * <p>
     * 思路：
     * 1)定义两个指针：第一个指针small指向序列的第一个元素(即最小元素)，第二个指针big指向序列的最后一个元素(即最大元素)。
     * 2)small默认指向1，big默认指向2。
     * 3)若序列的和(small~big之间的数的和)大于sum，则将序列中的最小值去掉，即small向后移动，一直移动到序列的和不大于sum。
     * 4)若序列的和(small~big之间的数的和)小于sum，则将序列中的最大值增大，即big向后移动，一直移动到序列的和不小于sum。
     * 5)若序列的和(small~big之间的数的和)等于sum，则将序列打印出来，然后将big向后移动，继续寻找其它和为sum的序列。(big向后移动后，序列的和就大于sum了，故此时又进入到第3步)
     * 6)序列中最少有两个数字且和为sum，故序列的第一个元素一定小于(sum+1)/2，所以当small不小于(sum+1)/2时，所有和为sum的序列都已经被打印过了。
     *
     * @param sum
     */
    public static void printContinuousNumbersWithSum(int sum) {
        if (sum < 3) return;
        int small = 1;
        int big = 2;
        // 序列中最少有两个数字且和为sum，故序列的第一个元素一定小于(sum+1)/2
        int half = (sum + 1) / 2;
        int currentSum = small + big;
        while (small < half) {
            if (currentSum == sum) {
                printContinuousNumbers(small, big);
            } else {
                // currentSum > sum     将small向后移动，直到currentSum不大于sum
                while (currentSum > sum && small < half) {
                    currentSum = currentSum - small;
                    small++;
                    if (currentSum == sum) printContinuousNumbers(small, big);
                }
            }
            /**
             * 两种情况会执行下面代码：
             *  1)currentSum < sum  将big向后移动，直到currentSum不小于sum
             *  2)currentSum == sum 且 已经将序列打印了，故big继续向后移动，继续寻找和为sum的序列。
             */
            big++;
            currentSum = currentSum + big;
        }
    }

    public static void printContinuousNumbers(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(i);
        }
        System.out.println();
    }
}
