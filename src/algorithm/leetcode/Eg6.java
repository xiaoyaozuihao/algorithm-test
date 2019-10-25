package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串的z字形转换
 *
 * @author xuyh
 * @date 2019/10/25
 */
public class Eg6 {
    public static void main(String[] args) {
        Eg6 eg6=new Eg6();
        System.out.println(eg6.convert0("abcdefgh", 3));
    }
    //将z字形分组找规律
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); i++) {
            int index = i % (2 * numRows - 2);
            index = index < numRows ? index : 2 * numRows - 2 - index;
            sbs[index].append(s.charAt(i));
        }
        for (int i = 1; i < numRows; i++) {
            sbs[0].append(sbs[i]);
        }
        return sbs[0].toString();
    }

    /**
     * 对于所有整数i，
     * 行0中的字符位于索引 i*(2*numRows−2) 处;
     * 行numRows−1中的字符位于索引 i*(2*numRows−2)+ numRows−1 处;
     * 内部的行k中的字符位于索引 i*(2*numRows−2)+k 以及(i+1)(2*numRows−2)−k处;
     * @param s
     * @param numRows
     * @return
     */
    public String convert0(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                res.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    res.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return res.toString();
    }

    //设置标志位，到行首或行尾时进行方向交换
    public String convert1(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean goingDown = false;
        for (Character c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder res = new StringBuilder();
        rows.forEach(row -> res.append(row));
        return res.toString();
    }

    //使用char数组和移位运算，最优解
    public String convertBest(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int step = (numRows - 1) << 1;
        int len = s.length();
        char[] ans = new char[len];
        char[] chars = s.toCharArray();
        int index = -1;
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < len; j += step) {
                ans[++index] = chars[j];
                if (i != 0 && i != numRows - 1 && step - (i << 1) + j < len) {
                    ans[++index] = chars[step - (i << 1) + j];
                }
            }
        }
        return new String(ans);
    }
}
