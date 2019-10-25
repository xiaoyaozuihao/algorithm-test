package leetcode;

import binaryTree.printTree.TreeNode;
import util.BaseUtil;

/**
 * 链表两数相加
 * @author xuyh
 * @date 2019/5/22
 */
public class Eg2 {
    //迭代版本
    public static TreeNode addTwoNumbers(TreeNode l1, TreeNode l2) {
        TreeNode dummyNode = new TreeNode(0);
        TreeNode p = l1, q = l2, cur = dummyNode;
        int carry = 0;
        while (p != null || q != null) {
            int x = 0, y = 0;
            if (p != null) {
                x = p.val;
                p = p.next;
            }
            if (q != null) {
                y = q.val;
                q = q.next;
            }
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new TreeNode(sum % 10);
            cur = cur.next;
        }
        if (carry > 0) {
            cur.next = new TreeNode(carry);
        }
        return dummyNode.next;
    }

    public static TreeNode addTwoNumbers1(TreeNode l1, TreeNode l2) {
        TreeNode dummyNode = new TreeNode(0);
        dfs(dummyNode, l1, l2, 0);
        return dummyNode.next;
    }

    private static void dfs(TreeNode cur, TreeNode l1, TreeNode l2, int carry) {
        if (l1 == null && l2 == null) {
            if (carry > 0) {
                cur.next = new TreeNode(carry);
            }
            return;
        }
        if (l1 != null) {
            carry += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
        }
        cur.next = new TreeNode(carry % 10);
        dfs(cur.next, l1, l2, carry / 10);
    }

    public static TreeNode addTwoNumber2(TreeNode l1, TreeNode l2) {
        return dfs(l1, l2, 0);
    }

    public static TreeNode dfs(TreeNode l1, TreeNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
        TreeNode list = new TreeNode(sum % 10);
        list.next = dfs((l1 != null ? l1.next : null), (l2 != null ? l2.next : null), sum / 10);
        return list;
    }

    public static void main(String[] args) {
        TreeNode l1 = new TreeNode(6);
        l1.next = new TreeNode(2);
        l1.next.next = new TreeNode(1);
        TreeNode l2 = new TreeNode(8);
        l2.next = new TreeNode(5);
        l2.next.next = new TreeNode(2);
        TreeNode treeNode = addTwoNumbers(l1, l2);
        BaseUtil.printLinkedList(treeNode);
        TreeNode treeNode1 = addTwoNumbers1(l1, l2);
        BaseUtil.printLinkedList(treeNode1);
        TreeNode dfs = addTwoNumber2(l1, l2);
        BaseUtil.printLinkedList(dfs);
    }
}
