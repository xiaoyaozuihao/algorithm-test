package linkedList;

import binaryTree.DoubleNode;
import binaryTree.TreeNode;
import util.BaseUtil;

/**
 * 反转链表
 *
 * @author xuyh
 * @date 2019/4/25
 */
public class ReverseLinkedList {

    //单链表反转
    public static TreeNode reverseList(TreeNode head) {
        TreeNode pre = null;
        TreeNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //单链表反转，递归解法
    public static TreeNode reverseList1(TreeNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        TreeNode cur = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return cur;
    }


    public static DoubleNode reverseDoubleNode(DoubleNode head) {
        DoubleNode next;
        DoubleNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }



    public static void main(String[] args) {
        TreeNode head=new TreeNode(1);
        head.next=new TreeNode(2);
        head.next.next=new TreeNode(3);
        head.next.next.next=new TreeNode(4);
        BaseUtil.printLinkedList(head);
        BaseUtil.printLinkedList(reverseList(head));
        DoubleNode head1 = new DoubleNode(1);
        head1.next = new DoubleNode(2);
        head1.next.pre = head1;
        head1.next.next = new DoubleNode(3);
        head1.next.next.pre = head1.next;
        head1.next.next.next = new DoubleNode(4);
        head1.next.next.next.pre = head1.next.next;
        BaseUtil.printDoubleLinkedList(head1);
        BaseUtil.printDoubleLinkedList(reverseDoubleNode(head1));
    }
}
