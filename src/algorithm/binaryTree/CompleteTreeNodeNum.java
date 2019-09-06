package binaryTree;

import binaryTree.printTree.TreeNode;

/**
 * 完全二叉树的节点数量
 * @author xuyh
 * @date 2019/5/13
 */
public class CompleteTreeNodeNum {

    public static int getCBTNum(TreeNode node){
        if(node==null){
            return 0;
        }
        return bs(node,1,mostLeftLevel(node,1));
    }
    public static int bs(TreeNode node,int level,int height){
        if(level==height){
            return 1;
        }
        if(mostLeftLevel(node.right, level+1)==height){//整个左子树是满二叉树
            return (1<<(height-level))+bs(node.right,level+1,height);
        }else{
            return (1<<(height-level-1))+bs(node.left,level+1,height);
        }
    }

    //最左的子树的高度
    public static int mostLeftLevel(TreeNode node,int level){
        while(node!=null){
            node=node.left;
            level++;
        }
        return level-1;
    }

    //更简洁的方法
    public static int countNodes(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=getDepth(root.left);
        int right=getDepth(root.right);
        if(left==right){
            return (1<<left)+countNodes(root.right);
        }else{
            return (1<<right)+countNodes(root.left);
        }
    }

    public static int getDepth(TreeNode root){
        int depth=0;
        while(root!=null){
            root=root.left;
            depth++;
        }
        return depth;
    }


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        System.out.println(getCBTNum(head));
        System.out.println(countNodes(head));
    }
}
