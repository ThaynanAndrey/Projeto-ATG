package com.ufcg.atg.library;

import com.ufcg.atg.graph.BaseGraph;
import com.ufcg.atg.graph.Graph;
import com.ufcg.atg.graph.RepresentationType;
import com.ufcg.atg.graph.WeightedGraph;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Library to Graphs and Weighted Graphs.
 *
 * @author Thaynan Nunes.
 */
public class GraphLibrary {

    private static final String EMPTY_SPACE_STRING_EDGE = " ";
    private static final int INDEX_FIRST_VERTEX_ON_FILE = 0;
    private static final int INDEX_SECOND_VERTEX_ON_FILE = 1;
    private static final int INDEXES_WEIGHT_ON_FILE = 2;

    /**
     * Add edge in {@link Graph}.
     *
     * @param graph Graph to be placed on the edge.
     * @param edge Edge to be inserted.
     */
    private void addEdgeGraph(BaseGraph graph, String edge) {
        String[] vertexes = edge.split(EMPTY_SPACE_STRING_EDGE);
        Integer v1 = Integer.parseInt(vertexes[INDEX_FIRST_VERTEX_ON_FILE]);
        Integer v2 = Integer.parseInt(vertexes[INDEX_SECOND_VERTEX_ON_FILE]);
        graph.addEdge(v1, v2);
    }

    /**
     * Add weighted edge in {@link WeightedGraph}.
     *
     * @param graph Graph to be placed on the edge.
     * @param edge Edge to be inserted.
     */
    private void addEdgeWeightedGraph(WeightedGraph graph, String edge) {
        String[] vertexes = edge.split(EMPTY_SPACE_STRING_EDGE);
        Integer v1 = Integer.parseInt(vertexes[INDEX_FIRST_VERTEX_ON_FILE]);
        Integer v2 = Integer.parseInt(vertexes[INDEX_SECOND_VERTEX_ON_FILE]);
        Float weight = Float.parseFloat(vertexes[INDEXES_WEIGHT_ON_FILE]);
        graph.addEdge(v1, v2, weight);
    }

    /**
     * Reads the path file and places the edges found in the graph.
     * Analyse, if the graph is weighted, so places the weighted edges
     * on {@link WeightedGraph}, else places the edges on {@link Graph}.
     *
     * @param graph Graph to be placed on the edges.
     * @param path Path from where the edges will be obtained.
     */
    private void readFile(BaseGraph graph, String path) {
        boolean isWeightedGraph = graph instanceof WeightedGraph;
        try {
            FileReader file = new FileReader(path);
            BufferedReader bfFile = new BufferedReader(file);

            String currentLine = bfFile.readLine();
            Integer edgesAmount = Integer.parseInt(currentLine);
            for (int i=0; i < edgesAmount; i++) {
                String edge = bfFile.readLine();
                if(isWeightedGraph) {
                    addEdgeWeightedGraph((WeightedGraph) graph, edge);
                } else {
                    addEdgeGraph(graph, edge);
                }
            }
            file.close();
        } catch (IOException e) {
            System.err.println("There was an error opening the file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a graph from file and returns it.
     *
     * @param path File path.
     * @return Read graph.
     */
    public BaseGraph readGraph(String path) {
        Graph<Integer> graph = new Graph<>();
        readFile(graph, path);
        return graph;
    }

    /**
     * Reads a weighted graph from file and returns it.
     *
     * @param path File path.
     * @return Read graph.
     */
    public BaseGraph readWeightedGraph(String path) {
        WeightedGraph<Integer> graph = new WeightedGraph<>();
        readFile(graph, path);
        return graph;
    }

    /**
     * Returns the number of vertexes of the graph.
     *
     * @param graph Graph to be obtained vertexes' number.
     * @return Number of vertexes of the graph.
     */
    public int getVertexNumber(BaseGraph graph) {
        return  graph.getVertexesNumber();
    }

    /**
     * Returns the number of edges of the graph.
     *
     * @param graph Graph to be obtained edges' number.
     * @return Number of edges of the graph.
     */
    public int getEdgeNumber(BaseGraph graph) {
        return graph.getEdgesNumber();
    }

    /**
     * Returns the mean edge of the graph.
     *
     * @param graph Graph to be obtained mean edge.
     * @return Mean edge of the graph.
     */
    public float getMeanEdge(BaseGraph graph) {
        return graph.getMeanEdge();
    }

    /**
     * Returns the graph representation based on the {@link RepresentationType}
     * specified.
     *
     * @param type Type of the representation to be returned.
     * @throws RuntimeException If the representation type isn't supported.
     * @return The graph representation.
     */
    public String graphRepresentation(BaseGraph graph, RepresentationType type) {
        return graph.graphRepresentation(type);
    }

    /**
     * Returns a tree representation based on the Breadth-first Search (BFS)
     * algorithm starting from the vertex specified.
     *
     * @param graph Graph to be obtained BFS.
     * @param v The vertex to be the root of the returned tree.
     * @throws RuntimeException If the vertex specified isn't in the graph.
     * @return BFS of the graph.
     */
    public String BFS(BaseGraph graph, Integer v) {
        return graph.BFS(v);
    }

    /**
     * Returns a tree representation based on the Depth-first Search (DFS)
     * algorithm starting from the vertex specified.
     *
     * @param graph Graph to be obtained BFS.
     * @param v The vertex to be the root of the returned tree.
     * @throws RuntimeException If the vertex specified doesn't belong to
     * the graph.
     * @return DFS of the graph.
     */
    public String DFS(BaseGraph graph, Integer v) {
        return graph.DFS(v);
    }

    /**
     * Returns a representation of all strongly connected components (SCC)
     * of the graph.
     *
     * @param graph Graph to be obtained SCC.
     * @return All SCCs of the graph.
     */
    public String SCC(BaseGraph graph) {
        return graph.SCC();
    }

    /**
     * Returns a representation of the shortest path between the specified
     * vertexes.
     *
     * @param graph Graph to be obtained shortest path between v1 and v2.
     * @param v1 Origin vertex of the path.
     * @param v2 Target vertex of the path.
     * @throws RuntimeException If {@code v1} or {@code v2} doesn't belong
     * to the graph.
     * @return Shortest path between {@code v1} e {@code v2}.
     */
    public String shortestPath(BaseGraph graph, Integer v1, Integer v2) {
        return graph.shortestPath(v1, v2);
    }

    /**
     * Returns a representation of the Minimum Spanning Tree (MST) of the
     * graph.
     *
     * @param graph Graph to be obtained MST.
     * @return MST of the graph.
     */
    String MST(BaseGraph graph) {
        return graph.MST();
    }
}