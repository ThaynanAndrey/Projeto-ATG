package graph;

/**
 * Defines the interface of a undirected and weighted Graph. Extending the
 * common methods from {@link IGraph}.
 *
 * @param <V> Type of the Vertex.
 *
 * @author Vélmer Oliveira
 */
public interface IWeightedGraph<V> extends IGraph<V> {

    @Override
    WeightedEdge addEdge(V v1, V v2);

}
