import java.lang.Math.*;

public class Board{
	private static int boardSize;
	private static int numOfShips;
	private static int maxShipSize = 7;
	private static int minShipSize = 2;
	private Ship[] shipArray;
	private int [][] shipBoard;
	private boolean [][] hitBoard;
	
	public Board(){
		createEmptyBoard();
		
	}
	
	public static void setBoardSize(int sz){
		//@hello set checks for invalid sizes
		boardSize = sz;
		
		// the number of ships is half the size(i.e. a 10x10 board has 5 ships)
		numOfShips = (int)(sz/2);
		
	}
	
	public static int getBoardSize(){
		return boardSize;
	}
	
		
	private void createEmptyBoard(){
		shipBoard = new int [boardSize][boardSize];
		hitBoard = new boolean [boardSize][boardSize];
		shipArray = new Ship[numOfShips];
		
	}
	
	public static int getNumOfShips(){
		return numOfShips;
	}
	
	
	/**
	* Determine the number of ships and sizes base on the size of the boardSize
	* @return shipsToAdd an array of ship sizes to be placed on the board
	*/
	public static int[] generateShipsToAdd(){
		//largest ship size is either the same length as the number of ship or the preset maximum
		int shipSize = Math.min(numOfShips, maxShipSize);
		int [] shipsToAdd = new int[numOfShips];
		
		for(int i = 0; i < numOfShips; i++){	
			if(shipSize > 3){
				//only allow one of each ship with size larger than 3
				shipsToAdd[i] = shipSize;
				shipSize--;
			}else if(i < (numOfShips - 2)){
				//rest of the ships are size 3 except for the last 2
				shipsToAdd[i] = 3;
			}else{ 
				//last two ships are size 2 (minimum ship size)
				shipsToAdd[i] = minShipSize;
			}
	
		}
		
		return shipsToAdd;
		
	}
	
	public void setShipArraySize (int sz){
		shipArray = new Ship[getNumOfShips()];

	}
	public void setShipArray(int ID, int length, char orientation, int row, int col){
		//addShipToBoard1(orientation, length, col, row);
		shipArray[ID] = new Ship(ID, length, orientation, row, col);
		int [][] addCoordinates = shipArray[ID].getShipCoordinates();
		for (int i = 0; i <addCoordinates.length; i++){
			int r = addCoordinates[i][1];
			int c = addCoordinates[i][0];
			shipBoard[r][c] = ID+1;
		}
		
	}
	
	/*@remove temporary method for printing elements of the ship array 
	*/
	public void getShipArray(){
		for (int i = 0; i < shipArray.length; i++){
			System.out.println(shipArray[i]);
			//shipArray[i].printCoordinates();
		}
	}
	
	/*@remove temporary method for printing coordinates of each ships (row, colmn (starting at 0))
	*/
	public void printCoordinates(){
		for (int i = 0; i < shipArray.length; i++){
			
			shipArray[i].printCoordinates();
		}
	}
	
	/*
	* @remove tempary method to print ship board
	*/
	public void printShipBoard(){
		for (int i = 0; i < shipBoard.length; i++){
			for (int j =0; j < shipBoard[i].length; j++){
				System.out.print(shipBoard[i][j]);
			}
			System.out.println(" ");
		}
	}
	
	/*check if ship can be put on the board. if 'h' changingCoord = column if 'v' changingCoord = row
	*/
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
				if (shipBoard[row-1][x-1] != 0) {		
					throw new IllegalArgumentException("The area contains another ship");
				}
			}
		}else if (orientation == 'v') {
			for (int x = changingCoord; x <= maxCoord; x++) {	
				if (shipBoard[x-1][column-1] != 0) {			
					throw new IllegalArgumentException("The area contains another ship");
				}
			}
		}
	}
	
	
	public void makeHit(int row, int col){
		if (hitBoard[col-1][row-1] != true){
			hitBoard[col-1][row-1] = true;
		}else{
			throw new IllegalArgumentException("Area has already been hit");
		}
			
	}
	/**
	* @remove temp to print hitboard
	*/
	public void printHitBoard(){
		for (int i = 0; i < hitBoard.length; i++){
			for (int j =0; j < hitBoard[i].length; j++){
				System.out.print(hitBoard[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
	//@remove if no longer used
	//addShip given all properties of the ship
	//just make sure for horizontal indicate the left most coordinates
	//and for vertical indicate the top most coordinate
	public void addShipToBoard1(char orientation, int length, int column, int row){
		switch(orientation) {
			case 'h': {
				int maxColumn = column + (length-1); //right most coordinate of the ship					
				for (int x = column; x <= maxColumn; x++) {	//changing the values of the coordinates it occupies.
					shipBoard[row-1][x-1] = 5;
				}break;
			}
			case 'v': {
				int maxRow = row + (length-1); //bottom most coordinate of the ship	
				for (int x = row; x <= maxRow; x++) {	//change values
						shipBoard[x-1][column-1] = 5;
				}break;
			}
		}
	}	
	
	
	
	
}
