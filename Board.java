/**
* created January 30, 2018
* @author Betty Zhang
* Blueprint for ship objects
*/
import java.util.Scanner;

class Board{
	// Initial values of boards and ships
    // Remove duplicates of these, turn these into privates, create getter methods for these
	private static int boardSize = 5;	
	public int [][] gameBoard = new int [boardSize][boardSize];
	public int [][] guessBoard = new int [boardSize][boardSize];
	private static int maxShipSize = 5;
	private static int minShipSize = 2;
    private static boolean guessing = false;

	
    /**
    *	Gets board size 
    *	@return boardSize - size of board
    */
    public static int getBoardSize() {
        return boardSize;
    }

    /**
    *	Sets board size 
    *	@param size - size of board
    */
    public static void setBoardSize(int size) {
        boardSize = size;
    }

    /**
    *	Gets minimum ship size 
    *	@return minShipSize - minimum size of ship  
    */
    public static int getMinShipSize() {
        return minShipSize;
    }


    /**
    *	Gets maximum ship size 
    *	@return maxShipSize - maximum size of ship  
    */
    public static int getMaxShipSize() {
        return maxShipSize;
    }
    
    // Constructor for our board
	public void Board(){
	}
    
	// https://www.mkyong.com/java/java-enum-example/
	/* 
	*	Enum types for hit/miss/ship values on board
	*/
	public enum Definitions {
		MISS {
				void printLabel () {
		            System.out.print("\t" + "*");
				}
		},
		
		DEFAULT {
				void printLabel () {
		            System.out.print("\t" + "~");
				}

		},
		
		HIT {
				void printLabel () {
		            System.out.print("\t" + "X");
				}
		},
		
		SHIP {
				void printLabel () {
		            System.out.print("\t" + "S");
				}		
		};
		
		static void checkValue(int boardValue) {
			if (boardValue == -1) {
				MISS.printLabel();
			} else if (boardValue == 0) {
				DEFAULT.printLabel();
			} else if (boardValue == 1) {
				HIT.printLabel();
			} else if (boardValue == 5) {
				if (guessing != true) {
					SHIP.printLabel();
				} else {
					DEFAULT.printLabel();
				}
			}
		};
		
		abstract void printLabel();
	}
	
	
	// I changed the way the board is formatted
	// -1 = Miss, 	    denoted by * 
	// 0 = Default, 	denoted by ~
	// 1 = Hit, 		denoted by X
	// 5 - our ship 	denoted by S
	// We can add more if we like - Brandon



    /**
    *	Prints out the board 
    *	@param boardType - type of board, 1 if for gameboard, 2 is for guessing 
    */
	public void returnBoard(int boardType) {
		int[][] board = null;

        // our definitions
//        char hidden = '~';
//        char miss = '*';
//        char hit = 'X';
//        char ship = 'S';    
//        char downed = 'Z';  

        // specify if this board is for game, or guessing
        if (boardType == 1) {
            board = this.gameBoard;
            guessing = false;
        } else if (boardType == 2) {
            board = this.guessBoard;
            guessing = true;
        }

        for (int x = 0; x < boardSize; x++) {
        	// Will print the x axis
        	System.out.print(("\t" + (x + 1)));
        }
        // Print a blank line for formatting purposes
        System.out.println();

        for (int row = 0 ; row < boardSize ; row++ ) {
        	// Print the y axis - will probably change to letters
        	// Convert from numerical to char

            char rowName = (char)(row + 65);
            System.out.print(rowName);



            // For each column, check if any of the values match the following
            // They're spaced out for now so we can edit them with ease
            for (int column = 0 ; column < boardSize ; column++ ) {

            	
            		Definitions.checkValue(board[row][column]);
//                if (board[row][column] == 0) {
//                    System.out.print("\t" + hidden);
//                } else if (board[row][column] == -1) {
//                    System.out.print("\t" + miss);  //if we want the players to see where the enemy missed in their gameBoard
//                } else if (board[row][column] == 1) {   // can change to miss ^^^
//                    System.out.print("\t" + hit);
//                }
//                if (guessing != true) {
//                    if (board[row][column] == 5) {
//                        System.out.print("\t" + ship);
//                    }
//                } else {
//                    if (board[row][column] == 5) {
//                        System.out.print("\t" + hidden);
//                    }
//                }
//              
            }
            // Another blank space
            System.out.println();
        }
	}
	
		
	//addShip given all properties of the ship
	//just make sure for horizontal indicate the left most coordinates
	//and for vertical indicate the top most coordinate


    /**
    *	Add ship to the board 
    *	@param orientation - whether ship is horizontal or vertical   
    *		   lengh - how long the ship is 
    *		   column - which column in the board it will be in
    *		   row - which row in the board it will be in 
    */
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
	

}

				
