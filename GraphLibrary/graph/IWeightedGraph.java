package graph;

/**
 * Defines the interface of a undirected and weighted graph, extending the
 * common methods from {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 *
 * @author Vélmer Oliveira
 */
public interface IWeightedGraph<V> extends IGraph<V> {

    /**
     * Adds a weighted edge to the graph. {@code v1} is going to be the edge
     * origin and {@code v2} the edge target. As the weight wasn't specified,
     * it will be defined as zero.
     *
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @return The added weighted edge.
     */
    @Override
    WeightedEdge addEdge(V v1, V v2);

    /**
     * Adds a weighted edge to the graph. {@code v1} is going to be the edge
     * origin and {@code v2} the edge target.
     *
     * @param v1 Vertex to be the edge origin.
     * @param v2 Vertex to be the edge target.
     * @param weight The weight of the edge.
     * @return The added weighted edge.
     */
    WeightedEdge addEdge(V v1, V v2, float weight);

}
