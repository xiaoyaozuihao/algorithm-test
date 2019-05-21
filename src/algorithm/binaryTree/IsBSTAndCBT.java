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
    public static boolean isBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        int recent = Integer.MIN_VALUE;
        while (!deque.isEmpty() || node != null) {
            if (node != null) {
                deque.push(node);
                node = node.left;
            } else {
                node = deque.pop();
                if (node.val < recent) {
                    return false;
                }
                recent = node.val;
                node = node.right;
            }
        }
        return true;
    }

    public static boolean isCBT(TreeNode node) {
        if (node == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        boolean isLeaf = false;
        TreeNode left;
        TreeNode right;
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
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));
    }
}
