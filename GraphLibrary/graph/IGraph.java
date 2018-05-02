package graph;

public interface IGraph {

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
