package com.ufcg.atg.graph;

/**
 * Represents a implementation of a undirected and unweighted graph, based on
 * the interface defined by {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 *
 * @author Vélmer Oliveira
 */
public class Graph<V> extends BaseGraph<V, Edge<V>> implements IGraph<V, Edge<V>> {

    private static final int EDGE_WEIGHT = 1;

    /**
     * Constructs a {@link Graph}.
     */
    public Graph() {
        super();
    }

    @Override
    protected float getEdgeWeight(Edge<V> e) {
        return EDGE_WEIGHT;
    }

    @Override
    public String shortestPath(V v1, V v2) {
        return null;
    }

}
