import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.*;


public class Game{
	
	// This will toggle if our game will let us fight another player or an AI
    private static boolean aiStatus = false;
    
    public static void enableAI() {
    		aiStatus = true;
    }
    
    public static boolean getAIStatus() {
    		return aiStatus;
    }

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
    // Debug tool while also hiding enemy boards!
	public static void clearScreen() {
        // ASCII escape codes  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  

	// This will pause program execution - use what would be a reasonable
	// delay for the user to read the console
	public static void sleepThread(int milliseconds) {
        // Try sleeping for specified time given in ms
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    // setup the initial board for gameplay
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
		player2Board.returnBoard(1);

        // loop to add ships into ship array
        GameConfig.playerInputShips(shipArray2, player2Board, shipCount);

		System.out.println("Player 2 game board successfully set.");

		sleepThread(1000);
		clearScreen();

	}

    // Check the board for remaining ships
	public static boolean winCondition(Board board) {
        int shipCounter = 0;
        for (int x = 0; x < board.getBoardSize(); x++) {
            for (int y = 0; y < board.getBoardSize(); y++) {
                if (board.gameBoard[x][y] == 5) {
                    shipCounter++;
                }
            }
        }

        if (shipCounter == 0) {
            return true;
        }
        return false;

    }


    public static void mapFromFiles(String mapLevel, Board board){

        //Initiate line for ship data from file 
        String shipInfo = null;
        
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

                board.addShip(orientation,length,row,column);
     
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
    *   Rules for specific ship lengths
    *   Use the AI thingy to setup random board placement
    *   Research enum on sendAttack
    *   Inheritance on the players
    *   use readFile for default maps
    *   Implement Ship class features - ship sunk
    *   Fix board size constants
    **/
   public static void main(String[] args) {
   		// create boards for both the players
        // difficulty will rely on these settings - add user input to specify difficulty
        int userBoardSize = 5;
        int userShipCount = 2;

        String fileName = "map.txt";

        // Initialize the boards and set the board sizes
        // WIP:
        //      - Re-create the board using the new boardSize values
        Board player1Board = new Board();
        player1Board.setBoardSize(userBoardSize);
        Board player2Board = new Board();
        player2Board.setBoardSize(userBoardSize);

        // populate boards with battleships
        
		// setupBoard(player1Board, player2Board, userShipCount);
        mapFromFiles(fileName, player1Board);
        mapFromFiles(fileName, player2Board);

        // instantiate our players
		Player player1 = new HumanPlayer(player1Board);
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
			player1Board.guessBoard = player2Board.getGameBoard();
			player2Board.guessBoard = player1Board.getGameBoard();

            // Player 1 turn
			clearScreen();
			System.out.println("Player 1 turn starting....");
            // Take the user coordinates and attack
			// DO NOTE
			// Currently, you need to typecast the type the player is to access the playerTurn method
			((HumanPlayer) player1).playerTurn();
            // Check for remaining ships on enemy board
			if (winCondition(player2Board) == true) {
				System.out.println("Player 1 has won!");
				sleepThread(2500);
				System.exit(0);
			}
			//sleepThread(1000);
			
			//check win conditions for every turn
			
            // Player 2 turn
			clearScreen();
			System.out.println("Player 2 turn starting....");
            // Take the user coordinates and attack
			// DO NOTE
			// Currently, you need to typecast the type the player is to access the playerTurn method
			if (getAIStatus() == true) {
				((ComputerPlayer) player2).playerTurn();
			} else {
				((HumanPlayer) player2).playerTurn();
			}
            // Check for remaining ships on enemy board
			if (winCondition(player1Board) == true) {
				System.out.println("Player 2 has won!");
				sleepThread(2500);
				System.exit(0);
			}
			//sleepThread(1000);
			
			//check win conditions maybe make this an exception. throws an exception if winning conditions are met, catches condition and exits loop.
			
		} while (winCondition != true);

	
    }
}
	