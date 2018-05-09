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

    /**
     * Constructs a {@link Graph}.
     */
    public Graph() {
        super();
    }

    @Override
    public Edge<V> addEdge(V v1, V v2) {
        addIfAbsent(v1);
        addIfAbsent(v2);
        Edge<V> edgeToReturn = new Edge<>(v1, v2),
                reverseEdge = new Edge<>(v2, v1);
        vertexes.get(v1).add(edgeToReturn);
        vertexes.get(v2).add(reverseEdge);
        return edgeToReturn;
    }

    @Override
    public String graphRepresentation(RepresentationType representationType) {
        return null;
    }

    @Override
    public String shortestPath(V v1, V v2) {
        return null;
    }

}
