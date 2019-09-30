package util;

import java.util.LinkedList;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        String str="23*3+7/9+(2-1)*2";
        System.out.println(getValue(str));
    }

    public static int getValue(String str) {
        return compute(str.toCharArray(), 0)[0];
    }

    private static int[] compute(char[] arr, int i) {
        int[] res;
        int tmp = 0;
        LinkedList<String> list = new LinkedList<>();
        while (i < arr.length && arr[i] != ')') {
            if (arr[i] >= '0' && arr[i] <= '9') {
                tmp = tmp * 10 + arr[i++] - '0';
            } else if (arr[i] != '(') {
                addNum(tmp, list);
                list.addLast(String.valueOf(arr[i++]));
                tmp = 0;
            } else {
                res = compute(arr, i + 1);
                tmp = res[0];
                i = res[1] + 1;
            }
        }
        addNum(tmp, list);
        return new int[]{getNum(list), i};
    }

    private static int getNum(LinkedList<String> list) {
        int res = 0;
        boolean add = true;
        while (!list.isEmpty()) {
            String str = list.pollFirst();
            if (str.equals("+")) {
                continue;
            } else if (str.equals("-")) {
                add = false;
            } else {
                int num = Integer.parseInt(str);
                res += add ? num : -num;
            }
        }
        return res;
    }

    private static void addNum(int num, LinkedList<String> list) {
        if (!list.isEmpty()) {
            String cur = list.pollLast();
            if (cur.equals("+") || cur.equals("-")) {
                list.addLast(cur);
            } else {
                int tmp = Integer.parseInt(list.pollLast());
                num = cur.equals("*") ? num * tmp : tmp / num;
            }
        }
        list.addLast(String.valueOf(num));
    }
}