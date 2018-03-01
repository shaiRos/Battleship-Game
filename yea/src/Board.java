

public class Board{
	private static int boardSize;
	private static int numOfShips;
	private static int maxShipSize = 7;
	private static int minShipSize = 2;
	private Ship[] shipArray;
	private int [][] shipBoard;
	private boolean [][] guessBoard;
	
	public Board(){		
	}
	
	public static void setBoardSize(int sz){
		//@hello set checks for invalid sizes
		boardSize = sz;
		
		// the number of ships is half the size(i.e. a 10x10 board has 5 ships)
		setNumOfShips((int)(sz/2));
		
	}
	public static void setNumOfShips(int num){
		numOfShips = num;
		
	}
	
	public static int getBoardSize(){
		return boardSize;
	}
	
		
	public void createEmptyBoards(){
		shipBoard = new int [boardSize][boardSize];
		guessBoard = new boolean [boardSize][boardSize];
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
	
	public void setShipArraySize (){
		shipArray = new Ship[getNumOfShips()];

	}
	
	public void setShipArray(int [] shipProperties){
		setShipArray(shipProperties[0], shipProperties[1], shipProperties[2], shipProperties[3], shipProperties[4]);
	}
	public void setShipArray(int ID, int orient, int len, int row, int col){
		shipArray[ID] = new Ship(ID, orient, len, row, col);
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
	* @remove temporary method to print ship board
	*/
	public void printShipBoard(){
		for (int i = 0; i < shipBoard.length; i++){
			for (int j =0; j < shipBoard[i].length; j++){
				System.out.print(shipBoard[i][j]);
			}
			System.out.println(" ");
		}
	}
	

	public boolean shipFitsBoard(int orientation, int length, int row, int column){
		int changingCoord = 'n';
		if (orientation == 0){
			changingCoord = column;
		}else if (orientation == 1) {
			changingCoord = row;
		}
		int maxCoord = changingCoord + (length-1); //right or top most coordinate of the ship
		//Check if ship doesn't go overboard.
		
		if (maxCoord > boardSize) {
			System.out.println("The ship doesn't fit on the board");
			return false;	
		}
		
		//check if all coordinates it occupies doesn't contain another ship
		if (orientation == 1){
			for (int x = changingCoord; x <= maxCoord; x++) {	
				if (shipBoard[row-1][x-1] != 0) {
					System.out.println("The area contains another ship");
					return false;
				}
			}
		}else if (orientation == 0) {
			for (int x = changingCoord; x <= maxCoord; x++) {	
				if (shipBoard[x-1][column-1] != 0) {
					System.out.println("The area contains another ship");
					return false;
				}
			}
		}
		return true;
	}
	
	public void makeHit(int [] position){
		int row = position[0];
		int col = position[1];
		if (guessBoard[col-1][row-1] != true){
			guessBoard[col-1][row-1] = true;
		}else{
			throw new IllegalArgumentException("Area has already been hit");
		}
			
	}
	/**
	* @remove temporary method to print hitboard
	*/
	public void printHitBoard(){
		for (int i = 0; i < guessBoard.length; i++){
			for (int j =0; j < guessBoard[i].length; j++){
				System.out.print(guessBoard[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	

}
