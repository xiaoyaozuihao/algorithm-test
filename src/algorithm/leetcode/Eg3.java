package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * @author xuyh
 * @date 2019/10/10
 */
public class Eg3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("adfdfsgabc"));
        System.out.println(lengthOfLongestSubstring1("adfdfsgabc"));
        System.out.println(lengthOfLongestSubstring2("adfdfsgabc"));
        System.out.println(lengthOfLongestSubstringBest("adfdfsgabc"));
    }

    //暴力法，双循环判断每个子串是否有重复元素
    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (allUnique(i, j, s)) {
                    res = Math.max(res, j - i);
                }
            }
        }
        return res;
    }

    private static boolean allUnique(int i, int j, String s) {
        HashSet<Character> set = new HashSet<>();
        for (int start = i; start < j; start++) {
            if (set.contains(s.charAt(start))) {
                return false;
            }
            set.add(s.charAt(start));
        }
        return true;
    }

    public static int lengthOfLongestSubstring1(String s) {
        Set<Character> set = new HashSet<>();
        int res = 0, i = 0, j = 0;
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                res = Math.max(res, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int res = 0, i = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            Integer index = map.get(s.charAt(j));
            if (index != null) {
                i = Math.max(index, i);
            }
            res = Math.max(res, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return res;
    }

    public static int lengthOfLongestSubstring3(String s) {
        int[] arr = new int[256];
        int res = 0, i = 0;
        for (int j = 0; j < s.length(); j++) {
            i = Math.max(i, arr[s.charAt(j)]);
            res = Math.max(res, j - i + 1);
            arr[s.charAt(j)] = j + 1;
        }
        return res;
    }

    //leetCode最快
    public static int lengthOfLongestSubstringBest(String s) {
        int i = 0, j, k, max = 0;
        char[] A = s.toCharArray();
        for (j = 0; j < s.length(); j++) {
            for (k = i; k < j; k++) {
                if (A[k] == A[j]) {
                    i = k + 1;
                    break;
                }
            }
            if (j - i + 1 > max)
                max = j - i + 1;
        }
        return max;
    }
}
