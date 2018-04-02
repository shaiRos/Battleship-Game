package board;


import java.util.Scanner;
import board.*;

import java.util.Arrays;

/**
* created January 30, 2018
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
*	Board object that will hold all values of our board
*/
public class Board {

	private static int boardSize;
	private final static int MAXSHIPSIZE = 5;
	private final static int MINSHIPSIZE = 2;
	private static boolean guessing = false;
	public BoardValue[][] guessBoard;
	public BoardValue[][] gameBoard;
	private static int numOfShips;
	private Ship[] shipArray;
	private int[][] shipBoard;
	private static int[] listOfShipSizes;
	

	

	/**
	 * Default constructor for our board
	 * initialize all arrays and variables base on boardSize
	 */
	public Board() {
		guessBoard = new BoardValue[boardSize][boardSize];
		gameBoard = new BoardValue[boardSize][boardSize];
		shipBoard = new int[boardSize][boardSize];
		initializeGameBoard();
		shipArray = new Ship[numOfShips];

	}

	/**
	 * Populates the board with initial values
	 *
	 */
	private void initializeGameBoard() {
		for (BoardValue[] row : gameBoard) {
			Arrays.fill(row, BoardValue.EMPTY);
		}

	}
	
	/**
	 * getters for board size
	 * @return boardsize the static variable boardsize
	 */
	public static int getBoardSize() {
		return boardSize;
	}
	
	/**
	* Setter method for setting board size
	*/
	public static void setBoardSize(int size) {
		boardSize = size;
		numOfShips = (int)(Math.ceil(size/2.0));
		listOfShipSizes = generateShipsToAdd();
	}
	

	
	/**
	* getter method for the variable numOfShips
	*/
	public static int getNumOfShips() {
		return numOfShips;
	}

	public static int[] getGeneratedShips() {
		return listOfShipSizes;
	}

	/**
	* getter method for the constant MINSHIPSIZE
	*/
	public static int getMinShipSize() {
		return MINSHIPSIZE;
	}

	public static int getMaxShipSize() {
		return MAXSHIPSIZE;
	}
	
	public Ship[] getShipArray() {
		return shipArray;
	}

	// Method to check if correct board type for JUnit testing
	public void setBoardType(int boardType) {
		BoardValue[][] board = null;

		// specify if this board is for game, or guessing
		if (boardType == 1) {
			board = this.gameBoard;
			guessing = false;
		} else if (boardType == 2) {
			board = this.guessBoard;
			guessing = true;
		}
	}
	
	/**
	* Determine the number of ships and sizes base on the size of the boardSize
	* @return shipsToAdd an array of ship sizes to be placed on the board
	*/
	public static int[] generateShipsToAdd(){
		
		int shipSize = Math.min(numOfShips, MAXSHIPSIZE);
		int [] shipsToAdd = new int[numOfShips];
		
		for(int i = 0; i < numOfShips; i++){	
			if(shipSize > 3){
				//only allow one of each ship with size larger than 3
				shipsToAdd[i] = shipSize;
				shipSize--;
			}else if(i < (numOfShips - 2)){
				//rest of the ships are size 3 except for the last 2
				shipsToAdd[i] = 3;
			}else{ 
				//last two ships are size 2 (minimum ship size)
				shipsToAdd[i] = MINSHIPSIZE;
			}
	
		}
		
		return shipsToAdd;
		
	}

	// Getter method for board type for JUnit testing
	public boolean getBoardType() {
		return guessing;
	}


	/**
	 * Returns a formatted version of the board.
	 * 
	 * @param boardType
	 *            - Int that will specify whether the game shall display the
	 *            gameBoard or guessBoard
	 *
	 **/
	public void returnBoard(int boardType) {
		BoardValue[][] board = null;

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

		for (int row = 0; row < boardSize; row++) {
			// Print the y axis - will probably change to letters
			// Convert from numerical to char

			char rowName = (char) (row + 65);
			System.out.print(rowName);

			// For each column, check if any of the values match the following
			// They're spaced out for now so we can edit them with ease
			for (int column = 0; column < boardSize; column++) {

				// @ENUM
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

	/**
	 * Adds ship board base on its coordinates generated in the ship class. 
	 * Will be called after all verification and checks
	 * are passed
	 * 
	 * @param int
	 *            len, col, ro - Properties of the ship char orient - Properties of
	 *            the ship
	 **/
	
	public void addShip1(int ID, int len, char orient, int ro, int col) {
		shipArray[ID] = new Ship(ID, len, orient, ro, col);
		int[][] addCoordinates = shipArray[ID].getShipCoordinates();
		for (int i = 0; i < addCoordinates.length; i++) {
			int r = addCoordinates[i][0];
			int c = addCoordinates[i][1];
			shipBoard[r][c] = ID + 1;
			gameBoard[r][c] = BoardValue.SHIP;
		}

	}


	/**
	* check if a ship has sunken base on the position being attacked
	* @param rowAttacked y coordinate of position attacked
	* @param columnAttacked x coordinate of position attacked
	* @return a boolean indicating whether a ship has been sunken
	*/
	public boolean aShipSunken(int rowAttacked, int columnAttacked) {
		int shipID = shipBoard[rowAttacked][columnAttacked];
		if (shipID > 0 && shipID <= numOfShips) {
			shipArray[shipID - 1].takeHit(rowAttacked, columnAttacked);
			return (shipArray[shipID - 1].checkShipIsSunken());
		}

		return false;
	}

	/**
	* check the values of whether each ship is sunken in the ship array
	* @return true if a ships are sunken
	*/
	public boolean checkAllShipSunken() {
		for (int i = 0; i < shipArray.length; i++) {
			if (shipArray[i].checkShipIsSunken() == false) {
				return false; //if one of the ship isn't sunken, return false
			}
		}
		return true;
	}

}
