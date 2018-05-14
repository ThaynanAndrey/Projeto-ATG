package com.ufcg.atg.graph;

import com.ufcg.atg.util.Utils;

import java.util.*;
import java.util.stream.Collectors;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static com.ufcg.atg.util.Utils.STRING_EMPTY;


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

    protected Map<V, Set<E>> vertexes;

    /**
     * Constructs a {@link BaseGraph}.
     */
    public BaseGraph() {
        vertexes = new HashMap<>();
    }

    @Override
    public void addVertex(V v) {
        if (v == null) {
            throw new IllegalArgumentException();
        }
        if (vertexes.containsKey(v)) {
            throw new RuntimeException();
        }
        addIfAbsent(v);
    }

    /**
     * Adds a vertex if isn't already on the graph.
     *
     * @param v Vertex to be added.
     */
    protected void addIfAbsent(V v) {
        if (!vertexes.containsKey(v)) {
            vertexes.put(v, new HashSet<>());
        }
    }

    @Override
    public Set<V> getAllVertexes() {
        return vertexes.keySet();
    }

    @Override
    public Set<E> getAllEdges() {
        return vertexes.values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<E> getVertexsEdges(V v) {
        return vertexes.get(v);
    }

    @Override
    public Set<V> getAdjacentVertexes(V v) {
        return vertexes.get(v).stream()
                .map(E::getTargetVertex)
                .collect(Collectors.toSet());
    }

    @Override
    public int getVertexesNumber() {
        return vertexes.size();
    }

    @Override
    public int getEdgesNumber() {
        return getAllEdges().size() / 2;
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

    /**
     * Returns the adjacency matrix representation.
     */
    private String adjacencyMatrixRepresentation() {
        List<V> orderedVertexes = getOrderedVertexesList();
        float adjacencyMatrix[][] = getAdjacencyMatrix(orderedVertexes);
        return setUpAdjacencyMatrixString(orderedVertexes, adjacencyMatrix);
    }

    private float[][] getAdjacencyMatrix(List<V> orderedVertexes) {
        int vertexesNumber = getVertexesNumber();
        float adjacencyMatrix[][] = new float[vertexesNumber][vertexesNumber];
        for(int i = 0; i < vertexesNumber; i++) {
            V currentVertex = orderedVertexes.get(i);
            Set<E> connectedEdges = vertexes.get(currentVertex);
            for (E edge : connectedEdges) {
                V targetVertex = edge.getTargetVertex();
                adjacencyMatrix[i][orderedVertexes.indexOf(targetVertex)] = getEdgeWeight(edge);
            }
        }
        return adjacencyMatrix;
    }

    private String setUpAdjacencyMatrixString(List<V> orderedVertexes, float adjacencyMatrix[][]) {
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

    /**
     * Returns the adjacency list representation.
     */
    private String adjacencyListRepresentation() {
        List<V> orderedVertexes = getOrderedVertexesList();
        StringBuilder list = new StringBuilder();

        for (V v: orderedVertexes) {
            String neighbors = vertexes.get(v).stream()
                    .map(E::getTargetVertex)
                    .sorted()
                    .map(V::toString)
                    .reduce((s, s2) -> s + " " + s2).orElse(STRING_EMPTY);
            list.append(v).append(" - ").append(neighbors).append(LINE_SEPARATOR);
        }
        return list.toString();
    }

    /**
     * Returns a ordered list with all vertexes.
     */
    private List<V> getOrderedVertexesList() {
        List<V> orderedVertexes = new ArrayList<>(getAllVertexes());
        Collections.sort(orderedVertexes);
        return orderedVertexes;
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
        return Objects.equals(vertexes, baseGraph.vertexes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexes);
    }

}
