import java.util.ArrayList;

/**
*   Parent Player of the players created. Will contain the methods each Player requires to complete game logic functionality
*
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
**/
public abstract class Player {

	/**
    *   Checks whether the ship has been previously hit by the player
    *   @param playerBoard - current Board object
    *           int row, column - Int form row and column attacked by player
    *   @return boolean - Will specify whether the attack is new, or has been previously attacked
    **/
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
    *   Quickly convert two integers into a string for data storage
    *   @param int row, column - Row and column that will be formatted
    *   @return formattedString - String which will contain values in column,row format
    **/
	public String coordToString(int row, int column) {
        String formattedString = Integer.toString(row) + "," + Integer.toString(column);
        return formattedString;
	}

    // Abstract class that will enable us to differentiate human vs AI turns

	public abstract String playerTurn();

	
	public abstract Board getPlayerBoard();

}
