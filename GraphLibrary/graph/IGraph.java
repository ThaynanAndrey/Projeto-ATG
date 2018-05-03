package graph;

public interface IGraph<V> {

    void addVertex(V v);

    Edge addEdge(V v1, V v2);

    int getVertexesNumber();

    int getEdgesNumber();

    float getMeanEdge();

    String graphRepresentation(RepresentationType representationType);

    String BFS();

    String DFS();

    String SCC();

    String shortestPath(int v1, int v2);

    String MST();

}
