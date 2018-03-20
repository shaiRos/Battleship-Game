/**
* created January 30, 2018
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
*	Board object that will hold all values of our board
*/
import java.util.Scanner;
import java.util.Arrays;

class Board{

    // Instantiate these initial values. Some of these are hardcoded - will later be changed so we can have more flexibility in difficulty
	private static int boardSize = 5;	
	private static int maxShipSize = 5;
	private static int minShipSize = 2;
    private static boolean guessing = false;
	public BoardValue [][] guessBoard = new BoardValue[boardSize][boardSize];
	public BoardValue[][] gameBoard = new BoardValue[boardSize][boardSize];

	/**
	*	getters and setters for board constants
	*	
	**/
    public static int getBoardSize() {
        return boardSize;
    }

    public static void setBoardSize(int size) {
        boardSize = size;
    }

    public static int getMinShipSize() {
        return minShipSize;
    }

    public static int getMaxShipSize() {
        return maxShipSize;
    }
    
    
	/**
	*	Default constructor for our board
	*
	**/
	public Board(){
		intializeGameBoard();

	}
	/**
	*	Populates the board with initial values
	*
	**/
	private void intializeGameBoard(){
		for (BoardValue[] row : gameBoard){
			Arrays.fill(row, BoardValue.EMPTY);
		}
		
	}
	
	/**
	*	Returns a formatted version of the board.
	*	@param boardType - Int that will specify whether the game shall display the gameBoard or guessBoard
	*
	**/
	public void returnBoard(int boardType) {
		//int[][] board = null;
		BoardValue [][] board = null;

        // our definitions
//        char hidden = '~';
//        char miss = '*';
//        char hit = 'X';
//        char ship = 'S';    
//        char downed = 'Z';  

        // specify if this board is for game, or guessing
        if (boardType == 1) {
            board = this.gameBoard;
            guessing = false;
        } else if (boardType == 2) {
            board = this.guessBoard;
            guessing = true;
        }

        for (int x = 0; x < boardSize; x++) {
        	// Will print the x axis
        	System.out.print(("\t" + (x + 1)));
        }
        // Print a blank line for formatting purposes
        System.out.println();

        for (int row = 0 ; row < boardSize ; row++ ) {
        	// Print the y axis - will probably change to letters
        	// Convert from numerical to char

            char rowName = (char)(row + 65);
            System.out.print(rowName);

            // For each column, check if any of the values match the following
            // They're spaced out for now so we can edit them with ease
            for (int column = 0 ; column < boardSize ; column++ ) {

					//@ENUM
            		if ((guessing == true) && (board[row][column] == BoardValue.SHIP)) {
            			System.out.print("\t" + BoardValue.EMPTY);
            		} else {
            			System.out.print("\t" + board[row][column]);

            		}

            }
            // Another blank space
            System.out.println();
        }
	}

	//addShip given all properties of the ship
	//just make sure for horizontal indicate the left most coordinates
	//and for vertical indicate the top most coordinate
	/**
	*	Adds ship directly to board. Will be called after all verification and checks are passed
	*	@param int len, col, ro - Properties of the ship
	*			char orient - Properties of the ship
	**/
	public void addShip(char orientation, int length, int column, int row){
		switch(orientation) {
			case 'h': {
				int maxColumn = column + (length-1); //right most coordinate of the ship					
				for (int x = column; x <= maxColumn; x++) {	//changing the values of the coordinates it occupies.
					gameBoard[row-1][x-1] = BoardValue.SHIP;
				}break;
			}
			case 'v': {
				int maxRow = row + (length-1); //bottom most coordinate of the ship	
				for (int x = row; x <= maxRow; x++) {	//change values
						gameBoard[x-1][column-1] = BoardValue.SHIP;

				}break;
			}
		}
	}		
	

}

				
