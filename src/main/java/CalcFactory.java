/**
 * Create the right Calc object for the right calculation type
 */
public class CalcFactory {

    /**
     * Create a closest points calculator
     *
     * @param pointReader Points for the calculation
     * @return Closest points calculator
     */
    public static Calc createClosestCalc(PointReader pointReader) {
        return new CalcImpl(pointReader, new MaxHeapComparator());
    }

    /**
     * Create a farthest points calculator
     *
     * @param pointReader Points for the calculation
     * @return Farthest points calculator
     */
    public static Calc createFarthestCalc(PointReader pointReader) {
        return new CalcImpl(pointReader, new MinHeapComparator());
    }

}
