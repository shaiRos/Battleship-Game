import java.util.ArrayList;

public class Player {
	// was thinking of moving stuff into here once it was working, but it doesnt
    /**
    *   We want to research enumeration for this method
    **/
	public boolean sendAttack(Board playerBoard, int row, int column) {
        // check the value of the block specified, if the values match, change the values with
        // a hit or a miss
        int boardValue = (playerBoard.guessBoard[column - 1][row - 1]);
        if (boardValue == 5) {
            playerBoard.guessBoard[column - 1][row - 1] = 1;
            System.out.println("Hit!");
            return true;
        } else if (boardValue == 0) {
            playerBoard.guessBoard[column - 1][row - 1] = -1;
            System.out.println("Miss!");
        } else if (boardValue == -1) {
            playerBoard.guessBoard[column - 1][row - 1] = -1;
            System.out.println("Miss!");
        // Should probably have a different check case for else
        } else {
            System.out.println("I broke something whoops");
            System.out.println("Debuggies");
            System.out.println(boardValue);
        }
        
        return false;

	}
	
	public String coordToString(int column, int row) {
        String formattedString = Integer.toString(column) + "," + Integer.toString(row);
        return formattedString;
	}
	
	public boolean checkPreviousHit(Board playerBoard, int row, int column) {
		int boardValue = (playerBoard.guessBoard[column - 1][row - 1]);
		if (boardValue == 1) {
			return true;
		} else if (boardValue == -1) {
			return true;
		}
		return false;
	}

}
