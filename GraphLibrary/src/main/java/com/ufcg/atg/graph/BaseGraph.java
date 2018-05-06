package com.ufcg.atg.graph;

import java.util.*;

/**
 * Represents a skeletal implementation of a graph, based on the interface
 * defined by {@link IGraph}.
 *
 * @param <V> Type of the vertex.
 * @param <E> Type of the edge.
 *
 * @author Vélmer Oliveira
 */
public abstract class BaseGraph<V extends Comparable<V>, E extends Edge<V>> implements IGraph<V, E> {

    protected Set<E> edges;

    protected Set<V> vertexes;

    /**
     * Constructs a {@link BaseGraph}.
     */
    public BaseGraph() {
        edges = new HashSet<>();
        vertexes = new HashSet<>();
    }

    @Override
    public void addVertex(V v) {

    }

    @Override
    public E addEdge(V v1, V v2) {
        return null;
    }

    @Override
    public int getVertexesNumber() {
        return vertexes.size();
    }

    @Override
    public int getEdgesNumber() {
        return edges.size();
    }

    @Override
    public float getMeanEdge() {
        return getVertexesNumber() > 0 ? ((2*getEdgesNumber()) / getVertexesNumber()) : 0;
    }

    @Override
    public String graphRepresentation(RepresentationType representationType) {
        if (representationType == RepresentationType.ADJACENCY_MATRIX) {
            return adjacencyMatrixRepresentation();
        } else if (representationType == RepresentationType.ADJACENCY_LIST) {
            return adjacencyListRepresentation();
        } else {
            throw new RuntimeException("Tipo de representação não suportado.");
        }
    }

    private String adjacencyMatrixRepresentation() {
        float adjacencyMatrix[][] = getAdjacencyMatriz();

        return null;
    }

    private String adjacencyListRepresentation() {
        float adjacencyMatrix[][] = getAdjacencyMatriz();

        return null;
    }

    private float[][] getAdjacencyMatriz() {
        int vertexesNumber = getVertexesNumber();
        float adjacencyMatrix[][] = new float[vertexesNumber+1][vertexesNumber+1];
        Iterator<V> it = vertexes.iterator();
        Map<V, ArrayList<E>> vertexToConnectedEdges = getVertexToConnectedEdgesMap();

        while (it.hasNext()) {
            V currentVertex = it.next();
            ArrayList<E> connectedEdges = vertexToConnectedEdges.get(currentVertex);

            for (E e: connectedEdges) {
                int currentVertexIntRepr = (Integer) currentVertex,
                        neighborIntRepr = (Integer) e.getTargetVertex();
                adjacencyMatrix[currentVertexIntRepr][neighborIntRepr] = getEdgeWeight(e);
            }
        }

        return adjacencyMatrix;
    }

    private Map<V, ArrayList<E>> getVertexToConnectedEdgesMap() {
        Map<V, ArrayList<E>> vertexToConnectedEdges = new HashMap<>();

        for (E e: edges) {
            V originVertex = e.getOriginVertex();
            ArrayList<E> connectedEdges = vertexToConnectedEdges.get(originVertex);
            if (connectedEdges == null) {
                connectedEdges = new ArrayList<>();
            }
            connectedEdges.add(e);
            vertexToConnectedEdges.put(originVertex, connectedEdges);
        }

        return vertexToConnectedEdges;
    }

    protected abstract float getEdgeWeight(E e);

    @Override
    public String BFS(V v) {
        return null;
    }

    @Override
    public String DFS(V v) {
        return null;
    }

    @Override
    public String SCC() {
        return null;
    }

    @Override
    public String MST() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseGraph<?, ?> baseGraph = (BaseGraph<?, ?>) o;
        return Objects.equals(edges, baseGraph.edges) &&
                Objects.equals(vertexes, baseGraph.vertexes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edges, vertexes);
    }

}
