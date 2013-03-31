package battleships;

import org.junit.*;
import static org.junit.Assert.*;

public class OceanTest
{
	private int size = 10;
	private Ocean oc;
	private Ship[][] board = new Ship[size][size];

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
        oc = null;
        board = null;
    }

    @Test
    public void testConstruction()
    {
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                assertFalse(oc.isOccupied(i, j));
            }
        }
    }

    @Test
    public void placeAllShipsRandomly()
    {
        int count = 0;

        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(oc.isOccupied(i, j))
                {
                    count++;
                }
            }
        }

        assertEquals(0, count);

        oc.placeAllShipsRandomly();

        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(oc.isOccupied(i, j))
                {
                    count++;
                }
            }
        }

        assertEquals(20, count);
    }

    @Test
    public void testIsOccupied()
    {
        int count = 0;

        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(oc.isOccupied(i, j))
                {
                    count++;
                }
            }
        }

        assertEquals(0, count);

        oc.placeAllShipsRandomly();

        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(oc.isOccupied(i, j))
                {
                    count++;
                }
            }
        }

        assertEquals(20, count);
    }

    @Test
    public void shootAt()
    {
        assertFalse(oc.isGameOver());

        oc.placeAllShipsRandomly();

        // setup scenario for all hits
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(!oc.isGameOver())
                {
                    oc.shootAt(i, j);
                }
            }
        }

        // test for correct number of hits
        assertTrue(oc.isGameOver());
    }

    @Test
    public void getShotsFired()
    {
    	assertEquals(0, oc.getShotsFired());

        oc.shootAt(0, 0);

        assertEquals(1, oc.getShotsFired());
    }

    @Test
    public void getShipSunk()
    {
    	assertEquals(0, oc.getShipsSunk());

        oc.placeAllShipsRandomly();

        // setup scenario for 20 hits
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(!oc.isGameOver())
                {
                    oc.shootAt(i, j);
                }
            }
        }

        // test for correct number of hits
        assertEquals(10, oc.getShipsSunk());
    }

    @Test
    public void isGameOver()
    {
        assertFalse(oc.isGameOver());

        oc.placeAllShipsRandomly();

        // setup scenario for 20 hits
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(!oc.isGameOver())
                {
                    oc.shootAt(i, j);
                }
            }
        }

        // test for correct number of hits
        assertTrue(oc.isGameOver());
    }

    @Test
    public void getShipArray()
    {
        Ship[][] ship = oc.getShipArray();

        for(Ship[] sarr : ship)
        {
            for(Ship s : sarr)
            {
                assertTrue(s instanceof Ship);
            }
        }
    }

    @Test
    public void isSunk()
    {
        int isSunkCount = 0;

        oc.placeAllShipsRandomly();

        assertFalse(oc.isSunk(0, 0));

        // setup scenario for 20 hits
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(!oc.isGameOver())
                {
                    oc.shootAt(i, j);
                }
            }
        }

        // setup scenario for 20 hits
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(oc.isSunk(i, j))
                {
                    isSunkCount++;
                }
            }
        }

        assertEquals(20, isSunkCount);
    }

    @Test
    public void getShipType()
    {
        assertEquals("EmptySea", oc.getShipType(0, 0));
    }

    @Test
    public void printFinalScores()
    {
    	oc.placeAllShipsRandomly();

        // setup scenario for 20 hits
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(!oc.isGameOver())
                {
                    oc.shootAt(i, j);
                }
            }
        }

        StringBuilder strbld = new StringBuilder();
        strbld.append("GAME OVER!! You scored ").append(oc.getHitCount()).append(".");
        strbld.append(" You sank ").append(oc.getShipsSunk()).append(" ships");
        strbld.append(" and used ").append(oc.getShotsFired()).append(" shots" + ".");
        
        assertEquals(strbld.toString(), oc.printFinalScores());
    }

    @Test
    public void getDimension()
    {
    	assertEquals(this.size, oc.getDimension());
    }

    @Test
    public void testMissAt()
    {
        assertFalse(oc.shootAt(0, 0));
    }

    @Test
    public void testGetHitCount()
    {
        assertEquals(0, oc.getHitCount());

        oc.placeAllShipsRandomly();

        // setup scenario for 20 hits
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(!oc.isGameOver())
                {
                    oc.shootAt(i, j);
                }
            }
        }

        // test for correct number of hits
        assertEquals(20, oc.getHitCount());
    }
}
