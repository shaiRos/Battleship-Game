import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ComputerPlayer extends Player{
	
	Board playerBoard = null;
	private ArrayList<String> guessed = new ArrayList<String>();
	private ArrayList<String> queue = new ArrayList<String>();

	public ComputerPlayer(Board board) {
		this.playerBoard = board;
	}
	
	public int randomCoordinate() {
		Random rand = new Random();
		int coordinate = rand.nextInt(playerBoard.getBoardSize()) + 1;
		return coordinate;
	}
	
	public boolean checkNotAttacked(String coordinate) {
		boolean contains = true;
		if (guessed.isEmpty() == true) {
			return true;
		} else {
			for (String coords : guessed) {
				if (coords.equals(coordinate)) {
					contains = false;
				}
			}
		}

		return contains;
	}
	
	public void makeQueue(int column, int row) {
		int boardSize =  Board.getBoardSize();
		if ((column + 1 > boardSize) && (row > 0 && row <= boardSize)) {
			System.out.println("col + 1 > size");
			if (checkNotAttacked(coordToString(column, row + 1)) == true) {
				queue.add(coordToString(column, row + 1));

			}
			if (checkNotAttacked(coordToString(column, row - 1)) == true) {
				queue.add(coordToString(column, row - 1));

			}
			if (checkNotAttacked(coordToString(column - 1, row)) == true) {
				queue.add(coordToString(column - 1, row));

			}

		} else if ((row + 1 > boardSize) && (column > 0 && column <= boardSize)) {
			System.out.println("row + 1 > size");
			if (checkNotAttacked(coordToString(column + 1, row)) == true) {
				queue.add(coordToString(column + 1, row));

			}
			if (checkNotAttacked(coordToString(column, row - 1)) == true) {
				queue.add(coordToString(column, row - 1));

			}
			if (checkNotAttacked(coordToString(column - 1, row)) == true) {
				queue.add(coordToString(column - 1, row));

			}

		} else if ((column - 1 <= 0) && (row >= 1 && row <= boardSize)) {
			System.out.println("col  - 1 <= 0");
			if (checkNotAttacked(coordToString(column, row + 1)) == true) {
				queue.add(coordToString(column, row + 1));

			}
//			if (checkNotAttacked(coordToString(column, row - 1)) == true) {
//				queue.add(coordToString(column, row - 1));
//
//			}
			if (checkNotAttacked(coordToString(column + 1, row)) == true) {
				queue.add(coordToString(column + 1, row));

			}

		} else if ((row - 1 <= 0) && (column >= 1 && column <= boardSize)) {
			System.out.println("row - 1 < 0");
			if (checkNotAttacked(coordToString(column, row + 1)) == true) {
				queue.add(coordToString(column, row + 1));

			}
			if (checkNotAttacked(coordToString(column + 1, row)) == true) {
				queue.add(coordToString(column + 1, row));

			}
			if (checkNotAttacked(coordToString(column - 1, row)) == true) {
				queue.add(coordToString(column - 1, row));

			}

		} else if (((column + 1) > boardSize) && ((row + 1) > boardSize)) {
			System.out.println("col + 1 & row + 1 > size");
			if (checkNotAttacked(coordToString(column, row - 1)) == true) {
				queue.add(coordToString(column, row - 1));

			}
			if (checkNotAttacked(coordToString(column - 1, row)) == true) {
				queue.add(coordToString(column - 1, row));

			}

		} else if (((column - 1) <= 0) && ((row - 1) <= 0)) {
			System.out.println("col - 1 & row - 1 < 0");
			if (checkNotAttacked(coordToString(column, row + 1)) == true) {
				queue.add(coordToString(column, row + 1));

			}
			if (checkNotAttacked(coordToString(column + 1, row)) == true) {
				queue.add(coordToString(column + 1, row));

			}

		} else {
			System.out.println("else statement");
			
			if (checkNotAttacked(coordToString(column, row + 1)) == true) {
				queue.add(coordToString(column, row + 1));

			}
			if (checkNotAttacked(coordToString(column, row - 1)) == true) {
				queue.add(coordToString(column, row - 1));

			}
			if (checkNotAttacked(coordToString(column - 1, row)) == true) {
				queue.add(coordToString(column - 1, row));

			}
			if (checkNotAttacked(coordToString(column + 1, row)) == true) {
				queue.add(coordToString(column + 1, row));

			}

		}
	}
	public void playerTurn() {
        boolean formatted = false;
        while (formatted != true) {
            try {

	        		int row = randomCoordinate();
	        		int column = randomCoordinate();	
	        		
	        		if (queue.isEmpty()) {
	            		while ((checkNotAttacked(coordToString(column, row)) != true)) {
	            			row = randomCoordinate();
	                		column = randomCoordinate();	                	
	                		
	            		}
	        		} else {
            			String[] values = queue.get(0).split(",");
            			row = Integer.parseInt(values[1]);
            			column = Integer.parseInt(values[0]);  
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
                    guessed.add(coordToString(column, row));
                    // Send the attack. Check if the attack hits or misses
                    
                    if (!queue.isEmpty()) {
                        queue.remove(0);
                    }
                    
                    if (sendAttack(playerBoard, row, column) == true) {
                    		makeQueue(column, row);
                    }
                    
                    System.out.println("Current guessed values: ");
                    for (String values: guessed) {
                    		System.out.println(values);
                    }
                    
                    System.out.println("Current guessing queue: ");
                    for (String values: queue) {
                    		System.out.println(values);
                    }
                }

            }
            // error checking
            catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong format");
                System.out.println(e.getMessage());
                formatted = false;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                formatted = false;              
            
            }
        }

    }
    
}