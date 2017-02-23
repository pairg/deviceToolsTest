import java.util.PriorityQueue;

/**
 * Calculate the closest / farthest points
 */
public interface Calc {

    /**
     * Calculate the N number of closest / farthest points for the base point
     *
     * @param x X coordinate of the base point
     * @param y Y coordinate of the base point
     * @param numberOfOutputPoint Number of output points (N)
     * @param calcType Calculation type: closest / farthest
     * @return N number of points from the input points
     * @throws Exception If the PointReader have some problem
     */
    PriorityQueue<PointTuple> calculate(double x, double y, int numberOfOutputPoint)
            throws Exception;

}
