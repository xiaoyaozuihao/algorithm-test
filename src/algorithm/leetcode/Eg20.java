package leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 校验括号的有效性，包括(),[],{}这三种括号。
 *
 * @author xuyh
 * @date 2019/11/1
 */
public class Eg20 {
    //最高效解法
    public static boolean isValid(String s) {
        char[] stack = new char[s.length() + 1];
        int top = 1;
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack[top++] = c;
            } else if (c == ')' && stack[--top] != '(') {
                return false;
            } else if (c == ']' && stack[--top] != '[') {
                return false;
            } else if (c == '}' && stack[--top] != '{') {
                return false;
            }
        }
        return top == 1;
    }

    //利用map优化代码
    public boolean isValid1(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (map.containsKey(cur)) {
                char top = stack.isEmpty() ? '#' : stack.pop();
                if (top != map.get(cur)) {
                    return false;
                }
            } else {
                stack.push(cur);
            }
        }
        return stack.isEmpty();
    }

    //直接判断
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(cur);
            } else {
                char peek = stack.peek();
                if ((cur == ')' && peek == '(') || (cur == ']' && peek == '[') || (cur == '}' && peek == '{')) {
                    stack.pop();
                } else {
                    stack.push(cur);
                }
            }
        }
        return stack.isEmpty();
    }
}
