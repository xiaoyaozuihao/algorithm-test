package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 列出所有组合
 *
 * @author: xuyh
 * @create: 2020/5/5
 **/
public class Combinations {
    public static void combinations(List<Integer> selected, List<Integer> list, int num) {
        if (num == 0) {
            for (Integer i : selected) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            return;
        }
        if (list == null || list.size() == 0) {
            return;
        }
        List subList = list.subList(1, list.size());
        selected.add(list.get(0));
        combinations(selected, subList, num - 1);
        selected.remove(selected.size() - 1);
        combinations(selected, subList, num);
    }

    public static void main(String[] args) {
        combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 2);
        System.out.println("-----------------------");
        combinations(new ArrayList<>(), Arrays.asList(), 2);
        System.out.println("-----------------------");
        combinations(new ArrayList<>(), Arrays.asList(), 0);
        System.out.println("-----------------------");
        combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 0);
        System.out.println("-----------------------");
        combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4, 6, 7, 9), 3);
    }
}
