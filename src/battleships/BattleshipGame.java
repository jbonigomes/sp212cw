/*
 * A Battleship Game class
 * sets up the game; accepts "shots" from the user; displays the results;
 * prints final scores; and asks the user if he/she wants to play again.
 */

/* uses Java 7 features */

package battleships;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * This class contains the main method, run it to play the battleship game
 *
 * @author MARGARET WRIGHT
 * @author KLM
 * @author Jose B. Gomes
 */
public class BattleshipGame
{
    public static void main(String[] args)
    {
        Set<String> replies = new HashSet<String>();
        replies.add("yes");
        replies.add("y");

        int limit;
        String reply;
        Scanner input = new Scanner(System.in);

        do
        {
            // set up the game
            Ocean oc = new Ocean();
            oc.placeAllShipsRandomly();
            System.out.println();
            System.out.println(oc);
            limit = oc.getDimension();

            do
            {
                // read in the shot
                Position p = getValidInput(input, limit);

                System.out.println();
                System.out.println();

                // accept shots & check whether it's a hit (in Ocean)
                if(oc.shootAt(p.getX(), p.getY()))
                {
                    if(oc.isSunk(p.getX(), p.getY()))
                    {
                        System.out.println("Well done, you sank a ship of type " + oc.getShipType(p.getX(), p.getY()) + "!");
                    }
                    else
                    {
                        System.out.println("You got a hit, keep it up!");
                    }
                }
                else
                {
                    System.out.println("A miss, try again!");
                }

                System.out.println();
                System.out.println(oc);
                System.out.println("[. is empty sea; X is a miss; H is a hit; $ is a sunken ship.]");
                System.out.println();

            } while(!oc.isGameOver());
            
            // print out final scores
            System.out.println(oc.printFinalScores());

            System.out.print("Do you want to play again (Yes or No)? ");
            reply = input.next();
            reply = reply.toLowerCase();

        } while(replies.contains(reply)); // play again?
    }


    /*
     * Gets valid input from user for row (x) & column (y)
     *
     * @param user input from keyboard
     *
     * @return the valid shot location
     */
    private static Position getValidInput(Scanner input, int limit)
    {
        // x is the row; y is the column
        int x, y;
        System.out.println();
        System.out.println("Where do you want to fire (x,y)? ");
        x = askForInput(input, "x/row = ", limit);
        y = askForInput(input, "y/col = ", limit);
        return new Position(x, y);
    }

    /*
     * Checks for valid user input for x or y
     *
     * @param input from user
     *
     * @return the valid co-ordinate
     */
    private static int askForInput(Scanner input, String rowCol, int limit)
    {
        int coordinate;
        do
        {
            try
            {
                do
                {
                    System.out.print(rowCol);
                    coordinate = input.nextInt();

                } while (coordinate < 0 || coordinate > limit - 1);

                return coordinate;
            }
            catch (Exception ex) // checks for not an integer
            {
                System.err.println("Invalid answer - please enter a number between 0-" + limit + ".");
                input.nextLine();
            }

        } while(true);
    }
}
