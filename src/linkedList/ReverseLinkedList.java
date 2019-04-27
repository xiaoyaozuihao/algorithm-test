package linkedList;

/**
 * 反转链表
 * @author xuyh
 * @date 2019/4/25
 */
public class ReverseLinkedList {
    public static class Node{
        private int value;
        private Node next;
        public Node(int data){
            value=data;
        }
    }

    //单链表反转
    public static Node reverseList(Node head){
        Node pre=null;
        Node next;
        while(head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data){
            this.value=data;
        }

        public static DoubleNode reverseDoubleNode(DoubleNode head){
            DoubleNode pre=null;
            DoubleNode next;
            while(head!=null){
                next=head.next;
                head.next=pre;
                head.last=next;
                pre=head;
                head=next;
            }
            return pre;
        }
    }

    public static void printLinkedList(Node head){
        System.out.print("Linked list:");
        while(head!=null){
            System.out.print(head.value+" ");
            head=head.next;
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(DoubleNode head){
        System.out.print("DoubleLinked list:");
        DoubleNode end=null;
        while(head!=null){
            System.out.print(head.value+" ");
            end=head;
            head=head.next;
        }
        System.out.print("| ");
        while(end!=null){
            System.out.print(end.value+" ");
            end =end.last;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head=new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(3);
        printLinkedList(head);
        printLinkedList(reverseList(head));
        DoubleNode head1=new DoubleNode(1);
        head1.next=new DoubleNode(2);
        head1.next.last=head1;
        head1.next.next=new DoubleNode(3);
        head1.next.next.last=head1.next;
        printDoubleLinkedList(head1);
        printDoubleLinkedList(DoubleNode.reverseDoubleNode(head1));
    }
}
