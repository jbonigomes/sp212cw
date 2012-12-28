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
 * @author MARGARET WRIGHT
 * @author KLM
 */
public class BattleshipGame {

    public static void main(String[] args) {

        Set<String> replies = new HashSet<String>();   // TODO - improve this
        replies.add("Yes");
        replies.add("yes");
        replies.add("y");
        replies.add("Y");

        int limit;
        String reply;
        Scanner input = new Scanner(System.in);

        do {
            // set up the game
            // uncomment me - Ocean oc = new Ocean();
            // uncomment me - oc.placeAllShipsRandomly();
            System.out.println();
            System.out.println(/* uncomment me oc*/ '+');
            limit = 5; // uncomment me - oc.getDimension();

            int counter = 5; // delete this line

            do {
                // read in the shot
                Position p = getValidInput(input, limit);
                // accept shots & check whether it's a hit (in Ocean)
                if (/* uncomment me - !oc.shootAt(p.getX(), p.getY())*/ true) // a miss
                    System.out.println("A miss, try again.");

                // uncomment me - System.out.println(oc);
                System.out.println("[. is empty sea; X is a miss; H is a hit; $ is a sunken ship.]");
                System.out.println();
                counter++;
            } while (/* uncomment me - !oc.isGameOver()*/ counter > 0);
            // print out final scores
            System.out.println(/* uncomment me - oc.printFinalScores()*/ "the scores will be printed here");

            System.out.print("Do you want to play again (Yes or No)? ");
            reply = input.next();
        } while (replies.contains(reply)); // play again?
    }


    /*
     * Gets valid input from user for row (x) & column (y)
     *
     * @param user input from keyboard
     *
     * @return the valid shot location
     */
    private static Position getValidInput(Scanner input, int limit) {
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
    private static int askForInput(Scanner input, String rowCol, int limit) {
        int coordinate;
        do {
            try {
                do {
                    System.out.print(rowCol);
                    coordinate = input.nextInt();
                } while (coordinate < 0 || coordinate > limit - 1);
                return coordinate;
            } // checks for not an integer
            catch (Exception ex) {
                System.err.println("Invalid answer - please enter a number between 0-" + limit + ".");
                input.nextLine();
            } // end of catch
        } while (true);
    }
}
