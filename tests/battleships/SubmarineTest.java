
package battleships;

import org.junit.*;
import static org.junit.Assert.*;

public class SubmarineTest
{
    private Submarine sb;
    private Ocean oc;

    @Before
    public void setUp()
    {
        sb = new Submarine();
        oc = new Ocean();
    }

    @After
    public void tearDown()
    {
        sb = null;
        oc = null;
    }

    @Test
    public void testConstructor()
    {
        assertEquals(1, sb.getSize());
    }

    @Test
    public void testToString()
    {
        assertEquals("S", sb.toString());
    }

    @Test
    public void testOkToPlaceShipAt()
    {
        assertEquals(true, sb.okToPlaceShipAt(0, 0, false, oc));
        assertEquals(true, sb.okToPlaceShipAt(0, 0, true, oc));

        assertEquals(false, sb.okToPlaceShipAt(-1, -1, true, oc));
        assertEquals(false, sb.okToPlaceShipAt(0, 10, true, oc));
    }

    @Test
    public void testShootAt()
    {
        assertFalse(oc.shootAt(0, 0));
        assertFalse(oc.shootAt(1, 0));

        sb.placeShipAt(0, 0, false, oc);

        assertTrue(oc.shootAt(0, 0));
        assertFalse(oc.shootAt(1, 0));
    }

    @Test
    public void testIsSunk()
    {
        assertFalse(sb.isSunk());

        sb.placeShipAt(0, 0, false, oc);

        oc.shootAt(0, 0);

        assertTrue(sb.isSunk());
    }
}