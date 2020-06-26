package players;

import java.util.ArrayList;

import board.Board;
import board.BoardValue;

/**
 * Parent Player of the players created. Will contain the methods each Player
 * requires to complete game logic functionality
 *
 * @author Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayag
 */
public abstract class Player {
    String name;


    /**
     * Constructor allows user to set the name of the player
     *
     * @param playerName the name of the Player
     */
    public Player(String playerName) {
        this.name = playerName;
    }

    /**
     * Checks whether the ship has been previously hit by the player
     *
     * @param playerBoard current Board object int row, column - Int form row and column
     *                    attacked by player'
     * @param row - int row being checked
     * @param column - int column being checked
     * @return boolean Will specify whether the attack is new, or has been
     * previously attacked
     */
    public boolean checkPreviousHitEnum(Board playerBoard, int row, int column) {
        BoardValue value = (playerBoard.guessBoard[row - 1][column - 1]);
        if (value == BoardValue.HIT) {
            return true;
        } else if (value == BoardValue.MISS) {
            return true;
        }
        return false;
    }

    /**
     * getter funciton for the name of the player
     *
     * @return namm in a string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Quickly convert two integers into a string for data storage
     *
     * @param row    int that will be formatted into a string
     * @param column int that will be formatted into a string
     * @return formattedString - String which will contain values in column,row
     */
    public String coordToString(int row, int column) {
        String formattedString = Integer.toString(row) + "," + Integer.toString(column);
        return formattedString;
    }


    /**
     * Abstract method where the player returns a string indicating the
     * position they have chosen to attack
     * @return playerTurn - runs current players turn
     */
    public abstract String playerTurn();

    /**
     * Abstract method, getter for player board,for JUNIT testing purpose
     * @return playerBoard - returns current playerBoard
     */
    public abstract Board getPlayerBoard();

    /**
     * Abstract method where the players return a string
     * indicating the orientation and position they have chosen to attack
     * @return playerSetup - returns current players setup board
     */
    public abstract String playerSetup();

}
