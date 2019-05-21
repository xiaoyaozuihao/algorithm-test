package linkedList;

import binaryTree.TreeNode;

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

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }

        public static DoubleNode reverseDoubleNode(DoubleNode head) {
            DoubleNode next;
            DoubleNode pre = null;
            while (head != null) {
                next = head.next;
                head.next = pre;
                head.last = next;
                pre = head;
                head = next;
            }
            return pre;
        }
    }

    public static void printLinkedList(TreeNode head) {
        System.out.print("Linked list:");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        System.out.print("DoubleLinked list:");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.next;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.last;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode head=new TreeNode(1);
        head.next=new TreeNode(2);
        head.next.next=new TreeNode(3);
        head.next.next.next=new TreeNode(4);
        printLinkedList(head);
        printLinkedList(reverseList(head));
        DoubleNode head1 = new DoubleNode(1);
        head1.next = new DoubleNode(2);
        head1.next.last = head1;
        head1.next.next = new DoubleNode(3);
        head1.next.next.last = head1.next;
        head1.next.next.next = new DoubleNode(4);
        head1.next.next.next.last = head1.next.next;
        printDoubleLinkedList(head1);
        printDoubleLinkedList(DoubleNode.reverseDoubleNode(head1));
    }
}
