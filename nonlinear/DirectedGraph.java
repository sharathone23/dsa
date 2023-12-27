package nonlinear;

import java.util.*;

public class DirectedGraph extends Graph{

    /**
     * Adds an edge from v1 to v2. Creates new vertices if not exists or else uses existing ones.
     * This is for Unweighted Directed Graphs
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
    }

    /**
     * Adds an edge from v1 to v2. Creates new vertices if not exists or else uses existing ones.
     * This is for Weighted Directed Graphs
     * @param v1
     * @param v2
     */
    void addWeightedEdge(int v1, int v2, int weight) {
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
            v2Node.weightsMap.put(v1Node, weight); // Add weight from v1Node to the weightsMap.
        }
    }

    /**
     * Checks if a cycle exists in the graph using DFS.
     *
     * @return true if a cycle exists, false otherwise.
     */
    public boolean isCycleExists() {
        Set<GraphNode> visited = new HashSet<>();
        Set<GraphNode> recursionStack = new HashSet<>();

        for (GraphNode node : nodes) {
            if (!visited.contains(node)) {
                if (isCyclicDFS(node, visited, recursionStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method to perform DFS and check for cycles.
     *
     * @param node           The current node.
     * @param visited        Set of visited nodes.
     * @param recursionStack Set of nodes in the current recursion stack.
     * @return true if a cycle is found, false otherwise.
     */
    private boolean isCyclicDFS(GraphNode node, Set<GraphNode> visited, Set<GraphNode> recursionStack) {
        if (recursionStack.contains(node)) {
            // If the node is in the recursion stack, a cycle is found.
            return true;
        }
        if (visited.contains(node)) {
            // If the node is already visited and not in the recursion stack, no cycle is detected here.
            return false;
        }

        // Add the node to visited and recursion stack sets.
        visited.add(node);
        recursionStack.add(node);

        // Recur for all the vertices adjacent to this vertex.
        for (GraphNode neighbour : node.neighbours) {
            if (isCyclicDFS(neighbour, visited, recursionStack)) {
                return true;
            }
        }

        // Remove the node from recursion stack before returning.
        recursionStack.remove(node);
        return false;
    }


    /**Returns the Vertices in the Topological Sort Order
     * Topological sorting is a linear ordering of vertices in a graph such that for every directed edge UV from vertex U to vertex V, U comes before V in the ordering.
     * This concept is independent of whether a graph is weighted or unweighted, but it should be Directed
     */
    List<Integer> getTopologicalSortOrderList(){
        List<Integer> result = new ArrayList<>();

        //Return if cycle exists, Topological sort is for Acyclic graphs
        if(isCycleExists()){
            System.out.println("Cycle Exists - Topological Order requires Acyclic graphs");
            return result;
        }
        //Find degree of each vertex
        Map<Integer, Integer> degreeCount = new HashMap<>();
        for(GraphNode node: nodes){
            for(GraphNode neighbour: node.neighbours){
                degreeCount.put(neighbour.data, degreeCount.getOrDefault(neighbour.data, 0) +1);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        //Add all zero degree vertices
        for(GraphNode node: nodes){
            if(!degreeCount.containsKey(node.data)){
                queue.add(node.data);
            }
        }
        while(!queue.isEmpty()){
            Integer vertex = queue.poll();// Remove one vertex and decrement its neighbours in degree by 1
            result.add(vertex);
            GraphNode node = findNode(vertex);
            for(GraphNode neighbour: node.neighbours){
                degreeCount.put(neighbour.data, degreeCount.get(neighbour.data)-1);
                if(degreeCount.get(neighbour.data) == 0){
                    queue.add(neighbour.data); // Add it to queue if in degree count is zero and remove from map.
                    degreeCount.remove(neighbour.data);
                }
            }
        }
        return result;
    }

    /**
     * Finds the shortest path using Topological Sort approach.
     * Topological sorting is a linear ordering of vertices in a graph such that for every directed edge UV from vertex U to vertex V, U comes before V in the ordering.
     * This concept is independent of whether a graph is weighted or unweighted, but it should be Directed
     * Note: Applies only for Directed Acyclic Graph (DAG's)
     *  @param source
     */
    void findShortestPathUsingTopologicalSort(int source){
        if(findNode(source) == null || isCycleExists()) return; // If source is not part of the graph or there is a Cycle in the Graph

        List<Integer> topSortOrderList = getTopologicalSortOrderList(); // Create the topological sort order list
        int[] distanceArray = new int[nodes.size()];
        // Initialize all the distances to Infinite
        Arrays.fill(distanceArray, Integer.MAX_VALUE);
        distanceArray[source] = 0; // Set source distance to 0 as the distance to itself is 0.
        for(int topSortOrderVertex: topSortOrderList){ // Iterate over topological sort order list neighbours and calculate the distance based on current parent node.
            // Process only if the vertex has been reached
            if (distanceArray[topSortOrderVertex] != Integer.MAX_VALUE) {
                GraphNode currentVertexNode = findNode(topSortOrderVertex);
                for (GraphNode neighbour : currentVertexNode.neighbours) {
                    int weight = getWeight(topSortOrderVertex, neighbour.data);
                    if (weight != Integer.MAX_VALUE && distanceArray[neighbour.data] > distanceArray[topSortOrderVertex] + weight) {
                        distanceArray[neighbour.data] = distanceArray[topSortOrderVertex] + weight;
                    }
                }
            }
        }

        for(int i = 0; i < distanceArray.length; i++){
            if(distanceArray[i] == Integer.MAX_VALUE) {
                System.out.println("Distance from source " + source + " to vertex " + i + " is not reachable");
            } else {
                System.out.println("Distance from source " + source + " to vertex " + i + " is " + distanceArray[i]);
            }
        }
    }

    void findShortestPathUsingDijkstra(int source){
        //TODO
    }

    void findShortestPathUsingBellmanFord (int source){
        //TODO
    }

    private int getWeight(int v1, int v2){
        if(v1 == v2) return 0;
        GraphNode v1Node = findNode(v1);
        GraphNode v2Node = findNode(v2);
        if(v1Node == null || v2Node==null) return Integer.MAX_VALUE;
        if (!v1Node.neighbours.contains(v2Node)) {
            return Integer.MAX_VALUE;
        }else{
            return v2Node.weightsMap.get(v1Node);
        }
    }

    public static void main(String[] args) {
        DirectedGraph unweightedDirectedGraph = new DirectedGraph();

        // Add edges to the graph to form a Directed Acyclic Graph (DAG)
        unweightedDirectedGraph.addEdge(5, 2);
        unweightedDirectedGraph.addEdge(5, 0);
        unweightedDirectedGraph.addEdge(4, 0);
        unweightedDirectedGraph.addEdge(4, 1);
        unweightedDirectedGraph.addEdge(2, 3);
        unweightedDirectedGraph.addEdge(3, 1);

        // Print the topological sort order
        System.out.println("Topological Sort Order for unweightedDirectedGraph:");
        List<Integer> topSortOrderList = unweightedDirectedGraph.getTopologicalSortOrderList();
        for(int v: topSortOrderList){
            System.out.print(v + " ");
        }


        DirectedGraph weightedDirectedGraph = new DirectedGraph();
        weightedDirectedGraph.addWeightedEdge(0, 1, 1);
        weightedDirectedGraph.addWeightedEdge(1, 2,3);
        weightedDirectedGraph.addWeightedEdge(2, 3,4);
        weightedDirectedGraph.addWeightedEdge(1, 3,2);
        System.out.println();
        System.out.println("ShortestPathUsingTopologicalSortOrder for Weighted Directed Acyclic Graph from source 2 is");

        weightedDirectedGraph.findShortestPathUsingTopologicalSort(1);
    }

}
