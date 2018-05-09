package com.ufcg.atg.graph;

/**
 * Represents a implementation of a undirected and unweighted graph, based on
 * the interface defined by {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 *
 * @author Vélmer Oliveira
 */
public class Graph<V extends Comparable<V>> extends BaseGraph<V, Edge<V>> implements IGraph<V, Edge<V>> {

    private static final int EDGE_WEIGHT = 1;

    /**
     * Constructs a {@link Graph}.
     */
    public Graph() {
        super();
    }

    @Override
    public Edge<V> addEdge(V v1, V v2) {
        if (!vertexes.contains(v1)) {
            addVertex(v1);
        }
        if (!vertexes.contains(v2)) {
            addVertex(v2);
        }
        Edge<V> edgeToReturn = new Edge<>(v1, v2),
                otherEdge    = new Edge<>(v2, v1);
        edges.add(edgeToReturn);
        edges.add(otherEdge);
        return edgeToReturn;
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
