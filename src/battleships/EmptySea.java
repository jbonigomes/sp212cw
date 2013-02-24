/*
 * Class Empty Sea
 * describes part of the ocean that doesn't have a ship in it.
 * Length of 1
 *
 */
package battleships;

/**
 * @author KLM
 * @author Jose B. Gomes
 */
public class EmptySea extends Ship
{
	private final static int SIZE = 1;
	private boolean miss;

	/**
	 * sets the length & clears the hit array
	 */
	public EmptySea()
	{
		super(SIZE, "EmptySea", ".");
		this.miss = false;
	}

	/**
     * @return true if this empty sea space has been hit during the game, false otherwise 
     */
	public boolean getMissHit()
	{
		return this.miss;
	}

	/**
     * Sets the miss variable to true
     * Should be called when a miss occours during the game
     */
	public void setMissHit()
	{
		this.miss = true;
	}
}
