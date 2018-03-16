import java.util.ArrayList;

/**
*   Parent Player of the players created. Will contain the methods each Player requires to complete game logic functionality
*
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
**/
public abstract class Player {

    /**
    *   Validates if the attack is valid, and changes board based on the information
    *   @param playerBoard - current Board object used
    *           int row, column - Int form row and column specified by the player
    *   @return boolean - Will return whether the attack successfully hit a ship or missed.
    **/
		public boolean sendAttack(Board playerBoard, int row, int column) {
        // check the value of the block specified, if the values match, change the values with
        // a hit or a miss
        BoardValue value = (playerBoard.guessBoard[column - 1][row - 1]);
        if (value == BoardValue.SHIP) {
            playerBoard.guessBoard[column - 1][row - 1] = BoardValue.HIT;
            System.out.println("Hit!");
            return true;
        } else if (value == BoardValue.EMPTY) {
            playerBoard.guessBoard[column - 1][row - 1] = BoardValue.MISS;
            System.out.println("Miss!");
        } else if (value == BoardValue.MISS) {
            playerBoard.guessBoard[column - 1][row - 1] = BoardValue.MISS;
            System.out.println("Miss!");
        // Should probably have a different check case for else
        } else {
            System.out.println("I broke something whoops??");
			System.out.println(playerBoard.guessBoard[column - 1][row - 1]);
            System.out.println("Debuggies");
            System.out.println(value);
        }
        
        return false;

	}

	/**
    *   Checks whether the ship has been previously hit by the player
    *   @param playerBoard - current Board object
    *           int row, column - Int form row and column attacked by player
    *   @return boolean - Will specify whether the attack is new, or has been previously attacked
    **/
	public boolean checkPreviousHitEnum(Board playerBoard, int row, int column) {
        BoardValue value = (playerBoard.guessBoard[column - 1][row - 1]);
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
	public String coordToString(int column, int row) {
        String formattedString = Integer.toString(column) + "," + Integer.toString(row);
        return formattedString;
	}

    // Abstract class that will enable us to differentiate human vs AI turns
	public abstract void playerTurn();
	
	public abstract Board getPlayerBoard();

}
