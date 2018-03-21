import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.*;

/**
*   Game implements the main controller that will call for the initialization of all our starting variables
*   and contains the main logic for the game loop
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
*
**/
public class Game{
	// This will toggle if our game will let us fight another player or an AI
    private static boolean aiStatus = false;
    private static boolean hitSuccess = false;


    /**
    *   The main constructor that will initialize the game. This will ruin the start() method for the current game object
    *   @param specifyAIStatus - Boolean that signifies whether the game will implement Player vs Player or Player vs AI
    **/
    public Game(boolean specifyAIStatus) {
        aiStatus = specifyAIStatus;
        start();
    }
    
    /**
    *   A toggle that will set the flag which enables the AI
    *
    **/
    public static void enableAI() {
    		aiStatus = true;
    }
    /**
    *   A getter that returns the AI flag's status
    *   @return aiStatus - Boolean that when true, indicates the AI has been selected
    **/
    public static boolean getAIStatus() {
    		return aiStatus;
    }

    /**
     *	Returns a true or false based on whether the attack was successful or not
     * @return hitSuccess - True for hit, false for miss and any other scenario
     */
    public static boolean getHitSuccess() {
    		return hitSuccess;
    }
    
	public static void setHitSuccess(boolean b) {
		hitSuccess = b;
	}

    /**
    *   When ran on unix systems, will clear the console for improved output and management of the text version of the game
    *
    **/
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
        // ASCII escape codes  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
    /**
    *   When ran, will sleep the current thread for the milliseconds, effectively giving a transition frame
    *   @param milliseconds - Int of milliseconds to pause execution
    **/

	public static void sleepThread(int milliseconds) {
        // Try sleeping for specified time given in ms
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    /**
    *   Will setup the board when Player wishes to manually setup the boards. Contains error checking and validation to ensure the ship's added meet specification
    *   @param player1Board - Board object that stores all of the information of the main player's board
    *          player2Board - Board object that stores all of the information of the opposing player's board
    **/
	public static void setupBoard(Board player1Board, Board player2Board, int shipCount) {

		int maxShips = shipCount;	//max number of ships for each board

		System.out.println("Player 1 setup phase: ");
        // create a list to store our ships into
		ArrayList<Ship> shipArray1 = new ArrayList<Ship>();
        // return the game board of current player
		player1Board.returnBoard(1);

        // loop to add ships into ship array
        GameConfig.playerInputShips(shipArray1, player1Board, shipCount);

		System.out.println("Player 1 game board successfully set. Player 2 standby...");
		sleepThread(1000);
		clearScreen();


		System.out.println("Player 2 setup phase: ");
        // create a list to store our ships into
		ArrayList<Ship> shipArray2 = new ArrayList<Ship>();
        // return the game board of the current player
		player2Board.returnBoard(2);


        // loop to add ships into ship array
        GameConfig.playerInputShips(shipArray2, player2Board, shipCount);

		System.out.println("Player 2 game board successfully set.");

		sleepThread(1000);
		clearScreen();

	}

	/**
    *   A check that will be ran after every turn. Will scan the current and enemy player's boards for existing ships, and declares a winner if none are found
    *   @param board - The board the win condition scan will be performed on
    *   @return boolean - Will return a boolean to indicate whether the win conditions have been met
    **/
	// Check the board for remaining ships
	public static boolean winCondition(Board board) {
        int shipCounter = 0;
        for (int x = 0; x < board.getBoardSize(); x++) {
            for (int y = 0; y < board.getBoardSize(); y++) {
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
    *   Reads from a given file and creates the current board and ship placements based on line-by-line fed information
    *   @param mapLevel - The final that contains the information required to build the level
    *
    **/
    public static void mapFromFiles(String mapLevel, Board board){

        //Initiate line for ship data from file 
        String shipInfo = null;
		int shipPlaced = 0;

        
        try {
            // FileReader reads text files
            FileReader fileReader = new FileReader(mapLevel);

            // Wrap FileReader in BufferedReader to efficiently read chars, lines, etc. (lines in this case)
            BufferedReader bufferedReader = new BufferedReader(fileReader);

           
            //Extracting data from file
            while ((shipInfo = bufferedReader.readLine()) != null) {
                System.out.println(shipInfo);
                String[] line = shipInfo.split(" ");
                
                char orientation = line[0].toLowerCase().charAt(0);
                int length = Integer.parseInt(line[1]);
                char tempRow = line[2].toUpperCase().charAt(0);
                int row = (((int)(tempRow) - 65 ) + 1);
                int column = Integer.parseInt(line[3]);

                System.out.println(orientation);
                System.out.println(length);
                System.out.println(row);
                System.out.println(column);
				//@betty adjust board
                //board.addShip(orientation,length,row,column);
				board.addShip1(shipPlaced,length, orientation, row, column);
				shipPlaced ++;
     
            }
            //Need to always close after done using it
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + mapLevel + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + mapLevel + "'");
            System.out.println(ex.getMessage());
        }

    }
    


    /**
    *   Default board difficulties
    *   Use the AI thingy to setup random board placement
    *   Implement Ship class features - ship sunk
    *   Fix board size constants
    **/
    /**
    *   Starting method that will instantiate all of our variables and begin the game loop
    *
    **/
   public void start() {
   		// create boards for both the players
        // difficulty will rely on these settings - add user input to specify difficulty
        int userBoardSize = 5;
        int userShipCount = 2;

        String fileName = "map.txt";

        // Initialize the boards and set the board sizes
        // WIP:
        //      - Re-create the board using the new boardSize values
        Board.setBoardSize(userBoardSize);
        Board player1Board = new Board();
        Board player2Board = new Board();
        // populate boards with battleships
        
        // This will allow user to input coordinates and setup board
        // setupBoard(player1Board, player2Board, userShipCount);

        // This will read a file and allow us to setup predefined board
        mapFromFiles(fileName, player1Board);
        mapFromFiles(fileName, player2Board);

        // instantiate our players
        Player player1 = new HumanPlayer(player1Board);
        // We don't know what our player 2 is at this point, just instantiate a generic player2
        Player player2 = null;


		// Create a new human that can access their boards
        /**
        *   Make the player an inheritance of a Player class
        **/
        if (getAIStatus() != true) {
	    		player2 = new HumanPlayer(player2Board);
        } else {
        		player2 = new ComputerPlayer(player2Board);
        }

        // set the win condition
		boolean winCondition = false;

        // Game loop
		
		do {
            // set each player's guess board to the other player's game board

			
			player1Board.guessBoard = player2Board.gameBoard;
			player2Board.guessBoard = player1Board.gameBoard;
			boolean shipSunk =false;
			


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
			GameConfig.sendAttack(player1Board,row1,column1);
			shipSunk = GameConfig. checkSunken(player2Board,row1,column1);


            // Check for remaining ships on enemy board
			if (winCondition(player2Board) == true) {
				System.out.println("Player 1 has won!");
				sleepThread(2500);
				System.exit(0);
			}
			
			sleepThread(1000);
			
			//check win conditions for every turn
			
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
			GameConfig.sendAttack(player2Board,row2,column2);
			shipSunk = GameConfig.checkSunken(player1Board,row2,column2);

            // if this is a hit, we want all the ships around the guessed ship to be added to the queue
            if (Game.getHitSuccess() == true && getAIStatus() == true) {
            	((ComputerPlayer)player2).makeQueue(column2, row2);

            }

            // Check for remaining ships on enemy board
			if (winCondition(player1Board) == true) {
				System.out.println("Player 2 has won!");
				sleepThread(2500);
				System.exit(0);
			}
			sleepThread(1000);
			
			//check win conditions maybe make this an exception. throws an exception if winning conditions are met, catches condition and exits loop.
			
		} while (winCondition != true);

	
    }
}
	