import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by pairg on 2017.02.06..
 */
public class CalcImplTest {

    private CalcImpl generateCalc(
            double aX, double aY, double bX, double bY, double cX, double cY,
            double dX, double dY, double eX, double eY, double fX, double fY,
            Comparator<Double> comparator
    ){
        Point[] points = new Point[]{
                new Point(aX, aY), new Point(bX, bY), new Point(cX, cY),
                new Point(dX, dY), new Point(eX, eY), new Point(fX, fY),
        };
        TestPointReader reader = new TestPointReader(points);
        return new CalcImpl(reader, comparator);
    }

    @Test
    public void testCalculate() throws Exception {
        // Check: closest order, sort
        CalcImpl calc = generateCalc(
            6d, 6d, 3d, 3d, 1d, 1d, 4d, 4d, 2d, 2d, 5d, 5d,
            new MaxHeapComparator()
        );
        PriorityQueue<PointTuple> result = calc.calculate(0d, 0d, 3);
        assertEquals(3, result.size());
        assertTrue(new Point(3d, 3d).equals(result.poll().point));
        assertTrue(new Point(2d, 2d).equals(result.poll().point));
        assertTrue(new Point(1d, 1d).equals(result.poll().point));

        // Check: same point twice, farthest order
        CalcImpl calc2 = generateCalc(
            1d, 1d, 4d, 4d, 3d, 3d, 4d, 4d, 2d, 2d, 5d, 5d,
            new MinHeapComparator()
        );
        PriorityQueue<PointTuple> result2 = calc2.calculate(0d, 0d, 4);
        assertEquals(4, result2.size());
        assertTrue(new Point(3d, 3d).equals(result2.poll().point));
        assertTrue(new Point(4d, 4d).equals(result2.poll().point));
        assertTrue(new Point(4d, 4d).equals(result2.poll().point));
        assertTrue(new Point(5d, 5d).equals(result2.poll().point));

        // Check: result's size is lower than the required output size
        CalcImpl calc3 = generateCalc(
            1d, 1d, 4d, 4d, 3d, 3d, 4d, 4d, 2d, 2d, 5d, 5d,
            new MinHeapComparator()
        );
        PriorityQueue<PointTuple> result3 = calc3.calculate(0d, 0d, 8);
        assertEquals(6, result3.size());
        assertTrue(new Point(1d, 1d).equals(result3.poll().point));
        assertTrue(new Point(2d, 2d).equals(result3.poll().point));
        assertTrue(new Point(3d, 3d).equals(result3.poll().point));
        assertTrue(new Point(4d, 4d).equals(result3.poll().point));
        assertTrue(new Point(4d, 4d).equals(result3.poll().point));
        assertTrue(new Point(5d, 5d).equals(result3.poll().point));
    }

}
