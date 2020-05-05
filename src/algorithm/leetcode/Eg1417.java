package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 重新格式化字符串
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 *
 * @author: xuyh
 * @create: 2020/4/25
 **/
public class Eg1417 {
    public static String reFormat0(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int digCount = 0, letterCount = 0;
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                digCount++;
            } else {
                letterCount++;
            }
        }
        if (Math.abs(digCount - letterCount) > 1) {
            return "";
        }
        int digStart = 1, letterStart = 1;
        if (digCount > letterCount) {
            digStart = 0;
        } else {
            letterStart = 0;
        }
        char[] res = new char[digCount + letterCount];
        for (int i = 0; i < res.length; i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                res[digStart] = ch;
                digStart += 2;
            } else {
                res[letterStart] = ch;
                letterStart += 2;
            }
        }
        return new String(res);
    }

    public static String reFormat(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        Queue<Character> dig = new LinkedList<>();
        Queue<Character> letter = new LinkedList<>();
        for (char ch : chars) {
            if (ch >= '0' && ch <= '9') {
                dig.add(ch);
            } else {
                letter.add(ch);
            }
        }
        if (Math.abs(dig.size() - letter.size()) > 1) {
            return "";
        }
        if (dig.size() < letter.size()) {
            Queue tmp = dig;
            dig = letter;
            letter = tmp;
        }
        StringBuilder sb = new StringBuilder();
        while (!dig.isEmpty() && !letter.isEmpty()) {
            sb.append(dig.poll()).append(letter.poll());
        }
        if (!dig.isEmpty()) {
            sb.append(dig.poll());
        }
        return sb.toString();
    }
}
