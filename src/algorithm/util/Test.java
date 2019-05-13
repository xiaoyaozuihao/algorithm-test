package util;

import binaryTree.Node;

/**
 * @author xuyh
 * @date 2019/5/12
 */
public class Test {

    public static boolean isBalancedTree(Node node){
        boolean[] res=new boolean[1];
        res[0]=true;
        getHeight(node,1,res);
        return res[0];
    }

    private static int getHeight(Node node, int level, boolean[] res) {
        if(node==null){
            return level;
        }
        int hl = getHeight(node.left, level + 1, res);
        if(!res[0]){
            return level;
        }
        int hr = getHeight(node.right, level + 1, res);
        if(!res[0]){
            return level;
        }
        if(Math.abs(hl-hr)>1){
            res[0]=false;
        }
        return Math.max(hl,hr);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.parent = null;
        head.left = new Node(2);
        head.left.parent = head;
        head.left.left = new Node(4);
        head.left.left.parent = head.left;
        head.left.right = new Node(5);
        head.left.right.parent = head.left;
        head.right = new Node(3);
        head.right.parent = head;
        head.right.left = new Node(6);
        head.right.left.parent = head.right;
        head.right.right=new Node(7);
        head.right.right.parent= head.right;
        System.out.println(isBalancedTree(head));
    }
}
