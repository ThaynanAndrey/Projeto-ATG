package graph;

public class Edge {

    protected int outVertex;

    protected int inVertex;

    public Edge(int outVertex, int inVertex) {
        this.outVertex = outVertex;
        this.inVertex = inVertex;
    }

    public int getOutVertex() {
        return outVertex;
    }

    public void setOutVertex(int outVertex) {
        this.outVertex = outVertex;
    }

    public int getInVertex() {
        return inVertex;
    }

    public void setInVertex(int inVertex) {
        this.inVertex = inVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (outVertex != edge.outVertex) return false;
        return inVertex == edge.inVertex;
    }

    @Override
    public int hashCode() {
        int result = outVertex;
        result = 31 * result + inVertex;
        return result;
    }

}
