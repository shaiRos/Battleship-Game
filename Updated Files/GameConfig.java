import java.util.Scanner;


public class GameConfig {
	
	
	public static void setupInput(Ship name, Board board) {
		
		boolean formatted = false;
		while (formatted != true){
			try {
				
				System.out.print("\nIndicate (orientation length row column): ");
				Scanner Setup = new Scanner(System.in);
				String setup = Setup.nextLine();
				String setupInfo[] = setup.split(" ");
				char orientation = setupInfo[0].toLowerCase().charAt(0);
				int length = Integer.parseInt(setupInfo[1]);
				char tempRow = setupInfo[2].toUpperCase().charAt(0);
				int row = (((int)(tempRow) - 65 ) + 1);
				int column = Integer.parseInt(setupInfo[3]);

				//all checks
				validateCoordinate(column,row, board.getBoardSize());		//check if coordinates are within the board
				validateShipProperties(length,orientation);
				board.shipFitsBoard(orientation,length,column,row);
				
				// Adds ship to the grid
				board.addShip(orientation,length,column,row);
				//sets the values of the ship object
				name.setShipValues(orientation,length,column, row); 				
				
				
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
	
	public static void validateShipProperties(int len, char orientation) {
		int maxShipSize = 5;
		int minShipSize = 2;	
		
		if (len > maxShipSize || len < minShipSize) {  //check if ship is the supported size
			throw new IllegalArgumentException("Ship size is not supported");
		}
		if (orientation != 'h' && orientation != 'v') {
			throw new IllegalArgumentException("\nPlease indicate h or v");	
		}
	}
}

