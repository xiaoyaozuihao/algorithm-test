package linkedList;

import binaryTree.Node;

/**
 * 将单向链表按某值划分为左边小，中间相等，右边大的形式
 *
 * @author xuyh
 * @date 2019/5/2
 */
public class SmallEqualBigLinkedList {

    public static Node listPartition1(Node head, int pivot) {
        if(head==null){
            return head;
        }
        Node cur=head;
        int i=0;
        while(cur!=null){
            i++;
            cur=cur.next;
        }
        Node[] nodes=new Node[i];
        cur=head;
        for(i=0;i!=nodes.length;i++){
            nodes[i]=cur;
            cur=cur.next;
        }
        arrayPartition(nodes,pivot);
        for(i=1;i!=nodes.length;i++){
            nodes[i-1].next=nodes[i];
        }
        nodes[i-1].next=null;
        return nodes[0];
    }

    private static void arrayPartition(Node[] nodes, int pivot) {
        int less = -1;
        int more = nodes.length;
        int index = 0;
        while (index != more) {
            if (nodes[index].value < pivot) {
                swap(nodes, index++, ++less);
            } else if (nodes[index].value > pivot) {
                swap(nodes, index, --more);
            } else {
                index++;
            }
        }
    }

    private static void swap(Node[] nodes, int a, int b) {
        Node temp = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = temp;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node sh=null;
        Node st=null;
        Node eh=null;
        Node et=null;
        Node bh=null;
        Node bt=null;
        Node next=null;
        while(head!=null){
            next=head.next;
            head.next=null;
            if(head.value<pivot){
                if(sh==null){
                    sh=head;
                    st=head;
                }else{
                    st.next=head;
                    st=head;
                }
            }else if(head.value==pivot){
                if(eh==null){
                    eh=head;
                    et=head;
                }else{
                    et.next=head;
                    et=head;
                }
            }else{
                if(bh==null){
                    bh=head;
                    bt=head;
                }else{
                    bt.next=head;
                    bt=head;
                }
            }
            head=next;
        }
        if(st!=null){
            st.next=eh;
            et=et==null?st:et;
        }
        if(et!=null){
            et.next=bh;

        }
        return sh!=null?sh:eh!=null?eh:bh;
    }

    public static void printLinkedList(Node head){
        while(head!=null){
            System.out.print(head.value+" ");
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
//        Node node = listPartition1(head1, 3);
//        printLinkedList(node);
        Node node1 = listPartition2(head1, 3);
        printLinkedList(node1);
    }
}
