package linkedList;

import binaryTree.TreeNode;

import java.util.Stack;

/**
 * 判断是否是回文链表
 *
 * @author xuyh
 * @date 2019/4/30
 */
public class IsPalindromeList {
    //all push stack,need n extra space
    public static boolean isPalindromeList(TreeNode head) {
        Stack<Integer> stack = new Stack<>();
        TreeNode temp = head;
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //half push stack ,need n/2 extra space
    public static boolean isPalindromeList1(TreeNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //这种写法不管链表是奇数还是偶数，都能保证慢指针走到中点的下一个节点。节省遍历时间
        TreeNode slow = head.next;
        TreeNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Integer> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow.val);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //need O(1) extra space;
    public static boolean isPalindromeList2(TreeNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        TreeNode slow = head;
        TreeNode fast = head;
        //保证无论链表是奇数还是偶数，slow最终在中点或中点前一个位置
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //将链表的后半部分反转，slow最终到达链表结尾
        TreeNode next = slow.next;
        slow.next = null;
        TreeNode cur;
        while (next != null) {
            cur = next.next;
            next.next = slow;
            slow = next;
            next = cur;
        }
        //上面的循环cur,next指针用完，重复利用，保存头结点和尾节点
        cur = head;
        next = slow;
        boolean res = true;
        while (cur != null) {
            if (cur.val != next.val) {
                res = false;
                break;
            }
            cur = cur.next;
            next = next.next;
        }
        //还原链表,上一轮循环next,cur用完，这次重复利用
        next = slow.next;
        slow.next = null;
        while (next != null) {
            cur = next.next;
            next.next = slow;
            slow = next;
            next = cur;
        }
        return res;
    }

    //在寻找中点的同时反转前半部分，效率更优
    public static boolean isPalindromeList3(TreeNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        TreeNode slow = head, fast = head.next, pre = null, prepre = null;
        while (fast != null && fast.next != null) {
            //反转前半段链表
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            //先移动指针再来反转
            pre.next = prepre;
            prepre = pre;
        }
        TreeNode p2 = slow.next;
        slow.next = pre;
        TreeNode p1 = fast == null ? slow.next : slow;
        while (p1 != null) {
            if (p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    //其他解法
    public static boolean isPalindromeList4(TreeNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        TreeNode fast = head.next;
        TreeNode slow = head;
        while (fast.next != null) {
            while (fast.next.next != null) {
                fast = fast.next;
            }
            // 不停的从slow的后一个开始遍历，知道找到值相同的节点
            // 一次完成后，再移动到原节点的下一个节点开始，继续重复上面的步骤
            if (fast.next.val != slow.val) {
                return false;
            }
            fast.next = null;
            slow = slow.next;
            fast = slow.next;
            if (fast == null || fast.val == slow.val) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPalindromeList5(TreeNode head) {
        if (head == null) {
            return true;
        }
        TreeNode slow = head, fast = head, pre = null, temp = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            temp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = temp;
        }
        fast = fast == null ? slow : slow.next;
        slow = pre;
        while (fast != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.next = new TreeNode(1);
        head.next.next = new TreeNode(1);
        head.next.next.next = new TreeNode(1);
        head.next.next.next.next = new TreeNode(1);
//        head.next.next = new TreeNode(2);
//        head.next.next.next = new TreeNode(1);
//        System.out.println(isPalindromeList(head));
        System.out.println(isPalindromeList4(head));
//        System.out.println(isPalindromeList2(head));
//        printNode(head);
    }
}
