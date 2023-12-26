package nonlinear;

import java.util.*;

public class UndirectedGraph extends Graph{

    /**
     *
     * Adds an edge from v1 to v2 and also v2 to v2. Creates new vertices if not exists or else uses existing ones.
     * @param v1
     * @param v2
     */
    void addEdge(int v1, int v2) {
        if(v1 == v2) return;
        GraphNode v1Node = findNode(v1);
        GraphNode v2Node = findNode(v2);

        if (v1Node == null) {
            v1Node = new GraphNode(v1);
            nodes.add(v1Node);
        }
        if (v2Node == null) {
            v2Node = new GraphNode(v2);
            nodes.add(v2Node);
        }

        if (!v1Node.neighbours.contains(v2Node)) {
            v1Node.addNeighbor(v2Node);
        }
        if (!v2Node.neighbours.contains(v1Node)) {
            v2Node.addNeighbor(v1Node);
        }
    }

    /**
     * Finds and prints the shortest path to all vertices from the source vertex using BFS.
     * @param source - integer value of the source Vertex
     */
    void findShortestPath(int source){
        GraphNode sourceNode = findNode(source);
        if (sourceNode == null) {
            throw new IllegalArgumentException("Source node doesn't exists in the Graph");
        }
        Map<GraphNode, Integer> distanceMap = new HashMap<>();
        Set<GraphNode> visited = new HashSet<>();
        distanceMap.put(sourceNode, 0);
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(sourceNode);
        visited.add(sourceNode); // Add the source to visited set initially
        while(!queue.isEmpty()){
            GraphNode node = queue.poll();
            for(GraphNode neighbour: node.neighbours){
                if(!visited.contains(neighbour)){
                    distanceMap.put(neighbour, distanceMap.get(node) + 1);
                    queue.add(neighbour);
                    visited.add(neighbour);
                }
            }
        }
        for (Map.Entry<GraphNode, Integer> entry : distanceMap.entrySet()) {
            GraphNode node = entry.getKey();
            Integer distance = entry.getValue();
            System.out.println("Distance from source to Node " + node.data + " is " + distance);
        }
    }

    public static void main(String[] args) {
        // Create an instance of UndirectedGraph
        UndirectedGraph graph = new UndirectedGraph();

        // Add some edges to the graph
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        // BFS Traversal of Input
        System.out.println("BFS Traversal of Input");
        graph.printBFSTraversal();

        // Find and print the shortest path from a source node
        GraphNode source = new GraphNode(1);
        System.out.println("Finding shortest paths from Node " + source.data);
        graph.findShortestPath(1);
    }
}
