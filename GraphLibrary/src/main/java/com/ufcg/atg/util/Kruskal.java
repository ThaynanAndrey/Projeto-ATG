package com.ufcg.atg.util;

import java.util.*;

import com.ufcg.atg.graph.Edge;

public class Kruskal<V extends Comparable<V>, E extends Edge<V>>{
	//conjunto dos vertices do grafo
	private Set<V> vertexes;
	//conjunto das arestas do grafo
	private Set<E> edges;
	//conjunto das aresta da MST
	private Set<E> edgesMst;
	//subconjunto auxiliar que armazena as relações dos vertices
	private Map<V,V> subEdges;
	
	//iteratores para pecorrer os conjuntos
	private Iterator<E> edgeIterator;
	private Iterator<V> vertexeIterator;
	
	//construtor com inicializações
	public Kruskal(Set<V> vertexes, Set<E> edges){
		this.vertexes = vertexes;
		this.edges = edges;
		
		this.edgesMst = new HashSet<>();
		this.subEdges = new HashMap<>();
		
		this.edgeIterator = this.edges.iterator();
		this.vertexeIterator = this.vertexes.iterator();
	}
	
	private V find(Map<V,V> subEdges, V vertexe) {
		//verifica se a relação do vertice ta vazia, se sim, retorna o vertice
		if(subEdges.get(vertexe) == null) {
			return vertexe;
		}
		//caso contrario retorna a relacao do vertice ao qual ja possui relação
		return this.find(subEdges, subEdges.get(vertexe));
	}
	
	//faz a união das relaçoes do vertice
	private void union(Map<V,V> subEdges, V originVertexe, V targetVertexe) {
		V originVertexePut = this.find(subEdges, originVertexe);
		V targetVertexePut = this.find(subEdges, targetVertexe);
		subEdges.put(originVertexePut, targetVertexePut);
	}
	
	private String kruskalToString() {
		String result = "";
		Iterator<E> edgeIt = this.edgesMst.iterator();
		while(edgeIt.hasNext()) {
			E edge = edgeIt.next();
			result += "["+edge.getOriginVertex()+","+edge.getTargetVertex()+"]\n";
		}
		return result;
	}
	
	public String kruskal() {
		//popula o conjunto de retorno com todos os vertices da arvore e seta o valor inicial de relacao como null
		while(this.vertexeIterator.hasNext()) {
			this.subEdges.put(this.vertexeIterator.next(), null);
		}
		
		//Verifica cada uma das arestas
		while(this.edgeIterator.hasNext()) {
			E egdeOperation = this.edgeIterator.next();
			V originVertexe = this.find(subEdges, egdeOperation.getOriginVertex());
			V targetVertexe = this.find(subEdges, egdeOperation.getTargetVertex());
			
			//caso a relação dos vertices sejam iguais significa que possui um cilco 
			//e assim nao deve entrar no grafo MST
			if(!originVertexe.equals(targetVertexe)) {
				this.edgesMst.add(egdeOperation);
				this.union(subEdges, originVertexe, targetVertexe);
			}
		}
		return this.kruskalToString();		
	}
	
}
