package graph;

public interface IWeightedGraph<V> extends IGraph<V> {

    @Override
    WeightedEdge addEdge(V v1, V v2);

}
