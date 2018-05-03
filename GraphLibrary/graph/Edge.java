package graph;

import java.util.Objects;

public class Edge<V> {

    protected V outVertex;

    protected V inVertex;

    public Edge(V outVertex, V inVertex) {
        this.outVertex = outVertex;
        this.inVertex = inVertex;
    }

    public V getOutVertex() {
        return outVertex;
    }

    public void setOutVertex(V outVertex) {
        this.outVertex = outVertex;
    }

    public V getInVertex() {
        return inVertex;
    }

    public void setInVertex(V inVertex) {
        this.inVertex = inVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(outVertex, edge.outVertex) &&
                Objects.equals(inVertex, edge.inVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outVertex, inVertex);
    }

}
