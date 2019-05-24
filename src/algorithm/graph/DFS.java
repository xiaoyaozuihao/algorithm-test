package graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

/**
 * 深度优先遍历
 * @author xuyh
 * @date 2019/5/23
 */
public class DFS {
    public static void dfs(Node node){
        if(node==null){
            return;
        }
        Deque<Node> deque=new ArrayDeque<>();
        HashSet<Node> set=new HashSet<>();
        deque.push(node);
        set.add(node);
        System.out.println(node.value);
        while(!deque.isEmpty()){
            node=deque.pop();
            for(Node next:node.nexts){
                if(!set.contains(next)){
                    deque.push(node);
                    deque.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
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
        dfs(node);
    }
}
