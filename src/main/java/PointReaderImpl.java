import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by pairg on 2017.02.05..
 */
public class PointReaderImpl implements PointReader {

    // File path
    private String filePath = null;
    // Already red numbers
    private int numberOfDoubles = 0;
    // Input streams
    private FileInputStream fileInputStream = null;
    private DataInputStream dataInputStream = null;

    /**
     * Constructor
     *
     * @param inputFilePath Path of the input file
     * @throws Exception If an IO error occurred
     */
    public PointReaderImpl(String inputFilePath) throws Exception {
        this.filePath = inputFilePath;

        try{
            this.fileInputStream = new FileInputStream(this.filePath);
            this.dataInputStream = new DataInputStream(this.fileInputStream);
        }catch(Exception e){
            this.close();
            throw e;
        }
    }

    /**
     * Close the resources
     * WARNING: You have to call this method after the usage of this class!
     */
    public void close(){
        if (this.dataInputStream != null){

            try{
                this.dataInputStream.close();
                // The next line is unnecessary, but good to do it for double safety
                if(this.fileInputStream != null)
                    this.fileInputStream.close();
            }catch(IOException e){
                // Nothing to do ...
            }

            this.dataInputStream = null;
            this.fileInputStream = null;
        }
    }

    /**
     * Close the resource when the GC try to free up this instance
     * WARNING: This is not a reliable solution, but the last line of defense!
     *
     * @throws Throwable
     */
    public void finalize() throws Throwable{
        this.close();
        super.finalize();
    }

    /**
     * Read the next point from the file
     *
     * @return Point Return with a Point instance or with null if we don't have more points in the file
     * @throws Exception If an IO error occurred or the file syntax is invalid
     */
    public Point next() throws Exception {
        Double x = readNextNumber();
        Double y = readNextNumber();

        if(x != null && y != null)
            return new Point(x, y);

        // End of file OR error?
        if(x == null && y == null && this.dataInputStream == null)
            return null;
        else
            throw new Exception(
                "Invalid number in '"+this.filePath+"' at the "+this.numberOfDoubles+"th number!"
            );
    }

    /**
     * Get the next number
     *
     * @return Next number or null if we don't have more lines
     * @throws IOException IO error
     */
    private Double readNextNumber() throws IOException {
        Double number = null;
        if(this.dataInputStream != null){

            try {

                // Convert between little and big endian ordering
                long doubleAsLong = this.dataInputStream.readLong();
                number = Double.longBitsToDouble(Long.reverseBytes(doubleAsLong));

                // Count the numbers
                this.numberOfDoubles++;

            }catch(EOFException eof){

                // Automatic close at the end of the file
                this.close();

            }catch (IOException e){
                throw new IOException(
                    "Invalid file in '"+this.filePath+"' at the "+this.numberOfDoubles+"th number!", e
                );
            }

        }

        return number;
    }
}
