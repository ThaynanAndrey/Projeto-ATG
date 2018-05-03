package graph;

public interface IGraph {

    Edge addEdge(int v1, int v2);

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
