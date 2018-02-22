import java.util.Scanner;


public class GameConfig {
	
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
				//take the input that was converted into String and separate the info
				String setupInfo[] = setup.split(" ");
				//store info to designated variables and convert string to their types
				char orientation = setupInfo[0].toLowerCase().charAt(0);
				int length = Integer.parseInt(setupInfo[1]);
				char tempRow = setupInfo[2].toUpperCase().charAt(0);
				int row = (((int)(tempRow) - 65 ) + 1);
				int column = Integer.parseInt(setupInfo[3]);

				//all checks
				validateCoordinate(column,row, board.getBoardSize());	//check if coordinates chosen are wthin the board
				validateShipProperties(board,length,orientation);	//checks if ship properties meet the rules of the game
				board.shipFitsBoard(orientation,length,column,row);	//check if ship fits board depending on coordinates 
				
				// Adds ship to the grid
				board.addShip(orientation,length,column,row);
				//sets the values of the ship object
				name.setShipValues(orientation,length,row,column); 				
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
	
	//validates coordinates are reasonable depending on board size
	public static void validateCoordinate(int xCoord, int yCoord, int boardSize) {
		if ((xCoord > boardSize || xCoord < 0) || (yCoord > boardSize || yCoord < 0)) { //check if coordinates are within the board
			//throws error so it doesn't continue the procedures in the setUpInput exception. since input doesn't meet requirements
			throw new IllegalArgumentException("\ncoordinate over the board size");
		}
		
	}
	//validates ship properties are reasonable depending on game rules
	public static void validateShipProperties(Board board, int len, char orientation) {
		
		if (len > Board.getMaxShipSize() || len < Board.getMinShipSize()) {  //check if ship is the supported size
			throw new IllegalArgumentException("Ship size is not supported");
		}
		if (orientation != 'h' && orientation != 'v') { //Only takes choices between horizontal or vertical.
			throw new IllegalArgumentException("\nPlease indicate h or v");	
		}
	}
}

