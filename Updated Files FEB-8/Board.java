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
    
	public int getShipSize(){
		return minShipSize;
	}
	// I changed the way the board is formatted
	// -1 = Default, 	denoted by ~
	// 0 = Miss, 		denoted by *
	// 1 = Hit, 		denoted by X
	// 5 - our ship 	denoted by {}
	// We can add more if we like - Brandon
	public void returnBoard() {
		        
        for (int x = 0; x < boardSize; x++) {
        	// Will print the x axis
        	System.out.print(("\t" + (x + 1)));
        }
        // Print a blank line for formatting purposes
        System.out.println();
        
        for (int row=0 ; row < boardSize ; row++ ) {
        	// Print the y axis - will probably change to letters
        	// Convert from numerical to char
            System.out.print( (row + 1) + "" );

            // For each column, check if any of the values match the following
            // They're spaced out for now so we can edit them with ease
            for (int column=0 ; column < boardSize ; column++ ) {
                if (gameBoard[row][column] == 0) {

                    System.out.print("\t" + "~");

                } else if (gameBoard[row][column] == -1) {

                    System.out.print("\t" + "*");

                } else if (gameBoard[row][column] == 1) {

                    System.out.print("\t" + "X");

                } else if (gameBoard[row][column] == 5) {

                	System.out.print("\t" + "{}"); // TEMPORARY

                }
                
            }
            // Another blank space
            System.out.println();
        }
	}
	

	
	
	//check if ship can be put on the board. if 'h' changingCoord = column if 'v' changingCoord = row
	public void shipFitsBoard(char orientation,int length,int column, int row){

		int changingCoord = 'n';
		if (orientation == 'h'){
			changingCoord = column;
		}else if (orientation == 'v') {
			changingCoord = row;
		}
		int maxCoord = changingCoord + (length-1); //right or top most coordinate of the ship
		//Check if ship doesn't go overboard.
		if (maxCoord > boardSize) {
			throw new IllegalArgumentException("The ship doesn't fit on the board");
			
	
		}
		//check if all coordinates it occupies doesn't contain another ship
		if (orientation == 'h'){
			for (int x = changingCoord; x <= maxCoord; x++) {	
				int value = gameBoard[row-1][x-1];
				if (value != 0) {		
					throw new IllegalArgumentException("The area contains another ship");
				}
			}
		}else if (orientation == 'v') {
			for (int x = changingCoord; x <= maxCoord; x++) {	
				int value = gameBoard[x-1][column-1];
				if (value != 0) {			
					throw new IllegalArgumentException("The area contains another ship");
				}
			}
		}
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
			}//SHOULD PROBABLY MAKE A SHIP OBJECT AFTER UPDATING THE BOARD. AND TRANSFER IT'S CHARACTERISTICS
		}
	}		
	

}

				
