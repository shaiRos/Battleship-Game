// make grid look pretty like matrix
import java.util.StringJoiner;
/**
* created January 30, 2018
* @author Betty Zhang
* Blueprint for ship objects
*/
import java.util.Scanner;

class Board{
	int boardSize = 8;	
	int [][] gameBoard = new int [boardSize][boardSize];
	int [][] guessBoard = new int [boardSize][boardSize];
	int maxShipSize = 5;
	int minShipSize = 2;
	
	public void Board(){
	}

	// https://stackoverflow.com/questions/34846566/how-to-print-2d-arrays-to-look-like-a-grid-matrix
	public void returnBoard() {
		for (int[] row : gameBoard) {
            StringJoiner split = new StringJoiner(" | ");
			for (int column : row) {
				split.add(String.format("%01d", column));
			}
			System.out.println(split.toString());
		}
	}
	
	//method for choosing row or column indicate "row" or "column" in params.
	public int chooseCoordinate(String which) {
		boolean valid = true;
		int coordinate;
		do {
			if (which == "row"){					//ask for row/column
				System.out.print("\nRow Coordinate: ");
			}else if (which == "column"){
				System.out.print("\nColumn Coordinate: ");	
			}				
			Scanner Coord = new Scanner(System.in);
			coordinate = Coord.nextInt();		//EXCEPTION NEEDED NUMBER ONLY
			if (coordinate > boardSize || coordinate < 0) { //check if coordinates are within the board
				valid = false; 
				System.out.println("Invalid Row Coordinate");
			} else {
				valid = true;
			}	
		} while (valid != true);
		return coordinate;
	}
	
	//method for asking "length" or "orientation"
	public int shipProperties(String properties) {
		int number;
		boolean valid = true;
		do {
			if (properties == "length"){		//ask for input for length/orientation
				System.out.print("\nLength of ship (2-5): ");
			}else if (properties == "orientation"){
				System.out.print("\nOrientation of ship \n1) Horizontal \n2) Vertical\nOrientation: ");	
			}
			Scanner keyboard = new Scanner(System.in);	
			number = keyboard.nextInt();	//EXCEPTION MAKE SURE IT'S A NUMBER
			if (properties == "length"){	//different conditions for both
				if (number > maxShipSize || number < minShipSize) {  //check if ship is the supported size
					valid = false; 
					System.out.println("Invalid Ship Size");
				}else {
					valid = true;
				}	
			}else if (properties == "orientation"){	
				if (number != 1 && number != 2) {		//check if input is one of the two choices (1) or (2)
					System.out.println("\nPlease pick choice 1 or 2");
					valid = false;	
				}else {
					valid = true;
				}
			}
		}while (valid != true);
		return number;
	}
	
	//check if ship can be put on the board. if 'h' changingCoord = column if 'v' changingCoord = row
	public boolean shipPlaced(char orientation,int length,int column, int row){
		boolean valid = true;
		int changingCoord = 'n';
		if (orientation == 'h'){
			changingCoord = column;
		}else if (orientation == 'v') {
			changingCoord = row;
		}
		int maxCoord = changingCoord + (length-1); //right most coordinate of the ship
		//Check if ship doesn't go overboard.
		if (maxCoord > boardSize) {
			valid = false;
		}
		//check if all coordinates it occupies doesn't contain another ship
		if (orientation == 'h'){
			for (int x = changingCoord; x <= maxCoord; x++) {	
				int value = gameBoard[row-1][x-1];
				if (value != 0) {		
					valid = false;
				}
			}
		}else if (orientation == 'v') {
			for (int x = changingCoord; x <= maxCoord; x++) {	
				int value = gameBoard[x-1][column-1];
				if (value != 0) {			
					valid = false;
				}
			}
		}return valid;
	}		
				
	//addShip given all properties of the ship
	//just make sure for horizontal indicate the left most coordinates
	//and for vertical indicate the top most coordinate
	public void addShip(char orientation, int length, int column, int row){
		switch(orientation) {
			case 'h': {
				int maxColumn = column + (length-1); //right most coordinate of the ship					
				for (int x = column; x <= maxColumn; x++) {	//changing the values of the coordinates it occupies.
					gameBoard[row-1][x-1] = 5;
				}break;
			}
			case 'v': {
				int maxRow = row + (length-1); //bottom most coordinate of the ship	
				for (int x = row; x <= maxRow; x++) {	//change values
						gameBoard[x-1][column-1] = 5;
				}break;
			}
		}
	}		
	
	
	
	public boolean placeShips(){
		/*comment horizontal or vertical only
		if picked horizontal indicate the left most coordinate of where you want to put it
		if vertical, pick the top most coordinate of where you want to put it.
		*/
		boolean valid = true;
		int length;
		int column;
		int row;
		char orientation = 'n';
		int choice;
		
		returnBoard();
		length = shipProperties("length");
		choice = shipProperties("orientation"); //choose from horizontal or vertical
		switch(choice) {
			case 1: {
				orientation = 'h';
				break;
			}
			case 2: {
				orientation = 'v';
				break;
			}
		}
		row = chooseCoordinate("row");		//used choose Coordinate method.
		column = chooseCoordinate("column");
		//check if ship can be put on the board	using method	
		valid = shipPlaced(orientation,length,column,row);
		
		//actions for if ship can/can't be put in the board
		if (valid == false) {
			System.out.println("Cannot fit the ship in the indicated coordinate. Please try again");
			return false; //failed to set ship into the board
		}else {			//if all is good, update gameBoard.
			addShip(orientation,length,column,row);
			return true; //Success on setting the ships into the board
		}	
	
	}		
	
}

				
