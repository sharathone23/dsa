package nonlinear;

import java.util.ArrayList;
import java.util.List;

public class GraphNode{
    int data;
    List<GraphNode> neighbours;

    GraphNode(){
        this.data = 0;
        this.neighbours = new ArrayList<>();
    }

    GraphNode(int data){
        this.data = data;
        this.neighbours = new ArrayList<>();
    }

    void addNeighbor(GraphNode neighbor) {
        this.neighbours.add(neighbor);
    }

    void removeNeighbor(GraphNode neighbor) {
        this.neighbours.remove(neighbor);
    }

}
