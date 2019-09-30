package queue;

import java.util.LinkedList;

/**
 * 计算字符串表达式的值，由加减乘除符号，括号和数字组成
 *
 * @author xuyh
 * @date 2019/9/30
 */
public class ExpressionCompute {
    public static void main(String[] args) {
        String str = "23*3+7/9+(2-1)*2";
        System.out.println(getValue(str));
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));
        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));
        exp = "10-5*3";
        System.out.println(getValue(exp));
        exp = "-3*4";
        System.out.println(getValue(exp));
        exp = "3+1*4";
        System.out.println(getValue(exp));
    }

    public static int getValue(String str) {
        return compute(str.toCharArray(), 0)[0];
    }

    private static int[] compute(char[] str, int i) {
        LinkedList<String> list = new LinkedList<>();
        int[] res;
        int tmp = 0;
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                tmp = tmp * 10 + str[i++] - '0';
            } else if (str[i] != '(') {
                addNum(list, tmp);
                list.addLast(String.valueOf(str[i++]));
                tmp = 0;
            } else {
                res = compute(str, i + 1);
                tmp = res[0];
                i = res[1] + 1;
            }
        }
        addNum(list, tmp);
        return new int[]{getNum(list), i};
    }

    private static int getNum(LinkedList<String> list) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!list.isEmpty()) {
            cur = list.pollFirst();
            if (cur.equals("-")) {
                add = false;
            } else if (cur.equals("+")) {
                add = true;
            } else {
                num = Integer.parseInt(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }

    private static void addNum(LinkedList<String> list, int tmp) {
        if (!list.isEmpty()) {
            int cur = 0;
            String top = list.pollLast();
            if (top.equals("+") || top.equals("-")) {
                list.addLast(top);
            } else {
                cur = Integer.parseInt(list.pollLast());
                tmp = top.equals("*") ? (cur * tmp) : (cur / tmp);
            }
        }
        list.addLast(String.valueOf(tmp));
    }
}
