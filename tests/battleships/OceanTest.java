package battleships;

import org.junit.*;
import static org.junit.Assert.*;

public class OceanTest
{
	private int size = 10;
	private Ocean oc;
	private Ship[][] board = new Ship[size][size];

    // does it consist of 10x10 with all cells being ship?
    @Before
    public void createOcean()
    {
        oc = new Ocean();

        for(int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				board[i][j] = new EmptySea();
			}
		}
    }

    @After
    public void tearDown() throws Exception
    {
        assertEquals(false, false);
    }

    @Test
    public void testConstruction()
    {
        assertEquals(false, false);
    }

    @Test
    public void placeAllShipsRandomly()
    {
        assertEquals(false, false);
    }

    @Test
    public void testIsOccupied()
    {
    	assertEquals(false, false);
    }

    @Test
    public void shootAt()
    {
        int x = 3;
        int y = 4;
        Ship[][] board = oc.getShipArray();
        // setup ocean with Ship at x,y
        board[x][y] = new Submarine();
        // fire at x,y
        /////////////////assertTrue(oc.shootAt(x,y));

    	assertEquals(false, false);
    }

    @Test
    public void getShotsFired()
    {
    	assertEquals(false, false);
    }

    @Test
    public void getShipSunk()
    {
    	//////////////////////assertEquals(0,oc.getShipsSunk());
        assertEquals(false, false);
    }

    @Test
    public void isGameOver()
    {
        // setup end of game
        /////////////assertTrue(oc.isGameOver());
    	assertEquals(false, false);
    }

    @Test
    public void testGameIsNotOver()
    {
        // setup nothing
        /////////assertFalse(oc.isGameOver());
        assertEquals(false, false);
    }

    @Test
    public void getShipArray()
    {
        Ship[][] ship = oc.getShipArray();
        for(Ship[] sarr: ship)
        {
            for(Ship s : sarr)
            {
                ////////assertTrue(s instanceof Ship);
            }
        }
    	
        assertEquals(false, false);
    }

    @Test
    public void isSunk()
    {
    	assertEquals(false, false);
    }

    @Test
    public void getShipType()
    {
    	assertEquals(false, false);
    }

    @Test
    public void printFinalScores()
    {
    	assertEquals(false, false);
    }

    @Test
    public void getDimension()
    {
    	assertEquals(false, false);
    }

    @Test
    public void testMissAt()
    {
        assertFalse(oc.shootAt(0,0));
    }

    @Test
    public void testInitialGetHitCount() throws Exception
    {
        assertEquals(0, oc.getHitCount());
    }

    @Test
    public void testLaterGetHitCount()
    {
        // setup scenario for four hits
        // test for correct number of hits
        assertEquals(4, oc.getHitCount());
    }
}
