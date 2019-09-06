package binaryTree;

import binaryTree.printTree.TreeNode;
import binaryTree.printTree.utils.BinaryTreesUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化和反序列化二叉树
 * @author xuyh
 * @date 2019/5/11
 */
public class SerializeAndReconstructTree {
    //按照先序遍历的方式序列化二叉树，#代表空，!代表节点间的分隔符。
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
        if(++index>values.length||values[index].equals("#")){
            return null;
        }
        TreeNode node=new TreeNode(Integer.valueOf(values[index]));
        node.left=reconByPreString(preString);
        node.right=reconByPreString(preString);
        return node;
    }

    //使用队列方式还原
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

    //循环版本
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
        if(levelString==null||!levelString.contains("!")){
            return null;
        }
        String[] split = levelString.split("!");
        int index=0;
        TreeNode node=generateByString(split[index++]);
        Queue<TreeNode> queue=new LinkedList<>();
        if(node!=null){
            queue.offer(node);
        }
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            cur.left=generateByString(split[index++]);
            cur.right=generateByString(split[index++]);
            if(cur.left!=null){
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return node;
    }

    private static TreeNode generateByString(String str) {
        if(str.equals("#")){
            return null;
        }
        return new TreeNode(Integer.parseInt(str));
    }

//    //讨巧的解法，存在bug,当int值过大时，还原会出现问题
//    public String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        if (root == null) {
//            sb.append('#');
//            return sb.toString();
//        }
//        sb.append((char) (root.val + '0'));
//        sb.append(serialize(root.left));
//        sb.append(serialize(root.right));
//        return sb.toString();
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data == null || data.length() == 0) {
//            return null;
//        }
//        int[] index = {-1};
//        return recon(data, index);
//    }
//
//    public TreeNode recon(String data, int[] index) {
//        char c;
//        if (++index[0] >= data.length() || (c = data.charAt(index[0])) == '#') {
//            return null;
//        } else {
//            TreeNode node = new TreeNode(c - '0');
//            node.left = recon(data, index);
//            node.right = recon(data, index);
//            return node;
//        }
//    }

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
        BinaryTreesUtil.println(myNode);
        System.out.println(serialByLevel(head));
        TreeNode node=reconByLevelString("1!2!3!4!5!6!7!#!#!#!#!#!#!#!#!");
        BinaryTreesUtil.print(node);
    }
}
