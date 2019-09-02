package graph;

/**
 * 图生成器
 * @author xuyh
 * @date 2019/5/30
 */
public class GraphGenerator {
    public static void main(String[] args) {

    }

    //生成图，arr矩阵中三个要素，weight权重，from入节点的编号，to出节点的编号
    public static Graph generateGraph(Integer[][] arr){
        Graph graph=new Graph();
        for(int i=0;i<arr.length;i++){
            Integer weight=arr[i][0];
            Integer from=arr[i][1];
            Integer to=arr[i][2];
            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }
            Node fromNode=graph.nodes.get(from);
            Node toNode=graph.nodes.get(to);
            fromNode.out++;
            toNode.in++;
            fromNode.nexts.add(toNode);
            Edge edge=new Edge(weight,fromNode,toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}
