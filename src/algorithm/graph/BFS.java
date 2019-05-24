package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历，又叫广度优先遍历
 * @author xuyh
 * @date 2019/5/22
 */
public class BFS {
    public static void bfs(Node node){
        if(node==null){
            return;
        }
        Queue<Node> queue=new LinkedList<>();
        HashSet<Node> set=new HashSet<>();
        queue.offer(node);
        set.add(node);
        while(!queue.isEmpty()){
            node=queue.poll();
            System.out.println(node.value);
            for(Node next:node.nexts){
                if(!set.contains(next)){
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node node=new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        node.nexts.add(node1);
        node.nexts.add(node2);
        node.nexts.add(node3);
        Node node4=new Node(5);
        node1.nexts.add(node4);
        Node node5=new Node(6);
        node4.nexts.add(node5);
        bfs(node);
    }
}
