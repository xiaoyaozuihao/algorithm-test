package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * @author xuyh
 * @date 2019/10/21
 */
public class Eg119 {
    public static void main(String[] args) {
        Eg119 eg119 = new Eg119();
        System.out.println(eg119.getRow(3));
        System.out.println(eg119.getRow1(3));
        System.out.println(eg119.getRow2(3));
        System.out.println(eg119.getRow3(3));
        System.out.println(eg119.getRow4(3));
        System.out.println(eg119.getRow5(3));
    }

    //简洁写法
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; ++i) {
            res.add(1);
            for (int j = i - 1; j > 0; --j) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }

    //一层层的求，保存上一层的结果
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j) + pre.get(j - 1));
                }
            }
            pre = cur;
        }
        return cur;
    }

    //优化pre集合，用变量进行记录
    public List<Integer> getRow2(int rowIndex) {
        int pre = 1;
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                int temp = cur.get(j);
                cur.set(j, pre + cur.get(j));
                pre = temp;
            }
            cur.add(1);
        }
        return cur;
    }

    //逆序记录，不需要临时变量，接近简洁写法
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> cur = new ArrayList<>();
        cur.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                cur.set(j, cur.get(j - 1) + cur.get(j));
            }
            cur.add(1);
        }
        return cur;
    }

    //公式法，杨辉三角可以看做是由组合数构成的
    //C(k,n)=n!/(k!(n−k)!)=(n∗(n−1)∗(n−2)∗...(n−k+1))/k!
    public List<Integer> getRow4(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        int n = rowIndex;
        for (int i = 0; i <= n; i++) {
            res.add(combination(n, i));
        }
        return res;
    }

    private Integer combination(int n, int k) {
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - k + i) / i;
        }
        return (int) res;
    }

    //组合公式的优化，注意int溢出问题
    //C(k,n)=C(k-1,n)*(n-k+1)/k;
    public List<Integer> getRow5(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        int N = rowIndex;
        long pre = 1;
        ans.add(1);
        for (int k = 1; k <= N; k++) {
            long cur = pre * (N - k + 1) / k;
            ans.add((int) cur);
            pre = cur;
        }
        return ans;
    }
}
