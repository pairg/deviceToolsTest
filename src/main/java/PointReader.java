/**
 * Created by pairg on 2017.02.05..
 */
public interface PointReader {

    /**
     * Read the next point from the file
     *
     * @return Point Return with a Point instance or with null if we don't have more points in the file
     * @throws Exception If an IO error occurred or the file syntax is invalid
     */
    Point next() throws Exception;

    /**
     * Close the resources
     * WARNING: You have to call this method after the usage of this class!
     */
    void close();

}
