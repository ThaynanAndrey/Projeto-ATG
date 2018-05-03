package graph;

public interface IWeightedGraph extends IGraph {

    @Override
    WeightedEdge addEdge(int v1, int v2);
}
