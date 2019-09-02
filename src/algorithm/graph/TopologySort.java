package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 拓扑排序
 *
 * @author xuyh
 * @date 2019/5/31
 */
public class TopologySort {
    public static void main(String[] args) {
        Integer[][] arr = {
                {4, 1, 2},
                {2, 1, 3},
                {5, 4, 3},
                {1, 4, 5},
                {1, 5, 3},
                {1, 3, 2},
        };
        Graph graph = GraphGenerator.generateGraph(arr);
        List<Node> nodes = topologySort(graph);
        nodes.stream().map(node -> node.value).forEach(s -> System.out.print(s + " "));
    }

    public static List<Node> topologySort(Graph graph) {
        Queue<Node> zeroInNodes = new LinkedList<>();
        Map<Node, Integer> inMap = new HashMap<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInNodes.add(node);
            }
        }
        List<Node> res = new ArrayList<>();
        while (!zeroInNodes.isEmpty()) {
            Node cur = zeroInNodes.poll();
            res.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInNodes.add(next);
                }
            }
        }
        return res;
    }
}
