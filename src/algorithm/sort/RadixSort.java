package sort;

import java.util.Arrays;

/**
 * 基数排序既可以从高位优先进行排序（Most Significant Digit first，简称MSD），
 * 也可以从低位优先进行排序（Least Significant Digit first，简称LSD）
 *
 * 基数排序,基数排序对有负数和0的数列难以进行排序
 * 时间复杂度是O(N+M),N是输入数组的规模，M是元素每一位的值的变化范围，外循环最大字符串的长度可以认为是常数次
 * 空间复杂度也是O(N+M),
 * @author xuyh
 * @date 2019/5/30
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] a = {244, 167, 1234, 321, 29, 98, 1444, 111, 99, 99, 6};
        radixSort(a);
        System.out.println(Arrays.toString(a));
        String[] arr = {"qd", "abc", "qwe", "hhh", "a", "cws", "ope"};
        radixSort1(arr);
        System.out.println(Arrays.toString(arr));
    }

    //适用于正整数排序
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int R = 10;//数字范围0-9，基数为10
        int N = arr.length;
        //辅助数组，存放每一位的数0-9
        int[] aux = new int[N];
        int[] count = new int[R + 1];//长度加1，使count[0]始终为0
        // 以关键字来排序的轮数，由位数最多的数字决定，其余位数少的数字在比较高位时，自动用0进行比较
        // 将数字转换成字符串，字符串的长度就是数字的位数，字符串最长的那个数字也拥有最多的位数
        int W = Arrays.stream(arr).map(s -> String.valueOf(s).length()).max().getAsInt();
        for (int d = 0; d < W; d++) {
            //1,计算频率，在需要的数组长度上额外加1
            for (int j = 0; j < N; j++) {
                // 使用加1后的索引，有重复的该位置就自增
                count[digitAt(arr[j], d) + 1]++;
            }
            //2.计算元素的开始索引
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            //3. 元素按照开始索引分类，用到一个和待排数组一样大临时数组存放数据
            for (int i = 0; i < N; i++) {
                // 填充一个数据后，自增，以便相同的数据可以填到下一个空位
                aux[count[digitAt(arr[i], d)]++] = arr[i];
            }
            //4.数据回写
            for (int i = 0; i < N; i++) {
                arr[i] = aux[i];
            }
            //5.重置count[]，以便下一轮统计使用
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
        }
    }

    // 根据d，获取某个值的个位、十位、百位等，
    // d = 0取出个位，d = 1取出十位，以此类推。对于不存在的高位，用0补
    private static int digitAt(int value, int d) {
        return (value / (int) (Math.pow(10, d))) % 10;
    }

    public static final int ASCII_RANGE = 128;

    //适用于英文字符串排序
    public static void radixSort1(String[] arr) {
        int maxLength = Arrays.stream(arr).map(String::length).max(Integer::compareTo).get();
        //辅助数组，存放每一轮排好序的元素。
        String[] bucket = new String[arr.length];
        //从个位开始比较，一直到最高位
        for (int i = maxLength - 1; i >= 0; i--) {
            //计数排序的过程，分成三步：
            //1.创建辅助排序的统计数组，并把待排序的字符对号入座，
            //这里为了代码简洁，直接使用ascii码范围作为数组长度
            //辅助数组，对每一轮所有元素的某一位的值进行排序
            int[] count = new int[ASCII_RANGE];
            for (int j = 0; j < arr.length; j++) {
                count[getCharIndex(arr[j], i)]++;
            }
            //2.累加实现稳定排序
            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }
            //3.倒序遍历原数组，输出到辅助数组
            for (int j = arr.length - 1; j >= 0; j--) {
                int pos = --count[getCharIndex(arr[j], i)];
                bucket[pos] = arr[j];
            }
            //4.拷贝回原数组
            for (int j = 0; j < arr.length; j++) {
                arr[j] = bucket[j];
            }
        }
    }

    private static int getCharIndex(String str, int k) {
        //如果字符串长度小于k,直接返回0，相当于给不存在的位置补0
        if (str.length() < k + 1) {
            return 0;
        }
        return str.charAt(k);
    }
}
