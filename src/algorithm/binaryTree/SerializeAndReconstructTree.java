package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化和反序列化二叉树
 * @author xuyh
 * @date 2019/5/11
 */
public class SerializeAndReconstructTree {
    public static String serialByPre(Node node){
        if(node==null){
            return "#!";
        }
        String res=node.value+"!";
        res+=serialByPre(node.left);
        res+=serialByPre(node.right);
        return res;
    }

    static int index=-1;
    public static Node reconByPreString(String preString){
        //使用队列
//        String[] split = preString.split("!");
//        Queue<String> queue=new LinkedList<>();
//        for(int i=0;i!=split.length;i++){
//            queue.add(split[i]);
//        }
//        return reconPreOrder(queue);
        //使用数组
        String[] values = preString.split("!");
        index++;
        if(index>values.length||values[index].equals("#")){
            return null;
        }
        Node node=new Node(Integer.valueOf(values[index]));
        node.left=reconByPreString(preString);
        node.right=reconByPreString(preString);
        return node;
    }

    public static Node reconPreOrder(Queue<String> queue){
        String value=queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node node=new Node(Integer.valueOf(value));
        node.left=reconPreOrder(queue);
        node.right=reconPreOrder(queue);
        return node;
    }

    public static String serialByLevel(Node node){
        if(node==null){
            return "#!";
        }
        String res=node.value+"!";
        Queue<Node> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            node=queue.poll();
            if(node.left!=null){
                res+=node.left.value+"!";
                queue.offer(node.left);
            }else{
                res+="#!";
            }
            if(node.right!=null){
                res+=node.right.value+"!";
                queue.offer(node.right);
            }else{
                res+="#!";
            }
        }
        return res;
    }
    public static Node reconByLevelString(String levelString){
//        String[] values = levelString.split("!");
//        int index=0;
//        Node node = generateByLevelString(values[index++]);
//        Queue<Node> queue=new LinkedList<>();
//        if(node!=null){
//            queue.offer(node);
//        }
//        Node c;
//        while(!queue.isEmpty()){
//            c = queue.poll();
//            c.left=generateByLevelString(values[index++]);
//            c.right=generateByLevelString(values[index++]);
//            if(c.left!=null){
//                queue.offer(c.left);
//            }
//            if(c.right!=null){
//                queue.offer(c.right);
//            }
//        }
//        return node;
        String[] values = levelString.split("!");
        int index=0;
        Node node = generateByLevelString(values[index++]);
        Queue<Node> queue=new LinkedList<>();
        if(node!=null){
            queue.offer(node);
        }
        while(!queue.isEmpty()){
            Node c = queue.poll();
            c.left=generateByLevelString(values[index++]);
            c.right=generateByLevelString(values[index++]);
            if(c.left!=null){
                queue.offer(c.left);
            }
            if(c.right!=null){
                queue.offer(c.right);
            }
        }
        return node;

    }

    private static Node generateByLevelString(String value) {
        if(value.equals("#")){
            return null;
        }
        return new Node(Integer.valueOf(value));
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
        System.out.println(serialByPre(head));
        Node myNode = reconByPreString(serialByPre(head));
        myNode.preorderTraversal(myNode);
        System.out.println(serialByLevel(head));
        myNode=reconByLevelString(serialByLevel(head));
        myNode.preorderTraversal(myNode);
    }
}
