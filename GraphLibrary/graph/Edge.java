package graph;

import java.util.Objects;

/**
 * Represents a implementation of a edge.
 *
 * @param <V> Type of the origin and target vertexes.
 *
 * @author Vélmer Oliveira
 */
public class Edge<V> {

    protected V originVertex;

    protected V targetVertex;

    /**
     * Constructs a {@link Edge}.
     *
     * @param originVertex The origin vertex of the edge.
     * @param targetVertex The target vertex of the edge.
     */
    public Edge(V originVertex, V targetVertex) {
        this.originVertex = originVertex;
        this.targetVertex = targetVertex;
    }

    public V getOriginVertex() {
        return originVertex;
    }

    public void setOriginVertex(V originVertex) {
        this.originVertex = originVertex;
    }

    public V getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(V targetVertex) {
        this.targetVertex = targetVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(originVertex, edge.originVertex) &&
                Objects.equals(targetVertex, edge.targetVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originVertex, targetVertex);
    }

}
