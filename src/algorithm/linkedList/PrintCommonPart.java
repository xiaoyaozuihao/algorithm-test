package linkedList;

import binaryTree.TreeNode;

/**
 * 打印两个有序链表的公共部分
 * @author xuyh
 * @date 2019/5/2
 */
public class PrintCommonPart {
    public static void printCommonPart(TreeNode node1, TreeNode node2){
        while(node1!=null&&node2!=null){
            if(node1.val <node2.val){
                node1=node1.next;
            }else if(node1.val >node2.val){
                node2=node2.next;
            }else{
                System.out.print(node1.val +" ");
                node1=node1.next;
                node2=node2.next;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
        node1.next = new TreeNode(3);
        node1.next.next = new TreeNode(5);
        node1.next.next.next = new TreeNode(6);

        TreeNode node2 = new TreeNode(1);
        node2.next = new TreeNode(2);
        node2.next.next = new TreeNode(5);
        node2.next.next.next = new TreeNode(7);
        node2.next.next.next.next = new TreeNode(8);
        printCommonPart(node1,node2);
    }
}
