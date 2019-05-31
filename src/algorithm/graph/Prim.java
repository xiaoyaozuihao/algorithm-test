package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树P算法
 * @author xuyh
 * @date 2019/5/31
 */
public class Prim {
    public static Set<Edge> prim(Graph graph){
        Set<Edge> res=new HashSet<>();
        PriorityQueue<Edge> edges=new PriorityQueue<>(Comparator.comparing(e->e.weight));
        Set<Node> set=new HashSet<>();
        for(Node node:graph.nodes.values()){
            if(!set.contains(node)){
                for(Edge edge:node.edges){
                    edges.add(edge);
                }
                while(!edges.isEmpty()){
                    Edge cur=edges.poll();
                    if(!set.contains(cur.to)){
                        set.add(cur.to);
                        res.add(cur);
                        for(Edge nextEdge:cur.to.edges){
                            edges.add(nextEdge);
                        }
                    }
                }
            }
        }
        return res;
    }
}
