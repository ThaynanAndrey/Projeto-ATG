package com.ufcg.atg.graph;

/**
 * Defines the interface of a undirected and weighted graph, extending the
 * common methods from {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 * @param <E> Type of the edge.
 *
 * @author Vélmer Oliveira
 */
public interface IWeightedGraph<V extends Comparable<V>, E extends WeightedEdge<V>> extends IGraph<V, E> {

    /**
     * Adds a weighted edge to the graph. {@code v1} is going to be the edge
     * origin and {@code v2} the edge target. As the weight wasn't specified,
     * it will be defined as one (1).
     *
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @throws RuntimeException If already there is a edge connecting {@code v1}
     * and {@code v2}.
     * @return The added weighted edge.
     */
    @Override
    E addEdge(V v1, V v2);

    /**
     * Adds a weighted edge to the graph. {@code v1} is going to be the edge
     * origin and {@code v2} the edge target.
     *
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @param weight The weight of the edge.
     * @throws RuntimeException If already there is a edge connecting {@code v1}
     * and {@code v2}.
     * @return The added weighted edge.
     */
    E addEdge(V v1, V v2, float weight);

    /**
     * Returns a representation of the shortest path between the specified
     * vertexes. As this is an undirected graph, there's no way to find the
     * shortest path in a graph with negative weighted edges, because these
     * ones would represent a negative circle.
     *
     * @param v1 Origin vertex of the path.
     * @param v2 Target vertex of the path.
     * @throws RuntimeException If {@code v1} or {@code v2} doesn't belong
     * to the graph.
     * @return Shortest path between {@code v1} e {@code v2}.
     */
    String shortestPath(V v1, V v2);

}
