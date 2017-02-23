import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by pairg on 2017.02.05..
 */
public class DeviceToolsTestTest {

    // validateX()

    @Test(expected = Exception.class)
    public void testValidateXForEmpty() throws Exception {
        DeviceToolsTest.validateX("");
    }

    @Test(expected = Exception.class)
    public void testValidateXForNotNumber() throws Exception {
        DeviceToolsTest.validateX("b");
    }

    @Test
    public void testValidateXForValidNum() throws Exception {
        assertEquals(0d, DeviceToolsTest.validateX("0"), 0);
        assertEquals(1.234d, DeviceToolsTest.validateX("1.234"), 0);
        assertEquals(-123.4567d, DeviceToolsTest.validateX("-123.4567"), 0);
    }


    // validateY()

    @Test(expected = Exception.class)
    public void testValidateYForEmpty() throws Exception {
        DeviceToolsTest.validateY("");
    }

    @Test(expected = Exception.class)
    public void testValidateYForNotNumber() throws Exception {
        DeviceToolsTest.validateY("b");
    }

    @Test
    public void testValidateYForValidNum() throws Exception {
        assertEquals(0d, DeviceToolsTest.validateY("0"), 0);
        assertEquals(1.234d, DeviceToolsTest.validateY("1.234"), 0);
        assertEquals(-123.4567d, DeviceToolsTest.validateY("-123.4567"), 0);
    }


    // validateN()

    @Test(expected = Exception.class)
    public void testValidateNForEmpty() throws Exception {
        DeviceToolsTest.validateN("");
    }

    @Test(expected = Exception.class)
    public void testValidateNForNotNumber() throws Exception {
        DeviceToolsTest.validateN("b");
    }

    @Test(expected = Exception.class)
    public void testValidateNForZero() throws Exception {
        DeviceToolsTest.validateN("0");
    }

    @Test(expected = Exception.class)
    public void testValidateNForMinus() throws Exception {
        DeviceToolsTest.validateN("-1");
    }

    @Test
    public void testValidateNForValidNum() throws Exception {
        assertEquals(1, DeviceToolsTest.validateN("1"));
        assertEquals(123, DeviceToolsTest.validateN("123"));
    }


    // validateType()

    @Test(expected = Exception.class)
    public void testValidateTypeForEmpty() throws Exception {
        DeviceToolsTest.validateType("");
    }

    @Test(expected = Exception.class)
    public void testValidateTypeForInvalidStr() throws Exception {
        DeviceToolsTest.validateType("test");
    }

    @Test
    public void testValidateTypeForValidStr() throws Exception {
        assertEquals("closest", DeviceToolsTest.validateType("closest"));
        assertEquals("closest", DeviceToolsTest.validateType("CLOSEST"));
        assertEquals("closest", DeviceToolsTest.validateType("cLoSeSt"));
        assertEquals("farthest", DeviceToolsTest.validateType("farthest"));
        assertEquals("farthest", DeviceToolsTest.validateType("FARTHEST"));
        assertEquals("farthest", DeviceToolsTest.validateType("FaRtHeSt"));
    }


    // main()

    @Test
    public void testMain() {
        // Should not throw any exception if the arguments are INVALID
        DeviceToolsTest.main(new String[0]);

        // Should not throw any exception if the arguments are VALID
        String filePath = getClass().getClassLoader()
                .getResource("testPoints.bin")
                .getPath();
        DeviceToolsTest.main(new String[]{"1.123", "-123.4567809", "5", "closest", filePath});
    }
}
