package leetcode;

import binaryTree.printTree.TreeNode;
import util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除链表倒数第n个结点
 *
 * @author xuyh
 * @date 2019/11/1
 */
public class Eg19 {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.next = new TreeNode(2);
        head.next.next = new TreeNode(3);
        head.next.next.next = new TreeNode(4);
        head.next.next.next.next = new TreeNode(5);
        BaseUtil.printLinkedList(head);
        int n = 2;
        Eg19 eg19 = new Eg19();
        TreeNode node = eg19.removeNthFromEnd(head, n);
        BaseUtil.printLinkedList(node);
        TreeNode node1 = eg19.removeNthFromEnd1(head, n);
        BaseUtil.printLinkedList(node1);
        TreeNode node2 = new TreeNode(1);
        node2.next = new TreeNode(2);
        TreeNode node3 = eg19.removeNthFromEnd3(node2, n);
        BaseUtil.printLinkedList(node3);
    }

    //求出链表总长度，确定倒数n的位置，需要两次循环，总的遍历次数是2L-n
    public TreeNode removeNthFromEnd(TreeNode head, int n) {
        TreeNode dummy = new TreeNode(0);
        dummy.next = head;
        int len = 0;
        TreeNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        len -= n;
        node = dummy;
        while (len > 0) {
            len--;
            node = node.next;
        }
        node.next = node.next.next;
        return dummy.next;
    }

    //两个指针先固定间隔，然后同时遍历
    public TreeNode removeNthFromEnd1(TreeNode head, int n) {
        TreeNode dummy = new TreeNode(0);
        dummy.next = head;
        int j = 0;
        TreeNode first = head;
        TreeNode second = dummy;
        while (first != null) {
            first = first.next;
            if (++j > n) {
                second = second.next;
            }
        }
        second.next = second.next.next;
        return dummy.next;
    }

    // 双指针的另一种写法
    // second看似是顺便遍历，但总的遍历次数还是2L-n
    public TreeNode removeNthFromEnd2(TreeNode head, int n) {
        TreeNode dummy = new TreeNode(0);
        dummy.next = head;
        TreeNode first = dummy;
        TreeNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    //利用数组记录倒数第n个结点，就不需要重复遍历
    public TreeNode removeNthFromEnd3(TreeNode head, int n) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            list.add(node);
            node = node.next;
        }
        if (len == 1) {
            return null;
        }
        int remove = len - n;
        if (remove == 0) {
            return head.next;
        }
        //直接得到，不需要再遍历
        node = list.get(remove - 1);
        node.next = node.next.next;
        return head;
    }
}
