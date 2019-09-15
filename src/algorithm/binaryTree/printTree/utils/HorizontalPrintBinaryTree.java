package binaryTree.printTree.utils;

import binaryTree.printTree.TreeNode;

/**
 * 横向打印一颗二叉树
 * @author xuyh
 * @date 2019/5/10
 */
public class HorizontalPrintBinaryTree {

    public static void print(TreeNode node){
        System.out.println("BinaryTree:");
        printInOrder(node,0,"H",17);
        System.out.println();
    }
    /**
     * @param node      传入的节点
     * @param height　　层数(根节点为0)
     * @param to       表示的特定节点  H表示根节点   ^表示父亲节点在左上方　v表示父亲节点在左下方
     * @param len　　　 指定每一个节点打印的宽度(总宽度)
     */
    private static void printInOrder(TreeNode node, int height, String to, int len) {
        if(node==null){
            return ;
        }
        printInOrder(node.right,height+1,"v",len);
        String value=to+node.val +to;
        int lenM=value.length();
        int lenL=(len-lenM)/2;//  左边的空格  ((总长度-值所占长度)，然后分一半)
        int lenR=len-lenM-lenL;//  右边的空格  (总-值-左边空格长度)
        value=getSpace(lenL)+value+getSpace(lenR);
        System.out.println(getSpace(height*len)+value);
        printInOrder(node.left,height+1,"^",len);
    }

    private static String getSpace(int num) {
        String space=" ";
        StringBuffer buf=new StringBuffer();
        for(int i=0;i<num;i++){
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(-222222222);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(Integer.MIN_VALUE);
        head.right.left = new TreeNode(55555555);
        head.right.right = new TreeNode(66);
        head.left.left.right = new TreeNode(777);
        print(head);

        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);
        head.left.left.right = new TreeNode(7);
        print(head);

        head = new TreeNode(1);
        head.left = new TreeNode(1);
        head.right = new TreeNode(1);
        head.left.left = new TreeNode(1);
        head.right.left = new TreeNode(1);
        head.right.right = new TreeNode(1);
        head.left.left.right = new TreeNode(1);
        print(head);
    }
}
