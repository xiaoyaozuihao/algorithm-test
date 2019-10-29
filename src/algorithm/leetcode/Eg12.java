package leetcode;

/**
 * 整数转罗马数字
 *
 * @author xuyh
 * @date 2019/10/29
 */
public class Eg12 {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int a = num / values[i];
            if (a == 0) {
                continue;
            }
            for (int j = a; j > 0; j--) {
                sb.append(strs[i]);
            }
            num -= a * values[i];
            if (num == 0) {
                break;
            }
        }
        return sb.toString();
    }

    public String intToRoman1(int num) {
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] strs = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            while (num >= values[i]) {
                sb.append(strs[i]);
                num -= values[i];
            }
        }

//        while (num > 0) {
//            for (int i = keys.length - 1; i >= 0; i--) {
//                if (num >= keys[i]) {
//                    num -= keys[i];
//                    builder.append(values[i]);
//                    break;
//                }
//            }
//        }
        return sb.toString();
    }
}
