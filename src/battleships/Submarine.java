/*
 * A Submarine class which extends ship
 * One Submarine in the game
 * Length 1
 * 
 */

package battleships;

/**
 * @author KLM
 * @author Jose B. Gomes
 */
public class Submarine extends Ship
{
    private final static int SIZE = 1;

    /**
     * sets the length & clears the hit array
     */
    public Submarine()
    {
        super(SIZE, "Submarine", "S");
    }
}
