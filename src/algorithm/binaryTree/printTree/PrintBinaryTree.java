package binaryTree.printTree;

/**
 * 打印一颗二叉树，横向打印
 * @author xuyh
 * @date 2019/5/10
 */
public class PrintBinaryTree {
    public static class Node{
        private int val;
        private Node left;
        private Node right;
        public Node(int value){
            val=value;
        }
    }

    public static void printTree(Node node){
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
    private static void printInOrder(Node node, int height, String to, int len) {
        if(node==null){
            return ;
        }
        printInOrder(node.right,height+1,"v",len);
        String val=to+node.val+to;
        int lenM=val.length();
        int lenL=(len-lenM)/2;//  左边的空格  ((总长度-值所占长度)，然后分一半)
        int lenR=len-lenM-lenL;//  右边的空格  (总-值-左边空格长度)
        val=getSpace(lenL)+val+getSpace(lenR);
        System.out.println(getSpace(height*len)+val);
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
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);

    }
}
