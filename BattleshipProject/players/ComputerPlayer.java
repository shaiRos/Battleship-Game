package players;

import java.util.Scanner;

import board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Houses all of the core logic dedicated to the AI
 *
 * @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayag
 */
public class ComputerPlayer extends Player {

    Board playerBoard = null;
    private static ArrayList<String> guessed = new ArrayList<String>();
    private static ArrayList<String> queue = new ArrayList<String>();

    /**
     * constructor
     *
     * @param board links the provided board to the current ComputerPlayer object
     */
    public ComputerPlayer(Board board) {
        super("Computer"); //default name
        this.playerBoard = board;
    }

    /**
     * constructor
     *
     * @param board      links the provided board to the current ComputerPlayer object
     * @param playerName a name to the player
     */
    public ComputerPlayer(Board board, String playerName) {
        super(playerName);
        this.playerBoard = board;
    }

    /**
     * getter returns board
     */
    public Board getPlayerBoard() {
        return playerBoard;
    }

    /**
     * Clear the AI's guessing Queue once a ship has been sunken
     * so AI randomly attacks a position
     */
    public void clearQueue() {
        queue.clear();
    }

    /**
     * Generates random coordinates that are within the board's bounds
     *
     * @return coordinate - Int between 1 - board size
     */
    public int randomCoordinate() {
        Random rand = new Random();
        int coordinate = rand.nextInt(Board.getBoardSize()) + 1;
        return coordinate;
    }

    /**
     * Generate a random orientation for setting up the ships on the board
     *
     * @return char 'h' or 'v' to represent horizaontal or vertical
     */
    private char randomOrientation() {
        Random rand = new Random();
        int orientationNum = rand.nextInt(2);
        if (orientationNum == 0) {
            return 'h';
        } else {
            return 'v';
        }
    }

    /**
     * AI generates information necessary for setting up a ship
     *
     * @return a string consisting of randomly generated orientation, row, and column
     */
    public String playerSetup() {
        char orientation = randomOrientation();
        int col = randomCoordinate();
        int row = randomCoordinate();
        String setupMessage = orientation + " " + row + " " + col;
        return setupMessage;
    }

    /**
     * Compares the current attack to the AI's previously guessed values, stored
     * within an ArrayList
     *
     * @param coordinate - String formatted column,row value
     * @return contains - Boolean specifies whether the attack is valid
     */
    public boolean checkNotAttacked(String coordinate) {
        boolean contains = true;
        if (getGuessed().isEmpty() == true) {
            return true;
        } else {
            for (String coords : getGuessed()) {
                if (coords.equals(coordinate)) {
                    contains = false;
                }
            }
        }

        return contains;
    }


    /**
     * If the AI makes a hit, adds the hit to the current queue that will be
     * processed during AI turn
     *
     * @param column - Int values of the column that has been successfully attacked
     * @param row - Int values of the row that has been successfully attacked
     */
    public void makeQueue(int row, int column) {
        int boardSize = Board.getBoardSize();

        if ((checkNotAttacked(coordToString(row, column + 1)) == true) && (column + 1 <= Board.getBoardSize())) {
            queue.add(coordToString(row, column + 1));
        }

        if ((checkNotAttacked(coordToString(row + 1, column)) == true) && (row + 1 <= Board.getBoardSize())) {
            queue.add(coordToString(row + 1, column));
        }

        if ((checkNotAttacked(coordToString(row, column - 1)) == true) && (column - 1 > 0)) {
            queue.add(coordToString(row, column - 1));

        }
        if ((checkNotAttacked(coordToString(row - 1, column)) == true) && (row - 1 > 0)) {
            queue.add(coordToString(row - 1, column));

        }

    }

    /**
     * The procedure the AI will follow to complete their round. Contains all of the
     * logic required to make a guess and validate the guess values
     */
    public String playerTurn() {
        boolean formatted = false;
        int column = -1;
        int row = -1;
        while (formatted != true) {
            try {

                // instantiate initial values first just in case
                row = randomCoordinate();
                column = randomCoordinate();

                // if the queue is empty, then we'll just use the random values
                if (queue.isEmpty()) {
                    while ((checkNotAttacked(coordToString(row, column)) != true)) {
                        row = randomCoordinate();
                        column = randomCoordinate();

                    }
                    // if there are values in the queue, use those instead of the randomly generated
                    // values
                } else {
                    String[] values = queue.get(0).split(",");
                    row = Integer.parseInt(values[0]);
                    column = Integer.parseInt(values[1]);
                }

                // check to make sure its a legit value
                if ((row > Board.getBoardSize()) || (column > Board.getBoardSize()) || (row < 0) || (column < 0)) {
                    System.out.println("Invalid coordinates");
                    System.exit(0);
                } else {
                    formatted = true;
                    // Specify where the attack has went
                    System.out.println("AI sent attack to (" + (char) ((row + 65) - 1) + "," + column + ")");
                    getGuessed().add(coordToString(row, column));
                    // Send the attack. Check if the attack hits or misses

                    // we assume the attack is successfully sent, remove the item from the queue
                    if (!queue.isEmpty()) {
                        queue.remove(0);
                    }

                }

            }
            // error checking
            catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong format");
                System.out.println(e.getMessage());
                formatted = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                formatted = false;

            }
        }

        return (coordToString(row, column));
    }

    public static ArrayList<String> getGuessed() {
        return guessed;
    }

    public void setGuessed(ArrayList<String> guessed) {
        this.guessed = guessed;
    }

    public static ArrayList<String> getQueue() {
        return queue;
    }

    public void setQueue(ArrayList<String> queue) {
        this.queue = queue;
    }

}