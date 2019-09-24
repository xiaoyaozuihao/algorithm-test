package binaryTree;

import binaryTree.printTree.TreeNode;
import binaryTree.printTree.utils.BinaryTreesUtil;

/**
 * @author: xuyh
 * @create: 2019/9/24
 **/
public class MaxDistanceInTree {
    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(2);
        head1.right = new TreeNode(3);
        head1.left.left = new TreeNode(4);
        head1.left.right = new TreeNode(5);
        head1.right.left = new TreeNode(6);
        head1.right.right = new TreeNode(7);
        head1.left.left.left = new TreeNode(8);
        head1.right.left.right = new TreeNode(9);
        BinaryTreesUtil.println(head1);
        System.out.println(maxDistance(head1));
        System.out.println(maxDistance1(head1));
        System.out.println("-----------------------");
        TreeNode head2 = new TreeNode(1);
        head2.left = new TreeNode(2);
        head2.right = new TreeNode(3);
        head2.right.left = new TreeNode(4);
        head2.right.right = new TreeNode(5);
        head2.right.left.left = new TreeNode(6);
        head2.right.right.right = new TreeNode(7);
        head2.right.left.left.left = new TreeNode(8);
        head2.right.right.right.right = new TreeNode(9);
        BinaryTreesUtil.println(head2);
        System.out.println(maxDistance(head2));
        System.out.println(maxDistance1(head2));

    }

    public static class ReturnData {
        private int maxDistance;
        private int h;

        public ReturnData(int maxDistance, int h) {
            this.maxDistance = maxDistance;
            this.h = h;
        }
    }

    public static int maxDistance(TreeNode node) {
        return process(node).maxDistance;
    }

    public static ReturnData process(TreeNode node) {
        if (node == null) {
            return new ReturnData(0, 0);
        }
        ReturnData left = process(node.left);
        ReturnData right = process(node.right);
        int includeSelfDistance = left.h + right.h + 1;
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), includeSelfDistance);
        return new ReturnData(maxDistance, Math.max(left.h, right.h) + 1);
    }

    public static int maxDistance1(TreeNode node) {
        int[] records = new int[1];
        return process1(node, records);
    }

    public static int process1(TreeNode node, int[] records) {
        if (node == null) {
            records[0] = 0;
            return 0;
        }
        int left = process1(node.left, records);
        int maxLeft = records[0];
        int right = process1(node.right, records);
        int maxRight = records[0];
        records[0] = Math.max(maxLeft, maxRight) + 1;
        int includeSelf = maxLeft + maxRight + 1;
        int maxDistance = Math.max(Math.max(left, right), includeSelf);
        return maxDistance;
    }
}
