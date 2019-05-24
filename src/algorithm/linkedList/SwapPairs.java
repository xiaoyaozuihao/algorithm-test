package linkedList;

import binaryTree.TreeNode;

/**
 * 两两交换链表中的节点
 * @author xuyh
 * @date 2019/5/14
 */
public class SwapPairs {

    public static TreeNode swapPairs(TreeNode node){
        if(node==null||node.next==null){
            return node;
        }
        TreeNode next=node.next;
        node.next=swapPairs(next.next);
        next.next=node;
        return next;
    }
    public static TreeNode swapPairs1(TreeNode node){
        TreeNode p1, p2, p3;
        if(node == null||node.next==null) {
            return node;
        }
        p1 = node;
        node = p1.next;
        while (p1 != null && p1.next != null){

            p2 = p1.next;
            p3 = p2.next;

            p1.next = p2.next;
            p2.next = p1;

            if(p3 != null && p3.next != null){
                p1.next = p3.next;
            }

            p1 = p3;

        }
        return node;
    }
}
