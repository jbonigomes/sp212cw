
package battleships;

import org.junit.*;
import static org.junit.Assert.*;

public class BattleShipTest
{
    private BattleShip bs;
    private Ocean oc;

    @Before
    public void setUp()
    {
        bs = new BattleShip();
        oc = new Ocean();
    }

    @After
    public void tearDown()
    {
        bs = null;
        oc = null;
    }

    @Test
    public void testConstructor()
    {
        assertEquals(4, bs.getSize());
    }

    @Test
    public void testToString()
    {
        assertEquals("B", bs.toString());
    }

    @Test
    public void testOkToPlaceShipAt()
    {
        assertEquals(true, bs.okToPlaceShipAt(0, 0, false, oc));
        assertEquals(true, bs.okToPlaceShipAt(0, 0, true, oc));

        assertEquals(false, bs.okToPlaceShipAt(-1, -1, true, oc));
        assertEquals(false, bs.okToPlaceShipAt(0, 8, true, oc));
    }

    @Test
    public void testShootAt()
    {
        assertFalse(oc.shootAt(0, 0));
        assertFalse(oc.shootAt(1, 0));

        bs.placeShipAt(0, 0, false, oc);

        assertTrue(oc.shootAt(0, 0));
        assertTrue(oc.shootAt(1, 0));
    }

    @Test
    public void testIsSunk()
    {
        assertFalse(bs.isSunk());

        bs.placeShipAt(0, 0, false, oc);

        oc.shootAt(0, 0);
        oc.shootAt(1, 0);
        oc.shootAt(2, 0);
        oc.shootAt(3, 0);

        assertTrue(bs.isSunk());
    }
}