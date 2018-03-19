import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
*	Houses all of the core logic dedicated to the AI
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
**/
public class ComputerPlayer extends Player{
	
	Board playerBoard = null;
	private ArrayList<String> guessed = new ArrayList<String>();
	private ArrayList<String> queue = new ArrayList<String>();
	/**
	*	Default constructor
	*	@param board - links the provided board to the current ComputerPlayer object
	**/
	public ComputerPlayer(Board board) {
		this.playerBoard = board;
	}
	
	/**
	*	getter returns board
	*
	**/
	public Board getPlayerBoard() {
		return playerBoard;
	}
	
	/**
	*	Generates random coordinates that are within the board's bounds
	*	@return coordinate - Int between 1 - board size
	**/
	public int randomCoordinate() {
		Random rand = new Random();
		int coordinate = rand.nextInt(Board.getBoardSize()) + 1;
		return coordinate;
	}
	
	/**
	*	Compares the current attack to the AI's previously guessed values, stored within an ArrayList
	*	@param coordinate - String formatted column,row value
	*	@return contains - Boolean specifies whether the attack is valid
	**/
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
	// Long method, will check all four corners and all four sides of the board
	// Adds to the queue based on the parameters
	// NOTE
	// 	This makes the AI miss ALOT.
	//	NEEDS THE SUNK_SHIP BOOLEAN TO CLEAR THE QUEUE
	/**
	*	If the AI makes a hit, adds the hit to the current queue that will be processed during AI turn
	*	@param int column, row - Int values of the column and row that has been successfully attacked
	**/
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

	/**
	*	The procedure the AI will follow to complete their round. Contains all of the logic required to make a guess and validate the guess values
	*
	**/

	public String playerTurn() {
        boolean formatted = false;
        int column = -1;
        int row = -1;
        while (formatted != true) {
            try {
            		
            		// instantiate initial values first just in case
	        		int row = randomCoordinate();
	        		int column = randomCoordinate();	
	        		
	        		// if the queue is empty, then we'll just use the random values
	        		if (queue.isEmpty()) {
	            		while ((checkNotAttacked(coordToString(column, row)) != true)) {
	            			row = randomCoordinate();
	                		column = randomCoordinate();	                	
	                		
	            		}
	            	// if there are values in the queue, use those instead of the randomly generated values
	        		} else {
            			String[] values = queue.get(0).split(",");
            			row = Integer.parseInt(values[1]);
            			column = Integer.parseInt(values[0]);  
	        		}

         		
            		
                // check to make sure its a legit value
                if ((row > Board.getBoardSize()) || (column > Board.getBoardSize()) || (row < 0) || (column < 0)) {
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
                    
                    // we assume the attack is successfully sent, remove the item from the queue
                    if (!queue.isEmpty()) {
                        queue.remove(0);
                    }

                    // DEBUG
                    System.out.println("Current guessed values: ");
                    for (String values: guessed) {
                    		System.out.println(values);
                    }
                    
                    // DEBUG
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
        
        return (coordToString(column, row));
    }
    
}