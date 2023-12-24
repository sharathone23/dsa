package nonlinear;

import java.util.*;

public class Graph {
    List<GraphNode> nodes = new ArrayList<>();

    /**
     * Performs a Depth First Search(DFS) traversal through all the vertices in the Graph and prints in same line with spaces
     * Also avoids iterating the same vertex again by using a Set for tracking visited Vertices
     */
    void printDFSTraversal(){
        if(nodes.isEmpty()) return;
        Set<GraphNode> visited = new HashSet<>();
        //iterate over all nodes in the graph, which ensures that disconnected components are also traversed.
        for(GraphNode node: nodes){
            if(!visited.contains(node)) {
                printDFS(node, visited);
            }
        }
    }

    private void printDFS(GraphNode node, Set<GraphNode> visited){
        if(visited.contains(node)){
            return; // Avoid infinite loop for Cyclic graphs
        }
        visited.add(node);
        for(GraphNode gNode: node.neighbours){
            printDFS(gNode, visited);
        }
        System.out.print(node.data + " ");
    }

    /**
     * Performs a Breadth First Search(BFS) traversal through all the vertices in the Graph and prints in same line with spaces
     * Also avoids iterating the same vertex again by using a Set for tracking visited Vertices
     */
    void printBFSTraversal(){
        if(nodes.isEmpty()) return;
        Set<GraphNode> visited = new HashSet<>();
        //iterate over all nodes in the graph, which ensures that disconnected components are also traversed.
        for(GraphNode node: nodes){
            if(!visited.contains(node)){
                printBFS(node, visited);
            }
        }
    }

    private void printBFS(GraphNode node, Set<GraphNode> visited){
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        visited.add(node);
        while(!queue.isEmpty()){
            GraphNode graphNode = queue.poll();
            System.out.print(graphNode.data + " ");
            for(GraphNode neighbour: graphNode.neighbours){
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
    }
}

class GraphNode{
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
