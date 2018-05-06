package com.ufcg.atg.graph;

/**
 * Represents a implementation of a undirected and weighted graph, based on
 * the interface defined by {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 *
 * @author Vélmer Oliveira
 */
public class WeightedGraph<V extends Comparable<V>> extends BaseGraph<V, WeightedEdge<V>>
        implements IWeightedGraph<V, WeightedEdge<V>> {

    /**
     * Constructs a {@link WeightedGraph}.
     */
    public WeightedGraph() {
        super();
    }

    @Override
    public WeightedEdge<V> addEdge(V v1, V v2) {
        return null;
    }

    @Override
    public WeightedEdge<V> addEdge(V v1, V v2, float weight) {
        return null;
    }

    @Override
    protected float getEdgeWeight(WeightedEdge<V> e) {
        return e.getWeight();
    }

    @Override
    public String shortestPath(V v1, V v2) {
        return null;
    }

}
