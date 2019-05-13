package binaryTree;

/**
 * 完全二叉树的节点数量
 * @author xuyh
 * @date 2019/5/13
 */
public class CompleteTreeNodeNum {

    public static int getCBTNum(Node node){
        if(node==null){
            return 0;
        }
        return bs(node,1,mostLeftLevel(node,1));
    }

    private static int bs(Node node, int level, int height) {
        if(level==height){
            return 1;
        }
        if(mostLeftLevel(node.right,level+1)==height){
            return (1<<(height-level))+bs(node.right,level+1,height);
        }else{
            return (1<<(height-level-1))+bs(node.left,level+1,height);
        }
    }

    private static int mostLeftLevel(Node node, int level) {
        while(node!=null){
            level++;
            node=node.left;
        }
        return level-1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(getCBTNum(head));

    }
}
