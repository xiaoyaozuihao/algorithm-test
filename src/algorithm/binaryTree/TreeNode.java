package binaryTree;

import java.util.Stack;

/**
 * @author xuyh
 * @date 2019/5/11
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public TreeNode next;
    public TreeNode random;
    public TreeNode(int data){
        val =data;
    }

    public static void preorderTraversal(TreeNode head) {
        if(head==null){
            return ;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            head = stack.pop();
            System.out.print(head.val +" ");
            if(head.right!=null){
                stack.push(head.right);
            }
            if(head.left!=null){
                stack.push(head.left);
            }
        }
        System.out.println();
    }
}
