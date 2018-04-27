package graph;

import java.util.HashSet;
import java.util.Set;

public class Vertex {

	private final int SEM_PESO = 0;
	
	private Set<Neighbour> neighbours;
	private int number;
	
	public Vertex() {
		neighbours = new HashSet<>();
	}

	public Vertex(int number) {
		super();
		this.number = number;
	}

	public Set<Neighbour> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(Set<Neighbour> neighbours) {
		this.neighbours = neighbours;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public void addNeighbour(Vertex vertex) {
		Neighbour neighbour = new Neighbour(vertex, SEM_PESO);
		this.neighbours.add(neighbour);
	}
	
	public void addNeighbourWeighted(Vertex vertex, int weight) {
		Neighbour neighbour = new Neighbour(vertex, weight);
		this.neighbours.add(neighbour);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((neighbours == null) ? 0 : neighbours.hashCode());
		result = prime * result + number;
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
		Vertex other = (Vertex) obj;
		if (neighbours == null) {
			if (other.neighbours != null)
				return false;
		} else if (!neighbours.equals(other.neighbours))
			return false;
		if (number != other.number)
			return false;
		return true;
	}
}