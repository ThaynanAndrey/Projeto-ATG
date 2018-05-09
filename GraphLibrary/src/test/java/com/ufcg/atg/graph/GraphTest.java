package com.ufcg.atg.graph;

import org.junit.Assert;
import org.junit.Test;

import static com.ufcg.atg.util.Utils.LINE_SEPARATOR;

public class GraphTest {

    @Test
    public void graphOfIntegerVertexesMatrizRepresentationTest() throws Exception {
        BaseGraph<Integer, Edge<Integer>> graph = new Graph<>();
        Integer i1 = 1,
                i2 = 2,
                i3 = 3,
                i4 = 4,
                i5 = 5;
        graph.addEdge(i1, i2);
        graph.addEdge(i2, i5);
        graph.addEdge(i5, i3);
        graph.addEdge(i3, i4);
        graph.addEdge(i4, i5);
        graph.addEdge(i1, i5);

        String expectedMatrix = new StringBuilder()
                .append("  1 2 3 4 5").append(LINE_SEPARATOR)
                .append("1 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("2 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("3 0 0 0 1 1").append(LINE_SEPARATOR)
                .append("4 0 0 1 0 1").append(LINE_SEPARATOR)
                .append("5 1 1 1 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = graph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        Assert.assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void graphOfStringVertexesMatrizRepresentationTest() throws Exception {
        BaseGraph<String, Edge<String>> graph = new Graph<>();
        String s1 = "A",
                s2 = "B",
                s3 = "C",
                s4 = "D",
                s5 = "E";
        graph.addEdge(s1, s2);
        graph.addEdge(s2, s5);
        graph.addEdge(s5, s3);
        graph.addEdge(s3, s4);
        graph.addEdge(s4, s5);
        graph.addEdge(s1, s5);

        String expectedMatrix = new StringBuilder()
                .append("  A B C D E").append(LINE_SEPARATOR)
                .append("A 0 1 0 0 1").append(LINE_SEPARATOR)
                .append("B 1 0 0 0 1").append(LINE_SEPARATOR)
                .append("C 0 0 0 1 1").append(LINE_SEPARATOR)
                .append("D 0 0 1 0 1").append(LINE_SEPARATOR)
                .append("E 1 1 1 1 0").append(LINE_SEPARATOR)
                .toString();

        String matrix = graph.graphRepresentation(RepresentationType.ADJACENCY_MATRIX);
        Assert.assertEquals(matrix, expectedMatrix);
    }

}
