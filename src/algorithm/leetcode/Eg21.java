package leetcode;

import binaryTree.printTree.TreeNode;

/**
 * 合并两个有序链表
 *
 * @author xuyh
 * @date 2019/11/1
 */
public class Eg21 {
    public TreeNode mergeTwoLists(TreeNode n1, TreeNode n2) {
        if (n1 == null) {
            return n2;
        } else if (n2 == null) {
            return n1;
        } else if (n1.val < n2.val) {
            n1.next = mergeTwoLists(n1.next, n2);
            return n1;
        } else {
            n2.next = mergeTwoLists(n2.next, n1);
            return n2;
        }
    }

    public TreeNode mergeTwoLists1(TreeNode l1, TreeNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        TreeNode preHead = new TreeNode(-1);
        TreeNode prev = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;
        return preHead.next;
    }
}
