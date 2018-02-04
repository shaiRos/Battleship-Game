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
	public int chooseCoordinate(String which) {
		boolean valid = true;
		int coordinate;
		do {
			if (which == "row"){
				System.out.print("\nRow Coordinate: ");
			}else if (which == "column"){
				System.out.print("\nColumn Coordinate: ");	
			}				
			Scanner Coord = new Scanner(System.in);
			coordinate = Coord.nextInt();														//EXCEPTION NEEDED
			if (coordinate > boardSize || coordinate < 0) {
				valid = false; 
				System.out.println("Invalid Row Coordinate");
			} else {
				valid = true;
			}	
		} while (valid != true);
		return coordinate;
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
		do {		
			System.out.print("\nLength of ship (2-5): ");
			Scanner Len = new Scanner(System.in);
			length = Len.nextInt();														//NEED eXCEPTION
			if (length > maxShipSize || length < minShipSize) {
				valid = false; 
				System.out.println("Invalid Ship Size");
			} else {
				valid = true;
			}	
		} while (valid != true); 		
		
		
		//orientation of ship
		do {
			System.out.print("\nOrientation of ship \n1) Horizontal \n2) Vertical\nOrientation: ");
			Scanner Orient = new Scanner(System.in);
			choice = Orient.nextInt();  												//NEED EXCEPTION FOR INPUT
			if (choice != 1 && choice != 2) {
				System.out.println("\nPlease pick choice 1 or 2");
				valid = false;
			}else {
				valid = true;
			}
		}while (valid != true);
		
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

		//check if ship can be put on the board
		switch(orientation) { 
			case 'h': {
				int maxColumn = column + (length-1); //right most coordinate of the ship
				//Check if right most coordinate is within the grid
				if (maxColumn > boardSize) {
					valid = false;
					break;
				}
				
				//Check if all coordinates it occupies doesn't contain another ship				
				for (int x = column; x <= maxColumn; x++) {	
					int value = gameBoard[row-1][x-1];
					if (value != 0) {			//CHECK BACK IF THIS ACTUALLY WORKS
						valid = false;
						break;
					}
				}break;				
			}

			case 'v': {
				int maxRow = row + (length-1); //bottom most coordinate of the ship
				//Check if right most coordinate is within the grid
				if (maxRow > boardSize) {
					valid = false;
					break;
				}
				
				//Check if all coordinates it occupies doesn't contain another ship				
				for (int x = row; x <= maxRow; x++) {	
					int value = gameBoard[x-1][column-1];
					if (value != 0) {			
						valid = false;
						break;
					}
				}break;				
			}
		}
		//actions for if ship can/can't be put in the board
		if (valid == false) {
			System.out.println("Cannot fit the ship in the indicated coordinate. Please try again");
			return false; //failed to set ship into the board
		}else {			//if all is good, update gameBoard.
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
			
			return true; //Success on setting the ships into the board
		}
			
				
			
	}
	
}

				
