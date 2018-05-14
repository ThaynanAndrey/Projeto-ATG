package com.ufcg.atg.library;

import com.ufcg.atg.graph.Edge;
import com.ufcg.atg.graph.IGraph;
import com.ufcg.atg.graph.IWeightedGraph;
import com.ufcg.atg.graph.WeightedEdge;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the class {@link GraphLibrary}.
 *
 * @author Thaynan Nunes.
 */
class GraphLibraryTest {

    private static final int UNDIRECTED_GRAPH_FACTOR = 2;

    GraphLibrary<Integer> graphLibrary;

    /**
     * Tests Set up.
     */
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary<>();
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
    void readGraph() {
        String path = "graph.txt";
        String fileContent = "6, 1 2, 1 3, 2 3, 5 6, 6 3, 6 1";
        insertFile(path, fileContent);

        IGraph<Integer, Edge<Integer>> graph = graphLibrary.readGraph(path);
        int vertexAmount = 5;
        int edgeAmount = 6 * UNDIRECTED_GRAPH_FACTOR;
        Edge<Integer> e1 = new Edge<>(1, 2),
                      e2 = new Edge<>(1, 3),
                      e3 = new Edge<>(2, 3),
                      e4 = new Edge<>(5, 6),
                      e5 = new Edge<>(6, 3),
                      e6 = new Edge<>(6, 1);
        Set<Integer> allVertexes = graph.getAllVertexes();
        Set<Edge<Integer>> allEdges = graph.getAllEdges();

        assertEquals(graph.getVertexesNumber(), vertexAmount);
        assertEquals(graph.getEdgesNumber(), edgeAmount);
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
    void readWeightedGraph() {
        String path = "weighted_graph.txt";
        String fileContent = "6, 1 2 1.2, 1 3 0.5, 2 3 0.7, 5 6 1.3, 6 3 2.1, 6 1 5.2";
        insertFile(path, fileContent);

        IWeightedGraph<Integer, WeightedEdge<Integer>> weightedGraph = graphLibrary.readWeightedGraph(path);
        int vertexAmount = 5;
        int edgeAmount = 6 * UNDIRECTED_GRAPH_FACTOR;
        WeightedEdge<Integer> e1 = new WeightedEdge<>(1, 2, 1.2f),
                              e2 = new WeightedEdge<>(1, 3, 0.5f),
                              e3 = new WeightedEdge<>(2, 3, 0.7f),
                              e4 = new WeightedEdge<>(5, 6, 1.3f),
                              e5 = new WeightedEdge<>(6, 3, 2.1f),
                              e6 = new WeightedEdge<>(6, 1, 5.2f);
        Set<Integer> allVertexes = weightedGraph.getAllVertexes();
        Set<WeightedEdge<Integer>> allEdges = weightedGraph.getAllEdges();


        assertEquals(weightedGraph.getVertexesNumber(), vertexAmount);
        assertEquals(weightedGraph.getEdgesNumber(), edgeAmount);
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
}