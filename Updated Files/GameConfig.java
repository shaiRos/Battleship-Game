import java.util.Scanner;


public class GameConfig {
	
	// The main code for inserting ships on the other board
	// Error checking, logic checking etc
	public static void setupInput(Ship name, Board board) {
		
		boolean formatted = false;
		while (formatted != true){
			try {
				
				System.out.print("\nIndicate (orientation length column row): ");
				Scanner Setup = new Scanner(System.in);
				String setup = Setup.nextLine();
				String setupInfo[] = setup.split(" ");
				char orientation = setupInfo[0].toLowerCase().charAt(0);
				int length = Integer.parseInt(setupInfo[1]);
				char tempColumn = setupInfo[2].toUpperCase().charAt(0);
				int column = (((int)(tempColumn) - 65 ) + 1);
				int row = Integer.parseInt(setupInfo[3]);

				//all checks
				validateCoordinate(row,column, board.getBoardSize());		//check if coordinates are within the board
				validateShipProperties(board,length,orientation);
				board.shipFitsBoard(orientation,length,row,column);
				
				// Adds ship to the grid
				board.addShip(orientation,length,row,column);
				//sets the values of the ship object
				name.setShipValues(orientation,length,row,column); 				
				
				
				formatted = true;
				
			}
			catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Wrong format");
				formatted = false;
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				formatted = false;				
			
			}
		}
	}
	
	//method for choosing row or column indicate "row" or "column" in params.
	public static void validateCoordinate(int xCoord, int yCoord, int boardSize) {
		if ((xCoord > boardSize || xCoord < 0) || (yCoord > boardSize || yCoord < 0)) { //check if coordinates are within the board
			throw new IllegalArgumentException("\ncoordinate over the board size");
		}
		
	}
	
	public static void validateShipProperties(Board board, int len, char orientation) {
		
		if (len > Board.getMaxShipSize() || len < Board.getMinShipSize()) {  //check if ship is the supported size
			throw new IllegalArgumentException("Ship size is not supported");
		}
		if (orientation != 'h' && orientation != 'v') {
			throw new IllegalArgumentException("\nPlease indicate h or v");	
		}
	}
}

