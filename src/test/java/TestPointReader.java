import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Test Reader: get numbers without reading a file
 */
public class TestPointReader implements PointReader {

    private List<Point> points = null;
    private Iterator<Point> pointIterator = null;

    public TestPointReader(Point[] points) {
        this.points = Arrays.asList(points);
        this.reset();
    }

    public Point next() throws Exception {
        if(this.pointIterator != null && this.pointIterator.hasNext())
            return this.pointIterator.next();
        else
            return null;
    }

    public void close() {
        this.pointIterator = null;
    }

    public void reset(){
        this.pointIterator = this.points.iterator();
    }
}