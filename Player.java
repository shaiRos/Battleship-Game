import java.util.ArrayList;

public abstract class Player {
	// was thinking of moving stuff into here once it was working, but it doesnt
    /**
    *   We want to research enumeration for this method
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
	
	public boolean checkPreviousHitEnum(Board playerBoard, int row, int column) {
        BoardValue value = (playerBoard.guessBoard[column - 1][row - 1]);
		if (value == BoardValue.HIT) {
			return true;
		} else if (value == BoardValue.MISS) {
			return true;
		}
		return false;
	}
	
	
	public String coordToString(int column, int row) {
        String formattedString = Integer.toString(column) + "," + Integer.toString(row);
        return formattedString;
	}

	public abstract void playerTurn();
	
	public abstract Board getPlayerBoard();

}
