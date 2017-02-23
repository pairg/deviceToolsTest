import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * Created by pairg on 2017.02.05..
 */
public class MaxHeapComparatorTest {

    @Test
    public void testCompare(){
        Comparator<Double> comparator = new MaxHeapComparator();
        assertEquals(1, comparator.compare(1d, 2d));
        assertEquals(0, comparator.compare(2d, 2d));
        assertEquals(-1, comparator.compare(2d, 1d));
    }

}
