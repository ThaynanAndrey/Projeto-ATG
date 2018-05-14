package com.ufcg.atg.graph;

import com.ufcg.atg.util.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    private static final float EDGE_DEFAULT_WEIGHT = 1f;

    /**
     * Constructs a {@link WeightedGraph}.
     */
    public WeightedGraph() {
        super();
    }

    @Override
    public WeightedEdge<V> addEdge(V v1, V v2) {
        return addEdge(v1, v2, EDGE_DEFAULT_WEIGHT);
    }

    @Override
    public WeightedEdge<V> addEdge(V v1, V v2, float weight) {
        addIfAbsent(v1);
        addIfAbsent(v2);
        WeightedEdge<V> edgeToReturn = new WeightedEdge<>(v1, v2, weight),
                reverseEdge = new WeightedEdge<>(v2, v1, weight);
        boolean added = vertexes.get(v1).add(edgeToReturn);
        if (!added) {
            throw new RuntimeException();
        }
        vertexes.get(v2).add(reverseEdge);
        return edgeToReturn;
    }

    @Override
    protected float getEdgeWeight(WeightedEdge<V> e) {
        return e.getWeight();
    }

    @Override
    protected String mapOperatorListRepresentation(WeightedEdge<V> e) {
        return e.getTargetVertex().toString() + "(" + Utils.floatToString(getEdgeWeight(e)) + ")";
    }

    @Override
    public String shortestPath(V v1, V v2) {
        if (v1.equals(v2)) return v1.toString();
        Map<V, Float> distances = new HashMap<>();
        Map<V, V> predecessors = new HashMap<>();
        setUpShortestPath(v1, distances, predecessors);
        Set<V> allVertexes = getAllVertexes();
        Set<WeightedEdge<V>> allEdges = getAllEdges();

        for (V v: allVertexes) {
            if (v.equals(v1)) continue;
            for (WeightedEdge<V> e: allEdges) {
                relax(e.getOriginVertex(), e.getTargetVertex(), e,
                        distances, predecessors);
            }
        }
        for (WeightedEdge<V> e: allEdges) {
            if (distances.get(e.getTargetVertex()) > (distances.get(e.getOriginVertex())
                    + getEdgeWeight(e))) {
                throw new RuntimeException("It's not possible to find the shortest" +
                        " path in a graph with negative cicle.");
            }
        }

        return setUpShortestPathString(v1, v2, predecessors);
    }

}
