package players;

import java.util.Scanner;

import board.Board;

/**
 * Holds the code the main player will utilize. Contains the logic necessary to
 * execute a game 'turn'
 *
 * @author Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayag
 */
public class HumanPlayer extends Player {
    Board playerBoard = null;

    /**
     * Main constructor for HumanPlayer object
     *
     * @param board Board object that will be linked to the current player
     */
    public HumanPlayer(Board board) {
        super("human"); //default name
        this.playerBoard = board;
    }

    /**
     * constructor
     *
     * @param board      links the provided board to the current ComputerPlayer object
     * @param playerName a name to the player
     */
    public HumanPlayer(Board board, String playerName) {
        super(playerName);
        this.playerBoard = board;
    }

    /**
     * getter for playerBoard object
     * for JUNIT testing
     *
     * @return playerBoard Board object linked to player
     */
    public Board getPlayerBoard() {
        return playerBoard;
    }

    /**
     * prompt user for orientation and coordinates required to setup a ship
     *
     * @return setup a string containing the orientation, row, and column
     */
    public String playerSetup() {
        boolean formatted = false;
        String setup = " ";
        while (formatted != true) {
            try {
                System.out.print("Indicate (orientation row column): ");
                Scanner Setup = new Scanner(System.in);
                setup = Setup.nextLine();
                String setupInfo[] = setup.split(" ");
                char tempRow = setupInfo[1].toUpperCase().charAt(0);
                int row = (((int) (tempRow) - 65) + 1);
                setup = setupInfo[0] + " " + row + " " + setupInfo[2];
                formatted = true;
            } catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                // possible errors when doing the conversions of the string input
                System.out.println("\nWrong format, example: h A 1");
                formatted = false;
            }
        }
        return setup;

    }

    /**
     * Main logic to execute a game 'turn'. Will include all prompts, input checks
     * and validations required for a user to complete his attack
     *
     * @return a string that represent the coordinates to attack
     */

    public String playerTurn() {
        int row = -1;
        int column = -1;

        boolean formatted = false;
        while (formatted != true) {
            try {
                System.out.println("Current game board");
                // print the game board
                playerBoard.returnBoard(1);

                System.out.println("\n");
                System.out.println("Current guessing board");
                // print the guessing board
                // playerBoard.returnBoard(2);
                playerBoard.returnBoard(2);

                System.out.println("Enter coordinates to attack (row column): ");

                // Take row and column from input
                Scanner input = new Scanner(System.in);
                String inputLine = input.nextLine();
                String[] inputInfo = inputLine.split(" ");
                char tempRow = inputInfo[0].toUpperCase().charAt(0);
                row = (((int) (tempRow) - 65) + 1);
                column = Integer.parseInt(inputInfo[1]);

                // check
                if ((row > Board.getBoardSize()) || (column > Board.getBoardSize()) || (row < 0) || (column < 0)) {
                    System.out.println("Invalid coordinates");
                } else if (checkPreviousHitEnum(playerBoard, row, column) == true) {
                    System.out.println("Previously guessed! Try again");
                } else {
                    formatted = true;
                    // Specify where the attack has went
                    System.out.println("Sending attack to (" + (char) ((row + 65) - 1) + "," + column + ")");

                }

            }
            // error checking
            catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong format");
                formatted = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                formatted = false;

            }
        }
        return (coordToString(row, column));
    }

}