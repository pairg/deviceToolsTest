import java.util.*;

/**
 * Calculate implementation
 */
public class CalcImpl implements Calc {

    // Input point reader
    private PointReader pointReader = null;

    // Compare two points distance from the base point
    private Comparator<Double> distanceComparator = null;

    /**
     * Constructor
     * The calculation type is based on the different comparator implementations.
     *
     * @param pointReader Input point reader
     * @param distanceComparator Compare two points distance from the base point
     */
    public CalcImpl(PointReader pointReader, Comparator<Double> distanceComparator) {
        this.pointReader = pointReader;
        this.distanceComparator = distanceComparator;
    }

    /**
     * Calculate the N number of closest / farthest points for the base point
     *
     * @param x X coordinate of the base point
     * @param y Y coordinate of the base point
     * @param numberOfOutputPoint Number of output points (N)
     * @return N number of points from the input points
     * @throws Exception If the PointReader have some problem
     */
    public PriorityQueue<PointTuple> calculate(double x, double y, int numberOfOutputPoint)
            throws Exception {

        // Result set: Min / Max heap (depends on the calculation type)
        PriorityQueue<PointTuple> results = new PriorityQueue<PointTuple>(
                numberOfOutputPoint, new PointTupleComparator(this.distanceComparator)
        );

        // Base point
        Point basePoint = new Point(x, y);

        // Current point
        Point currentPoint = null;

        while((currentPoint = pointReader.next()) != null){
            double currentDistance = basePoint.distanceFrom(currentPoint);

            if(results.size() < numberOfOutputPoint){
                // This is one of the first N elements (N := numberOfOutputPoints)
                results.add(new PointTuple(currentDistance, currentPoint));
            }else{

                // The result set is full: compare the current point with the result set's first element
                if(
                    this.distanceComparator.compare(results.peek().distance, currentDistance) < 0
                ){
                    results.add(new PointTuple(currentDistance, currentPoint));
                    results.poll();
                }

            }
        }

        // Close the reader
        pointReader.close();

        return results;
    }

}
