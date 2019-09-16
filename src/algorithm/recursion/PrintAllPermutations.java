package recursion;

import java.util.HashSet;

/**
 * @author xuyh
 * @description: 打印字符串全排列
 * @date 2019/9/16
 */
public class PrintAllPermutations {
    public static void main(String[] args) {
        printAllPermutations("abc");
        System.out.println("----------------------");
        printAllPermutations1("abc");
    }

    public static void printAllPermutations(String str) {
        process(str.toCharArray(), 0);
    }

    private static void process(char[] chars, int i) {
        if (i == chars.length) {
            System.out.println(chars);
            return;
        }
        for (int j = i; j < chars.length; j++) {
            swap(chars, j, i);
            process(chars, i + 1);
            swap(chars, j, i);
        }
    }

    public static void printAllPermutations1(String str) {
        char[] chs = str.toCharArray();
        process1(chs, 0);
    }

    public static void process1(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
        }
        HashSet<Character> set = new HashSet<>();
        for (int j = i; j < chs.length; j++) {
            if (!set.contains(chs[j])) {
                set.add(chs[j]);
                swap(chs, i, j);
                process1(chs, i + 1);
                swap(chs, i, j);
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
