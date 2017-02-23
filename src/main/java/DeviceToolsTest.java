import java.util.PriorityQueue;

/**
 * Main console app
 */
public class DeviceToolsTest {

    // Help message for the console app
    private static final String helpMessage = "\n"+
            "Calculate the first N closest/farthest point for the P point.\n"+
            "Arguments:\n"+
            " 1.: X coordinate of P point - double\n"+
            " 2.: Y coordinate of P point - double\n"+
            " 3.: N: number of points - positive and not 0 integer\n"+
            " 4.: Type: calculation type - 'closest' or 'farthest' string\n"+
            " 5.: File: input file path - string, optional, default: 'points.bin'\n\n";

    // Default input file path
    private static final String defaultInputFile = "points.bin";

    // Calculation types
    private static final String CLOSEST_CALC_TYPE = "closest";
    private static final String FARTHEST_CALC_TYPE = "farthest";

    /**
     * Main console app
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        try{
            double x, y;
            int numberOfOutputPoints;
            String calcType;
            String inputFilePath = defaultInputFile;

            if(args.length < 4 || args.length > 5)
                throw new Exception("Invalid number of arguments!");

            // Validate the X arg
            x = validateX(args[0]);
            System.out.println("X := "+x);

            // Validate the Y arg
            y = validateY(args[1]);
            System.out.println("Y := "+y);

            // Validate the N arg
            numberOfOutputPoints = validateN(args[2]);
            System.out.println("N := "+numberOfOutputPoints);

            // Validate the Type arg
            calcType = validateType(args[3]);
            System.out.println("Type := "+calcType);

            // Validate the File arg
            if(args.length == 5 && !args[4].trim().isEmpty())
                inputFilePath = args[4].trim();
            System.out.println("File := "+inputFilePath);

            // Calculate...
            PriorityQueue<PointTuple> result = calculate(inputFilePath, x, y, numberOfOutputPoints, calcType);

            // Print out the results
            System.out.println("\nResult (end to start):");
            PointTuple currPtTup = null;
            while((currPtTup = result.poll()) != null){
                System.out.println(currPtTup.point.toString()+" - distance: "+currPtTup.distance);
            }

        }catch (Exception e){
            System.err.println("ERROR: "+e.getMessage());
            System.out.println(helpMessage);
        }
    }

    /**
     * Validate the first argument
     *
     * @param x The argument as a String
     * @return Valid X value
     * @throws Exception If the argument is invalid
     */
    static double validateX(String x) throws Exception {
        try {
            return Double.parseDouble(x);
        }catch (Exception e){
            throw new Exception("Invalid X (first) argument!");
        }
    }

    /**
     * Validate the second argument
     *
     * @param y The argument as a String
     * @return Valid Y value
     * @throws Exception If the argument is invalid
     */
    static double validateY(String y) throws Exception {
        try {
            return Double.parseDouble(y);
        }catch (Exception e){
            throw new Exception("Invalid Y (second) argument!");
        }
    }

    /**
     * Validate the third argument
     *
     * @param nAsStr The argument as a String
     * @return Valid N value
     * @throws Exception If the argument is invalid
     */
    static int validateN(String nAsStr) throws Exception {
        int n;
        try {
            n = Integer.parseInt(nAsStr);
        }catch (Exception e){
            throw new Exception("Invalid N (third) argument!");
        }

        if(n < 1)
            throw new Exception("Invalid N (third) argument: smaller than 1!");

        return n;
    }

    /**
     * Validate the fourth argument
     *
     * @param typeAsStr The argument as a String
     * @return Valid type value
     * @throws Exception If the argument is invalid
     */
    static String validateType(String typeAsStr) throws Exception{
        if(typeAsStr.toLowerCase().equals(CLOSEST_CALC_TYPE))
            return CLOSEST_CALC_TYPE;
        else if(typeAsStr.toLowerCase().equals(FARTHEST_CALC_TYPE))
            return FARTHEST_CALC_TYPE;
        else
            throw new Exception("Invalid calculation type!");
    }

    /**
     * Calculate the result
     *
     * @param inputFile Input file
     * @param x The base point's X coordinate
     * @param y The base point's Y coordinate
     * @param numberOfOutputPoints The number of output points
     * @param calcType Valid calculation type
     * @return Result
     * @throws Exception If the calculation failed
     */
    static PriorityQueue<PointTuple> calculate(
            String inputFile, double x, double y, int numberOfOutputPoints, String calcType
    ) throws Exception {
        PointReader reader = new PointReaderImpl(inputFile);
        Calc calc;
        if(calcType.equals(CLOSEST_CALC_TYPE))
            calc = CalcFactory.createClosestCalc(reader);
        else
            calc = CalcFactory.createFarthestCalc(reader);

        return calc.calculate(x, y, numberOfOutputPoints);
    }

}
