package binaryTree;

import binaryTree.printTree.TreeNode;
import binaryTree.printTree.utils.BinaryTreesUtil;

/**
 * 给定一个二叉树，获取这个二叉树中最大的二叉搜索子树
 *
 * @author: xuyh
 * @create: 2019/9/23
 **/
public class BiggestSubBSTInTree {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(6);
        head.left = new TreeNode(1);
        head.left.left = new TreeNode(0);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(12);
        head.right.left = new TreeNode(10);
        head.right.left.left = new TreeNode(4);
        head.right.left.left.left = new TreeNode(2);
        head.right.left.left.right = new TreeNode(5);
        head.right.left.right = new TreeNode(14);
        head.right.left.right.left = new TreeNode(11);
        head.right.left.right.right = new TreeNode(15);
        head.right.right = new TreeNode(13);
        head.right.right.left = new TreeNode(20);
        head.right.right.right = new TreeNode(16);
        BinaryTreesUtil.println(head);
        TreeNode bst = biggestSubBST(head);
        System.out.println("-----------------------");
        BinaryTreesUtil.println(bst);
    }

    public static class ReturnData {
        private int size;
        private TreeNode head;
        private int max;
        private int min;

        public ReturnData(int size, TreeNode head, int max, int min) {
            this.size = size;
            this.head = head;
            this.max = max;
            this.min = min;
        }
    }

    public static TreeNode biggestSubBST(TreeNode node) {
        if (node == null) {
            return null;
        }
        return process(node).head;
    }

    private static ReturnData process(TreeNode head) {
        if (head == null) {
            return new ReturnData(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);
        int max = Math.max(leftData.max, rightData.max);
        int min = Math.min(leftData.min, rightData.min);
        //判断当前结点能不能成为头结点的最大树大小
        if (leftData.head == head.left
                && rightData.head == head.right
                && leftData.max < head.val
                && rightData.min > head.val) {
            int includeSelfSize = leftData.size + 1 + rightData.size;
            return new ReturnData(includeSelfSize, head, Math.max(max, head.val), Math.min(min, head.val));
        }
        int maxSize = Math.max(leftData.size, rightData.size);
        TreeNode maxHead = leftData.size > rightData.size ? leftData.head : rightData.head;
        return new ReturnData(maxSize, maxHead, max, min);
    }

    public static TreeNode biggestSubBST1(TreeNode head) {
        int[] record = new int[3]; // 0->size, 1->min, 2->max
        return posOrder(head, record);
    }

    public static TreeNode posOrder(TreeNode head, int[] record) {
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.val;
        TreeNode left = head.left;
        TreeNode right = head.right;
        TreeNode lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        TreeNode rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        record[1] = Math.min(rMin, Math.min(lMin, value)); // lmin, value, rmin -> min
        record[2] = Math.max(lMax, Math.max(rMax, value)); // lmax, value, rmax -> max
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
    }
}
