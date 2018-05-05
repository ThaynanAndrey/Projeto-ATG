package com.ufcg.atg.graph;

/**
 * Defines the interface of a undirected and unweighted src.main.java.com.ufcg.atg.graph.
 *
 * @param <V> Type of the vertex.
 * @param <E> Type of the edge.
 *
 * @author Vélmer Oliveira
 */
public interface IGraph<V, E extends Edge<V>> {

    /**
     * Adds a vertex to the src.main.java.com.ufcg.atg.graph.
     *
     * @param v Vertex to be added.
     * @throws RuntimeException If the vertex is already on the src.main.java.com.ufcg.atg.graph.
     */
    void addVertex(V v);

    /**
     * Adds a edge to the src.main.java.com.ufcg.atg.graph. {@code v1} is going to be the edge origin
     * and {@code v2} the edge target.
     *
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @return The added edge.
     */
    E addEdge(V v1, V v2);

    /**
     * Returns the number of vertexes of the src.main.java.com.ufcg.atg.graph.
     *
     * @return Number of vertexes of the src.main.java.com.ufcg.atg.graph.
     */
    int getVertexesNumber();

    /**
     * Returns the number of edges of the src.main.java.com.ufcg.atg.graph.
     *
     * @return Number of edges of the src.main.java.com.ufcg.atg.graph.
     */
    int getEdgesNumber();

    /**
     * Returns the mean edge of the src.main.java.com.ufcg.atg.graph.
     *
     * @return Mean edge of the src.main.java.com.ufcg.atg.graph.
     */
    float getMeanEdge();

    /**
     * Returns the src.main.java.com.ufcg.atg.graph representation based on the {@link RepresentationType}
     * specified.
     *
     * @param representationType Type of the representation to be returned.
     * @return The src.main.java.com.ufcg.atg.graph representation.
     */
    String graphRepresentation(RepresentationType representationType);

    /**
     * Returns a tree representation based on the Breadth-first Search (BFS)
     * algorithm starting from the vertex specified.
     *
     * @param v The vertex to be the root of the returned tree.
     * @throws RuntimeException If the vertex specified isn't in the src.main.java.com.ufcg.atg.graph.
     * @return BFS of the src.main.java.com.ufcg.atg.graph.
     */
    String BFS(V v);

    /**
     * Returns a tree representation based on the Depth-first Search (DFS)
     * algorithm starting from the vertex specified.
     *
     * @param v The vertex to be the root of the returned tree.
     * @throws RuntimeException If the vertex specified doesn't belong to
     * the src.main.java.com.ufcg.atg.graph.
     * @return DFS of the src.main.java.com.ufcg.atg.graph.
     */
    String DFS(V v);

    /**
     * Returns a representation of all strongly connected components (SCC)
     * of the src.main.java.com.ufcg.atg.graph.
     *
     * @return All SCCs of the src.main.java.com.ufcg.atg.graph.
     */
    String SCC();

    /**
     * Returns a representation of the shortest path between the specified
     * vertexes.
     *
     * @param v1 Origin vertex of the path.
     * @param v2 Target vertex of the path.
     * @throws RuntimeException If {@code v1} or {@code v2} doesn't belong
     * to the src.main.java.com.ufcg.atg.graph.
     * @return Shortest path between {@code v1} e {@code v2}.
     */
    String shortestPath(V v1, V v2);

    /**
     * Returns a representation of the Minimum Spanning Tree (MST) of the
     * src.main.java.com.ufcg.atg.graph.
     *
     * @return MST of the src.main.java.com.ufcg.atg.graph.
     */
    String MST();

}