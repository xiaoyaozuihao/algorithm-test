package leetcode;

/**
 * 字符串转整数
 *
 * @author xuyh
 * @date 2019/10/28
 */
public class Eg8 {
    public static void main(String[] args) {
        Eg8 eg8 = new Eg8();
        System.out.println(eg8.myAtoi("-2147483649"));
        System.out.println(eg8.myAtoi1("-2147483649"));
        System.out.println(eg8.myAtoi2("-2147483649"));
    }

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        int sign = 1;
        int start = 0;
        if (start < str.length() && str.charAt(start) == '+') {
            start++;
        } else if (start < str.length() && str.charAt(start) == '-') {
            start++;
            sign = -1;
        } else {
            return 0;
        }
        int res = 0;
        char firstChar = str.charAt(0);
        if (firstChar == '+') {
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return res * sign;
            }
            int cur = str.charAt(i) - '0';
            int temp = res * sign;
            if (temp > Integer.MAX_VALUE / 10 || (temp == Integer.MAX_VALUE / 10 && cur > 7)) {
                return Integer.MAX_VALUE;
            }
            if (temp < Integer.MIN_VALUE / 10 || (temp == Integer.MIN_VALUE / 10 && cur > 8)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + cur;
        }
        return res * sign;
    }

    //直接在一次循环中找到每个条件应该做的事情
    public int myAtoi1(String str) {
        int flag = 1;
        boolean start = false;
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            if (!start) {
                if (str.charAt(i) == ' ') {
                    continue;
                } else if (str.charAt(i) == '+') {
                    flag = 1;
                } else if (str.charAt(i) == '-') {
                    flag = -1;
                } else if (Character.isDigit(str.charAt(i))) {
                    res = res * 10 + str.charAt(i) - '0';
                } else {
                    break;
                }
                start = true;
            } else {
                if (!Character.isDigit(str.charAt(i))) {
                    return flag * res;
                }
                int temp = Integer.MAX_VALUE / 10;
                if (flag == 1 && (res > temp || (res == temp && str.charAt(i) > 7))) {
                    return Integer.MAX_VALUE;
                }
                if (flag == -1 && (res > temp || (res == temp && str.charAt(i) > 8))) {
                    return Integer.MIN_VALUE;
                }
                res = res * 10 + str.charAt(i) - '0';
            }
        }
        return flag * res;
    }

    //跳过空格，遇到+或-则匹配开始，然后判断是否是数字，是数字就判断是否越界，不越界继续，否则结束
    public int myAtoi2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int start = 0;
        while (start < str.length() && str.charAt(start) == ' ') {
            start++;
        }
        int sign = 1;
        if (start < str.length() && str.charAt(start) == '+') {
            start++;
        } else if (start < str.length() && str.charAt(start) == '-') {
            start++;
            sign = -1;
        }
        int res = 0;
        while (start < str.length()) {
            if (str.charAt(start) >= '0' && str.charAt(start) <= '9') {
                int temp = Integer.MAX_VALUE / 10;
                if (sign == 1) {
                    if (res > temp || (res == temp && str.charAt(start) > 7)) {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    if (res > temp || (res == temp && str.charAt(start) > 8)) {
                        return Integer.MIN_VALUE;
                    }
                }
                res = res * 10 + str.charAt(start) - '0';
            } else {
                break;
            }
            start++;
        }
        return sign * res;
    }

    public int myAtoi3(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int sign = 1;
        int res = 0;
        boolean start = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && !start) {
                continue;
            }
            if (str.charAt(i) == '+' && !start) {
                start = true;
                continue;
            }
            if (str.charAt(i) == '-' && !start) {
                sign = -1;
                start = true;
                continue;
            }
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                int pop = str.charAt(i) - '0';
                int temp = res * sign;
                if (temp < Integer.MIN_VALUE / 10 || (temp == Integer.MIN_VALUE / 10 && pop * sign > 7)) {
                    return Integer.MIN_VALUE;
                }
                if (temp > Integer.MAX_VALUE / 10 || (temp == Integer.MAX_VALUE / 10 && pop * sign < -8)) {
                    return Integer.MAX_VALUE;
                }
                res = res * 10 + pop;
            } else {
                return res * sign;
            }
        }
        return res * sign;
    }
}
