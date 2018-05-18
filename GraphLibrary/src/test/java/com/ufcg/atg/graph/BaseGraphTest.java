package com.ufcg.atg.graph;

import com.ufcg.atg.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the class {@link BaseGraph} as a base skeletal of {@link IGraph}.
 */
public class BaseGraphTest {

    private IGraph<Integer, Edge<Integer>> integerGraph;
    private IGraph<Integer, Edge<Integer>> integerGraphDuplicate;
    private IGraph<String, Edge<String>> stringGraph;
    private IGraph<Integer, Edge<Integer>> disconnectedGraph;

    /**
     * Tests' set up.
     */
    @BeforeEach
    public void setUp() {
        setUpGraphOfIntegers();
        setUpGraphOfStrings();
        setUpDisconnectedGraph();
        setUpGraphOfIntegersDuplicate();
    }

    /**
     * Constructs a children class of {@link BaseGraph} with vertices of integer value.
     */
    private void setUpGraphOfIntegers() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph = getGraphOfIntegers();
        integerGraph.addEdge(i1, i2);
        integerGraph.addEdge(i2, i5);
        integerGraph.addEdge(i5, i3);
        integerGraph.addEdge(i4, i5);
        integerGraph.addEdge(i1, i5);
    }

    /**
     * Constructs a {@link Graph} with vertices of integer duplicate value.
     */
    private void setUpGraphOfIntegersDuplicate() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5, i6 = 6;
        integerGraphDuplicate = getGraphOfIntegers();
        integerGraphDuplicate.addEdge(i1, i2);
        integerGraphDuplicate.addEdge(i1, i4);
        integerGraphDuplicate.addEdge(i1, i5);
        integerGraphDuplicate.addEdge(i2, i3);
        integerGraphDuplicate.addEdge(i2, i6);
        integerGraphDuplicate.addEdge(i2, i5);
        integerGraphDuplicate.addEdge(i3, i4);
        integerGraphDuplicate.addEdge(i3, i6);
        integerGraphDuplicate.addEdge(i4, i5);
        integerGraphDuplicate.addEdge(i5, i6);
    }

    /**
     * Constructs a {@link Graph} with vertices of string value.
     */
    private void setUpGraphOfStrings() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraph = getGraphOfStrings();
        stringGraph.addEdge(s1, s2);
        stringGraph.addEdge(s2, s5);
        stringGraph.addEdge(s5, s3);
        stringGraph.addEdge(s4, s5);
        stringGraph.addEdge(s1, s5);
    }

    /**
     * Constructs a disconnected {@link Graph} with vertices of integer value.
     */
    private void setUpDisconnectedGraph() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        disconnectedGraph = getGraphOfIntegers();
        disconnectedGraph.addVertex(i1);
        disconnectedGraph.addVertex(i2);
        disconnectedGraph.addVertex(i3);
        disconnectedGraph.addVertex(i4);
        disconnectedGraph.addVertex(i5);
    }

    /**
     * @return any implementation of {@link BaseGraph} that has {@link Integer}
     * as vertex type.
     */
    private IGraph<Integer, Edge<Integer>> getGraphOfIntegers() {
        return new Graph<>();
    }

    /**
     * @return any implementation of {@link BaseGraph} that has {@link String}
     * as vertex type.
     */
    private IGraph<String, Edge<String>> getGraphOfStrings() {
        return new Graph<>();
    }

    /**
     * Tests the adjacency list representation of a integer's disconnected {@link Graph}.
     */
    @Test
    public void graphDisconnetecListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - ").append(LINE_SEPARATOR)
                .append("2 - ").append(LINE_SEPARATOR)
                .append("3 - ").append(LINE_SEPARATOR)
                .append("4 - ").append(LINE_SEPARATOR)
                .append("5 - ").append(LINE_SEPARATOR)
                .toString();

        String list = disconnectedGraph.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
    }

    /**
     * Tests if throws exception when try get shortest path between disconnected vertexes.
     */
    @Test
    public void shortestPathBetweenDisconnectedVertexesTest() {
        try {
            disconnectedGraph.shortestPath(1, 2);
            fail("Should have thrown exception when trying to find the" +
                    " shortest path between disconnected vertexes.");
        } catch (Exception e) {
            assertEquals("There isn't a path between 1 and 2",
                    e.getMessage(), "A mensagem de erro está errada.");
        }

        try {
            disconnectedGraph.shortestPath(5, 3);
            fail("Should have thrown exception when trying to find the" +
                    " shortest path between disconnected vertexes.");
        } catch (Exception e) {
            assertEquals("There isn't a path between 5 and 3",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
    }

    /**
     * Tests the return's string of BFS in {@link Graph}.
     */
    @Test
    public void BFSTest() {
        String bfsFrom1 = new StringBuilder()
                .append("1 - 0 -").append(LINE_SEPARATOR)
                .append("2 - 1 1").append(LINE_SEPARATOR)
                .append("3 - 2 5").append(LINE_SEPARATOR)
                .append("4 - 2 5").append(LINE_SEPARATOR)
                .append("5 - 1 1").append(LINE_SEPARATOR)
                .toString();
        String bfsFrom2 = new StringBuilder()
                .append("1 - 1 2").append(LINE_SEPARATOR)
                .append("2 - 0 -").append(LINE_SEPARATOR)
                .append("3 - 2 5").append(LINE_SEPARATOR)
                .append("4 - 2 5").append(LINE_SEPARATOR)
                .append("5 - 1 2").append(LINE_SEPARATOR)
                .toString();
        String bfsFrom5 = new StringBuilder()
                .append("1 - 1 5").append(LINE_SEPARATOR)
                .append("2 - 1 5").append(LINE_SEPARATOR)
                .append("3 - 1 5").append(LINE_SEPARATOR)
                .append("4 - 1 5").append(LINE_SEPARATOR)
                .append("5 - 0 -").append(LINE_SEPARATOR)
                .toString();

        assertEquals(bfsFrom1, integerGraph.BFS(1));
        assertEquals(bfsFrom2, integerGraph.BFS(2));
        assertEquals(bfsFrom5, integerGraph.BFS(5));
    }

    /**
     * Tests the return's string of BFD in a disconnected {@link Graph}.
     */
    @Test
    public void BFSInDisconnectedGraphTest(){
        String bfsFrom1 = new StringBuilder()
                .append("1 - 0 -").append(LINE_SEPARATOR)
                .toString();
        String bfsFrom2 = new StringBuilder()
                .append("2 - 0 -").append(LINE_SEPARATOR)
                .toString();
        String bfsFrom3 = new StringBuilder()
                .append("3 - 0 -").append(LINE_SEPARATOR)
                .toString();
        String bfsFrom4 = new StringBuilder()
                .append("4 - 0 -").append(LINE_SEPARATOR)
                .toString();
        String bfsFrom5 = new StringBuilder()
                .append("5 - 0 -").append(LINE_SEPARATOR)
                .toString();

        assertEquals(bfsFrom1, disconnectedGraph.BFS(1));
        assertEquals(bfsFrom2, disconnectedGraph.BFS(2));
        assertEquals(bfsFrom3, disconnectedGraph.BFS(3));
        assertEquals(bfsFrom4, disconnectedGraph.BFS(4));
        assertEquals(bfsFrom5, disconnectedGraph.BFS(5));
    }

    /**
     * Tests the return's string of DFS in {@link Graph}.
     */
    @Test
    public void DFSTest(){
        String dfsFrom1 = new StringBuilder()
                .append("1 - 0 -").append(LINE_SEPARATOR)
                .append("2 - 1 1").append(LINE_SEPARATOR)
                .append("3 - 3 5").append(LINE_SEPARATOR)
                .append("4 - 3 5").append(LINE_SEPARATOR)
                .append("5 - 2 2").append(LINE_SEPARATOR)
                .toString();
        String dfsFrom2 = new StringBuilder()
                .append("1 - 1 2").append(LINE_SEPARATOR)
                .append("2 - 0 -").append(LINE_SEPARATOR)
                .append("3 - 3 5").append(LINE_SEPARATOR)
                .append("4 - 3 5").append(LINE_SEPARATOR)
                .append("5 - 2 1").append(LINE_SEPARATOR)
                .toString();
        String dfsFrom5 = new StringBuilder()
                .append("1 - 1 5").append(LINE_SEPARATOR)
                .append("2 - 2 1").append(LINE_SEPARATOR)
                .append("3 - 1 5").append(LINE_SEPARATOR)
                .append("4 - 1 5").append(LINE_SEPARATOR)
                .append("5 - 0 -").append(LINE_SEPARATOR)
                .toString();

        assertEquals(dfsFrom1, integerGraph.DFS(1));
        assertEquals(dfsFrom2, integerGraph.DFS(2));
        assertEquals(dfsFrom5, integerGraph.DFS(5));
    }

    /**
     * Tests the return's string of DFS in a disconnected {@link Graph}.
     */
    @Test
    public void DFSInDisconnectedGraphTest(){
        String dfsFrom1 = new StringBuilder()
                .append("1 - 0 -").append(LINE_SEPARATOR)
                .toString();
        String dfsFrom2 = new StringBuilder()
                .append("2 - 0 -").append(LINE_SEPARATOR)
                .toString();
        String dfsFrom3 = new StringBuilder()
                .append("3 - 0 -").append(LINE_SEPARATOR)
                .toString();
        String dfsFrom4 = new StringBuilder()
                .append("4 - 0 -").append(LINE_SEPARATOR)
                .toString();
        String dfsFrom5 = new StringBuilder()
                .append("5 - 0 -").append(LINE_SEPARATOR)
                .toString();

        assertEquals(dfsFrom1, disconnectedGraph.DFS(1));
        assertEquals(dfsFrom2, disconnectedGraph.DFS(2));
        assertEquals(dfsFrom3, disconnectedGraph.DFS(3));
        assertEquals(dfsFrom4, disconnectedGraph.DFS(4));
        assertEquals(dfsFrom5, disconnectedGraph.DFS(5));
    }

    /**
     * Tests connected {@link Graph}.
     */
    @Test
    public void connectedGraphTest(){
        IGraph<Integer, Edge<Integer>> singleVertexGraph = new Graph<>();
        assertTrue(singleVertexGraph.connected());
        assertTrue(integerGraph.connected());
        assertFalse(disconnectedGraph.connected());
    }

    /**
     * Tests MST in disconnected {@link Graph}.
     */
    @Test
    public void mstDisconnectedGraphTest() {
        assertEquals(Utils.STRING_EMPTY, disconnectedGraph.MST());
    }
}
