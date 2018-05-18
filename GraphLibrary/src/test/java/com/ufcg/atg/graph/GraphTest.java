package com.ufcg.atg.graph;

import com.ufcg.atg.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the class {@link Graph}.
 */
public class GraphTest {

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
     * Constructs a {@link Graph} with vertices of integer value.
     */
    private void setUpGraphOfIntegers() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph = new Graph<>();
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
        integerGraphDuplicate = new Graph<>();
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
        stringGraph = new Graph<>();
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
    	disconnectedGraph = new Graph<>();
    	disconnectedGraph.addVertex(i1);
    	disconnectedGraph.addVertex(i2);
    	disconnectedGraph.addVertex(i3);
    	disconnectedGraph.addVertex(i4);
    	disconnectedGraph.addVertex(i5);
    }

    /**
     * Tests the adjacency matrix representation of a {@link Graph} with
     * duplicated integers.
     */
    @Test
    public void graphOfIntegersDuplicateMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5 6").append(LINE_SEPARATOR)
                .append("1 0 1 0 1 1 0").append(LINE_SEPARATOR)
                .append("2 1 0 1 0 1 1").append(LINE_SEPARATOR)
                .append("3 0 1 0 1 0 1").append(LINE_SEPARATOR)
                .append("4 1 0 1 0 1 0").append(LINE_SEPARATOR)
                .append("5 1 1 0 1 0 1").append(LINE_SEPARATOR)
                .append("6 0 1 1 0 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = integerGraphDuplicate.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency matrix representation of a integer's {@link Graph}.
     */
    @Test
    public void graphOfIntegersMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("2 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("3 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("4 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("5 1 1 1 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = integerGraph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency matrix representation of a string's {@link Graph}.
     */
    @Test
    public void graphOfStringsMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  A B C D E").append(LINE_SEPARATOR)
                .append("A 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("B 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("C 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("D 0 0 0 0 1").append(LINE_SEPARATOR)
                .append("E 1 1 1 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = stringGraph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency matrix representation of a integer's  disconnected {@link Graph}.
     */
    @Test
    public void graphDisconnetedMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 0 0 0 0").append(LINE_SEPARATOR)
                .append("2 0 0 0 0 0").append(LINE_SEPARATOR)
                .append("3 0 0 0 0 0").append(LINE_SEPARATOR)
                .append("4 0 0 0 0 0").append(LINE_SEPARATOR)
                .append("5 0 0 0 0 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = disconnectedGraph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency list representation of a string's {@link Graph}
     * with duplicated strings.
     */
    @Test
    public void graphOfIntegersDuplicateListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2 4 5").append(LINE_SEPARATOR)
                .append("2 - 1 3 5 6").append(LINE_SEPARATOR)
                .append("3 - 2 4 6").append(LINE_SEPARATOR)
                .append("4 - 1 3 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 4 6").append(LINE_SEPARATOR)
                .append("6 - 2 3 5").append(LINE_SEPARATOR)
                .toString();

        String list = integerGraphDuplicate.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
    }

    /**
     * Tests the adjacency list representation of a integer's  {@link Graph}.
     */
    @Test
    public void graphOfIntegersListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2 5").append(LINE_SEPARATOR)
                .append("2 - 1 5").append(LINE_SEPARATOR)
                .append("3 - 5").append(LINE_SEPARATOR)
                .append("4 - 5").append(LINE_SEPARATOR)
                .append("5 - 1 2 3 4").append(LINE_SEPARATOR)
                .toString();

        String list = integerGraph.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
    }

    /**
     * Tests the adjacency list representation of a string's  {@link Graph}.
     */
    @Test
    public void graphOfStringsListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("A - B E").append(LINE_SEPARATOR)
                .append("B - A E").append(LINE_SEPARATOR)
                .append("C - E").append(LINE_SEPARATOR)
                .append("D - E").append(LINE_SEPARATOR)
                .append("E - A B C D").append(LINE_SEPARATOR)
                .toString();

        String list = stringGraph.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
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
     * Tests the shortest path between two vertexes at {@link Graph}.
     */
    @Test
    public void graphOfIntegersShortestPathTest() {
        String expectedPathBetween1And3 = "1 5 3";
        String expectedPathBetween1And4 = "1 5 4";
        String expectedPathBetween3And2 = "3 5 2";
        String expectedPathBetween5And2 = "5 2";
        String expectedPathBetween5And5 = "5";

        assertEquals(expectedPathBetween1And3, integerGraph.shortestPath(1, 3));
        assertEquals(expectedPathBetween1And4, integerGraph.shortestPath(1, 4));
        assertEquals(expectedPathBetween3And2, integerGraph.shortestPath(3, 2));
        assertEquals(expectedPathBetween5And2, integerGraph.shortestPath(5, 2));
        assertEquals(expectedPathBetween5And5, integerGraph.shortestPath(5, 5));
    }

    /**
     * Tests if throws exception when try get shortest path between disconnected vertexes.
     */
    @Test
    public void disconnectedGraphShortestPathTest() {
        IGraph<Integer, Edge<Integer>> disconnectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        disconnectedGraph.addVertex(i1);
        disconnectedGraph.addEdge(i2, i3);
        String expectedPathBetween2And3 = "2 3";
        assertEquals(expectedPathBetween2And3, disconnectedGraph.shortestPath(2, 3));

        try {
            disconnectedGraph.shortestPath(i1, i2);
            fail("Should have thrown exception when trying to find the" +
                    " shortest path between disconnected vertexes.");
        } catch (Exception e) {
            assertEquals("There isn't a path between 1 and 2",
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
        IGraph<Integer, Edge<Integer>> connectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        singleVertexGraph.addVertex(i1);
        connectedGraph.addVertex(i1);
        connectedGraph.addEdge(i1, i2);
        connectedGraph.addEdge(i2, i3);

        assertTrue(singleVertexGraph.connected());
        assertTrue(connectedGraph.connected());
        assertFalse(disconnectedGraph.connected());
    }

    /**
     * Tests MST in integer's {@link Graph}.
     */
    @Test
    public void mstIntegerGraphTest() {
    	 String expectedMST = new StringBuilder()
                 .append("[1, 2]").append(LINE_SEPARATOR)
                 .append("[1, 5]").append(LINE_SEPARATOR)
                 .append("[3, 5]").append(LINE_SEPARATOR)
                 .append("[4, 5]").append(LINE_SEPARATOR)
                 .toString();
    	assertEquals(integerGraph.MST(), expectedMST);
    }

    /**
     * Tests MST in integer's {@link Graph} with duplicated elements.
     */
    @Test
    public void mstIntegerDuplicateGraphTest() {
    	 String expectedMST = new StringBuilder()
    			 .append("[1, 2]").append(LINE_SEPARATOR)
                 .append("[1, 4]").append(LINE_SEPARATOR)
                 .append("[1, 5]").append(LINE_SEPARATOR)
                 .append("[2, 3]").append(LINE_SEPARATOR)
                 .append("[2, 6]").append(LINE_SEPARATOR)
                 .toString();
    	assertEquals(expectedMST, integerGraphDuplicate.MST());
    }

    /**
     * Tests MST in string's {@link Graph}.
     */
    @Test
    public void mstStringGraphTest() {
    	String expectedMST = new StringBuilder()
                .append("[A, B]").append(LINE_SEPARATOR)
                .append("[A, E]").append(LINE_SEPARATOR)
                .append("[C, E]").append(LINE_SEPARATOR)
                .append("[D, E]").append(LINE_SEPARATOR) 
                .toString();
   	assertEquals(expectedMST, stringGraph.MST());
    }

    /**
     * Tests MST in disconnected {@link Graph}.
     */
    @Test
    public void mstDisconnectedGraphTest() {
    	assertEquals(Utils.STRING_EMPTY, disconnectedGraph.MST());
    }
}
