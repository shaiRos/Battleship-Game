import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ComputerPlayer extends Player{
	
	Board playerBoard = null;
	private ArrayList<String> guessed = new ArrayList<String>();

	public ComputerPlayer(Board board) {
		this.playerBoard = board;
	}
	
	public int randomCoordinate() {
		Random rand = new Random();
		int coordinate = rand.nextInt(playerBoard.getBoardSize()) + 1;
		return coordinate;
	}
	
	public boolean checkNotAttacked(int column, int row) {
		boolean contains = true;
		if (guessed.isEmpty() == true) {
			return true;
		} else {
			for (String coords : guessed) {
				if (coords.equals(Integer.toString(column) + ", " + Integer.toString(row))) {
					contains = false;
				}
			}
		}

		return contains;
	}
	
	public void playerTurn() {
        boolean formatted = false;
        while (formatted != true) {
            try {

        		int row = randomCoordinate();
        		int column = randomCoordinate();	
            		while (checkNotAttacked(column, row) != true) {
            			row = randomCoordinate();
                		column = randomCoordinate();	
            		}
            		
                // check to make sure its a legit value
                if ((row > playerBoard.getBoardSize()) || (column > playerBoard.getBoardSize()) || (row < 0) || (column < 0)) {
                    System.out.println("Invalid coordinates");
                    System.out.println(row);
                    System.out.println(column);
                    System.exit(0);
                } else {
                    formatted = true;
                    // Specify where the attack has went
                    System.out.println("AI sent attack to (" + (char)((column + 65) - 1) + "," + row + ")" );
                    guessed.add(Integer.toString(column) + ", " + Integer.toString(row));
                    // Send the attack. Check if the attack hits or misses
                    sendAttack(playerBoard, row, column);
                    System.out.println("Current guessed values: ");
                    for (String values: guessed) {
                    		System.out.println(values);
                    }
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