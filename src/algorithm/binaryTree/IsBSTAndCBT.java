package binaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一颗二叉树是否是搜索二叉树和完全二叉树
 *
 * @author xuyh
 * @date 2019/5/13
 */
public class IsBSTAndCBT {
    public static boolean isBST(Node node) {
        if (node == null) {
            return true;
        }
        Deque<Node> deque = new ArrayDeque<>();
        int recent = Integer.MIN_VALUE;
        while (!deque.isEmpty() || node != null) {
            if (node != null) {
                deque.push(node);
                node = node.left;
            } else {
                node = deque.pop();
                if (node.value < recent) {
                    return false;
                }
                recent = node.value;
                node = node.right;
            }
        }
        return true;
    }

    public static boolean isCBT(Node node) {
        if (node == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        boolean isLeaf = false;
        Node left;
        Node right;
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            left = node.left;
            right = node.right;
            if ((isLeaf && (left != null || right != null))||(left==null&&right!=null)){
                return false;
            }
            if(left!=null){
                queue.offer(left);
            }
            if(right!=null){
                queue.offer(right);
            }else{
                isLeaf=true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));
    }
}
