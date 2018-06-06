package com.ufcg.atg.library;

import com.ufcg.atg.graph.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the class {@link GraphLibrary}.
 *
 * @author Thaynan Nunes.
 */
class GraphLibraryTest {

    private static final int UNDIRECTED_GRAPH_FACTOR = 2;

    private GraphLibrary<Integer> graphLibrary;
    private IGraph<Integer, Edge<Integer>> unweightedGraph;
    private IWeightedGraph<Integer, WeightedEdge<Integer>> weightedGraph;

    /**
     * Tests Set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();
        setUpUnweightedGraph();
        setUpWeightedGraph();
    }

    /**
     * Constructs a children class of {@link BaseGraph} with vertices of integer value.
     */
    private void setUpUnweightedGraph() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        unweightedGraph = new Graph<>();
        unweightedGraph.addEdge(i1, i2);
        unweightedGraph.addEdge(i2, i5);
        unweightedGraph.addEdge(i5, i3);
        unweightedGraph.addEdge(i4, i5);
        unweightedGraph.addEdge(i1, i5);
    }

    /**
     * Constructs a weighted graph with vertices of integer value.
     */
    private void setUpWeightedGraph() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        weightedGraph = new WeightedGraph<>();
        weightedGraph.addEdge(i1, i2, 0.1f);
        weightedGraph.addEdge(i2, i5, 0.2f);
        weightedGraph.addEdge(i5, i3, 5f);
        weightedGraph.addEdge(i3, i4, -9.5f);
        weightedGraph.addEdge(i4, i5, 2.3f);
        weightedGraph.addEdge(i1, i5,1f);
    }

    /**
     * Deletes the files created for the tests.
     */
    @AfterAll
    public static void deleteFiles() {
        try {
            File graphTxt = new File("graph.txt");
            File weightedGraphTxt = new File("weighted_graph.txt");
            graphTxt.delete();
            weightedGraphTxt.delete();
        } catch (Exception e) {
            fail("There was an error to delete files.");
        }
    }

    /**
     * Insert file for future tests.
     *
     * @param path File's path.
     * @param fileContent Content to be insert in file.
     */
    private void insertFile(String path, String fileContent) {
        try {
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String[] lines = fileContent.split(", ");
            for(int i=0; i < lines.length; i++) {
                writer.write(lines[i]);
                if(i < lines.length - 1) {
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            fail("There was an error on file's writen.");
        }
    }

    /**
     * Tests the GraphLibrary's readGraph method, verifying if are correctly
     * insert the vertexes and edges in the Graph.
     */
    @Test
    public void readGraph() {
        String path = "graph.txt";
        String fileContent = "6, 1 2, 1 3, 2 3, 5 6, 6 3, 6 1";
        insertFile(path, fileContent);

        IGraph<Integer, Edge<Integer>> graph = graphLibrary.readGraph(path);
        int vertexAmount = 6;
        int edgeAmount = 6 * UNDIRECTED_GRAPH_FACTOR;
        Edge<Integer> e1 = new Edge<>(1, 2),
                      e2 = new Edge<>(1, 3),
                      e3 = new Edge<>(2, 3),
                      e4 = new Edge<>(5, 6),
                      e5 = new Edge<>(6, 3),
                      e6 = new Edge<>(6, 1);
        Set<Integer> allVertexes = graph.getAllVertexes();
        Set<Edge<Integer>> allEdges = graph.getAllEdges();

        assertEquals(graph.getVertexNumber(), vertexAmount);
        assertEquals(graph.getEdgeNumber(), edgeAmount);
        assertTrue(allVertexes.contains(1));
        assertTrue(allVertexes.contains(2));
        assertTrue(allVertexes.contains(3));
        assertTrue(allVertexes.contains(5));
        assertTrue(allVertexes.contains(6));
        assertTrue(allEdges.contains(e1));
        assertTrue(allEdges.contains(e2));
        assertTrue(allEdges.contains(e3));
        assertTrue(allEdges.contains(e4));
        assertTrue(allEdges.contains(e5));
        assertTrue(allEdges.contains(e6));
    }

    /**
     * Tests the GraphLibrary's readWeightedGraph method, verifying if are correctly
     * insert the vertexes and edges in the Graph.
     */
    @Test
    public void readWeightedGraph() {
        String path = "weighted_graph.txt";
        String fileContent = "6, 1 2 1.2, 1 3 0.5, 2 3 0.7, 5 6 1.3, 6 3 2.1, 6 1 5.2";
        insertFile(path, fileContent);

        IWeightedGraph<Integer, WeightedEdge<Integer>> weightedGraph = graphLibrary.readWeightedGraph(path);
        int vertexAmount = 6;
        int edgeAmount = 6 * UNDIRECTED_GRAPH_FACTOR;
        WeightedEdge<Integer> e1 = new WeightedEdge<>(1, 2, 1.2f),
                              e2 = new WeightedEdge<>(1, 3, 0.5f),
                              e3 = new WeightedEdge<>(2, 3, 0.7f),
                              e4 = new WeightedEdge<>(5, 6, 1.3f),
                              e5 = new WeightedEdge<>(6, 3, 2.1f),
                              e6 = new WeightedEdge<>(6, 1, 5.2f);
        Set<Integer> allVertexes = weightedGraph.getAllVertexes();
        Set<WeightedEdge<Integer>> allEdges = weightedGraph.getAllEdges();


        assertEquals(weightedGraph.getVertexNumber(), vertexAmount);
        assertEquals(weightedGraph.getEdgeNumber(), edgeAmount);
        assertTrue(allVertexes.contains(1));
        assertTrue(allVertexes.contains(2));
        assertTrue(allVertexes.contains(3));
        assertTrue(allVertexes.contains(5));
        assertTrue(allVertexes.contains(6));
        assertTrue(allEdges.contains(e1));
        assertTrue(allEdges.contains(e2));
        assertTrue(allEdges.contains(e3));
        assertTrue(allEdges.contains(e4));
        assertTrue(allEdges.contains(e5));
        assertTrue(allEdges.contains(e6));
    }

    /**
     * Tests the addition of edges.
     */
    @Test
    public void addEdgeTest() {
        int edgeNumber = graphLibrary.getEdgeNumber(unweightedGraph);
        graphLibrary.addEdge(unweightedGraph,1, 3);
        graphLibrary.addEdge(unweightedGraph,4, 1);
        edgeNumber += (2 * UNDIRECTED_GRAPH_FACTOR);
        assertEquals(graphLibrary.getEdgeNumber(unweightedGraph), edgeNumber);

        int weightedEdgeNumber = graphLibrary.getEdgeNumber(weightedGraph);
        graphLibrary.addEdge(weightedGraph,2, 3);
        graphLibrary.addEdge(weightedGraph,4, 1);
        weightedEdgeNumber += (2 * UNDIRECTED_GRAPH_FACTOR);
        assertEquals(graphLibrary.getEdgeNumber(weightedGraph), weightedEdgeNumber);
    }

    /**
     * Tests the addition of already existent edges.
     */
    @Test
    public void addExistentEdgeTest() {
        try {
            graphLibrary.addEdge(unweightedGraph, 1, 5);
            fail("Should have thrown exception when trying to add a" +
                    " existent edge.");
        } catch (Exception e) {
            assertEquals("The graph already contains the specified edge.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
    }

    /**
     * Tests the addition of vertexes.
     */
    @Test
    public void addVertexTest() {
        int vertexNumber = graphLibrary.getVertexNumber(unweightedGraph);
        graphLibrary.addVertex(unweightedGraph, 7);
        graphLibrary.addVertex(unweightedGraph, 6);
        vertexNumber += 2;
        assertEquals(graphLibrary.getVertexNumber(unweightedGraph), vertexNumber);
    }

    /**
     * Tests the addition of already existent vertexes.
     */
    @Test
    public void addExistentVertexTest() {
        try {
            graphLibrary.addVertex(unweightedGraph, 1);
            fail("Should have thrown exception when trying to add a" +
                    " existent vertex.");
        } catch (Exception e) {
            assertEquals("The graph already contains the specified vertex.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
    }

    /**
     * Tests the method that returns all the vertexes of a graph.
     */
    @Test
    public void getAllVertexesTest() {
        IGraph<Integer, Edge<Integer>> graph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        Set<Integer> addedVertexes = new HashSet<>();
        addedVertexes.add(i1);
        addedVertexes.add(i2);
        addedVertexes.add(i3);
        graphLibrary.addVertex(graph, i1);
        graphLibrary.addVertex(graph, i2);
        graphLibrary.addVertex(graph, i3);
        assertEquals(addedVertexes, graphLibrary.getAllVertexes(graph));
    }

    /**
     * Tests the method that checks if a graph contains a vertex.
     */
    @Test
    public void containsVertexTest() {
        assertTrue(graphLibrary.containsVertex(unweightedGraph, 1));
        assertTrue(graphLibrary.containsVertex(unweightedGraph, 4));
        assertTrue(graphLibrary.containsVertex(unweightedGraph, 5));
        assertFalse(graphLibrary.containsVertex(unweightedGraph, -1));
        assertFalse(graphLibrary.containsVertex(unweightedGraph, 6));
    }

    /**
     * Tests the method that checks if a graph contains a edge.
     */
    @Test
    public void containsEdgeTest() {
        Edge<Integer> addedEdge = unweightedGraph.addEdge(1, 4);
        Edge<Integer> notAddedEdge = new Edge<>(1, 3);
        Edge<Integer> notAddedEdge2 = new Edge<>(0, -1);
        assertTrue(graphLibrary.containsEdge(unweightedGraph, addedEdge));
        assertFalse(graphLibrary.containsEdge(unweightedGraph, notAddedEdge));
        assertFalse(graphLibrary.containsEdge(unweightedGraph, notAddedEdge2));
    }

    /**
     * Tests the calculus of mean edge of a graph.
     */
    @Test
    void getMeanEdgeTest() {
        assertEquals(2.0f, graphLibrary.getMeanEdge(unweightedGraph));
        assertEquals(2.0f, graphLibrary.getMeanEdge(unweightedGraph));
        graphLibrary.addVertex(unweightedGraph, 6);
        graphLibrary.addVertex(unweightedGraph, 7);
        assertEquals(1.0f, graphLibrary.getMeanEdge(unweightedGraph));
    }

    /**
     * Tests the BFS method passing a non existent vertex as parameter.
     */
    @Test
    public void BFSWithNonExistentVertexTest() {
        try {
            graphLibrary.BFS(unweightedGraph, 6);
            fail("Should have thrown exception when trying to run BFS" +
                    " of a non existent vertex.");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
    }

    /**
     * Tests the DFS method passing a non existent vertex as parameter.
     */
    @Test
    public void DFSWithNonExistentVertexTest() {
        try {
            graphLibrary.DFS(unweightedGraph, 6);
            fail("Should have thrown exception when trying to run DFS" +
                    " of a non existent vertex.");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains the specified vertex.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
    }

    /**
     * Tests the Shortest Path method passing non existent vertexes as parameter.
     */
    @Test
    public void shotestPathWithNonExistentVertexesTest() {
        try {
            graphLibrary.shortestPath(unweightedGraph, 1, 6);
            fail("Should have thrown exception when trying to run DFS" +
                    " of a non existent vertex.");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
        try {
            graphLibrary.shortestPath(unweightedGraph, 7, 2);
            fail("Should have thrown exception when trying to run DFS" +
                    " of a non existent vertex.");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
        try {
            graphLibrary.shortestPath(unweightedGraph, 8, 9);
            fail("Should have thrown exception when trying to run DFS" +
                    " of a non existent vertex.");
        } catch (Exception e) {
            assertEquals("The graph doesn't contains both specified vertexes.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
    }

}