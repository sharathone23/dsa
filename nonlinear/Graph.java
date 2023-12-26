package nonlinear;

import java.util.*;

public class Graph {
    List<GraphNode> nodes = new ArrayList<>();
    public GraphNode findNode(int data) {
        for (GraphNode node : nodes) {
            if (node.data == data) {
                return node;
            }
        }
        return null;
    }


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

    /**
     * Checks if a cycle exists in a graph.
     *
     * This method iterates over each node in the graph to determine if there is a cycle. It is designed to work
     * on both connected and disconnected graphs. The method uses a Breadth-First Search (BFS) approach
     * implemented in the 'checkCycleBFS' method to explore each node and its neighbours.
     *
     * A cycle is detected if a node is revisited during the BFS traversal, indicating that there is a path
     * leading back to an already visited node. This method ensures all nodes are checked including the ones in disconnected components.
     *
     * @return true if a cycle exists in the graph, false otherwise.
     */
    boolean isCycleExists(){
        if(nodes.isEmpty()) return false;
        Set<GraphNode> visited = new HashSet<>();
        //iterate over all nodes in the graph, which ensures that disconnected components are also traversed.
        for(GraphNode node: nodes){
            if(!visited.contains(node)){
                if(checkCycleBFS(node, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Performs a Breadth-First Search (BFS) to detect a cycle starting from a given node.
     *
     * This method uses BFS to explore the graph from the specified starting node. It keeps track of visited
     * nodes to detect if a cycle exists. A cycle is identified if a node is encountered again during the BFS
     * traversal that is not the parent of the current node. The method uses a queue for BFS traversal and a
     * map to keep track of each node's parent.
     *
     * @param node The starting node for the BFS traversal.
     * @param visited A set of nodes that have already been visited in the BFS traversal.
     * @return true if a cycle is detected during the BFS traversal, false otherwise.
     */
    private boolean checkCycleBFS(GraphNode node, Set<GraphNode> visited){
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(node);
        visited.add(node);
        Map<GraphNode, GraphNode> parentMap = new HashMap<>();
        parentMap.put(node, null);
        while(!queue.isEmpty()){
            GraphNode graphNode = queue.poll();
            for(GraphNode neighbour: graphNode.neighbours){
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    queue.add(neighbour);
                    parentMap.put(neighbour, graphNode);
                }else{
                    if(parentMap.get(graphNode) != neighbour){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

