/**
 * Tuple implementation for "(distanceFromBasePoint, currentPoint)" tuples
 */
public class PointTuple {

    // The point distance from the base point
    public final double distance;
    // Point
    public final Point point;

    /**
     * Constructor
     *
     * @param distance Distance from the base point
     * @param point Point
     */
    public PointTuple(double distance, Point point) {
        this.point = point;
        this.distance = distance;
    }

}
