package game;

import java.util.Scanner;

import board.*;
import players.*;
import board.Ship.*;
import saveload.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.*;

/**
 * Game implements the main controller that will call for the initialization of
 * all our starting variables and contains the main logic for the game loop
 * 
 * @author Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayang
 *
 */
public class Game {
	// This will toggle if our game will let us fight another player or an AI
	private static boolean aiStatus = false;
	private static boolean hitSuccess = false;
	private static boolean shipSunk = false;

	/**
	* default constructor to create a game object
	* default to player vs player mode if called
	*/
	public Game() {
		start();
	}

	/**
	 * The main constructor that will initialize the game. This will ruin the
	 * start() method for the current game object
	 * 
	 * @param specifyAIStatus
	 *            - Boolean that signifies whether the game will implement Player vs
	 *            Player or Player vs AI
	 */
	public Game(boolean specifyAIStatus) {
		aiStatus = specifyAIStatus;
		start();
	}

	/**
	 * A toggle that will set the flag which enables the AI
	 */
	public static void enableAI() {
		aiStatus = true;
	}

	/**
	 * A toggle that will set the flag which disables the AI
	 *
	 */	
	public static void disableAI() {
		aiStatus = false;
	}
	
	/**
	 * A getter that returns the AI flag's status
	 * 
	 * @return aiStatus Boolean that when true, indicates the AI has been selected
	 */
	public static boolean getAIStatus() {
		return aiStatus;
	}

	/**
	 * Returns a true or false based on whether the attack was successful or not
	 * 
	 * @return hitSuccess True for hit, false for miss and any other scenario
	 */
	public static boolean getHitSuccess() {
		return hitSuccess;
	}
	
	/**
	* setter for hitSuccess
	* @param b boolean for resetting hit success each round
	*/
	public static void setHitSuccess(boolean b) {
		hitSuccess = b;
	}

	/**
	 * When ran on unix systems, will clear the console for improved output and
	 * management of the text version of the game
	 *
	 */
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		// ASCII escape codes
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * When ran, will sleep the current thread for the milliseconds, effectively
	 * giving a transition frame
	 * 
	 * @param milliseconds
	 *            - Int of milliseconds to pause execution
	 */
	private static void sleepThread(int milliseconds) {
		// Try sleeping for specified time given in ms
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Will setup the board when Player wishes to manually setup the boards.
	 * Contains error checking and validation to ensure the ship's added meet
	 * specification
	 * 
	 * @param player1Board
	 *            Board object that stores all of the information of the main
	 *            player's board 
	 * @param player2Board
	 * @param player1 player object linked to player1Board
	 * @param player2 
	 */
	public static void setupBoard(Board player1Board, Player player1, Board player2Board, Player player2) {

		System.out.println("Player 1 setup phase: ");

		// loop to add ships into ship array
		userPlaceShip(player1Board, player1);

		System.out.println("Player 1 game board successfully set. Player 2 standby...");
		sleepThread(300);
		clearScreen();

		System.out.println("Player 2 setup phase: ");

		// loop to add ships into ship array
		userPlaceShip(player2Board, player2);
		

		System.out.println("Player 2 game board successfully set.");

		sleepThread(300);
		clearScreen();

	}
	
	/**
	* the main function used for the players to setup their ship placements
	* @param playerBoard the Board object of the player setting the ship
	* @param currentPlayer
	*/
	public static void userPlaceShip(Board playerBoard, Player currentPlayer){
		int [] typeOfShipToAdd = Board.generateShipsToAdd();
		int maxShips = typeOfShipToAdd.length; // max number of ships for each board
		
		for(int shipNumber = 0; shipNumber < maxShips; shipNumber++){
			int shipLength = typeOfShipToAdd[shipNumber];
		
			setupInput(playerBoard, currentPlayer, shipLength, shipNumber);
			
			if (currentPlayer instanceof HumanPlayer)
			{
				playerBoard.returnBoard(1);
				System.out.println("\n" + (maxShips - (shipNumber + 1)) + " ship(s) left to place");
				System.out.println("Placing a length " + shipLength + " ship");

			}
			
		}
	}
	
	/**
	 * Receives human and text file input, and creates boards based on the given
	 * information. Checks implemented to ensure the placements are not outside the
	 * scope of the given board
	 * 
	 * @param board the current board being set up
	 * @param currentPlayer the current player connected to the board
	 * @param shipLength length of the ship
	 * @param shipCount the current ship being placed
	 */
	public static void setupInput(Board board, Player currentPlayer, int shipLength, int shipCount){
		boolean formatted = false;

		while (formatted != true) {
			try {
				String setup = currentPlayer.playerSetup();
				// take the input that was converted into String and separate the info
				String setupInfo[] = setup.split(" ");
				// store info to designated variables and convert string to their types
				char orientation = setupInfo[0].toLowerCase().charAt(0);
				int row = Integer.parseInt(setupInfo[1]);
				int column = Integer.parseInt(setupInfo[2]);

				GameConfig.validateShipProperties(board, shipLength, orientation, column, row); 

				// Adds ship to the board
				board.addShip(shipCount, shipLength, orientation, row, column);

				formatted = true;

			} catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
				// possible errors when doing the conversions of the string input
				if(currentPlayer instanceof HumanPlayer){
					System.out.println("\nWrong format, example: h A 1");
				}
				formatted = false;
			} catch (IllegalArgumentException e) {
				// input must meet the requirements. This is done in the validate methods.
				if(currentPlayer instanceof HumanPlayer){
					System.out.println(e.getMessage());
				}
				formatted = false;

			}
		}
	}

	/**
	 * A check that will be ran after every turn. Will scan the current and enemy
	 * player's boards for existing ships, and declares a winner if none are found
	 * 
	 * @param board
	 *            - The board the win condition scan will be performed on
	 * @return  Will return a boolean to indicate whether the win
	 *         conditions have been met
	 */
	public static boolean winCondition(Board board) {
		int shipCounter = 0;
		for (int x = 0; x < Board.getBoardSize(); x++) {
			for (int y = 0; y < Board.getBoardSize(); y++) {
				if (board.gameBoard[x][y] == BoardValue.SHIP) {
					shipCounter++;
				}
			}
		}

		if (shipCounter == 0) {
			return true;
		}
		return false;

	}

	/**
	 * Reads from a given file and creates the current board and ship placements
	 * based on line-by-line fed information
	 * 
	 * @param mapLevel
	 *            The final that contains the information required to build the
	 *            level
	 * @param board the board being setup
	 *
	 */
	public static void mapFromFiles(String mapLevel, Board board) {

		// Initiate line for ship data from file
		String shipInfo = null;
		int shipPlaced = 0;

		try {
			// FileReader reads text files
			FileReader fileReader = new FileReader(mapLevel);

			// Wrap FileReader in BufferedReader to efficiently read chars, lines, etc.
			// (lines in this case)
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Extracting data from file
			while ((shipInfo = bufferedReader.readLine()) != null) {
				System.out.println(shipInfo);
				String[] line = shipInfo.split(" ");

				char orientation = line[0].toLowerCase().charAt(0);
				int length = Integer.parseInt(line[1]);
				char tempRow = line[2].toUpperCase().charAt(0);
				int row = (((int) (tempRow) - 65) + 1);
				int column = Integer.parseInt(line[3]);

				board.addShip(shipPlaced, length, orientation, row, column);
				shipPlaced++;

			}
			// Need to always close after done using it
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + mapLevel + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + mapLevel + "'");
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * Default board difficulties Use the AI thingy to setup random board placement
	 * Implement Ship class features - ship sunk Fix board size constants
	 * Starting method that will instantiate all of our variables and begin the game
	 * loop
	 */
	public void start() {
		// create boards for both the players
		// difficulty will rely on these settings (will be used in gui version)
		int userBoardSize = 5;
		boolean winCondition = false;


		// Initialize the boards and set the board sizes
		// WIP:
		// - Re-create the board using the new boardSize values
		Board player1Board = new Board(userBoardSize);
		Board player2Board = new Board(userBoardSize);
		// populate boards with battleships

		// instantiate our players
		Player player1 = new HumanPlayer(player1Board, "player 1");
		// We don't know what our player 2 is at this point, just instantiate a generic
		// player2
		Player player2 = null;

		// Create a new human that can access their boards
		if (getAIStatus() != true) {
			player2 = new HumanPlayer(player2Board, "player 2");
		} else {
			player2 = new ComputerPlayer(player2Board);
		}

		// This will allow user to input coordinates and setup board
		setupBoard(player1Board, player1, player2Board, player2);
		
		//index for switching turns in player and game board array 
		int currentPlayerNum = 0;
		int swtichTurn = 1;

		// Game loop
		do {
			// set each player's guess board to the other player's game board
			player1Board.guessBoard = player2Board.gameBoard;
			player2Board.guessBoard = player1Board.gameBoard;

			//the following arrays are used to switch turns between players
			Board [] boards = new Board[]{player1Board, player2Board};
			Player [] players = new Player[]{player1,player2};
			

			clearScreen();
			System.out.println(players[currentPlayerNum].getName() + " turn starting....");

			// Take coordinates from the player turn as col,row
			// Convert back to usable values
			String coord = players[currentPlayerNum].playerTurn();
				
			
			String[] coordFormatted = coord.split(",");
			int row1 = Integer.parseInt(coordFormatted[0]);
			int column1 = Integer.parseInt(coordFormatted[1]);
			// Send the attack to the board once properly formatted
			GameConfig.sendAttack(boards[currentPlayerNum], row1, column1);

			// Check for remaining ships on enemy board
			shipSunk = GameConfig.checkSunken(boards[currentPlayerNum+swtichTurn], row1, column1);
			if (winCondition(boards[currentPlayerNum+swtichTurn]) == true) {
				System.out.println(players[currentPlayerNum].getName() +  " has won!");
				sleepThread(2500);
				System.exit(0);
			}

			sleepThread(1000);
			//switch player
			currentPlayerNum += swtichTurn;
			swtichTurn *= -1;

		} while (winCondition != true);

	}

}
