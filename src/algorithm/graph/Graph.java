package graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author xuyh
 * @date 2019/5/22
 */
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;
    public Graph(){
        nodes=new HashMap<>();
        edges=new HashSet<>();
    }

}
