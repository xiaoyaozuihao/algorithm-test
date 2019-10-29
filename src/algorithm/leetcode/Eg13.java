package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 *
 * @author xuyh
 * @date 2019/10/29
 */
public class Eg13 {
    public static void main(String[] args) {
        String str = "III";
        Eg13 eg13 = new Eg13();
        System.out.println(eg13.romanToInt(str));
    }

    public int romanToInt0(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int m, n, ret = 0;
        for (int i = 0; i < s.length(); i++) {
            m = map.get(s.charAt(i));
            if (i < s.length() - 1) {
                n = map.get(s.charAt(i + 1));
                if (m < n) {
                    ret -= m;
                } else {
                    ret += m;
                }
            }else{
                ret += m;
            }
        }
        return ret;
    }

    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
        int i = 0;
        int res = 0;
        while (i < s.length()) {
            if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                res += map.get(s.substring(i, i + 2));
                i += 2;
            } else {
                res += map.get(s.substring(i, i + 1));
            }
        }
        return res;
    }

    //leetCode速度最快
    public int romanToInt1(String s) {
        char[] chars = s.toCharArray();
        int ret = 0;
        for (int i = 0, length = chars.length; i < length; i++) {
            char c = chars[i];
            switch (c) {
                case 'M':
                    ret += 1000;
                    break;
                case 'D':
                    ret += 500;
                    break;
                case 'C':
                    if (i < length - 1) {
                        if (chars[i + 1] == 'M') {
                            ret += 900;
                            i++;
                            break;
                        } else if (chars[i + 1] == 'D') {
                            ret += 400;
                            i++;
                            break;
                        }
                    }
                    ret += 100;
                    break;
                case 'L':
                    ret += 50;
                    break;
                case 'X':
                    if (i < length - 1) {
                        if (chars[i + 1] == 'C') {
                            ret += 90;
                            i++;
                            break;
                        } else if (chars[i + 1] == 'L') {
                            ret += 40;
                            i++;
                            break;
                        }
                    }
                    ret += 10;
                    break;
                case 'V':
                    ret += 5;
                    break;
                default:
                    // I
                    if (i < length - 1) {
                        if (chars[i + 1] == 'X') {
                            ret += 9;
                            i++;
                            break;
                        } else if (chars[i + 1] == 'V') {
                            ret += 4;
                            i++;
                            break;
                        }
                    }
                    ret += 1;
                    break;
            }
        }
        return ret;
    }
}
