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
     * @return The added weighted edge.
     */
    E addEdge(V v1, V v2, float weight);

}
