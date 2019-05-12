package binaryTree;

/**
 * 获取二叉树的前驱节点
 * @author xuyh
 * @date 2019/5/11
 */
public class PredecessorNode {
    public static Node getPredecessorNode(Node node){
        if(node==null){
            return null;
        }
        if(node.left!=null){
            return getRightMost(node.left);
        }else{
            Node parent=node.parent;
            while(parent!=null&&parent.right!=node){
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }

    private static Node getRightMost(Node node) {
        if(node==null){
            return null;
        }
        while(node.right!=null){
            node=node.right;
        }
        return node;
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

        Node test = head;
        System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
        test = head.left.left;
        System.out.println(test.value + " pre: " + getPredecessorNode(test));
        test = head.left.right;
        System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
        test = head.right.right;
        System.out.println(test.value + " pre: " + getPredecessorNode(test).value);
    }
}
