package binaryTree;

import binaryTree.printTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层次遍历，给定二叉树: [3,9,20,null,null,15,7],
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * @author xuyh
 * @date 2019/5/15
 */
public class BTLevelOrder {

    public static List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count=queue.size();
            List<Integer> list=new ArrayList<>();
            while(count>0){
                root=queue.poll();
                list.add(root.val);
                if(root.left!=null){
                    queue.add(root.left);
                }
                if(root.right!=null){
                    queue.add(root.right);
                }
                count--;
            }
            res.add(list);
        }
        return res;
    }

    public static void printLevelOrder1(TreeNode root){
        if(root==null){
            return ;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        int count;
        int level=1;
        queue.offer(root);
        while(!queue.isEmpty()){
            count=queue.size();
            System.out.print("level "+(level++)+":");
            while(count>0){
                root=queue.poll();
                System.out.print(root.val+" ");
                if(root.left!=null){
                    queue.offer(root.left);
                }
                if(root.right!=null){
                    queue.offer(root.right);
                }
                count--;
            }
            System.out.println();
        }
    }

    public static void printLevelOrder(TreeNode root){
        if(root==null){
            return ;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        int level=1;
        TreeNode last=root;
        TreeNode nlast=null;
        queue.offer(root);
        System.out.print("level "+ (level++) +":");
        while(!queue.isEmpty()){
            root=queue.poll();
            System.out.print(root.val+" ");
            if(root.left!=null){
                queue.offer(root.left);
                nlast=root.left;
            }
            if(root.right!=null){
                queue.offer(root.right);
                nlast=root.right;
            }
            if(root==last&&!queue.isEmpty()){
                System.out.print("\nLevel "+(level++)+":");
                last=nlast;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode node=new TreeNode(1);
        node.left=new TreeNode(2);
        node.right=new TreeNode(3);
        node.left.left=new TreeNode(4);
        node.left.right=new TreeNode(5);
        node.right.left=new TreeNode(6);
        node.right.right=new TreeNode(7);
//        levelOrder(node);
//        printLevelOrder(node);
        printLevelOrder1(node);
    }
}
