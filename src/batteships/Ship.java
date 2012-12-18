/*
 * Class Ship describes characteristics common to all the ships.
 *
 */
package batteships;

/**
 * @author MARGARET WRIGHT
 * @author KLM
 */

public abstract class Ship {
    // TODO add appropriate comments
    private int size;
    private String type;
    private String shortForm;

    // TODO add appropriate comments
    private int bowRow;
    private int bowColumn;
    private boolean horizontal;

    /**
     * An array of boolean which indicates whether that part of the ship has been hit.
     * This is initialised by the appropriate sub-class.
     * Battleships use all 4 locations; cruisers use the first 3; destroyers 2;
     * submarines 1; and "empty sea" 1.
     */
    protected boolean[] hit;

    /**
     * clears the hit array indicating whether that part of the "Ship" has been
     * hit
     */
    protected Ship(int size, String type, String shortForm) {
        this.size = size;
        this.type = type;
        this.shortForm = shortForm;
        hit = new boolean[size];
        for (int i = 0; i < hit.length; i++)
            hit[i] = false;
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
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal,
                                   Ocean ocean) {
        // TODO add appropriate code and comments

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
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);

        Ship ships[][] = ocean.getShipArray();

        for (int i = 0; i < getSize(); i++) {
            // set position in array to contain the ship
            ships[row][column] = this;
            if (horizontal) {
                column++;
            } else {
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
    public boolean shootAt(int row, int column) {
        if ((isHorizontal() && (row != getBowRow()))
                || (!isHorizontal() && (column != getBowColumn())))
            return false; // it's not a hit

        // it's a hit. Work out offset & set that position in hit array to true
        hit[(row - getBowRow() + column - getBowColumn())] = true;

        return true;
    }

    /**
     * checks whether this ship is sunk - using the hit array
     *
     * @return true if every part of the ship has been hit, false otherwise.
     */
    public boolean isSunk() {

        for (boolean b : hit)
            if (!b)
                return false;

        return true;
    }

    /**
     * @return a single character String to use in Ocean's print method
     */
    @Override
    public final String toString() {
        return shortForm;
    }

    /**
     * @return the size of the Ship
     */
    protected final int getSize() {
        return size;
    }


    /**
     * the row containing the bow (front) of the ship.
     */
    public int getBowRow() {
        return bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }


    /**
     * the column containing the bow (front) of the ship.
     */
    int getBowColumn() {
        return bowColumn;
    }

    void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    /**
     * true if the ship occupies a single row, false otherwise.
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
}
