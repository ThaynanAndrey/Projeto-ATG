package graph;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseGraph<T extends Edge> implements IGraph {

    protected Set<T> edges;

    protected int vertexesNumber;

    public BaseGraph() {
        edges = new HashSet<>();
        vertexesNumber = 0;
    }

    @Override
    public int getVertexesNumber() {
        return vertexesNumber;
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

        BaseGraph<?> baseGraph = (BaseGraph<?>) o;

        if (vertexesNumber != baseGraph.vertexesNumber) return false;
        return edges != null ? edges.equals(baseGraph.edges) : baseGraph.edges == null;
    }

    @Override
    public int hashCode() {
        int result = edges != null ? edges.hashCode() : 0;
        result = 31 * result + vertexesNumber;
        return result;
    }

}
