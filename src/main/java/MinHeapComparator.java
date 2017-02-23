import java.util.Comparator;

/**
 * Class for the PriorityQueue to implement min heap
 * -> easily remove the closest element in the queue when when we find a farther element
 */
public class MinHeapComparator implements Comparator<Double> {

    /**
     * Compare two points distance
     *
     * @param distanceA Distance A
     * @param distanceB Distance B
     * @return 0 if A == B; 1 if A > B; -1 if A < B
     */
    public int compare(Double distanceA, Double distanceB) {
        if(distanceA.equals(distanceB))
            return 0;
        else if(distanceA < distanceB)
            return -1;
        else
            return 1;
    }
}
