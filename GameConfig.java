import java.util.Scanner;
import java.util.ArrayList;

/**
*	A configurator that validates and sanitizes all input, and prepares the given values for game usage
*
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
**/
public class GameConfig {
	
	/**
	*	Receives human and text file input, and creates boards based on the given information. Checks implemented to ensure the placements are not outside the scope of the given board
	*	@param name - Ship object that will be used to store all information about the player's respective ships
	*			board - Holds all of the information and game state of the current board
	**/
	// The main code for inserting ships on the other board
	// Error checking, logic checking etc
	public static void setupInput(Ship name, Board board) {
		
		boolean formatted = false;
		while (formatted != true){
			try {
				//asd for input for the variables needed to place ships
				System.out.print("\nIndicate (orientation length row column): ");
				Scanner Setup = new Scanner(System.in);
				String setup = Setup.nextLine();

				//Scanner to test user input
				//take the input that was converted into String and separate the info
				String setupInfo[] = setup.split(" ");
				//store info to designated variables and convert string to their types
				char orientation = setupInfo[0].toLowerCase().charAt(0);
				int length = Integer.parseInt(setupInfo[1]);
				char tempRow = setupInfo[2].toUpperCase().charAt(0);
				int row = (((int)(tempRow) - 65 ) + 1);
				int column = Integer.parseInt(setupInfo[3]);

				// TODO
				/*	
				*	POSSIBLE JUNIT TESTING 
				*	
				*/
				//all checks
				validateShipProperties(board,length,orientation,column,row);	//checks if ship properties meet the rules of the game
				
				// Adds ship to the grid
				board.addShip(orientation,length,column,row);
				//sets the values of the ship object
				name.setShipValues(orientation,length,column,row); 				
				formatted = true;
				
			}
			catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
				//possible errors when doing the conversions of the string input
				System.out.println("Wrong format");
				formatted = false;
			}
			catch (IllegalArgumentException e) {
				//input must meet the requirements. This is done in the validate methods. If it doesn't,the methods throws this
				//exception, exits the loop, and asks the user for a new value that meets the requiremnts.
				System.out.println(e.getMessage());
				formatted = false;				
			
			}
		}
	}
	
    /**
    *   Validates if the attack is valid, and changes board based on the information
    *   @param playerBoard - current Board object used
    *           int row, column - Int form row and column specified by the player
    *   @return boolean - Will return whether the attack successfully hit a ship or missed.
    **/
	public static void sendAttack(Board playerBoard, int row, int column) {
        // check the value of the block specified, if the values match, change the values with
        // a hit or a miss
        BoardValue value = (playerBoard.guessBoard[column - 1][row - 1]);
        if (value == BoardValue.SHIP) {
            playerBoard.guessBoard[column - 1][row - 1] = BoardValue.HIT;
            System.out.println("Hit!");
            Game.setHitSuccess(true);
        } else if (value == BoardValue.EMPTY) {
            playerBoard.guessBoard[column - 1][row - 1] = BoardValue.MISS;
            System.out.println("Miss!");
            Game.setHitSuccess(false);
        } else if (value == BoardValue.MISS) {
            playerBoard.guessBoard[column - 1][row - 1] = BoardValue.MISS;
            System.out.println("Miss!");
            Game.setHitSuccess(false);
        // Should probably have a different check case for else
        } else {
            System.out.println("I broke something whoops??");
			System.out.println(playerBoard.guessBoard[column - 1][row - 1]);
            System.out.println("Debuggies");
            System.out.println(value);
        }
        
	}
	
	
	/**
	*	Main loop that creates the user ships. Will run as long as the specified amount of ships has not been met
	*	@param shipArray - ArrayList<Ship> that contains all of the ships created
	*			playerBoard - Borad object which signifies the current board that is being set up
	*			shipCount - Int that specifies the max amount of ships created per player
	*
	**/
	public static void playerInputShips (ArrayList<Ship> shipArray, Board playerBoard, int shipCount) {
		int maxShips = shipCount;	//max number of ships for each board

		for (int numOfShips = 1; numOfShips <= maxShips ; numOfShips++) {
			//creates the ship object first with 0 values...will be set in placeShips.
			shipArray.add(new Ship('n', 0, 0, 0));
			// Place the ships into the grid, this is important step because all of the orientation, values of row and column are still saved
			GameConfig.setupInput(shipArray.get(numOfShips-1), playerBoard);
			// return player 1 board
			playerBoard.returnBoard(1);
            System.out.println("\n" + (maxShips-numOfShips) + " more ships to place");
            // At this point, the loop will restart, clearing the placeShips variables to 0.
		}
	}
	/**
	*	Checks whether the inputs all meet project criteria, organized in a manner that we may use the data at a later time
	*	@param board - Board object that represents the current board
	*			length, column, row - Int values that represent the properties of the given ships
	*			char orientation - char that represents orientation of given ship
	*
	**/
	//validates ship properties are reasonable depending on game rules
	public static void validateShipProperties(Board board, int length, char orientation, int column, int row) {
		int changingCoord = 'n';
		if (orientation == 'h'){
			changingCoord = column;
		}else if (orientation == 'v') {
			changingCoord = row;
		}
		int maxCoord = changingCoord + (length-1); //right or top most coordinate of the ship
		//Check if ship doesn't go overboard.
		if (maxCoord > board.getBoardSize()) {
			throw new IllegalArgumentException("The ship doesn't fit on the board");
			
	
		}
		//check if all coordinates it occupies doesn't contain another ship
		if (orientation == 'h'){
			for (int x = changingCoord; x <= maxCoord; x++) {	
				if(board.gameBoard[row-1][x-1] != BoardValue.EMPTY){
					throw new IllegalArgumentException("The area contains another ship");
				}
				
			}
			
			
		} else if (orientation == 'v') {
			for (int x = changingCoord; x <= maxCoord; x++) {	

				if(board.gameBoard[x-1][column-1] != BoardValue.EMPTY){
					throw new IllegalArgumentException("The area contains another ship");
				}
			}
		}
		
		
		

		if (length > Board.getMaxShipSize() || length < Board.getMinShipSize()) {  //check if ship is the supported size
			throw new IllegalArgumentException("Ship size is not supported");
		}
		if (orientation != 'h' && orientation != 'v') { //Only takes choices between horizontal or vertical.
			throw new IllegalArgumentException("\nPlease indicate h or v");	
		}
	}
}

