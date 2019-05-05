package binaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树的先序，中序，后序遍历
 * @author xuyh
 * @date 2019/5/3
 */
public class BinaryTreeTraversal {
    public static List<Integer> postorderTraversal(TreeNode root){
        List<Integer> result=new ArrayList<>();
        Deque<Guide> deque=new ArrayDeque<>();
        deque.addFirst(new Guide(0,root));
        while(!deque.isEmpty()){
            Guide cur = deque.removeFirst();
            if(cur.node==null){
                continue;
            }
            if(cur.opt==1){
                result.add(cur.node.val);
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
        private TreeNode node;
        public Guide(int opt,TreeNode node){
            this.opt=opt;
            this.node=node;
        }
    }
    public static class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int data){
            val=data;
        }
    }
}
