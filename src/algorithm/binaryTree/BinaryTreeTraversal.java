package binaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的先序，中序，后序遍历
 * @author xuyh
 * @date 2019/5/3
 */
public class BinaryTreeTraversal {
    public static void main(String[] args) {
        Node node=new Node(1);
        node.left=new Node(2);
        node.right=new Node(3);
        node.left.left=new Node(4);
        node.left.right=new Node(5);
        node.right.left=new Node(6);
        node.right.right=new Node(7);
        preOrderRecur(node);
        System.out.println();
        inOrderRecur(node);
        System.out.println();
        postOrderRecur(node);
        System.out.println();
        preOrderUnRecur(node);
        System.out.println();
        inOrderUnRecur(node);
        System.out.println();
        postOrderUnRecur(node);
        postOrderUnRecur1(node);
    }
    public static void preOrderRecur(Node node){
        if(node==null){
            return;
        }
        System.out.print(node.value+" ");
        preOrderRecur(node.left);
        preOrderRecur(node.right);
    }
    public static void inOrderRecur(Node node){
        if(node==null){
            return;
        }
        inOrderRecur(node.left);
        System.out.print(node.value+" ");
        inOrderRecur(node.right);
    }
    public static void postOrderRecur(Node node){
        if(node==null){
            return;
        }
        postOrderRecur(node.left);
        postOrderRecur(node.right);
        System.out.print(node.value+" ");
    }

    public static void preOrderUnRecur(Node node){
        System.out.print("pre-order:");
        if(node!=null){
            Stack<Node> stack=new Stack<>();
            stack.push(node);
            while(!stack.isEmpty()){
                node=stack.pop();
                System.out.print(node.value+" ");
                if(node.right!=null){
                    stack.push(node.right);
                }
                if(node.left!=null){
                    stack.push(node.left);
                }
            }
        }
    }

    public static void inOrderUnRecur(Node node){
        System.out.print("in-order:");
        if(node!=null){
            Stack<Node> stack=new Stack<>();
            while(!stack.isEmpty()|| node!=null){
                if(node!=null){
                    stack.push(node);
                    node=node.left;
                }else{
                    node = stack.pop();
                    System.out.print(node.value+" ");
                    node=node.right;
                }
            }
        }
    }
    //双栈结构版本
    public static void postOrderUnRecur(Node node){
        System.out.print("post-order:");
        if(node!=null){
            Stack<Node> s1=new Stack<>();
            Stack<Node> s2=new Stack<>();
            s1.push(node);
            while(!s1.isEmpty()){
                node = s1.pop();
                s2.push(node);
                if(node.left!=null){
                    s1.push(node.left);
                }
                if(node.right!=null){
                    s1.push(node.right);
                }
            }
            while(!s2.isEmpty()){
                System.out.print(s2.pop().value+" ");
            }
        }
    }

    //单栈版本
    public static void postOrderUnRecur1(Node head){
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node c ;
            while (!stack.isEmpty()) {
                c = stack.peek();//c代表栈顶元素
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;//head代表最近一次弹出并打印的节点
                }
            }
        }
    }

    //循环版本，非常巧妙，适用于三种遍历，且只需要向递归版本一样调整两行代码顺序即可
    public static List<Integer> postorderTraversal(Node root){
        List<Integer> result=new ArrayList<>();
        Deque<Guide> deque=new ArrayDeque<>();
        deque.addFirst(new Guide(0,root));
        while(!deque.isEmpty()){
            Guide cur = deque.removeFirst();
            if(cur.node==null){
                continue;
            }
            if(cur.opt==1){
                result.add(cur.node.value);
            }else{
                deque.addFirst(new Guide(1,cur.node));
                deque.addFirst(new Guide(0,cur.node.right));
                deque.addFirst(new Guide(0,cur.node.left));
            }
        }
        return result;
    }

    public static class Guide{
        private int opt;//0,visit 1,print
        private Node node;
        public Guide(int opt, Node node){
            this.opt=opt;
            this.node=node;
        }
    }

}
