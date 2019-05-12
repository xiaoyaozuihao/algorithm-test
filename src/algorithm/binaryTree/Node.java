package binaryTree;

import java.util.Stack;

/**
 * @author xuyh
 * @date 2019/5/11
 */
public class Node {
    public int value;
    public Node left;
    public Node right;
    public Node parent;
    public Node next;
    public Node random;
    public Node(int data){
        value=data;
    }

    public static void preorderTraversal(Node head) {
        if(head==null){
            return ;
        }
        Stack<Node> stack=new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            head = stack.pop();
            System.out.print(head.value+" ");
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
