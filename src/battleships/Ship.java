/*
 * Class Ship describes characteristics common to all the ships.
 *
 */
package battleships;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MARGARET WRIGHT
 * @author KLM
 */

public abstract class Ship
{
    // TODO add appropriate comments
    @Getter
    private int size;

    private String type;
    private String shortForm;

    // TODO add appropriate comments
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int bowRow;
    
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int bowColumn;
    
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private boolean horizontal;
    
    @Setter(AccessLevel.PACKAGE)
    private boolean notYetSunk;

    /**
     * An array of boolean which indicates whether that part of the ship has been hit.
     * This is initialised by the appropriate sub-class.
     */
    protected boolean[] hit;

    /**
     * clears the hit array indicating whether that part of the "Ship" has been
     * hit
     */
    protected Ship(int size, String type, String shortForm)
    {
        this.size = size;
        this.type = type;
        this.shortForm = shortForm;
        this.notYetSunk = true;

        this.hit = new boolean[size];
        
        for (int i = 0; i < this.hit.length; i++)
        {
            this.hit[i] = false;
        }
    }

    /**
     * This method is here because it would not compile the lombok @Getter
     */
    public boolean getNotYetSunk()
    {
        return this.notYetSunk;
    }

    
    public boolean withinRange(int row, int column, boolean horizontal, int oceanDimension)
    {
        if(horizontal)
        {
            if(row >= 0 && row < oceanDimension && column >= 0 && (column + (getSize() - 1)) < oceanDimension)
            {
                return true;
            }
        }
        else
        {
            if(column >= 0 && column < oceanDimension && row >= 0 && (row + (getSize() - 1)) < oceanDimension)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks that ship of this size will not overlap another ship, or touch
     * another ship (vertically, horizontally, or diagonally) and that ship will
     * not "stick out" beyond the array.
     *
     * @param row        that will contain the bow
     * @param column     that will contain the bow
     * @param horizontal
     * @param ocean
     * @return true if it is okay to put a ship of this size with its bow in
     *         this location, with the given orientation.
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean)
    {
        Ship ships[][] = ocean.getShipArray();

        int oceanDimension = ocean.getDimension();

        // check if numbers are within range
        if(!(withinRange(row, column, horizontal, oceanDimension)))
        {
            return false;
        }

        /*

        // check if numbers are within range
        if(withinRange(row, column, horizontal, oceanDimension))
        {
            if(isTopLeft()) return checkTopLeft();

            if(isTopRight()) return checkTopRight();

            if(isTop()) return checkTop();

            if(isBottomLeft()) return checkBottomLeft();

            if(isBottomRight()) return checkBottomRight();

            if(isBottom()) return checkBottom();

            if(isLeft()) return checkLeft();

            if(isRight()) return checkRight();

            return fullCheck();
        }

        return false

        */

        // check if it is horizontal
        if(horizontal)
        {
            // check if left side is empty sea, if not, return false
            if((column > 0) && (ocean.isOccupied(row, column - 1)))
            {
                return false;
            }

            // check if right side is empty sea
            if(((column + getSize()) < ocean.getDimension()) && (!(ships[row][column + getSize()] instanceof EmptySea)))
            {
                return false;
            }

            for(int i = 0; i < getSize(); i++)
            {
                // check if current cell is emptysea
                if(ocean.isOccupied(row, column))
                {
                    return false;
                }

                // check if top cell is empty sea
                if((row > 0) && (ocean.isOccupied(row - 1, column)))
                {
                    return false;
                }

                // check if bottom cell is empty sea
                if((row < (ocean.getDimension() - 1)) && (ocean.isOccupied(row + 1, column)))
                {
                    return false;
                }

                column++;
            }
        
        }
        else // must be vertical
        {
            // check if top side is empty sea
            if((row > 0) && (ocean.isOccupied(row - 1, column)))
            {
                return false;
            }

            // check if bottom side is empty sea
            if(((row + getSize()) < ocean.getDimension()) && (ocean.isOccupied(row + getSize(), column)))
            {
                return false;
            }

            for(int i = 0; i < getSize(); i++)
            {
                // check if current cell is empty sea
                if(ocean.isOccupied(row, column))
                {
                    return false;
                }

                // check if left cell is empty sea
                if((column > 0) && (ocean.isOccupied(row, column - 1)))
                {
                    return false;
                }
                
                // check if right cell is empty sea
                if((column < (ocean.getDimension() - 1)) && (ocean.isOccupied(row, column + 1)))
                {
                    return false;
                }

                row++;
            }
        }
        
        return true;
    }


    /**
     * "places" the ship in the ocean, assigning values to the bowRow, bowColumn, and
     * horizontal.
     * Places a reference to the ship in the ships array in the Ocean object.
     *
     * @param row        to contain the bow
     * @param column     to contain the bow
     * @param horizontal
     * @param ocean
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean)
    {
        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);

        Ship ships[][] = ocean.getShipArray();

        for(int i = 0; i < getSize(); i++)
        {
            // set position in array to contain the ship
            ships[row][column] = this;
            
            if(horizontal)
            {
                column++;
            }
            else
            {
                row++;
            }
        }
    }

    /**
     * If this ship has been hit, marks that part of the ship as "hit"
     *
     * @param row    User's supplied row shot
     * @param column User's supplied column shot
     * @return true if ship is hit, false otherwise
     */
    public boolean shootAt(int row, int column, Ocean ocean)
    {
        // check if it is a hit
        if(!ocean.isOccupied(row, column))
        {
            ((EmptySea)this).setMissHit();
            return false;
        }

        // it's a hit. Work out offset & set that position in hit array to true
        hit[(row - getBowRow() + column - getBowColumn())] = true;

        return true;
    }

    /**
     * checks whether this ship is sunk - using the hit array
     *
     * @return true if every part of the ship has been hit, false otherwise.
     */
    public boolean isSunk()
    {
        for(boolean b : hit)
        {
            if(!b)
            {
                return false;
            }
        }

        return true;
    }

    public boolean isHitEmptySea()
    {
        if(this.type == "EmptySea")
        {
            return ((EmptySea)this).getMissHit();
        }

        return false;
    }

    public boolean isAreaHit(int row, int column)
    {
        // we don't care about empty sea
        if(this.type == "EmptySea")
        {
            return false;
        }

        return hit[(row - getBowRow() + column - getBowColumn())];
    }

    public void setShortForm(String shortForm)
    {
        this.shortForm = shortForm;
    }

    /**
     * @return a single character String to use in Ocean's print method
     */
    @Override
    public String toString()
    {   
        return shortForm;
    }
}
