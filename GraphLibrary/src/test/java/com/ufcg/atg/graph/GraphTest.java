package com.ufcg.atg.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    private IGraph<Integer, Edge<Integer>> integerGraph;
    private IGraph<Integer, Edge<Integer>> integerGraphDuplicate;
    private IGraph<String, Edge<String>> stringGraph;
    private IGraph<Integer, Edge<Integer>> disconnectedGraph;

    @BeforeEach
    public void setUp() {
        setUpGraphOfIntegers();
        setUpGraphOfStrings();
        setUpDisconnectedGraph();
        setUpGraphOfIntegersDuplicate();
    }

    private void setUpGraphOfIntegers() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph = new Graph<>();
        integerGraph.addEdge(i1, i2);
        integerGraph.addEdge(i2, i5);
        integerGraph.addEdge(i5, i3);
        integerGraph.addEdge(i4, i5);
        integerGraph.addEdge(i1, i5);
    }
    
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

    private void setUpGraphOfStrings() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraph = new Graph<>();
        stringGraph.addEdge(s1, s2);
        stringGraph.addEdge(s2, s5);
        stringGraph.addEdge(s5, s3);
        stringGraph.addEdge(s4, s5);
        stringGraph.addEdge(s1, s5);
    }
    
    private void setUpDisconnectedGraph() {
    	Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
    	disconnectedGraph = new Graph<>();
    	disconnectedGraph.addVertex(i1);
    	disconnectedGraph.addVertex(i2);
    	disconnectedGraph.addVertex(i3);
    	disconnectedGraph.addVertex(i4);
    	disconnectedGraph.addVertex(i5);
    }
    
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
    
    @Test
    public void BFSTest() {
    	String expectedResultRoot1 = "1 2 5 3 4";
    	String expectedResultRoot2 = "2 1 5 3 4";
    	String expectedResultRoot3 = "3 5 1 2 4";
    	String expectedResultRoot4 = "4 5 1 2 3";
    	String expectedResultRoot5 = "5 1 2 3 4";
    	
    	assertEquals(expectedResultRoot1, integerGraph.BFS(1));
    	assertEquals(expectedResultRoot2, integerGraph.BFS(2));
    	assertEquals(expectedResultRoot3, integerGraph.BFS(3));
    	assertEquals(expectedResultRoot4, integerGraph.BFS(4));
    	assertEquals(expectedResultRoot5, integerGraph.BFS(5));
    }
  

    @Test
    public void disconnectedGraphTest(){
        IGraph<Integer, Edge<Integer>> disconnectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        disconnectedGraph.addVertex(i1);
        disconnectedGraph.addEdge(i2, i3);

        assertFalse(disconnectedGraph.connected());
    }

    @Test
    public void dfsInConnectedGraph1Test(){
        IGraph<Integer, Edge<Integer>> connectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        connectedGraph.addVertex(i1);
        connectedGraph.addEdge(i1, i2);
        connectedGraph.addEdge(i2, i3);

        StringBuilder sb = new StringBuilder()
                .append("1 - 0 -" + LINE_SEPARATOR)
                .append("2 - 1 1" + LINE_SEPARATOR)
                .append("3 - 2 2" + LINE_SEPARATOR);

        assertEquals(sb.toString(), connectedGraph.DFS(i1));

    }

    @Test
    public void dfsInConnectedGraph2Test(){
        IGraph<Integer, Edge<Integer>> connectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        connectedGraph.addVertex(i1);
        connectedGraph.addEdge(i1, i2);
        connectedGraph.addEdge(i2, i3);

        StringBuilder sb = new StringBuilder()
                .append("1 - 1 2" + LINE_SEPARATOR)
                .append("2 - 0 -" + LINE_SEPARATOR)
                .append("3 - 1 2" + LINE_SEPARATOR);

        assertEquals(sb.toString(), connectedGraph.DFS(i2));
    }

    @Test
    public void dfsInConnectedGraph3Test(){
        IGraph<Integer, Edge<Integer>> connectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        connectedGraph.addVertex(i1);
        connectedGraph.addEdge(i1, i2);
        connectedGraph.addEdge(i2, i3);

        StringBuilder sb = new StringBuilder()
                .append("1 - 2 2" + LINE_SEPARATOR)
                .append("2 - 1 3" + LINE_SEPARATOR)
                .append("3 - 0 -" + LINE_SEPARATOR);

        assertEquals(sb.toString(), connectedGraph.DFS(i3));

    }

    @Test
    public void dfsInDisconnectedGraph1Test(){
        IGraph<Integer, Edge<Integer>> disconnectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4;
        disconnectedGraph.addVertex(i1);
        disconnectedGraph.addEdge(i1, i2);
        disconnectedGraph.addEdge(i2, i3);
        disconnectedGraph.addVertex(i4);

        StringBuilder sb = new StringBuilder()
                .append("1 - 2 2" + LINE_SEPARATOR)
                .append("2 - 1 3" + LINE_SEPARATOR)
                .append("3 - 0 -" + LINE_SEPARATOR);

        assertEquals(sb.toString(), disconnectedGraph.DFS(i3));

    }

    @Test
    public void dfsInDisconnectedGraph2Test(){
        IGraph<Integer, Edge<Integer>> disconnectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4;
        disconnectedGraph.addVertex(i1);
        disconnectedGraph.addEdge(i1, i2);
        disconnectedGraph.addEdge(i2, i3);
        disconnectedGraph.addVertex(i4);

        StringBuilder sb = new StringBuilder()
                .append("4 - 0 -" + LINE_SEPARATOR);

        assertEquals(sb.toString(), disconnectedGraph.DFS(i4));

    }

    @Test
    public void connectedGraphSingleVertexTest(){
        IGraph<Integer, Edge<Integer>> connectedGraph = new Graph<>();
        Integer i1 = 1;
        connectedGraph.addVertex(i1);

        assertTrue(connectedGraph.connected());
    }

    @Test
    public void connectedGraphTest(){
        IGraph<Integer, Edge<Integer>> connectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        connectedGraph.addVertex(i1);
        connectedGraph.addEdge(i1, i2);
        connectedGraph.addEdge(i2, i3);

        assertTrue(connectedGraph.connected());
    }
    
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
    
    @Test
    public void mstIntegerDuplicateGraphTest() {
    	 String expectedMST = new StringBuilder()
    			 .append("[1, 2]").append(LINE_SEPARATOR)
                 .append("[1, 4]").append(LINE_SEPARATOR)
                 .append("[1, 5]").append(LINE_SEPARATOR)
                 .append("[2, 3]").append(LINE_SEPARATOR)
                 .append("[2, 6]").append(LINE_SEPARATOR)
                 .toString();
    	assertEquals(integerGraphDuplicate.MST(), expectedMST);
    }
    
    @Test
    public void mstStringGraphTest() {
    	String expectedMST = new StringBuilder()
                .append("[A, B]").append(LINE_SEPARATOR)
                .append("[A, E]").append(LINE_SEPARATOR)
                .append("[C, E]").append(LINE_SEPARATOR)
                .append("[D, E]").append(LINE_SEPARATOR) 
                .toString();
   	assertEquals(stringGraph.MST(), expectedMST);
    }
    
    @Test
    public void mstDisconnectedGraphTest() {
    	assertEquals(disconnectedGraph.MST(), "disconneted graph");
    }
}
