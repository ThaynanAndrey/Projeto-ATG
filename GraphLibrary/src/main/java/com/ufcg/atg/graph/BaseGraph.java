package com.ufcg.atg.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a skeletal implementation of a graph, based on the interface
 * defined by {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 * @param <E> Type of the edge.
 *
 * @author Vélmer Oliveira
 */
public abstract class BaseGraph<V extends Comparable<V>, E extends Edge<V>> implements IGraph<V, E> {

    protected Map<V, Set<E>> vertexes;

    /**
     * Constructs a {@link BaseGraph}.
     */
    public BaseGraph() {
        vertexes = new HashMap<>();
    }

    @Override
    public void addVertex(V v) {
        if (v == null) {
            throw new IllegalArgumentException();
        }
        if (vertexes.containsKey(v)) {
            throw new RuntimeException();
        }
        addIfAbsent(v);
    }

    /**
     * Adds a vertex if isn't already on the graph.
     *
     * @param v Vertex to be added.
     */
    protected void addIfAbsent(V v) {
        if (!vertexes.containsKey(v)) {
            vertexes.put(v, new HashSet<>());
        }
    }

    @Override
    public Set<V> getAllVertexes() {
        return vertexes.keySet();
    }

    @Override
    public Set<E> getAllEdges() {
        return vertexes.values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<E> getVertexsEdges(V v) {
        return vertexes.get(v);
    }

    @Override
    public int getVertexesNumber() {
        return vertexes.size();
    }

    @Override
    public int getEdgesNumber() {
        return getAllEdges().size();
    }

    @Override
    public float getMeanEdge() {
        return getVertexesNumber() > 0 ? ((2*getEdgesNumber()) / getVertexesNumber()) : 0;
    }

    @Override
    public String BFS(V v) {
        return null;
    }

    @Override
    public String DFS(V v) {
        return null;
    }

    @Override
    public String SCC() {
        return null;
    }

    @Override
    public String MST() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseGraph<?, ?> baseGraph = (BaseGraph<?, ?>) o;
        return Objects.equals(vertexes, baseGraph.vertexes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexes);
    }

}
