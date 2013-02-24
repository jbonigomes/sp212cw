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
    // Ship size, how many spaces it takes in the ocean
    @Getter
    private int size;

    // Ship type
    @Getter
    private String type;

    // Ship shortForm
    private String shortForm;

    // The row in the ocean where this ship starts
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int bowRow;
    
    // The column in the ocean where this ship starts
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int bowColumn;
    
    // True if this ship is horizontal, false otherwise
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private boolean horizontal;
    
    // True if this ship has not yet been sunk, false otherwise
    @Setter(AccessLevel.PACKAGE)
    private boolean notYetSunk;

    // An array of boolean which indicates whether that part of the ship has been hit. This is initialised by the appropriate sub-class.
    protected boolean[] hit;

    /**
     * clears the hit array indicating whether that part of the "Ship" has been hit
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
     * Set the shortForm to be returned by the toString method
     *
     * @param shortForm supplied in the if statement on ocean's print method
     */
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

    /**
     * This method is here because it would not compile with the lombok @Getter
     * it's @Setter is done via a lombok annotation
     *
     * @return true if the ship has already been sunk in a previous shot, false otherwise
     */
    public boolean getNotYetSunk()
    {
       return this.notYetSunk;
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
            // we need a way to set the miss hit on EmptySet
            ((EmptySea)this).setMissHit();
            return false;
        }

        // it's a hit. Work out offset & set that position in hit array to true
        hit[(row - getBowRow() + column - getBowColumn())] = true;

        return true;
    }

    /**
     * Used by the Ocean's print method, to know
     * whether EmptySea has already been hit during the game
     *
     * @return true if type is EmptySea and has not had an attempted shot before, false otherwise
     */
    public boolean isHitEmptySea()
    {
        if(this.type == "EmptySea")
        {
            return ((EmptySea)this).getMissHit();
        }

        return false;
    }

    /**
     * Used by the Ocean's print method, to know
     * whether a non-EmptySea block has been hit during the game
     * The parameters are suplied by the if statement in the ocean
     *
     * @param row the row position of this shot in the ocean
     * @param column the column position of this shot in the ocean
     * @return true if non-EmptySea that has already been hit during the game, false otherwise
     */
    public boolean isAreaHit(int row, int column)
    {
        // we don't care about empty sea
        if(this.type == "EmptySea")
        {
            return false;
        }

        return hit[(row - getBowRow() + column - getBowColumn())];
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
        if(withinRange(row, column, horizontal, oceanDimension))
        {
            if(isTopLeft(row, column)) return checkTopLeft(row, column, horizontal, ocean);

            if(isTopRight(row, column, horizontal, oceanDimension)) return checkTopRight(row, column, horizontal, ocean);

            if(isTop(row)) return checkTop(row, column, horizontal, ocean);

            if(isBottomLeft(row, column, horizontal, oceanDimension)) return checkBottomLeft(row, column, horizontal, ocean);

            if(isBottomRight(row, column, horizontal, oceanDimension)) return checkBottomRight(row, column, horizontal, ocean);

            if(isBottom(row, horizontal, oceanDimension)) return checkBottom(row, column, horizontal, ocean);

            if(isLeft(column)) return checkLeft(row, column, horizontal, ocean);

            if(isRight(column, horizontal, oceanDimension)) return checkRight(row, column, horizontal, ocean);

            return fullCheck(row, column, horizontal, ocean);
        }

        return false;
    }
    
    /* From this point onwards, all methods are helper methods of the okToPlaceShipAt method */

    private boolean withinRange(int row, int column, boolean horizontal, int oceanDimension)
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

    private boolean isTopLeft(int row, int column)
    {
        return (row == 0) && (column == 0);
    }

    private boolean isTopRight(int row, int column, boolean horizontal, int oceanDimension)
    {
        if(horizontal)
        {
            return (row == 0) && ((column + (getSize() - 1)) == (oceanDimension - 1));
        }

        return (row == 0) && (column == (oceanDimension - 1));
    }

    private boolean isTop(int row)
    {
        return row == 0;
    }

    private boolean isBottomLeft(int row, int column, boolean horizontal, int oceanDimension)
    {
        if(horizontal)
        {
            return (column == 0) && (row == (oceanDimension - 1));
        }

        return (column == 0) && ((row + (getSize() - 1)) == (oceanDimension - 1));
    }

    private boolean isBottomRight(int row, int column, boolean horizontal, int oceanDimension)
    {
        if(horizontal)
        {
            return ((column + (getSize() - 1)) == (oceanDimension - 1)) && (row == (oceanDimension - 1));
        }

        return (column == (oceanDimension - 1)) && ((row + (getSize() - 1)) == (oceanDimension - 1));
    }

    private boolean isBottom(int row, boolean horizontal, int oceanDimension)
    {
        if(horizontal)
        {
            return row == (oceanDimension - 1);
        }

        return (row + (getSize() - 1)) == (oceanDimension - 1);
    }

    private boolean isLeft(int column)
    {
        return column == 0;
    }

    private boolean isRight(int column, boolean horizontal, int oceanDimension)
    {
        if(horizontal)
        {
            return (column + (getSize() - 1)) == (oceanDimension - 1);
        }

        return column == (oceanDimension - 1);
    }

    private boolean fullLoop(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            column--;
        }
        else
        {
            row--;
        }

        for(int i = 0; i <= (getSize() + 1); i++)
        {
            // check if current cell is emptysea
            if(ocean.isOccupied(row, column))
            {
                return false;
            }

            if(horizontal)
            {
                column++;
            }
            else
            {
                row++;
            }
        }

        return true;
    }

    private boolean beforeOffsetLoop(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            column--;
        }
        else
        {
            row--;
        }

        for(int i = 0; i <= getSize(); i++)
        {
            // check if current cell is emptysea
            if(ocean.isOccupied(row, column))
            {
                return false;
            }

            if(horizontal)
            {
                column++;
            }
            else
            {
                row++;
            }
        }

        return true;
    }

    private boolean afterOffsetLoop(int row, int column, boolean horizontal, Ocean ocean)
    {
        for(int i = 0; i <= getSize(); i++)
        {
            // check if current cell is emptysea
            if(ocean.isOccupied(row, column))
            {
                return false;
            }

            if(horizontal)
            {
                column++;
            }
            else
            {
                row++;
            }
        }

        return true;
    }

    private boolean fullCheck(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return fullLoop(row, column, horizontal, ocean) && fullLoop(row + 1, column, horizontal, ocean) && fullLoop(row - 1, column, horizontal, ocean);
        }

        return fullLoop(row, column, horizontal, ocean) && fullLoop(row, column + 1, horizontal, ocean) && fullLoop(row, column - 1, horizontal, ocean);
    }

    private boolean checkTopLeft(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return afterOffsetLoop(row, column, horizontal, ocean) && afterOffsetLoop(row + 1, column, horizontal, ocean);
        }

        return afterOffsetLoop(row, column, horizontal, ocean) && afterOffsetLoop(row, column + 1, horizontal, ocean);
    }

    private boolean checkTopRight(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return beforeOffsetLoop(row, column, horizontal, ocean) && beforeOffsetLoop(row + 1, column, horizontal, ocean);
        }

        return afterOffsetLoop(row, column, horizontal, ocean) && afterOffsetLoop(row, column - 1, horizontal, ocean);
    }

    private boolean checkTop(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return fullLoop(row, column, horizontal, ocean) && fullLoop(row + 1, column, horizontal, ocean);
        }

        return afterOffsetLoop(row, column, horizontal, ocean) && afterOffsetLoop(row, column + 1, horizontal, ocean) && afterOffsetLoop(row, column - 1, horizontal, ocean);
    }

    private boolean checkBottomLeft(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return afterOffsetLoop(row, column, horizontal, ocean) && afterOffsetLoop(row - 1, column, horizontal, ocean);
        }

        return beforeOffsetLoop(row, column, horizontal, ocean) && beforeOffsetLoop(row, column + 1, horizontal, ocean);
    }

    private boolean checkBottomRight(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return beforeOffsetLoop(row, column, horizontal, ocean) && beforeOffsetLoop(row - 1, column, horizontal, ocean);
        }

        return beforeOffsetLoop(row, column, horizontal, ocean) && beforeOffsetLoop(row, column - 1, horizontal, ocean);
    }

    private boolean checkBottom(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return fullLoop(row, column, horizontal, ocean) && fullLoop(row - 1, column, horizontal, ocean);
        }

        return beforeOffsetLoop(row, column, horizontal, ocean) && beforeOffsetLoop(row, column + 1, horizontal, ocean) && beforeOffsetLoop(row, column - 1, horizontal, ocean);
    }

    private boolean checkLeft(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return afterOffsetLoop(row, column, horizontal, ocean) && afterOffsetLoop(row + 1, column, horizontal, ocean) && afterOffsetLoop(row - 1, column, horizontal, ocean);
        }

        return fullLoop(row, column, horizontal, ocean) && fullLoop(row, column + 1, horizontal, ocean);
    }

    private boolean checkRight(int row, int column, boolean horizontal, Ocean ocean)
    {
        if(horizontal)
        {
            return beforeOffsetLoop(row, column, horizontal, ocean) && beforeOffsetLoop(row + 1, column, horizontal, ocean) && beforeOffsetLoop(row - 1, column, horizontal, ocean);
        }

        return fullLoop(row, column, horizontal, ocean) && fullLoop(row, column - 1, horizontal, ocean);
    }
}
