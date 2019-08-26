package util;

import binaryTree.TreeNode;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        Test test = new Test();
        System.out.println(test.countNodes(head));
        System.out.println(test.getCBTNum(head));
    }

    public int getCBTNum(TreeNode node){
        if(node==null){
            return 0;
        }
        return bs(node,1,mostLeftLevel(node,1));
    }

    private int bs(TreeNode node, int level, int height) {
        if(level==height){
            return 1;
        }
        if(mostLeftLevel(node.right,level+1)==height){
            return (1<<(height-level))+bs(node.right,level+1,height);
        }else{
            return (1<<(height-level-1))+bs(node.left,level+1,height);
        }

    }

    private int mostLeftLevel(TreeNode node, int level) {
        while(node!=null){
            node=node.left;
            level++;
        }
        return level-1;
    }

    public int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) + countNodes(node.right);
        } else {
            return (1 << rightDepth) + countNodes(node.left);
        }
    }

    public int getDepth(TreeNode node) {
        int res = 0;
        while (node != null) {
            node = node.left;
            res++;
        }
        return res;
    }
}

