package linkedList;

import binaryTree.Node;

import java.util.Stack;

/**
 * 判断是否是回文链表
 *
 * @author xuyh
 * @date 2019/4/30
 */
public class IsPalindromeList {

    //all push stack,need n extra space
    public static boolean isPalindromeList(Node head) {
        Stack<Integer> stack = new Stack<>();
        Node temp = head;
        while (temp != null) {
            stack.push(temp.value);
            temp = temp.next;
        }
        while (head != null) {
            if (head.value != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //half push stack ,need n/2 extra space
    public static boolean isPalindromeList1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head.next;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Integer> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow.value);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //need O(1) extra space;
    public static boolean isPalindromeList2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;//slow pointer
        Node n2 = head;//fast pointer
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;//rightPart first Node
        n1.next = null;
        Node n3;
        while (n2 != null) {//reverse right part
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;// save lastNode
        n2 = head;//save firstNode
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        //after compare,recover right part
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    private static void printNode(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        System.out.println(isPalindromeList(head));
        System.out.println(isPalindromeList1(head));
        System.out.println(isPalindromeList2(head));
        printNode(head);
    }
}
