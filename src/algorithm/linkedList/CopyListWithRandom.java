package linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 深度拷贝带有随机指针的链表
 * @author xuyh
 * @date 2019/5/2
 */
public class CopyListWithRandom {
    public static class Node{
        private int value;
        private Node next;
        private Node random;
        public Node(int data){
            value=data;
        }
    }
    
    public static Node copyListWithRandom1(Node head){
        Map<Node,Node> map=new HashMap();
        Node cur=head;
        while(cur!=null){
            map.put(cur,new Node(cur.value));
            cur=cur.next;
        }
        cur=head;
        for (Node node : map.keySet()) {
            map.get(node).next=map.get(node.next);
            map.get(node).random=map.get(node.random);
        }
        return map.get(cur);

    }
    public static Node copyListWithRandom2(Node head){
        if(head==null){
            return null;
        }
        Node cur=head;
        Node next;
        while(cur!=null){
            next=cur.next;
            cur.next=new Node(cur.value);
            cur.next.next=next;
            cur=next;
        }
        cur=head;
        while(cur!=null){
            cur.next.random=cur.random!=null?cur.random.next:null;
            cur=cur.next.next;
        }
        Node res=head.next;
        cur=head;
        Node temp;
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

    public static void printLinkedList(Node head){
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        cur = head;
        System.out.print("| rand:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "- " : cur.random.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        Node head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(5);
//        head.next.next.next = new Node(7);
//        head.next.next.next.next = new Node(8);
//        head.random=head.next.next.next.next;//1>>8
//        head.next.random=head.next.next;//2>>5
//        head.next.next.random=head.next.next.next;//5>>7
//        head.next.next.next.random=head;//7>>1
//        head.next.next.next.next.random=head.next;//8>>2
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4

        printLinkedList(head);
//        Node node = copyListWithRandom1(head);
//        printLinkedList(node);
        Node node = copyListWithRandom2(head);
        printLinkedList(node);
    }
}
