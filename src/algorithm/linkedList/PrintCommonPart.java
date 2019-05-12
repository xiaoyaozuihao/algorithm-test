package linkedList;

import binaryTree.Node;

/**
 * 打印两个有序链表的公共部分
 * @author xuyh
 * @date 2019/5/2
 */
public class PrintCommonPart {
    public static void printCommonPart(Node node1, Node node2){
        while(node1!=null&&node2!=null){
            if(node1.value<node2.value){
                node1=node1.next;
            }else if(node1.value>node2.value){
                node2=node2.next;
            }else{
                System.out.print(node1.value+" ");
                node1=node1.next;
                node2=node2.next;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);
        printCommonPart(node1,node2);
    }
}
