import java.util.Scanner;
import java.util.Random;

public class ComputerPlayer extends Player{
	
	Board playerBoard = null;

	public ComputerPlayer(Board board) {
		this.playerBoard = board;
	}
	
	public int randomCoordinate() {
		Random rand = new Random();
		int coordinate = rand.nextInt(playerBoard.getBoardSize()) + 1;
		return coordinate;
	}
	
	public void playerTurn() {
        boolean formatted = false;
        while (formatted != true) {
            try {

            		
            		int row = randomCoordinate();
            		int column = randomCoordinate();
                // check to make sure its a legit value
                if ((row > playerBoard.getBoardSize()) || (column > playerBoard.getBoardSize()) || (row < 0) || (column < 0)) {
                    System.out.println("Invalid coordinates");
                } else {
                    formatted = true;
                    // Specify where the attack has went
                    System.out.println("AI sent attack to (" + (char)((column + 65) - 1) + "," + row + ")" );

                    // Send the attack. Check if the attack hits or misses
                    sendAttack(playerBoard, row, column);
                }

            }
            // error checking
            catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong format");
                formatted = false;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                formatted = false;              
            
            }
        }

    }
    
}