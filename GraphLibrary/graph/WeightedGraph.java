package graph;

/**
 * Represents a implementation of a undirected and weighted graph, based on
 * the interface defined by {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 *
 * @author Vélmer Oliveira
 */
public class WeightedGraph<V> extends BaseGraph<V, WeightedEdge<V>>
        implements IWeightedGraph<V, WeightedEdge<V>> {

    /**
     * Constructs a {@link WeightedGraph}.
     */
    public WeightedGraph() {
        super();
    }

    @Override
    public WeightedEdge<V> addEdge(V v1, V v2) {
        return null;
    }

    @Override
    public WeightedEdge<V> addEdge(V v1, V v2, float weight) {
        return null;
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
