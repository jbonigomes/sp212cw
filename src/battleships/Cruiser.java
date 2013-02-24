/*
 * A Cruiser class which extends ship
 * One Cruiser in the game
 * Length 3
 * 
 */
package battleships;

/**
 * @author KLM
 * @author Jose B. Gomes
 */
public class Cruiser extends Ship
{
    private final static int SIZE = 3;

    /**
     * sets the length & clears the hit array
     */
    public Cruiser()
    {
        super(SIZE, "Cruiser", "C");
    }
}
