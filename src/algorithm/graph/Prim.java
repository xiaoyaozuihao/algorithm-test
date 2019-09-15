package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树P算法
 *
 * @author xuyh
 * @date 2019/5/31
 */
public class Prim {
    public static Set<Edge> prim(Graph graph) {
        Set<Edge> res = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(e -> e.weight));
        Set<Node> set = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()) {
                    Edge cur = queue.poll();
                    Node toNode = cur.to;
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        res.add(cur);
                        for (Edge nextEdge : toNode.edges) {
                            queue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return res;
    }
}
