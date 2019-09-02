package graph;

/**
 * 图的边定义
 * @author xuyh
 * @date 2019/5/22
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
