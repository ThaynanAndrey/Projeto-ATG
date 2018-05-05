package com.ufcg.atg.graph;

/**
 * Represents a implementation of a weighted edge.
 *
 * @param <V> Type of the origin and target vertexes.
 *
 * @author Vélmer Oliveira
 */
public class WeightedEdge<V> extends Edge<V> {

    private float weight;

    /**
     * Constructs a {@link WeightedEdge}.
     *
     * @param originVertex The origin vertex of the edge.
     * @param targetVertex The target vertex of the edge.
     * @param weight The weight of the edge.
     */
    public WeightedEdge(V originVertex, V targetVertex, float weight) {
        super(originVertex, targetVertex);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WeightedEdge that = (WeightedEdge) o;

        return Float.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        return result;
    }

}
