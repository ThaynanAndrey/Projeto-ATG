package com.ufcg.atg.library;

/*import com.ufcg.atg.graph.BaseGraph;
import com.ufcg.atg.graph.Edge;
import com.ufcg.atg.graph.WeightedEdge;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;*/

/**
 * Tests the class {@link GraphLibrary}.
 *
 * @author Thaynan Nunes.
 */
class GraphLibraryTest {

    /*GraphLibrary graphLibrary;

    /**
     * Tests Set up.
    @BeforeEach
    public void setUp() {
        graphLibrary = new GraphLibrary();
    }

     * Deletes the files created for the tests.
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

     * Insert file for future tests.
     *
     * @param path File's path.
     * @param fileContent Content to be insert in file.
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

     * Tests the GraphLibrary's readGraph method, verifying if are correctly
     * insert the vertexes and edges in the Graph.
    @Test
    void readGraph() {
        String path = "graph.txt";
        String fileContent = "6, 1 2, 1 3, 2 3, 5 6, 6 3, 6 1";
        insertFile(path, fileContent);

        BaseGraph baseGraph = graphLibrary.readGraph(path);
        int vertexAmount = 5;
        int edgeAmount = 6;
        Edge<Integer> e1 = new Edge<>(1, 2);
        Edge<Integer> e2 = new Edge<>(1, 3);
        Edge<Integer> e3 = new Edge<>(2, 3);
        Edge<Integer> e4 = new Edge<>(5, 6);
        Edge<Integer> e5 = new Edge<>(6, 3);
        Edge<Integer> e6 = new Edge<>(6, 1);

        assertEquals(baseGraph.getVertexesNumber(), vertexAmount);
        assertEquals(baseGraph.getEdgesNumber(), edgeAmount);
        assertTrue(baseGraph.getAllVertexes().contains(1));
        assertTrue(baseGraph.getAllVertexes().contains(2));
        assertTrue(baseGraph.getAllVertexes().contains(3));
        assertTrue(baseGraph.getAllVertexes().contains(5));
        assertTrue(baseGraph.getAllVertexes().contains(6));
        assertTrue(baseGraph.getAllEdges().contains(e1));
        assertTrue(baseGraph.getAllEdges().contains(e2));
        assertTrue(baseGraph.getAllEdges().contains(e3));
        assertTrue(baseGraph.getAllEdges().contains(e4));
        assertTrue(baseGraph.getAllEdges().contains(e5));
        assertTrue(baseGraph.getAllEdges().contains(e6));
    }

     * Tests the GraphLibrary's readWeightedGraph method, verifying if are correctly
     * insert the vertexes and edges in the Graph.
    @Test
    void readWeightedGraph() {
        String path = "weighted_graph.txt";
        String fileContent = "6, 1 2 1.2, 1 3 0.5, 2 3 0.7, 5 6 1.3, 6 3 2.1, 6 1 5.2";
        insertFile(path, fileContent);

        BaseGraph weightedGraph = graphLibrary.readWeightedGraph(path);
        int vertexAmount = 5;
        int edgeAmount = 6;
        WeightedEdge<Integer> e1 = new WeightedEdge<>(1, 2, 1.2f);
        WeightedEdge<Integer> e2 = new WeightedEdge<>(1, 3, 0.5f);
        WeightedEdge<Integer> e3 = new WeightedEdge<>(2, 3, 0.7f);
        WeightedEdge<Integer> e4 = new WeightedEdge<>(5, 6, 1.3f);
        WeightedEdge<Integer> e5 = new WeightedEdge<>(6, 3, 2.1f);
        WeightedEdge<Integer> e6 = new WeightedEdge<>(6, 1, 5.2f);

        assertEquals(weightedGraph.getVertexesNumber(), vertexAmount);
        assertEquals(weightedGraph.getEdgesNumber(), edgeAmount);
        assertTrue(weightedGraph.getAllVertexes().contains(1));
        assertTrue(weightedGraph.getAllVertexes().contains(2));
        assertTrue(weightedGraph.getAllVertexes().contains(3));
        assertTrue(weightedGraph.getAllVertexes().contains(5));
        assertTrue(weightedGraph.getAllVertexes().contains(6));
        assertTrue(weightedGraph.getAllEdges().contains(e1));
        assertTrue(weightedGraph.getAllEdges().contains(e2));
        assertTrue(weightedGraph.getAllEdges().contains(e3));
        assertTrue(weightedGraph.getAllEdges().contains(e4));
        assertTrue(weightedGraph.getAllEdges().contains(e5));
        assertTrue(weightedGraph.getAllEdges().contains(e6));
    }*/
}