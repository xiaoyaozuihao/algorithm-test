package binaryTree;

import binaryTree.printTree.TreeNode;

/**
 * 判断二叉树是否是平衡二叉树
 * @author xuyh
 * @date 2019/5/12
 */
public class IsBalancedTree {
    public static class ReturnData{
        private boolean isB;
        private int h;
        public ReturnData(boolean isB,int h){
            this.isB=isB;
            this.h=h;
        }
    }

    //方法1，自定义返回结构，包含是否平衡和树的高度。
    public static boolean isBalancedTree(TreeNode node){
        return process(node).isB;
    }

    public static ReturnData process(TreeNode node){
        if(node==null){
            return new ReturnData(true,0);
        }
        ReturnData left = process(node.left);
        if(!left.isB){
            return new ReturnData(false,0);
        }
        ReturnData right = process(node.right);
        if(!right.isB){
            return new ReturnData(false,0);
        }
        if(Math.abs(left.h-right.h)>1){
            return new ReturnData(false,0);
        }
        return new ReturnData(true,Math.max(left.h,right.h)+1);
    }

    //方法2，使用-1代表不平衡。
    public static boolean isBalancedTree1(TreeNode head){
        return process1(head)!=-1;
    }
    public static int process1(TreeNode head){
        if(head==null){
            return 1;
        }
        int hl=process1(head.left);
        if(hl==-1){
            return -1;
        }
        int hr=process1(head.right);
        if(hr==-1){
            return -1;
        }
        if(Math.abs(hl-hr)>1){
            return -1;
        }
        return Math.min(hl,hr)+1;
    }

    //方法3，使用boolean数组保存是否平衡,多使用level参数
    public static boolean isBalancedTree2(TreeNode node){
        boolean[] res=new boolean[1];
        res[0]=true;
        getHeight(node,1,res);
        return res[0];
    }

    private static int getHeight(TreeNode node, int level, boolean[] res) {
        if(node==null){
            return level;
        }
        int hl=getHeight(node.left,level+1,res);
        if(!res[0]){
            return level;
        }
        int hr=getHeight(node.right,level+1,res);
        if(!res[0]){
            return level;
        }
        if(Math.abs(hl-hr)>1){
            res[0]=false;
        }
        return Math.max(hl,hr);
    }

    //方法4，不使用level参数
    public static boolean isBalancedTree3(TreeNode node){
        boolean[] res=new boolean[1];
        res[0]=true;
        func(node,res);
        return res[0];
    }

    private static int func(TreeNode node, boolean[] res) {
        if(node==null){
            return 0;
        }
        int left = func(node.left, res);
        if(!res[0]){
            return left+1;
        }
        int right= func(node.right,res);
        if(!res[0]){
            return right+1;
        }
        if(Math.abs(left-right)>1){
            res[0]=false;
        }
        return Math.max(left,right)+1;
    }

    //精炼的写法
    public static boolean isBalancedTree4(TreeNode root) {
        if(root == null){
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) < 2
                && isBalancedTree4(root.left)
                && isBalancedTree4(root.right);
    }
    public static int height(TreeNode node){
        if(node == null){
            return 0;
        }
        return Math.max(height(node.left),height(node.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        System.out.println(isBalancedTree(head));
        System.out.println(isBalancedTree1(head));
        System.out.println(isBalancedTree2(head));
        System.out.println(isBalancedTree3(head));
        System.out.println(isBalancedTree4(head));
    }
}
