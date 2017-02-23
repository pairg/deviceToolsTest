import java.math.BigDecimal;
import java.math.RoundingMode;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * 2D point
 */
public class Point {
    // X coordinate
    private final double x;
    // Y coordinate
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Is this point equals with point b?
     *
     * @param b Other point
     * @return True if the the points are equal (contains the same coordinates)
     */
    public boolean equals(Point b) {
        return (this.getX() == b.getX() && this.getY() == b.getY());
    }

    /**
     * Calculate the distance between this point and the point b
     * Formula: sqrt( (b.x - this.x)^2 + (b.y - this.y)^2 )
     *
     * @param b Other point
     * @return Distance between the two points
     */
    public double distanceFrom(Point b) {
        double xDistance = b.getX() - this.getX();
        double yDistance = b.getY() - this.getY();
        double xDistPow = Math.pow(xDistance, 2d);
        double yDistPow = Math.pow(yDistance, 2d);

        if((xDistPow + yDistPow) == Infinity){
            // Use the BigDecimal only if we can't use the normal method. Slower.
            BigDecimal xBigDecimal = BigDecimal.valueOf(xDistance).pow(2);
            BigDecimal yBigDecimal = BigDecimal.valueOf(yDistance).pow(2);
            return bigDecimalSqrt(xBigDecimal.add(yBigDecimal), 1000).doubleValue();
        }else{
            return Math.sqrt(xDistPow + yDistPow);
        }
    }

    /**
     * Square root of BigDecimal
     *
     * From the StackOverflow, Java doesn't contains out-of-box solution for this
     * @link http://stackoverflow.com/questions/13649703/square-root-of-bigdecimal-in-java
     * @param num Number
     * @param scale Scale (number of digits)
     * @return bigDecimalSqrt(num)
     */
    private static BigDecimal bigDecimalSqrt(BigDecimal num, final int scale) {
        final BigDecimal two = BigDecimal.valueOf(2);
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = num.divide(two, RoundingMode.FLOOR);
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = num.divide(x0, scale, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(two, scale, RoundingMode.HALF_UP);
        }
        return x1;
    }

    public String toString(){
        return "("+this.getX()+", "+this.getY()+")";
    }

}
