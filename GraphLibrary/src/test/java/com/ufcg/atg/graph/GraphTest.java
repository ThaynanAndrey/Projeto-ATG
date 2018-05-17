package com.ufcg.atg.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    private IGraph<Integer, Edge<Integer>> integerGraph;
    private IGraph<String, Edge<String>> stringGraph;

    @BeforeEach
    public void setUp() {
        setUpGraphOfIntegers();
        setUpGraphOfStrings();
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

    private void setUpGraphOfStrings() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraph = new Graph<>();
        stringGraph.addEdge(s1, s2);
        stringGraph.addEdge(s2, s5);
        stringGraph.addEdge(s5, s3);
        stringGraph.addEdge(s4, s5);
        stringGraph.addEdge(s1, s5);
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
    public void disconnectedGraphTest(){
        IGraph<Integer, Edge<Integer>> disconnectedGraph = new Graph<>();
        Integer i1 = 1, i2 = 2, i3 = 3;
        disconnectedGraph.addVertex(i1);
        disconnectedGraph.addEdge(i2, i3);

        assertFalse(disconnectedGraph.connected());
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

}
