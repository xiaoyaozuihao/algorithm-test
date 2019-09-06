package binaryTree;

import binaryTree.printTree.TreeNode;

/**
 * 获取二叉树的前驱节点
 * 在二叉树的中序遍历的序列中，node的前一个节点叫作node的前驱节点。
 *
 * @author xuyh
 * @date 2019/5/11
 */
public class PredecessorNode {
    public static TreeNode getPredecessorNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return getRightMost(node.left);
        } else {
            TreeNode parent = node.parent;
            while (parent != null && parent.right != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static TreeNode getRightMost(TreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.parent = null;
        head.left = new TreeNode(2);
        head.left.parent = head;
        head.left.left = new TreeNode(4);
        head.left.left.parent = head.left;
        head.left.right = new TreeNode(5);
        head.left.right.parent = head.left;
        head.right = new TreeNode(3);
        head.right.parent = head;
        head.right.left = new TreeNode(6);
        head.right.left.parent = head.right;
        head.right.right = new TreeNode(7);
        head.right.right.parent = head.right;
        TreeNode test = head;
        System.out.println(test.val + " pre: " + getPredecessorNode(test).val);
        test = head.left;
        System.out.println(test.val + " pre: " + getPredecessorNode(test).val);
        test = head.left.left;
        System.out.println(test.val + " pre: " + getPredecessorNode(test));
        test = head.left.right;
        System.out.println(test.val + " pre: " + getPredecessorNode(test).val);
        test = head.right;
        System.out.println(test.val + " pre: " + getPredecessorNode(test).val);
        test = head.right.left;
        System.out.println(test.val + " pre: " + getPredecessorNode(test).val);
        test = head.right.right;
        System.out.println(test.val + " pre: " + getPredecessorNode(test).val);
    }
}
