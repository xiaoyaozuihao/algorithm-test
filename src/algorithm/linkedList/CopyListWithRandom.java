package linkedList;

import binaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 深度拷贝带有随机指针的链表
 * @author xuyh
 * @date 2019/5/2
 */
public class CopyListWithRandom {

    public static TreeNode copyListWithRandom1(TreeNode head){
        Map<TreeNode, TreeNode> map=new HashMap();
        TreeNode cur=head;
        while(cur!=null){
            map.put(cur,new TreeNode(cur.val));
            cur=cur.next;
        }
        cur=head;
        for (TreeNode node : map.keySet()) {
            map.get(node).next=map.get(node.next);
            map.get(node).random=map.get(node.random);
        }
        return map.get(cur);

    }
    public static TreeNode copyListWithRandom2(TreeNode head){
        if(head==null){
            return null;
        }
        TreeNode cur=head;
        TreeNode next;
        while(cur!=null){
            next=cur.next;
            cur.next=new TreeNode(cur.val);
            cur.next.next=next;
            cur=next;
        }
        cur=head;
        while(cur!=null){
            cur.next.random=cur.random!=null?cur.random.next:null;
            cur=cur.next.next;
        }
        TreeNode res=head.next;
        cur=head;
        TreeNode temp;
        while(cur!=null){
//            next=cur.next.next;
//            temp=cur.next;
//            cur.next=next;
//            temp.next=next!=null?next.next:null;
//            cur=next;
            next=cur.next.next;
            cur.next.next=next!=null?next.next:null;
            cur.next=next;
            cur=next;
        }
        return res;
    }

    public static void printLinkedList(TreeNode head){
        TreeNode cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        cur = head;
        System.out.print("| rand:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "- " : cur.random.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        TreeNode head = new TreeNode(1);
//        head.next = new TreeNode(2);
//        head.next.next = new TreeNode(5);
//        head.next.next.next = new TreeNode(7);
//        head.next.next.next.next = new TreeNode(8);
//        head.random=head.next.next.next.next;//1>>8
//        head.next.random=head.next.next;//2>>5
//        head.next.next.random=head.next.next.next;//5>>7
//        head.next.next.next.random=head;//7>>1
//        head.next.next.next.next.random=head.next;//8>>2
        TreeNode head = new TreeNode(1);
        head.next = new TreeNode(2);
        head.next.next = new TreeNode(3);
        head.next.next.next = new TreeNode(4);
        head.next.next.next.next = new TreeNode(5);
        head.next.next.next.next.next = new TreeNode(6);

        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4

        printLinkedList(head);
//        TreeNode node = copyListWithRandom1(head);
//        printLinkedList(node);
        TreeNode node = copyListWithRandom2(head);
        printLinkedList(node);
    }
}
