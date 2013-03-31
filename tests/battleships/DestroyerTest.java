
package battleships;

import org.junit.*;
import static org.junit.Assert.*;

public class DestroyerTest
{
    private Destroyer ds;
    private Ocean oc;

    @Before
    public void setUp()
    {
        ds = new Destroyer();
        oc = new Ocean();
    }

    @After
    public void tearDown()
    {
        ds = null;
        oc = null;
    }

    @Test
    public void testConstructor()
    {
        assertEquals(2, ds.getSize());
    }

    @Test
    public void testToString()
    {
        assertEquals("D", ds.toString());
    }

    @Test
    public void testOkToPlaceShipAt()
    {
        assertEquals(true, ds.okToPlaceShipAt(0, 0, false, oc));
        assertEquals(true, ds.okToPlaceShipAt(0, 0, true, oc));

        assertEquals(false, ds.okToPlaceShipAt(-1, -1, true, oc));
        assertEquals(false, ds.okToPlaceShipAt(0, 9, true, oc));
    }

    @Test
    public void testShootAt()
    {
        assertFalse(oc.shootAt(0, 0));
        assertFalse(oc.shootAt(1, 0));

        ds.placeShipAt(0, 0, false, oc);

        assertTrue(oc.shootAt(0, 0));
        assertTrue(oc.shootAt(1, 0));
    }

    @Test
    public void testIsSunk()
    {
        assertFalse(ds.isSunk());

        ds.placeShipAt(0, 0, false, oc);

        oc.shootAt(0, 0);
        oc.shootAt(1, 0);

        assertTrue(ds.isSunk());
    }
}