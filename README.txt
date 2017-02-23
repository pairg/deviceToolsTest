DeviceTools tech test
by Gergő Pásztor - 2017.01.05.

Task: Read the TASK.txt!

Requirements
- Java 7
- Maven 3

Install
$ sudo apt-get install openjdk-7-jdk maven
$ cd deviceToolsTest
$ mvn clean install

Run
1) Drop an input file to the project dir and rename it to 'points.bin'! 
2) Run the program:
$ cd deviceToolsTest
$ java -jar target/deviceToolsTest-1.0-SNAPSHOT.jar ..arguments..
For more info about the arguments: run the program without any argument!

Testing
$ cd deviceToolsTest
$ mvn test

Classes
- Point: 2D point representation
- PointTuple: Tuple implementation for "(distanceFromBasePoint, point)"
  (Java doesn't have any tuple type)
- PointTupleComparator: proxy class for Min/MaxHeapComparator for PointTuples
- MinHeapComparator, MaxHeapComparator: comparators used by the CalcImpl's PriorityQueue
- Calc, CalcImpl: calculate the result
- CalcFactory: create a Calc object for the calculation type
- PointReader, PointReaderImpl: File reader and line parser
- DeviceToolsTest: Main class, console app
\*\* 'Impl' means implementation of an interface with the same name

How is it working?
1) Read the lines from the input file one-by-one.
   Only store two line in the memory at the same time (one point). Optimized for low-memory usage.
2) Calculate the distance between the current point and the base point.
3) Store the current point if this is closer/farther for the base point than the actually stored N point.
4) Go to the 1)

We are only storing N pieces of point at any time, where N is the number of output points.
The stored points are ordered and stored in a min / max heap. We are using a max heap for the
'closest' and a min heap for the 'farthest' calculation, because we are using the first (peek) element for
comparing with the incoming points and we will drop it if we will find a better one.
Optimized for different input point sets.

Edge cases
I assumed that these points has a meaning, like these are the coordinates of peoples. In this case
if I found a point that is equal with the original one the calculation not exclude it from the results.
Also if the input set contains the same points that is close to the base point more than once, but not
equal with it the app will show all of these points (not just one).

Complexity
- Run over on every point from the input file once: O(n)
- Check that the current element is in the N element that we are searching for: O(1)
- Replace an element in the current result: O(log n)
- Overall worst case scenario: O(numberOfInputPoints * log(numberOfOutputPoints))
- Overall best case scenario: O(numberOfInputPoints)
