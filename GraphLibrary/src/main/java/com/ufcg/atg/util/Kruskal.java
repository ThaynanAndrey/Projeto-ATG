package com.ufcg.atg.util;

import java.util.*;

import com.ufcg.atg.graph.Edge;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;

/**
 * Implementation of the Kruskal algorithm with the help of the union-find 
 * implementation of the joint-disjoint structure.
 * @author Wesley Almeida
 *
 * @param <V> Type of the vertex.
 * @param <E> Type of the edge.
 */
public class Kruskal<V extends Comparable<V>, E extends Edge<V>>{
	
	
	private Set<V> vertexes;
	private Set<E> edges;
	
	private List<E> edgesMst;
	private Map<V,V> subEdges;
	
	private Iterator<V> vertexeIterator;
	
	/**
     * Constructs a {@link Kruskal}.
     * @param vertexes Set of vertexes of the graph.
     * @param edges Set of edges of the graph
     */
	public Kruskal(Set<V> vertexes, Set<E> edges){
		this.vertexes = vertexes;
		this.edges = edges;
		
		this.edgesMst = new ArrayList<>();
		this.subEdges = new HashMap<>();
		
		this.vertexeIterator = this.vertexes.iterator();
	}
	
	/**
	 * Determines whether the vertex is already grouped to a subset, using recursion,
	 * and if so verifies in the sub set of this vertex until finding
	 * a vertex of the non-associated subset
	 * @param subEdges subset of the vertexes of the graph with its associations.
	 * @param vertexe vertex to be checked in the subset
	 * @return associated vertex
	 */
	private V find(Map<V,V> subEdges, V vertexe) {
		if(subEdges.get(vertexe) == null) {
			return vertexe;
		}
		return this.find(subEdges, subEdges.get(vertexe));
	}
	
	/**
	 * Combines two vertexes creating a subset, which is the relationship between those vertices.
	 * @param subEdges subset of the vertexes of the graph with its associations.
	 * @param originVertexe The origin vertex of the edge.
	 * @param targetVertexe The target vertex of the edge.
	 */
	private void union(Map<V,V> subEdges, V originVertexe, V targetVertexe) {
		V originVertexePut = this.find(subEdges, originVertexe);
		V targetVertexePut = this.find(subEdges, targetVertexe);
		subEdges.put(originVertexePut, targetVertexePut);
	}
	
	/**
	 * Algorithm of kruskal, runs through the ordered set of edges of the graph,
	 * adding to the final set of minimal spanning tree in case the edge does not form a cycle.
	 * @return minimal spanning tree(MST) in string representation.
	 */
	public String kruskal() {
		while(this.vertexeIterator.hasNext()) {
			this.subEdges.put(this.vertexeIterator.next(), null);
		}

		List<E> listEdges = new ArrayList<>(this.edges);
		Collections.sort(listEdges);
	
		for(int i=0; i < listEdges.size(); i++) {
			E egdeOperation = listEdges.get(i);
			V originVertexe = this.find(subEdges, egdeOperation.getOriginVertex());
			V targetVertexe = this.find(subEdges, egdeOperation.getTargetVertex());
			
			/**
			 * if the vertices are equal, it means that they share the same subset,
			 *forming a cycle.
			 */
			if(!originVertexe.equals(targetVertexe)) {
				this.edgesMst.add(egdeOperation);
				this.union(subEdges, originVertexe, targetVertexe);
			}
		}
		return this.kruskalToString();		
	}
	
	/**
	 * Generates the string representing minimal spanning tree
	 * @return minimal spanning tree(MST) in string representation.
	 */
	private String kruskalToString() {
		String result = "";
		for(int i=0; i < this.edgesMst.size(); i++) {
			result += this.edgesMst.get(i).toString()+LINE_SEPARATOR;
		}
		return result;
	}
}