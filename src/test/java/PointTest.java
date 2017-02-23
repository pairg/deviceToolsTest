import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pairg on 2017.02.05..
 */
public class PointTest {

    @Test
    public void testEquals(){
        assertTrue(new Point(1d, 2d).equals(new Point(1d, 2d)));
        assertTrue(new Point(1d, 2d).equals(new Point(1.00d, 2.00d)));
        assertTrue(new Point(-12.1d, -0.123d).equals(new Point(-12.1d, -0.123d)));
        assertFalse(new Point(0d, -0.123456789d).equals(new Point(0d, -0.123456788d)));
    }

    @Test
    public void testDistanceFrom(){
        Point a = new Point(1d, 1d);
        assertEquals(0d, a.distanceFrom(new Point(1d, 1d)), 0);
        assertEquals(1d, a.distanceFrom(new Point(0d, 1d)), 0);
        assertEquals(1d, a.distanceFrom(new Point(1d, 0d)), 0);
        assertEquals(1d, a.distanceFrom(new Point(2d, 1d)), 0);
        assertEquals(1d, a.distanceFrom(new Point(1d, 2d)), 0);
        assertEquals(1.4142135623730951d, a.distanceFrom(new Point(0d, 0d)), 0.000000000000001d);
        assertEquals(1.4142135623730951d * 2, a.distanceFrom(new Point(-1d, -1d)), 0.000000000000001d);
        assertEquals(1.4142135623730951d, a.distanceFrom(new Point(2d, 2d)), 0.000000000000001d);

        // Check for Infinity result
        assertEquals(
                5.900122301971569E238,
                a.distanceFrom(new Point(5.239923412224983E238d, 2.7119450238230817E238d)),
                0.000000000000001d
        );
    }

}
