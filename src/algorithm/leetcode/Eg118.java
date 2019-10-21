package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 *
 * @author xuyh
 * @date 2019/10/21
 */
public class Eg118 {
    public static void main(String[] args) {
        int numRows=5;
        Eg118 eg118=new Eg118();
        System.out.println(eg118.generate(numRows));
        System.out.println(eg118.generate1(numRows));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> last = null;
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> row = new ArrayList<>(i + 1);
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(last.get(j - 1) + last.get(j));
            }
            if (i != 0) {
                row.add(1);
            }
            last = row;
            res.add(row);
        }
        return res;
    }

    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    List<Integer> last = res.get(i - 1);
                    row.add(last.get(j - 1) + last.get(j));
                }
            }
            res.add(row);
        }
        return res;
    }
}
