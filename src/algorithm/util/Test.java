package util;

import binaryTree.TreeNode;

import java.util.Stack;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
        TreeNode node=new TreeNode(1);
        node.left=new TreeNode(2);
        node.right=new TreeNode(3);
        node.left.left=new TreeNode(4);
        node.left.right=new TreeNode(5);
        node.right.left=new TreeNode(6);
        node.right.right=new TreeNode(7);
        postTraversal(node);
    }

    public static void postTraversal(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        TreeNode cur;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if(cur.left!=null&&cur.left!=head&&cur.right!=head){
                stack.push(cur.left);
            }else if(cur.right!=null&&cur.right!=head){
                stack.push(cur.right);
            }else{
                head=stack.pop();
                System.out.println(head.val+" ");
            }
        }
    }
}

