package dp;

import binaryTree.printTree.TreeNode;

/**
 * 约瑟夫环问题
 * 一些人围成一个圈，分别编号1，2，3...，
 * 从第一个人开始报数，报到第m个人时杀掉，从下一个开始继续报数，到m个人时杀掉，依次类推，求最后存活的人
 *
 * @author: xuyh
 * @create: 2019/10/7
 **/
public class JosephusProblem {
    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.next = new TreeNode(2);
        head1.next.next = new TreeNode(3);
        head1.next.next.next = new TreeNode(4);
        head1.next.next.next.next = new TreeNode(5);
        head1.next.next.next.next.next = head1;
        printCircularList(head1);
        head1 = josephusKill(head1, 3);
        printCircularList(head1);
        TreeNode head2 = new TreeNode(1);
        head2.next = new TreeNode(2);
        head2.next.next = new TreeNode(3);
        head2.next.next.next = new TreeNode(4);
        head2.next.next.next.next = new TreeNode(5);
        head2.next.next.next.next.next = head2;
        printCircularList(head2);
        head2 = josephusKillBest(head2, 3);
        printCircularList(head2);
    }

    public static TreeNode josephusKill(TreeNode node, int m) {
        if (node == null || node.next == null || m < 1) {
            return node;
        }
        //遍历环形链表，找到初始结点的前一个结点
        TreeNode last = node;
        while (last.next != node) {
            last = last.next;
        }
        int count = 0;
        //转圈的过程中只剩一个结点时停止
        while (node != last) {
            //每报一个数count+1，直到m,然后置零，last就是要移除的结点前一个结点
            if (++count == m) {
                last.next = node.next;
                count = 0;
            } else {
                //last在这里代表当前报数的结点，没到m，就一直往后报数
                last = last.next;
            }
            //node记录报数结点的下一个结点
            node = last.next;
        }
        return node;
    }

    public static TreeNode josephusKillBest(TreeNode node, int m) {
        if (node == null || node.next == null || m < 1) {
            return node;
        }
        TreeNode cur = node.next;
        //链表的长度
        int tmp = 1;
        while (cur != node) {
            tmp++;
            cur = cur.next;
        }
        //通过链表的长度和要杀掉的人的编号求出最后存活的人的初始编号
        //然后将头结点更新
        tmp = getLive(tmp, m);
        while (--tmp != 0) {
            node = node.next;
        }
        node.next = node;
        return node;
    }

    /**
     * 约瑟夫环问题通过画图发现，类似周期函数，可以用取模运算进行描述
     * 假设被杀掉的人的编号为s，则s=(m-1)%i+1;
     * 假设旧编号为old,新编号为new,则old=(new-1+s)%i+1，将s带入得old=(new+m-1)%i+1;
     * 这样就找出了新旧编号之间的关系
     * @param i 链表的长度
     * @param m 报数的长度
     * @return
     */
    private static int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

    public static void printCircularList(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.val + " ");
        TreeNode cur = head.next;
        while (cur != head) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.val);
    }
}
