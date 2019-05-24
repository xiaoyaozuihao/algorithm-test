package graph;

import com.sun.javafx.geom.Edge;

import java.util.ArrayList;

/**
 * @author xuyh
 * @date 2019/5/22
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value){
        this.value=value;
        in=0;
        out=0;
        nexts=new ArrayList<>();
        edges=new ArrayList<>();
    }
}
