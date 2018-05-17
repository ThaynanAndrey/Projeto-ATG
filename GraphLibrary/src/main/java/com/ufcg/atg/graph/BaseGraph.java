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
    public Set<E> getEdgesOfVertex(V v) {
        return vertexes.get(v);
    }

    @Override
    public Set<V> getAdjacentVertexes(V v) {
        return vertexes.get(v).stream()
                .map(E::getTargetVertex)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean containsVertex(V v) {
        return getAllVertexes().contains(v);
    }

    @Override
    public boolean containsEdge(Edge<V> e) {
        return vertexes.get(e.getOriginVertex()).contains(e);
    }

    @Override
    public int getVertexesNumber() {
        return vertexes.size();
    }

    @Override
    public int getEdgesNumber() {
        return getAllEdges().size();
    }

    @Override
    public float getMeanEdge() {
        return getVertexesNumber() > 0 ? ((getEdgesNumber()) / getVertexesNumber()) : 0;
    }

    @Override
    public String graphRepresentation(RepresentationType representationType) {
        if (representationType == RepresentationType.ADJACENCY_MATRIX) {
            return adjacencyMatrixRepresentation();
        } else if (representationType == RepresentationType.ADJACENCY_LIST) {
            return adjacencyListRepresentation();
        } else {
            return Utils.STRING_EMPTY;
        }
    }

    /**
     * Returns the adjacency matrix representation.
     */
    private String adjacencyMatrixRepresentation() {
        List<V> orderedVertexes = getOrderedVertexesList();
        float adjacencyMatrix[][] = setUpAdjacencyMatrix(orderedVertexes);
        return getAdjacencyMatrixString(orderedVertexes, adjacencyMatrix);
    }

    /**
     * Sets up a adjacency matrix based on the specified ordered vertexes.
     *
     * @param orderedVertexes Ordered vertexes that will have its connections
     *                        represented in the matrix.
     * @return Adjacency matrix with all connections between specified vertexes.
     */
    private float[][] setUpAdjacencyMatrix(List<V> orderedVertexes) {
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

    /**
     * Returns the string representation of specified adjacency matrix.
     *
     * @param orderedVertexes Ordered vertexes that are in the matrix.
     * @param adjacencyMatrix Adjacency matrix to have its string representation
     *                        returned.
     * @return The string representation of specified adjacency matrix.
     */
    private String getAdjacencyMatrixString(List<V> orderedVertexes, float adjacencyMatrix[][]) {
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
                    .sorted(Comparator.comparing(Edge::getTargetVertex))
                    .map(this::mapOperatorListRepresentation)
                    .reduce((s1, s2) -> s1 + " " + s2).orElse(STRING_EMPTY);
            list.append(v).append(" - ").append(neighbors).append(LINE_SEPARATOR);
        }
        return list.toString();
    }

    /**
     * Maps a edge to a string representation in adjacency list.
     *
     * @param e Edge to have its string representation returned.
     * @return Edge string representation.
     */
    protected abstract String mapOperatorListRepresentation(E e);

    /**
     * Returns a ordered list with all vertexes.
     */
    private List<V> getOrderedVertexesList() {
        List<V> orderedVertexes = new ArrayList<>(getAllVertexes());
        Collections.sort(orderedVertexes);
        return orderedVertexes;
    }

    /**
     * Returns the weight of the specified edge based on the subtype of
     * {@link BaseGraph}.
     *
     * @param e Edge to have its weight returned.
     * @return Weight of the specified edge.
     */
    protected abstract float getEdgeWeight(E e);

    @Override
    public String BFS(V v) {
        return null;
    }

    private void setUpWalk(Map<V, Boolean> visited) {
        for(V vertex : getAllVertexes()) {
            visited.put(vertex, false);
        }
    }

    @Override
    public String DFS(V v) {
        Map<V, Boolean> visited = new HashMap<>();
        setUpWalk(visited);
        List<String> DFSString = new ArrayList<String>();
        DFS(v, null,0, visited, DFSString);
        DFSString.sort(String::compareToIgnoreCase);
        return DFSString.stream()
                .reduce((s1, s2) -> s1 + s2)
                .orElse(Utils.STRING_EMPTY);
    }

    private void DFS(V currentVertex, V prevVertex, int vertexLevel, Map<V, Boolean> visited,
                     List<String> DFSString){
        visited.put(currentVertex, true);
        String previousVertex = prevVertex != null ? prevVertex.toString() : "-";
        DFSString.add(new StringBuilder()
                .append(currentVertex).append(" - ")
                .append(vertexLevel).append(" ")
                .append(previousVertex).append(LINE_SEPARATOR)
                .toString());
        for(V adjacentVertex : getAdjacentVertexes(currentVertex)){
            if(!visited.get(adjacentVertex)){
                DFS(adjacentVertex, currentVertex, vertexLevel + 1, visited, DFSString);
            }
        }
    }

    private void DFS(V currentVertex, Set<V> visited){
        visited.add(currentVertex);
        for(V adjacentVertex : getAdjacentVertexes(currentVertex)){
            if(!visited.contains(adjacentVertex))
                DFS(adjacentVertex, visited);
        }
    }

    @Override
    public boolean connected() {
        if (getAllVertexes().isEmpty()) return true;
        Set<V> visited = new HashSet<>();
        V currentVertex = vertexes.keySet().iterator().next();
        DFS(currentVertex, visited);
        return visited.equals(vertexes.keySet());
    }

    @Override
    public String shortestPath(V v1, V v2) {
        if (v1.equals(v2)) return v1.toString();
        if (containsNegativeWeightedEdge()) {
            throw new RuntimeException("The shortest path cannot be found in a" +
                    " graph with negative circle.");
        }
        Map<V, Float> distances = new HashMap<>();
        Map<V, V> predecessors = new HashMap<>();
        setUpShortestPath(v1, distances, predecessors);
        Queue<V> priorityQueue = new PriorityQueue<>((o1, o2) ->
                Float.compare(distances.get(o1), distances.get(o2)));
        priorityQueue.addAll(getAllVertexes());

        while (!priorityQueue.isEmpty()) {
            V current = priorityQueue.poll();
            if (current.equals(v2)) break;
            for (E e: getEdgesOfVertex(current)) {
                V adjacent = e.getTargetVertex();
                relax(current, adjacent, e, distances, predecessors);
            }
        }

        return setUpShortestPathString(v1, v2, predecessors);
    }

    /**
     * Returns if the current graph contains negative weighted edges. This base
     * class, as a representation of a unweighted graph, trivially, do not contains.
     * However, classes that extends {@link BaseGraph}, may do.
     */
    protected boolean containsNegativeWeightedEdge() {
        return false;
    }

    /**
     * Sets up the configuration to run the shortest path algorithm.
     *
     * Actions:
     * - Sets distances to the path start of all vertexes as +Infinity;
     * - Sets predecessors of all vertexes to {@code null};
     * - Sets the distance of the path start to 0 (zero).
     *
     * @param pathStart Vertex that starts the path.
     * @param distances {@link Map} that links a vertex to its distance to origin
     *                             of the shortest path.
     * @param predecessors {@link Map} that links a vertex to its predecessors.
     */
    protected void setUpShortestPath(V pathStart, Map<V, Float> distances, Map<V, V> predecessors) {
        for (V v: getAllVertexes()) {
            distances.put(v, Float.POSITIVE_INFINITY);
            predecessors.put(v, null);
        }
        distances.replace(pathStart, 0f);
    }

    /**
     * Sets up the shortest path between {@code pathStart} and {@code pathEnd}
     * string representation.
     *
     * @param pathStart Vertex that starts the path.
     * @param pathEnd Vertex that ends the path.
     * @param predecessors {@link Map} that links a vertex to its predecessor.
     * @return Shortest path string representation.
     */
    protected String setUpShortestPathString(V pathStart, V pathEnd, Map<V, V> predecessors) {
        StringBuilder shortestPath = new StringBuilder(pathEnd.toString());
        V predecessor = predecessors.get(pathEnd);
        V lastAddedPredecessor = predecessor;
        while(predecessor != null) {
            StringBuilder currentSB = new StringBuilder(predecessor.toString());
            shortestPath = currentSB.append(" ").append(shortestPath);
            lastAddedPredecessor = predecessor;
            predecessor = predecessors.get(predecessor);
        }
        if (lastAddedPredecessor == null || !lastAddedPredecessor.equals(pathStart)) {
            throw new RuntimeException("There isn't a path between " + pathStart
                    + " and " + pathEnd);
        }
        return shortestPath.toString();
    }

    /**
     * Relaxes the edge between vertexes {@code originVertex} and
     * {@code targetVertex}. Only if the distance between
     * {@code originVertex} and {@code targetVertex} is lesser than the
     * distance already leaded to {@code targetVertex}.
     *
     * @param originVertex Origin vertex of the edge.
     * @param targetVertex Target vertex of the edge.
     * @param e Edge from {@code originVertex} to {@code targetVertex}.
     * @param distances {@link Map} that links a vertex to its distance to origin
     *                             of the shortest path.
     * @param predecessors {@link Map} that links a vertex to its predecessor.
     */
    protected void relax(V originVertex, V targetVertex, E e, Map<V, Float> distances,
                         Map<V, V> predecessors) {
        Float targetVertexDistance = distances.get(targetVertex),
                originVertexDistance = distances.get(originVertex),
                edgeWeight = getEdgeWeight(e);
        if (targetVertexDistance > (originVertexDistance + edgeWeight)) {
            predecessors.put(targetVertex, originVertex);
            distances.replace(targetVertex, distances.get(originVertex) + edgeWeight);
        }
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
