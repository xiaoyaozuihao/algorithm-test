package greedy;

/**
 * 汉诺塔问题，打印最优移动路径
 *
 * @author xuyh
 * @date 2019/5/27
 */
public class Hanoi {
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, n, "left", "mid", "right");
        }
    }

    public static void hanoi1(int n) {
        if (n > 0) {
            func(n, "left", "mid", "right");
        }
    }

    /**
     * @param rest 剩余的圆盘
     * @param down 最底下圆盘的编号（从上到下依次是1~n）
     * @param from 初始柱子
     * @param help 辅助柱子
     * @param to   目标柱子
     */
    public static void func(int rest, int down, String from, String help, String to) {
        if (rest == 1) {
            System.out.println("move " + down + " from " + from + " to " + to);
        } else {
            func(rest - 1, down - 1, from, to, help);
            func(1, down, from, help, to);
            func(rest - 1, down - 1, help, from, to);
        }
    }

    /**
     * 另一种递归写法,减少一个入参
     *
     * @param n    总的圆盘数，也表示编号，第n个圆盘编号为n
     * @param from
     * @param help
     * @param to
     */
    public static void func(int n, String from, String help, String to) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            func(n - 1, from, to, help);
            System.out.println("move " + n + " from " + from + " to " + to);
            func(n - 1, help, from, to);
        }
    }

    public static void moveLeftToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to right");
        } else {
            moveLeftToMid(n - 1);
            System.out.println("move " + n + " from left to right");
            moveMidToRight(n - 1);
        }
    }

    private static void moveMidToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to right");
        } else {
            moveMidToLeft(n - 1);
            System.out.println("move " + n + " from mid to right");
            moveLeftToRight(n - 1);
        }
    }

    private static void moveMidToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to left");
        } else {
            moveMidToRight(n - 1);
            System.out.println("move " + n + " from mid to left");
            moveRightToLeft(n - 1);
        }
    }

    private static void moveRightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to left");
        } else {
            moveRightToMid(n - 1);
            System.out.println("move " + n + " from right to left");
            moveMidToLeft(n - 1);
        }
    }

    private static void moveLeftToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to mid");
        } else {
            moveLeftToRight(n - 1);
            System.out.println("move " + n + " from left to mid");
            moveRightToMid(n - 1);
        }
    }

    private static void moveRightToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to mid");
        } else {
            moveRightToLeft(n - 1);
            System.out.println("move " + n + " from right to mid");
            moveLeftToMid(n - 1);
        }
    }

    public static void main(String[] args) {
        hanoi(3);
        System.out.println("-----------------------");
        hanoi1(3);
        System.out.println("-----------------------");
        moveLeftToRight(3);
    }
}
