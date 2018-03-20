/**
* created January 30, 2018
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
* Blueprint for ship objects
*/

class Ship{
	private int shipID;
	private int length = 0;
	private char orientation = 0;
	private int column = 0;
	private int row = 0;
	private boolean [] isHit;
	private boolean isSunken = false;
	private int [][] shipCoordinates;
	
	

	/**
	*	Main constructor of the Ship
	*	@param int len, col, ro - Properties of the ship
	*			char orient - Properties of the ship
	*
	**/
	public Ship(char orient,int len, int col, int ro){
		orientation = orient;
		length = len;
		column = col;
		row = ro;
		// We should store the points that the ship takes up, so we can check exactly which
		// parts of the ship have and haven't been hit. Makes checking if it's sunken a lot easier too.
		int[] shipPoints = new int [len];
	}
	
	public Ship(int ID, int len, char orient, int ro, int col){
		shipID = ID;
		length = len;
		orientation = orient;
		column = col;
		row = ro;
		shipCoordinates = new int[len][2];
		isHit = new boolean [len];
		setShipPositions();
	}
	
	/**
	* determine the coordinates of a ship and store it in an aray
	* 1st dimension is each coordinate, 0 of second dimension is 
	* row number, 1 of second dimension is column number
	*/
	private void setShipPositions(){
		if(orientation == 'h') {
			for(int x = 0; x<length;x++){
				shipCoordinates[x][0] = row-1;
				shipCoordinates[x][1] = column-1+x;
			}
			
		}else if (orientation == 'v'){
			for(int x = 0; x<length;x++){
				shipCoordinates[x][0] = row-1+x;
				shipCoordinates[x][1] = column-1;
			}
		}
	
	}
	
	/*
	* take in the coordinate being attacked and update boolean
	* board if it had been hit
	*/
	public boolean takeHit(int rowAttacked, int colAttacked){
		for (int i = 0; i < shipCoordinates.length; i++){
			if(shipCoordinates[i][0] == rowAttacked && shipCoordinates[i][1] == colAttacked){
				isHit[i] = true;
				printHitBoard();
				return true;
			}
		}
		return false;
		
	}
	
	/*
	* check if a ship is sunken
	*/
	public boolean checkShipIsSunken() {
		if(this.isSunken != true) {
			for (int j = 0; j< isHit.length; j++) {
				if(this.isHit[j] == false) {
					return false;
				}
			}
		this.isSunken = true;
		}
		return this.isSunken;
	}
	
	

	/**
	*	Mass setter method that prepares the ship values
	*	@param int len, col, ro - Properties of the ship
	*			char orient - Properties of the ship
	**/
	public void setShipValues(char orient, int len, int col, int ro){
		orientation = orient;
		length = len;
		column = col;
		row = ro;	
	}
	
	
	/**
	*	getters of Ship properties
	*	@return int column, row, length - properties of the ship
	*			char orientation - properties of the ship
	**/
	public int getColumn() {
		return this.column;
	}
	
	public int getRow() {
		return this.row; 
	}
	
	public char getOrientation() {
		return this.orientation;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int[][] getShipCoordinates(){
		return shipCoordinates;
	}


	/**
	* temporary methods for checking purpose
	**/
	
	public String toString(){
		String info = shipID + "," + orientation + "," + length + "," + row + "," + column;
		return info;
	}
	
	public void printCoordinates(){
		for(int x = 0; x<shipCoordinates.length;x++){
			for (int y = 0; y < shipCoordinates[x].length; y ++){
				System.out.print(shipCoordinates[x][y]);
			}
			System.out.println(",");
		}
	}
	
	public void printHitBoard(){
		for (int i = 0; i < isHit.length; i++){
			System.out.println(isHit[i]);
		}
	}
	
}