package nonlinear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphNode{
    int data;
    HashMap<GraphNode, Integer> weightsMap;//to support weighted graphs
    List<GraphNode> neighbours;

    GraphNode(){
        this.data = 0;
        this.weightsMap = new HashMap<>();
        this.neighbours = new ArrayList<>();
    }

    GraphNode(int data){
        this.data = data;
        this.weightsMap = new HashMap<>();
        this.neighbours = new ArrayList<>();
    }

    void addNeighbor(GraphNode neighbor) {
        this.neighbours.add(neighbor);
    }

    void removeNeighbor(GraphNode neighbor) {
        this.neighbours.remove(neighbor);
    }

}
