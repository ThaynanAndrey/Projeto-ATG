package com.ufcg.atg.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the class {@link WeightedGraph} as an implementation of {@link IWeightedGraph}.
 */
public class WeightedGraphTest {

    private IWeightedGraph<Integer, WeightedEdge<Integer>> integerGraph;
    private IWeightedGraph<String, WeightedEdge<String>> stringGraph;

    /**
     * Tests' set up.
     */
    @BeforeEach
    public void setUp() {
        setUpGraphOfIntegers();
        setUpGraphOfStrings();
    }

    /**
     * Constructs a weighted graph with vertices of integer value.
     */
    private void setUpGraphOfIntegers() {
        Integer i1 = 1, i2 = 2, i3 = 3, i4 = 4, i5 = 5;
        integerGraph = new WeightedGraph<>();
        integerGraph.addEdge(i1, i2, 0.1f);
        integerGraph.addEdge(i2, i5, 0.2f);
        integerGraph.addEdge(i5, i3, 5f);
        integerGraph.addEdge(i3, i4, -9.5f);
        integerGraph.addEdge(i4, i5, 2.3f);
        integerGraph.addEdge(i1, i5,1f);
    }

    /**
     * Constructs a weighted graph with vertices of string value.
     */
    private void setUpGraphOfStrings() {
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D", s5 = "E";
        stringGraph = new WeightedGraph<>();
        stringGraph.addEdge(s1, s2, 0.1f);
        stringGraph.addEdge(s2, s5, 0.2f);
        stringGraph.addEdge(s5, s3, 5f);
        stringGraph.addEdge(s3, s4, -9.5f);
        stringGraph.addEdge(s4, s5, 2.3f);
        stringGraph.addEdge(s1, s5,1f);
    }

    /**
     * Tests the adjacency matrix representation of a integer's {@link WeightedGraph}.
     */
    @Test
    public void graphOfIntegersMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 0.1 0 0 1").append(LINE_SEPARATOR)
                .append("2 0.1 0 0 0 0.2").append(LINE_SEPARATOR)
                .append("3 0 0 0 -9.5 5").append(LINE_SEPARATOR)
                .append("4 0 0 -9.5 0 2.3").append(LINE_SEPARATOR)
                .append("5 1 0.2 5 2.3 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = integerGraph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency matrix representation of a string's {@link WeightedGraph}.
     */
    @Test
    public void graphOfStringsMatrixRepresentationTest() {
        String expectedMatrix = new StringBuilder()
                .append("  A B C D E").append(LINE_SEPARATOR)
                .append("A 0 0.1 0 0 1").append(LINE_SEPARATOR)
                .append("B 0.1 0 0 0 0.2").append(LINE_SEPARATOR)
                .append("C 0 0 0 -9.5 5").append(LINE_SEPARATOR)
                .append("D 0 0 -9.5 0 2.3").append(LINE_SEPARATOR)
                .append("E 1 0.2 5 2.3 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = stringGraph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        assertEquals(matrix, expectedMatrix);
    }

    /**
     * Tests the adjacency list representation of a integer's {@link WeightedGraph}.
     */
    @Test
    public void graphOfIntegersListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("1 - 2(0.1) 5(1)").append(LINE_SEPARATOR)
                .append("2 - 1(0.1) 5(0.2)").append(LINE_SEPARATOR)
                .append("3 - 4(-9.5) 5(5)").append(LINE_SEPARATOR)
                .append("4 - 3(-9.5) 5(2.3)").append(LINE_SEPARATOR)
                .append("5 - 1(1) 2(0.2) 3(5) 4(2.3)").append(LINE_SEPARATOR)
                .toString();

        String list = integerGraph.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
    }

    /**
     * Tests the adjacency list representation of a string's {@link WeightedGraph}.
     */
    @Test
    public void graphOfStringsListRepresentationTest() {
        String expectedList = new StringBuilder()
                .append("A - B(0.1) E(1)").append(LINE_SEPARATOR)
                .append("B - A(0.1) E(0.2)").append(LINE_SEPARATOR)
                .append("C - D(-9.5) E(5)").append(LINE_SEPARATOR)
                .append("D - C(-9.5) E(2.3)").append(LINE_SEPARATOR)
                .append("E - A(1) B(0.2) C(5) D(2.3)").append(LINE_SEPARATOR)
                .toString();

        String list = stringGraph.graphRepresentation(RepresentationType.ADJACENCY_LIST);
        assertEquals(list, expectedList);
    }

    /**
     * Tests the shortest path between two vertexes at {@link WeightedGraph}.
     */
    @Test
    void graphOfIntegersShortestPathTest() {
        IWeightedGraph<Integer, WeightedEdge<Integer>> integerGraph
                = new WeightedGraph<>();
        integerGraph.addEdge(1, 2, 0.1f);
        integerGraph.addEdge(2, 5, 0.2f);
        integerGraph.addEdge(5, 3, 5f);
        integerGraph.addEdge(4, 5, 2.3f);
        integerGraph.addEdge(1, 5,1f);

        String expectedPathBetween1And3 = "1 2 5 3";
        String expectedPathBetween1And4 = "1 2 5 4";
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
     * Tests if throws exception when trying to find the shortest path
     * in a graph with negative circle.
     */
    @Test
    public void graphWithNegativeCircleShortestPathTest() {
        try {
            integerGraph.shortestPath(1, 3);
            fail("Should have thrown exception when trying to find the" +
                    " shortest path in a graph with negative circle.");
        } catch (Exception e) {
            assertEquals("The shortest path cannot be found in a" +
                            " graph with negative circle.",
                    e.getMessage(), "A mensagem de erro está errada.");
        }
    }

    /**
     * Tests MST in integer's {@link WeightedGraph}.
     */
    @Test
    public void integerMstGraphTest() {
    	 String expectedMST = new StringBuilder()
                 .append("[3, 4] : -9.5").append(LINE_SEPARATOR)
                 .append("[1, 2] : 0.1").append(LINE_SEPARATOR)
                 .append("[2, 5] : 0.2").append(LINE_SEPARATOR)
                 .append("[4, 5] : 2.3").append(LINE_SEPARATOR)
                 .toString();
    	assertEquals(integerGraph.MST(), expectedMST);
    }

    /**
     * Tests MST in string's {@link WeightedGraph}.
     */
    @Test
    public void stringMstGraphTest() {
    	 String expectedMST = new StringBuilder()
                 .append("[C, D] : -9.5").append(LINE_SEPARATOR)
                 .append("[A, B] : 0.1").append(LINE_SEPARATOR)
                 .append("[B, E] : 0.2").append(LINE_SEPARATOR)
                 .append("[D, E] : 2.3").append(LINE_SEPARATOR)
                 .toString();
    	assertEquals(stringGraph.MST(), expectedMST);
    }

}