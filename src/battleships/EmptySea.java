/*
 * Class Empty Sea
 * describes part of the ocean that doesn't have a ship in it.
 * Length of 1
 *
 */
package battleships;

/**
 * @author KLM
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

	public boolean getMissHit()
	{
		return this.miss;
	}

	public void setMissHit()
	{
		this.miss = true;
	}
}
