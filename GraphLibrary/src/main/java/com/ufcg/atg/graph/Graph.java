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
            for (Edge<V> e: getVertexsEdges(current)) {
                V adjacent = e.getTargetVertex();
                relax(current, adjacent, e, distances, predecessors);
            }
        }

        return setUpShortestPathString(v1, v2, predecessors);
    }

    /**
     * Sets up the shortest path between {@code pathStart} and {@code pathEnd}
     * string representation.
     *
     * @param pathStart Vertex that starts the path.
     * @param pathEnd Vertex that ends the path.
     * @param predecessors {@link Map} that links a vertex to its predecessor.
     * @return Shortest path string representation.
     */
    private String setUpShortestPathString(V pathStart, V pathEnd, Map<V, V> predecessors) {
        StringBuilder shortestPath = new StringBuilder(pathEnd.toString());
        V predecessor = predecessors.get(pathEnd);
        V lastAddedPredecessor = predecessor;
        while(predecessor != null) {
            StringBuilder currentSB = new StringBuilder(predecessor.toString());
            shortestPath = currentSB.append(" ").append(shortestPath);
            lastAddedPredecessor = predecessor;
            predecessor = predecessors.get(predecessor);
        }
        if (lastAddedPredecessor == null || !lastAddedPredecessor.equals(pathStart)) {
            throw new RuntimeException("There isn't a path between " + pathStart
                    + " and " + pathEnd);
        }
        return shortestPath.toString();
    }

    /**
     * Relaxes the {@link Edge} between vertexes {@code originVertex} and
     * {@code targetVertex}. Only if the distance between
     * {@code originVertex} and {@code targetVertex} is lesser than the
     * distance already leaded to {@code targetVertex}.
     *
     * @param originVertex Origin vertex of the {@link Edge}.
     * @param targetVertex Target vertex of the {@link Edge}.
     * @param e Edge from {@code originVertex} to {@code targetVertex}.
     * @param distances {@link Map} that links a vertex to its distance to origin
     *                             of the shortest path.
     * @param predecessors {@link Map} that links a vertex to its predecessor.
     */
    private void relax(V originVertex, V targetVertex, Edge<V> e, Map<V, Float> distances,
                       Map<V, V> predecessors) {
        Float targetVertexDistance = distances.get(targetVertex),
              originVertexDistance = distances.get(originVertex),
              edgeWeight = getEdgeWeight(e);
        if (targetVertexDistance > (originVertexDistance + edgeWeight)) {
            predecessors.put(targetVertex, originVertex);
            distances.replace(targetVertex, distances.get(originVertex) + edgeWeight);
        }
    }

}
