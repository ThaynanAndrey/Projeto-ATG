package graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class BaseGraph<V, E extends Edge<V>> implements IGraph<V> {

    protected Set<E> edges;

    protected Set<V> vertexes;

    public BaseGraph() {
        edges = new HashSet<>();
        vertexes = new HashSet<>();
    }

    @Override
    public void addVertex(V v) {

    }

    @Override
    public Edge addEdge(V v1, V v2) {
        return null;
    }

    @Override
    public int getVertexesNumber() {
        return vertexes.size();
    }

    @Override
    public int getEdgesNumber() {
        return edges.size();
    }

    @Override
    public float getMeanEdge() {
        return getVertexesNumber() > 0 ? ((2*getEdgesNumber()) / getVertexesNumber()) : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseGraph<?, ?> baseGraph = (BaseGraph<?, ?>) o;
        return Objects.equals(edges, baseGraph.edges) &&
                Objects.equals(vertexes, baseGraph.vertexes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edges, vertexes);
    }

}
