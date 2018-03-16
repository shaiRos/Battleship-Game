/**
* created January 30, 2018
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
* Blueprint for ship objects
*/

class Ship{
	private int length = 0;
	private char orientation = 0;
	private int column = 0;
	private int row = 0;
	
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

	
}