package com.ufcg.atg.graph;

import java.util.*;

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
        addIfAbsent(v1);
        addIfAbsent(v2);
        Edge<V> edgeToReturn = new Edge<>(v1, v2),
                reverseEdge = new Edge<>(v2, v1);
        boolean added = vertexes.get(v1).add(edgeToReturn);
        if (!added) {
            throw new RuntimeException();
        }
        vertexes.get(v2).add(reverseEdge);
        return edgeToReturn;
    }

    @Override
    protected float getEdgeWeight(Edge<V> e) {
        return EDGE_WEIGHT;
    }

    @Override
    protected String mapOperatorListRepresentation(Edge<V> e) {
        return e.getTargetVertex().toString();
    }

    @Override
    public String shortestPath(V v1, V v2) {
        if (v1.equals(v2)) return v1.toString();
        Map<V, Float> distances = new HashMap<>();
        Map<V, V> predecessors = new HashMap<>();
        setUpShortestPath(v1, distances, predecessors);
        Queue<V> priorityQueue = new PriorityQueue<>((o1, o2) ->
                Float.compare(distances.get(o1), distances.get(o2)));
        priorityQueue.addAll(getAllVertexes());

        while (!priorityQueue.isEmpty()) {
            V current = priorityQueue.poll();
            if (current.equals(v2)) break;
            for (Edge<V> e: getEdgesOfVertex(current)) {
                V adjacent = e.getTargetVertex();
                relax(current, adjacent, e, distances, predecessors);
            }
        }

        return setUpShortestPathString(v1, v2, predecessors);
    }

}
