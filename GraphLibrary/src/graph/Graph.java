package graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Represent a graph entity with your set of vertexes and set of edges.  
 * 
 * @author Thaynan Nunes
 */
public class Graph {

	protected Set<Vertex> vertexes;
	
	public Graph() {
		vertexes = new HashSet<>();
	}
	
	public Set<Vertex> getVertexes() {
		return vertexes;
	}

	public void setVertexes(Set<Vertex> vertexes) {
		this.vertexes = vertexes;
	}

	public boolean addVertex(Vertex vertex) {
		return vertexes.add(vertex);
	}
	
	public boolean removeVertex(Vertex vertex) {
		// TODO: Ao remover, removê-lo como vizinho dos outros vértices.
		return vertexes.remove(vertex);
	}
	
	public void conectVertexes(Vertex vertex1, Vertex vertex2) {
		if(vertexes.contains(vertex1) && vertexes.contains(vertex2)) {
			vertex1.addNeighbour(vertex2);
			vertex2.addNeighbour(vertex1);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertexes == null) ? 0 : vertexes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (vertexes == null) {
			if (other.vertexes != null)
				return false;
		} else if (!vertexes.equals(other.vertexes))
			return false;
		return true;
	}
}