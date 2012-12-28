/*
 * A Destroyer class which extends ship
 * One Destroyer in the game
 * Length 2
 * 
 */
package battleships;

/**
 * @author Jose B. Gomes
 */
public class Destroyer extends Ship {

    private final static int SIZE = 2;

    /**
     * sets the length & clears the hit array
     */
    public Destroyer() {
        super(SIZE, "Destroyer", "D");
    }
}
