import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pairg on 2017.02.05..
 */
public class PointReaderImplTest {

    @Test
    public void testNextWithValidFile() throws Exception {
        String filePath = getClass().getClassLoader()
                .getResource("testPoints.bin")
                .getPath();
        PointReaderImpl reader = new PointReaderImpl(filePath);

        // Valid values
        assertTrue(new Point(0d, 0d).equals(reader.next()));
        assertTrue(new Point(1d, 2d).equals(reader.next()));
        assertTrue(new Point(33.39705086, 134.7628216).equals(reader.next()));
        assertTrue(new Point(57.31316023, -193.404109).equals(reader.next()));
        assertTrue(new Point(-17.08460909, -180.569577).equals(reader.next()));

        // Invalid value (half value) at the end
        try{
            reader.next();
            fail("Expected exception!");
        }catch(Exception e){}

        // End of file
        assertNull(reader.next());
        assertNull(reader.next());

        reader.close();
    }

    @Test(expected = Exception.class)
    public void testNextWithInvalidFile() throws Exception {
        new PointReaderImpl("/not/existing/file/path.test");
    }

    @Test
    public void testClose() throws Exception {
        String filePath = getClass().getClassLoader().getResource("testPoints.bin").getPath();
        PointReaderImpl reader = new PointReaderImpl(filePath);

        reader.close();

        // Closed resources means EOF
        assertNull(reader.next());
        assertNull(reader.next());
    }

}
