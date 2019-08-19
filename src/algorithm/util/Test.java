package util;

import binaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.next = new TreeNode(2);
        head.next.next = new TreeNode(3);
        head.next.next.next = new TreeNode(4);
        head.next.next.next.next = new TreeNode(5);
        head.next.next.next.next.next = new TreeNode(6);
        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4
        printRandomList(head);
        copyListWithRandom(head);
        printRandomList(head);
    }

    public static TreeNode copyListWithRandom(TreeNode head) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        TreeNode cur = head;
        while (cur != null) {
            map.put(cur, new TreeNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        for (TreeNode treeNode : map.keySet()) {
            map.get(treeNode).next = map.get(treeNode.next);
            map.get(treeNode).random = map.get(treeNode.random);
        }
        return map.get(cur);
    }

    public static void printRandomList(TreeNode head) {
        TreeNode cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
        System.out.print("|random: ");
        cur = head;
        while (cur != null) {
            System.out.print(cur.random==null?"- ":cur.random.val+" ");
            cur = cur.next;
        }
        System.out.println();
    }
}
