import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class CalcFactoryTest {

    @Test
    public void testCreateClosestCalc() throws Exception {
        TestPointReader reader = new TestPointReader(
            new Point[]{ new Point(0d, 1d), new Point(0d, 2d) }
        );
        Calc closestCalc = CalcFactory.createClosestCalc(reader);

        PriorityQueue<PointTuple> result = closestCalc.calculate(0d, 0d, 1);
        assertEquals(1d, result.peek().distance, 0d);
    }

    @Test
    public void testCreateFarthestCalc() throws Exception {
        TestPointReader reader = new TestPointReader(
            new Point[]{ new Point(0d, 1d), new Point(0d, 2d) }
        );
        Calc farthestCalc = CalcFactory.createFarthestCalc(reader);

        PriorityQueue<PointTuple> result = farthestCalc.calculate(0d, 0d, 1);
        assertEquals(2d, result.peek().distance, 0d);
    }

}
