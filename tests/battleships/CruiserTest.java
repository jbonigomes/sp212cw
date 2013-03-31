
package battleships;

import org.junit.*;
import static org.junit.Assert.*;

public class CruiserTest
{
    private Cruiser cs;
    private Ocean oc;

    @Before
    public void setUp()
    {
        cs = new Cruiser();
        oc = new Ocean();
    }

    @After
    public void tearDown()
    {
        cs = null;
        oc = null;
    }

    @Test
    public void testConstructor()
    {
        assertEquals(3, cs.getSize());
    }

    @Test
    public void testToString()
    {
        assertEquals("C", cs.toString());
    }

    @Test
    public void testOkToPlaceShipAt()
    {
        assertEquals(true, cs.okToPlaceShipAt(0, 0, false, oc));
        assertEquals(true, cs.okToPlaceShipAt(0, 0, true, oc));

        assertEquals(false, cs.okToPlaceShipAt(-1, -1, true, oc));
        assertEquals(false, cs.okToPlaceShipAt(0, 8, true, oc));
    }

    @Test
    public void testShootAt()
    {
        assertFalse(oc.shootAt(0, 0));
        assertFalse(oc.shootAt(1, 0));

        cs.placeShipAt(0, 0, false, oc);

        assertTrue(oc.shootAt(0, 0));
        assertTrue(oc.shootAt(1, 0));
    }

    @Test
    public void testIsSunk()
    {
        assertFalse(cs.isSunk());

        cs.placeShipAt(0, 0, false, oc);

        oc.shootAt(0, 0);
        oc.shootAt(1, 0);
        oc.shootAt(2, 0);

        assertTrue(cs.isSunk());
    }
}