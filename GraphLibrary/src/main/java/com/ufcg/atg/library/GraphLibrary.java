package com.ufcg.atg.library;

import com.ufcg.atg.graph.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * Library to Graphs and Weighted Graphs.
 *
 * @author Thaynan Nunes.
 */
public class GraphLibrary<V extends Comparable<V>> {

    private static final String EMPTY_SPACE_STRING_EDGE = " ";
    private static final int INDEX_FIRST_VERTEX_ON_FILE = 0;
    private static final int INDEX_SECOND_VERTEX_ON_FILE = 1;
    private static final int INDEXES_WEIGHT_ON_FILE = 2;

    /**
     * Reads a graph from file.
     *
     * @param path File path.
     * @return Read graph.
     */
    public IGraph<Integer, Edge<Integer>> readGraph(String path) {
        IGraph<Integer, Edge<Integer>> graph = new Graph<>();
        readFile(graph, path);
        return graph;
    }

    /**
     * Reads a weighted graph from a file.
     *
     * @param path File path.
     * @return Read graph.
     */
    public IWeightedGraph<Integer, WeightedEdge<Integer>> readWeightedGraph(String path) {
        IWeightedGraph<Integer, WeightedEdge<Integer>> graph = new WeightedGraph<>();
        readFile(graph, path);
        return graph;
    }

    /**
     * Reads the path file and places the edges found in the graph.
     * Analyse, if the graph is weighted, so places the weighted edges
     * on {@link WeightedGraph}, else places the edges on {@link Graph}.
     *
     * @param graph Graph to have a new edges added.
     * @param path Path from where the edges will be obtained.
     */
    private void readFile(IGraph<Integer, ? extends Edge<Integer>> graph, String path) {
        boolean isWeightedGraph = graph instanceof IWeightedGraph;
        try {
            FileReader file = new FileReader(path);
            BufferedReader bfFile = new BufferedReader(file);

            String currentLine = bfFile.readLine();
            Integer edgesAmount = Integer.parseInt(currentLine);
            for (int i=0; i < edgesAmount; i++) {
                String edge = bfFile.readLine();
                if(isWeightedGraph) {
                    addWeightedEdge((IWeightedGraph<Integer, WeightedEdge<Integer>>) graph, edge);
                } else {
                    addEdge((IGraph<Integer, Edge<Integer>>) graph, edge);
                }
            }
            file.close();
        } catch (IOException e) {
            System.err.println("There was an error opening the file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds edge in {@link Graph}.
     *
     * @param graph Graph to have a new edge added.
     * @param edge Edge to be added.
     */
    private void addEdge(IGraph<Integer, Edge<Integer>> graph, String edge) {
        String[] vertexes = edge.split(EMPTY_SPACE_STRING_EDGE);
        Integer v1 = Integer.parseInt(vertexes[INDEX_FIRST_VERTEX_ON_FILE]);
        Integer v2 = Integer.parseInt(vertexes[INDEX_SECOND_VERTEX_ON_FILE]);
        graph.addEdge(v1, v2);
    }

    /**
     * Adds weighted edge in {@link WeightedGraph}.
     *
     * @param graph Graph to have a new edge added.
     * @param edge Edge to be added.
     */
    private void addWeightedEdge(IWeightedGraph<Integer, WeightedEdge<Integer>> graph, String edge) {
        String[] vertexes = edge.split(EMPTY_SPACE_STRING_EDGE);
        Integer v1 = Integer.parseInt(vertexes[INDEX_FIRST_VERTEX_ON_FILE]);
        Integer v2 = Integer.parseInt(vertexes[INDEX_SECOND_VERTEX_ON_FILE]);
        Float weight = Float.parseFloat(vertexes[INDEXES_WEIGHT_ON_FILE]);
        graph.addEdge(v1, v2, weight);
    }

    /**
     * Adds a edge to the graph. {@code v1} is going to be the edge origin and
     * {@code v2} the edge target. As this is an undirected graph, each added
     * edge, internally, will be represented by two edges, one from {@code v1}
     * to {@code v2} and another from {@code v2} to {@code v1}.
     *
     * @param graph Graph to have a new edge added.
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @throws RuntimeException If already there is a edge connecting {@code v1}
     * and {@code v2}.
     * @return The added edge between {@code v1} and {@code v2}.
     */
    public Edge<V> addEdge(IGraph<V, ? extends Edge<V>> graph, V v1, V v2) {
        if (graph.containsEdge(new Edge<V>(v1, v2))) {
            throw new RuntimeException("The graph already contains the specified edge");
        }
        return graph.addEdge(v1, v2);
    }

    /**
     * Adds a weighted edge to the graph. {@code v1} is going to be the edge
     * origin and {@code v2} the edge target. As the weight wasn't specified,
     * it will be defined as one (1).
     *
     * @param graph Graph to have a new weighted edge added.
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @throws RuntimeException If already there is a edge connecting {@code v1}
     * and {@code v2}.
     * @return The added weighted edge.
     */
    public WeightedEdge<V> addEdge(IWeightedGraph<V, ? extends WeightedEdge<V>> graph,
                                   V v1, V v2) {
        if (graph.containsEdge(new WeightedEdge<V>(v1, v2,
                WeightedGraph.EDGE_DEFAULT_WEIGHT))) {
            throw new RuntimeException("The graph already contains the specified edge");
        }
        return graph.addEdge(v1, v2);
    }

    /**
     * Adds a weighted edge to the graph. {@code v1} is going to be the edge
     * origin and {@code v2} the edge target.
     *
     * @param graph Graph to have a new weighted edge added.
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @param weight The weight of the edge.
     * @throws RuntimeException If already there is a edge connecting {@code v1}
     * and {@code v2}.
     * @return The added weighted edge.
     */
    public WeightedEdge<V> addEdge(IWeightedGraph<V, ? extends WeightedEdge<V>> graph,
                                   V v1, V v2, float weight){
        if (graph.containsEdge(new WeightedEdge<V>(v1, v2, weight))) {
            throw new RuntimeException("The graph already contains the specified edge");
        }
        return graph.addEdge(v1, v2, weight);
    }

    /**
     * Adds the specified vertex to the specified graph.
     *
     * @param graph Graph to have a new vertex added.
     * @param v Vertex to be added
     * @throws RuntimeException If the vertex is already on graph.
     */
    public void addVertex(IGraph<V, ? extends Edge<V>> graph, V v) {
        if (graph.containsVertex(v)) {
            throw new RuntimeException("The graph already contains the specified vertex");
        }
        graph.addVertex(v);
    }

    /**
     * Returns all vertexes of the specified graph.
     *
     * @param graph Graph to have all its vertexes returned.
     * @return All vertexes of the specified graph
     */
    public Set<V> getAllVertexes(IGraph<V, ? extends Edge<V>> graph) {
        return graph.getAllVertexes();
    }

    /**
     * Returns all edges of the specified graph.
     *
     * @param graph Graph to have all its edges returned.
     * @return All edges of the specified graph
     */
    public Set<? extends Edge<V>> getAllEdges(IGraph<V, ? extends Edge<V>> graph) {
        return graph.getAllEdges();
    }

    /**
     * Returns all edges of the specified vertex, which belongs to the specified
     * graph.
     *
     * @param v Vertex to have all its edges returned.
     * @return Specified vertex's edges.
     */
    public Set<? extends Edge<V>> getEdgesOfVertex(IGraph<V, ? extends Edge<V>> graph, V v) {
        return graph.getEdgesOfVertex(v);
    }

    /**
     * Returns all adjacent vertexes of the specified vertex, which belongs to
     * the specified graph.
     *
     * @param v Vertex to have all its adjacent vertexes returned.
     * @return Specified vertex's adjacent vertexes.
     */
    public Set<V> getAdjacentVertexes(IGraph<V, ? extends Edge<V>> graph, V v) {
        return graph.getAdjacentVertexes(v);
    }

    /**
     * Returns if the specified graph contains the specified vertex.
     *
     * @param v Vertex that will have its existence in the graph verified.
     * @return {@code true} if contains, {@code false} otherwise.
     */
    public boolean containsVertex(IGraph<V, ? extends Edge<V>> graph, V v) {
        return graph.containsVertex(v);
    }

    /**
     * Returns if the specified graph contains the specified edge.
     *
     * @param e Edge that will have its existence in the graph verified.
     * @return {@code true} if contains, {@code false} otherwise.
     */
    public boolean containsEdge(IGraph<V, ? extends Edge<V>> graph, Edge<V> e) {
        return graph.containsEdge(e);
    }

    /**
     * Returns the number of vertexes of the graph.
     *
     * @param graph Graph to have its vertex number returned.
     * @return Number of vertexes of the graph.
     */
    public int getVertexNumber(IGraph<V, ? extends Edge<V>> graph) {
        return graph.getVertexesNumber();
    }

    /**
     * Returns the number of edges of the graph.
     *
     * @param graph Graph to be obtained edges' number.
     * @return Number of edges of the graph.
     */
    public int getEdgeNumber(IGraph<V, ? extends Edge<V>> graph) {
        return graph.getEdgesNumber();
    }

    /**
     * Returns the mean edge of the graph.
     *
     * @param graph Graph to be obtained mean edge.
     * @return Mean edge of the graph.
     */
    public float getMeanEdge(IGraph<V, ? extends Edge<V>> graph) {
        return graph.getMeanEdge();
    }

    /**
     * Returns the graph representation based on the {@link RepresentationType}
     * specified.
     *
     * @param type Type of the representation to be returned.
     * @return The graph representation.
     */
    public String graphRepresentation(IGraph<V, ? extends Edge<V>> graph, RepresentationType type) {
        return graph.graphRepresentation(type);
    }

    /**
     * Returns a tree representation based on the Breadth-first Search (BFS)
     * algorithm starting from the vertex specified.
     *
     * @param graph Graph to be obtained BFS.
     * @param v The vertex to be the root of the returned tree.
     * @return BFS of the graph.
     */
    public String BFS(IGraph<V, ? extends Edge<V>> graph, V v) {
        return graph.BFS(v);
    }

    /**
     * Returns a tree representation based on the Depth-first Search (DFS)
     * algorithm starting from the vertex specified.
     *
     * @param graph Graph to be obtained BFS.
     * @param v The vertex to be the root of the returned tree.
     * @return DFS of the graph.
     */
    public String DFS(IGraph<V, ? extends Edge<V>> graph, V v) {
        return graph.DFS(v);
    }

    /**
     * Returns a boolean indicating if the graph is connect or not.
     *
     * @param graph Graph to obtain the connectivity.
     * @return Connectivity of the graph.
     */
    public boolean connected(IGraph<V, ? extends Edge<V>> graph) {
        return graph.connected();
    }

    /**
     * Returns a representation of the shortest path between the specified
     * vertexes.
     *
     * @param graph Graph to be obtained shortest path between v1 and v2.
     * @param v1 Origin vertex of the path.
     * @param v2 Target vertex of the path.
     * @return Shortest path between {@code v1} e {@code v2}.
     */
    public String shortestPath(IGraph<V, ? extends Edge<V>> graph, V v1, V v2) {
        return graph.shortestPath(v1, v2);
    }

    /**
     * Returns a representation of the Minimum Spanning Tree (MST) of the
     * graph.
     *
     * @param graph Graph to be obtained MST.
     * @return MST of the graph.
     */
    String MST(IGraph<V, ? extends Edge<V>> graph) {
        return graph.MST();
    }
}