package com.ufcg.atg.graph;

import com.ufcg.atg.util.Utils;

import java.util.*;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;

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
        ArrayList<V> orderedVertexes = getOrderedVertexesList();
        float adjacencyMatrix[][] = getAdjacencyMatrix(orderedVertexes);
        String matrizString = setUpAdjacencyMatrizString(orderedVertexes, adjacencyMatrix);
        return matrizString;
    }

    private float[][] getAdjacencyMatrix(ArrayList<V> orderedVertexes) {
        int vertexesNumber = getVertexesNumber();
        float adjacencyMatrix[][] = new float[vertexesNumber][vertexesNumber];
        Map<V, ArrayList<E>> vertexToConnectedEdges = getVertexToConnectedEdgesMap();

        for(int i = 0; i < vertexesNumber; i++) {
            V currentVertex = orderedVertexes.get(i);
            ArrayList<E> connectedEdges = vertexToConnectedEdges.get(currentVertex);
            for (E edge : connectedEdges) {
                V targetVertex = edge.getTargetVertex();
                adjacencyMatrix[i][orderedVertexes.indexOf(targetVertex)] = getEdgeWeight(edge);
            }
        }

        return adjacencyMatrix;
    }

    private String setUpAdjacencyMatrizString(ArrayList<V> orderedVertexes, float adjacencyMatrix[][]) {
        int vertexesNumber = getVertexesNumber();
        StringBuilder matrixSB = new StringBuilder("  ");

        for (int i = 0; i < vertexesNumber; i++) {
            matrixSB.append(orderedVertexes.get(i));
            boolean shouldAddSpace = vertexesNumber - i > 1;
            if (shouldAddSpace) matrixSB.append(" ");
        }
        matrixSB.append(LINE_SEPARATOR);
        for(int i = 0; i < vertexesNumber; i++) {
            StringBuilder line = new StringBuilder(orderedVertexes.get(i) + " ");
            for(int j = 0; j < vertexesNumber; j++) {
                line.append(Utils.floatToString(adjacencyMatrix[i][j]));
                boolean shouldAddSpace = vertexesNumber - j > 1;
                if (shouldAddSpace) line.append(" ");
            }
            matrixSB.append(line).append(LINE_SEPARATOR);
        }

        return matrixSB.toString();
    }

    private String adjacencyListRepresentation() {
        ArrayList<V> orderedVertexes = getOrderedVertexesList();
        Map<V, ArrayList<E>> vertexToConnectedEdges = getVertexToConnectedEdgesMap();
        return null;
    }

    private ArrayList<V> getOrderedVertexesList() {
        ArrayList<V> orderedVertexes = getVertexesList();
        Collections.sort(orderedVertexes);
        return orderedVertexes;
    }

    private ArrayList<V> getVertexesList() {
        ArrayList<V> vertexes = new ArrayList<>();
        vertexes.addAll(this.vertexes);
        return vertexes;
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
