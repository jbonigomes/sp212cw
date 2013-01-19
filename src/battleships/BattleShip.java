/*
 * A Battleship class which extends ship
 * One Battleship in the game
 * Length 4
 * 
 */

package battleships;

/**
 * @author KLM
 */
public class BattleShip extends Ship
{
	private final static int SIZE = 4;

	/**
	 * sets the length & clears the hit array
	 */
	public BattleShip()
	{
		super(SIZE, "Battleship", "B");
	}
}
