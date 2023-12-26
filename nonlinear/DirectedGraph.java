package nonlinear;

public class DirectedGraph extends Graph{

    /**
     * Adds an edge from v1 to v2. Creates new vertices if not exists or else uses existing ones.
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

    /**Prints the Vertices in the Topological Order
     * Topological sorting is a linear ordering of vertices in a graph such that for every directed edge UV from vertex U to vertex V, U comes before V in the ordering.
     * This concept is independent of whether a graph is weighted or unweighted, but it should be Directed
     */
    void printTopologicalSort(){

    }

    /**
     * Finds the shortest path using Topological Sort approach.
     * Topological sorting is a linear ordering of vertices in a graph such that for every directed edge UV from vertex U to vertex V, U comes before V in the ordering.
     * This concept is independent of whether a graph is weighted or unweighted, but it should be Directed
     * Note: Applies only for Directed Acyclic Graph (DAG's)
     *  @param source
     */
    void findShortestPathUsingTopologicalSort(int source){
        //TODO
    }

    void findShortestPathUsingDijkstra(int source){
        //TODO
    }

    void findShortestPathUsingBellmanFord (int source){
        //TODO
    }
}
