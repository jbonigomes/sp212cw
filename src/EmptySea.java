/*
 * Class Empty Sea
 * describes part of the ocean that doesn't have a ship in it.
 * Length of 1
 *
 */
package batteships;

/**
 * @author KLM
 */
public class EmptySea extends Ship {

    private final static int SIZE = 1;

    /**
     * sets the length & clears the hit array
     */
    public EmptySea() {
        super(SIZE, "EmptySea", ".");
    }
}


