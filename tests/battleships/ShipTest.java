package battleships;

import org.junit.*;
import static org.junit.Assert.*;

public class ShipTest
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
    public void okToPlaceShipAt()
    {
    	assertEquals(true, bs.okToPlaceShipAt(0, 0, true, oc));

        assertEquals(false, bs.okToPlaceShipAt(-1, -1, true, oc));
        assertEquals(false, bs.okToPlaceShipAt(0, 8, true, oc));
    }

    @Test
    public void placeShipAt()
    {
        assertFalse(oc.isOccupied(0, 0));

        bs.placeShipAt(0, 0, true, oc);

        assertTrue(oc.isOccupied(0, 0));
    }

    @Test
    public void shootAt()
    {
    	assertFalse(oc.shootAt(0, 0));
        assertFalse(oc.shootAt(1, 0));

        bs.placeShipAt(0, 0, false, oc);

        assertTrue(oc.shootAt(0, 0));
        assertTrue(oc.shootAt(1, 0));
    }

    @Test
    public void isSunk()
    {
    	assertFalse(bs.isSunk());

        bs.placeShipAt(0, 0, false, oc);

        oc.shootAt(0, 0);
        oc.shootAt(1, 0);
        oc.shootAt(2, 0);
        oc.shootAt(3, 0);

        assertTrue(bs.isSunk());
    }

    @Test
    public void getSize()
    {
    	assertEquals(4, bs.getSize());
    }

    @Test
    public void getType()
    {
    	assertEquals("Battleship", bs.getType());
    }

    @Test
    public void getBowRow()
    {
    	bs.placeShipAt(0, 0, false, oc);
        assertEquals(0, bs.getBowRow());
    }

    @Test
    public void getBowColumn()
    {
    	bs.placeShipAt(0, 0, false, oc);
        assertEquals(0, bs.getBowColumn());
    }
}
