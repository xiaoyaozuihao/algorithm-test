package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 电话号码的字母组合
 *
 * @author xuyh
 * @date 2019/10/31
 */
public class Eg17 {
    public static void main(String[] args) {
        Eg17 eg17 = new Eg17();
        System.out.println(eg17.letterCombinations("234"));
        System.out.println(eg17.letterCombinations0("234"));
        System.out.println(eg17.letterCombinations01("234"));
    }

    //时间复杂度：O(3^N*4^M), 其中N是输入数字中对应3个字母的数目（比方说 2，3，4，5，6，8），
    //M是输入数字中对应 4 个字母的数目（比方说 7，9），N+M 是输入数字的总数。
    //空间复杂度同理
    public List<String> letterCombinations0(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) {
            return res;
        }
        String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTrace("", digits, res, map);
        return res;
    }

    public void backTrace(String combination, String digits, List<String> res, String[] map) {
        //当没有数字时，结束递归
        if (digits.isEmpty()) {
            res.add(combination);
            return;
        }
        //每次截取一个数字去进行组合
        String letters = map[digits.charAt(0) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            //剩下的数字继续递归组合
            backTrace(combination + letters.charAt(i), digits.substring(1), res, map);
        }
    }

    //另一种写法
    public List<String> letterCombinations01(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTrace("", digits, 0, res, map);
        return res;
    }

    private void backTrace(String tmp, String digits, int index, List<String> res, String[] map) {
        if (index == digits.length()) {
            res.add(tmp);
            return;
        }
        String letters = map[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            backTrace(tmp + letters.charAt(i), digits, index + 1, res, map);
        }
    }

    //队列解法，非常巧妙
    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits.isEmpty()) {
            return res;
        }
        String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (res.peek().length() == i) {
                String s = res.remove();
                for (char c : mapping[x].toCharArray()) {
                    res.add(s + c);
                }
            }
        }
        return res;
    }
}
