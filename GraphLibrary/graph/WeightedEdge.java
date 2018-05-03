package graph;

public class WeightedEdge<V> extends Edge<V> {

    private float weight;

    public WeightedEdge(V outVertex, V inVertex, float weight) {
        super(outVertex, inVertex);
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
