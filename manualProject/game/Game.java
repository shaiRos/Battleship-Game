package game;

import java.util.Scanner;

import board.*;
import players.*;
import board.Ship.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.*;

/**
 * Game implements the main controller that will call for the initialization of
 * all our starting variables and contains the main logic for the game loop
 * 
 * @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
 *
 */
public class Game {
	// This will toggle if our game will let us fight another player or an AI
	private static boolean aiStatus = false;
	private static boolean hitSuccess = false;
	private static boolean shipSunk = false;

	/**
	* default constructor to create a game object
	* default to player vs. player mode if called
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
	 *
	 */
	public static void enableAI() {
		aiStatus = true;
	}

	/**
	 * A getter that returns the AI flag's status
	 * 
	 * @return aiStatus - Boolean that when true, indicates the AI has been selected
	 */
	public static boolean getAIStatus() {
		return aiStatus;
	}

	/**
	 * Returns a true or false based on whether the attack was successful or not
	 * 
	 * @return hitSuccess - True for hit, false for miss and any other scenario
	 */
	public static boolean getHitSuccess() {
		return hitSuccess;
	}
	
	/**
	* @Brandon what does this dod
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
	public static void sleepThread(int milliseconds) {
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
	 *            - Board object that stores all of the information of the main
	 *            player's board player2Board - Board object that stores all of the
	 *            information of the opposing player's board
	 */
	public static void setupBoard(Board player1Board, Player player1, Board player2Board, Player player2) {

		System.out.println("Player 1 setup phase: ");

		// loop to add ships into ship array
		userPlaceShip(player1Board, player1);

		System.out.println("Player 1 game board successfully set. Player 2 standby...");
		sleepThread(1000);
		clearScreen();

		System.out.println("Player 2 setup phase: ");

		// loop to add ships into ship array
		userPlaceShip(player2Board, player2);
		

		System.out.println("Player 2 game board successfully set.");

		sleepThread(1000);
		clearScreen();

	}
	
	/**
	* the main function used for the players to setup their ship placements
	* @param playerBoard the Board object of the player setting the ship
	* @param currentPlayer the current player
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
			System.out.println((maxShips - (shipNumber + 1)) + " ships left to place");
			}
			
		}
	}
	
	/**
	 * Receives human and text file input, and creates boards based on the given
	 * information. Checks implemented to ensure the placements are not outside the
	 * scope of the given board
	 * 
	 * @param name
	 *            - Ship object that will be used to store all information about the
	 *            player's respective ships board - Holds all of the information and
	 *            game state of the current board
	 */
	// The main code for inserting ships on the other board
	// Error checking, logic checking etc
	public static void setupInput(Board board, Player currentPlayer, int shipLength, int shipCount){
		boolean formatted = false;

		while (formatted != true) {
			try {
				System.out.println("\nPlacing a length " + shipLength + " ship");
				String setup = currentPlayer.playerSetup();
				// take the input that was converted into String and separate the info
				String setupInfo[] = setup.split(" ");
				// store info to designated variables and convert string to their types
				char orientation = setupInfo[0].toLowerCase().charAt(0);
				int row = Integer.parseInt(setupInfo[1]);
				int column = Integer.parseInt(setupInfo[2]);

				GameConfig.validateShipProperties(board, shipLength, orientation, column, row); 

				// Adds ship to the grid
				board.addShip1(shipCount, shipLength, orientation, row, column);

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
	 * @return boolean - Will return a boolean to indicate whether the win
	 *         conditions have been met
	 */
	// Check the board for remaining ships
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
	 *            - The final that contains the information required to build the
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

				System.out.println(orientation);
				System.out.println(length);
				System.out.println(row);
				System.out.println(column);
				// @betty adjust board
				// board.addShip(orientation,length,row,column);
				board.addShip1(shipPlaced, length, orientation, row, column);
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
		// difficulty will rely on these settings - add user input to specify difficulty
		int userBoardSize = 5;
		int userShipCount = 2;
		boolean winCondition = false;


		// Initialize the boards and set the board sizes
		// WIP:
		// - Re-create the board using the new boardSize values
		Board.setBoardSize(userBoardSize);
		Board player1Board = new Board();
		Board player2Board = new Board();
		// populate boards with battleships


		// This will read a file and allow us to setup predefined board
		//String fileName = "map.txt";
		//mapFromFiles(fileName, player1Board);
		//mapFromFiles(fileName, player2Board);

		// instantiate our players
		Player player1 = new HumanPlayer(player1Board);
		// We don't know what our player 2 is at this point, just instantiate a generic
		// player2
		Player player2 = null;

		// Create a new human that can access their boards
		/*
		 * Make the player an inheritance of a Player class
		 */
		if (getAIStatus() != true) {
			player2 = new HumanPlayer(player2Board);
		} else {
			player2 = new ComputerPlayer(player2Board);
		}

		// This will allow user to input coordinates and setup board
		setupBoard(player1Board, player1, player2Board, player2);

		// Game loop
		do {
			// set each player's guess board to the other player's game board
			player1Board.guessBoard = player2Board.gameBoard;
			player2Board.guessBoard = player1Board.gameBoard;

			// Player 1 turn
			clearScreen();
			System.out.println("Player 1 turn starting....");

			// Take coordinates from the player turn as col,row
			// Convert back to usable values
			String coord = player1.playerTurn();
			String[] coordFormatted = coord.split(",");
			int row1 = Integer.parseInt(coordFormatted[0]);
			int column1 = Integer.parseInt(coordFormatted[1]);
			// Send the attack to the board once properly formatted
			System.out.println("row " + row1 + "column " + column1);
			GameConfig.sendAttack(player1Board, row1, column1);
			shipSunk = GameConfig.checkSunken(player2Board, row1, column1);
			// Check for remaining ships on enemy board
			if (winCondition(player2Board) == true) {
				System.out.println("Player 1 has won!");
				sleepThread(2500);
				System.exit(0);
			}

			sleepThread(1000);

			// check win conditions for every turn

			// Player 2 turn
			clearScreen();
			System.out.println("Player 2 turn starting....");
			// Take the user coordinates and attack

			// Take AI values as col,row
			// Convert it back to usable values
			String coordEnemy = player2.playerTurn();
			String[] coordFormattedEnemy = coordEnemy.split(",");
			int row2 = Integer.parseInt(coordFormattedEnemy[0]);
			int column2 = Integer.parseInt(coordFormattedEnemy[1]);
			GameConfig.sendAttack(player2Board, row2, column2);
			shipSunk = GameConfig.checkSunken(player1Board, row1, column1);
			// if this is a hit, we want all the ships around the guessed ship to be added
			// to the queue
			if (Game.getHitSuccess() == true && getAIStatus() == true) {
				((ComputerPlayer) player2).makeQueue(row2, column2);
			}
			if (shipSunk == true) {
				((ComputerPlayer) player2).clearQueue();
				System.out.println("Cleared the queue because ship has been sunk");
				// reset the flag
				shipSunk = false;
			}
			// DEBUG
			System.out.println("Current guessed values: ");
			for (String values : ComputerPlayer.getGuessed()) {
				System.out.println(values);
			}

			// DEBUG
			System.out.println("Current guessing queue: ");
			for (String values : ComputerPlayer.getQueue()) {
				System.out.println(values);
			}

			// Check for remaining ships on enemy board
				System.out.println("Player 2 has won!");
			if (winCondition(player1Board) == true) {
				sleepThread(2500);
				System.exit(0);
			}
			sleepThread(1000);

			/*check win conditions maybe make this an exception. throws an exception if
			* winning conditions are met, catches condition and exits loop. */

		} while (winCondition != true);

	}

	//@brandon or @charlene??
	// Method to check the start() method variables
	public void startCheck() {
		// create boards for both the players
		// difficulty will rely on these settings - add user input to specify difficulty
		// int userBoardSize = 5;
		// int userShipCount = 2;

		String fileName = "map.txt";

		// Initialize the boards and set the board sizes
		// WIP:
		// - Re-create the board using the new boardSize values
		//Board.setBoardSize(userBoardSize);
	}

}
