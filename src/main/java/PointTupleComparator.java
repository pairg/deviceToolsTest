import java.util.Comparator;

/**
 * Class for the PriorityQueue to implement min / max heap based on the calculation type
 * This is just a proxy class that unbound the distance value from the PointTuples.
 */
class PointTupleComparator implements Comparator<PointTuple> {

    // Inner comparator, used for compare two points distance from the base point
    Comparator<Double> distanceComparator = null;

    /**
     * Constructor
     *
     * @param distanceComparator Comparator for two distance value
     */
    public PointTupleComparator(Comparator<Double> distanceComparator) {
        this.distanceComparator = distanceComparator;
    }

    /**
     * Compare two points based on the calculation type and their distance from the base point
     *
     * @param tupleA Tuple A
     * @param tupleB Tuple B
     * @return 0 or 1 or -1, based on the inner distanceComparator
     */
    public int compare(PointTuple tupleA, PointTuple tupleB) {
        return this.distanceComparator.compare(tupleA.distance, tupleB.distance);
    }

}
