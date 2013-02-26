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

    @Test
    public void testIsOccupied()
    {
    	assertEquals(false, false);
    }

    @Test
    public void shootAt()
    {
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
    	assertEquals(false, false);
    }

    @Test
    public void isGameOver()
    {
    	assertEquals(false, false);
    }

    @Test
    public void getShipArray()
    {
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
}
