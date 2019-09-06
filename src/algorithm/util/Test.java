package util;

import binaryTree.printTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {
    public static void main(String[] args) {
    }

    public static boolean isCBT(TreeNode node){
        if(node==null){
            return true;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(node);
        TreeNode left;
        TreeNode right;
        boolean isLeaf=false;
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            left=cur.left;
            right=cur.right;
            if((isLeaf&&(left!=null||right!=null))||(left==null&&right!=null)){
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
}

