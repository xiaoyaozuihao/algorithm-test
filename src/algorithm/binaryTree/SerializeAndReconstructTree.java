package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化和反序列化二叉树
 * @author xuyh
 * @date 2019/5/11
 */
public class SerializeAndReconstructTree {
    public static String serialByPre(TreeNode node){
        if(node==null){
            return "#!";
        }
        String res=node.val +"!";
        res+=serialByPre(node.left);
        res+=serialByPre(node.right);
        return res;
    }

    static int index=-1;
    public static TreeNode reconByPreString(String preString){
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
        TreeNode node=new TreeNode(Integer.valueOf(values[index]));
        node.left=reconByPreString(preString);
        node.right=reconByPreString(preString);
        return node;
    }

    public static TreeNode reconPreOrder(Queue<String> queue){
        String value=queue.poll();
        if(value.equals("#")){
            return null;
        }
        TreeNode node=new TreeNode(Integer.valueOf(value));
        node.left=reconPreOrder(queue);
        node.right=reconPreOrder(queue);
        return node;
    }

    public static String serialByLevel(TreeNode node){
        if(node==null){
            return "#!";
        }
        String res=node.val +"!";
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            node=queue.poll();
            if(node.left!=null){
                res+=node.left.val +"!";
                queue.offer(node.left);
            }else{
                res+="#!";
            }
            if(node.right!=null){
                res+=node.right.val +"!";
                queue.offer(node.right);
            }else{
                res+="#!";
            }
        }
        return res;
    }
    public static TreeNode reconByLevelString(String levelString){
        String[] values = levelString.split("!");
        int index=0;
        TreeNode node = generateByLevelString(values[index++]);
        Queue<TreeNode> queue=new LinkedList<>();
        if(node!=null){
            queue.offer(node);
        }
        while(!queue.isEmpty()){
            TreeNode c = queue.poll();
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

    private static TreeNode generateByLevelString(String value) {
        if(value.equals("#")){
            return null;
        }
        return new TreeNode(Integer.valueOf(value));
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.parent = null;
        head.left = new TreeNode(2);
        head.left.parent = head;
        head.left.left = new TreeNode(4);
        head.left.left.parent = head.left;
        head.left.right = new TreeNode(5);
        head.left.right.parent = head.left;
        head.right = new TreeNode(3);
        head.right.parent = head;
        head.right.left = new TreeNode(6);
        head.right.left.parent = head.right;
        head.right.right=new TreeNode(7);
        head.right.right.parent= head.right;
        System.out.println(serialByPre(head));
        TreeNode myNode = reconByPreString(serialByPre(head));
        myNode.preorderTraversal(myNode);
        System.out.println(serialByLevel(head));
        myNode=reconByLevelString(serialByLevel(head));
        myNode.preorderTraversal(myNode);
    }
}
