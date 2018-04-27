package graph;

public class WeightedGraph extends Graph {

	public WeightedGraph() {
		super();
	}
	
	public void conectVertexesWithWeight(Vertex vertex1, Vertex vertex2, int weight) {
		if(super.vertexes.contains(vertex1) && super.vertexes.contains(vertex2)) {
			vertex1.addNeighbourWeighted(vertex2, weight);
			vertex2.addNeighbourWeighted(vertex1, weight);
		}
	}
}