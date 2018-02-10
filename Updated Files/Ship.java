/**
* created January 30, 2018
* @author Betty Zhang
* Blueprint for ship objects
*/

class Ship{
	private int length = 0;
	private char orientation = 0;
	private int column = 0;
	private int row = 0;

	// constructor for ship
	public Ship(char orient,int len, int col, int ro){
		orientation = orient;
		length = len;
		column = col;
		row = ro;
		// We should store the points that the ship takes up, so we can check exactly which
		// parts of the ship have and haven't been hit. Makes checking if it's sunket alot easier too.
		int[] shipPoints = new int [len];
	}
	


	public void setShipValues(char orient, int len, int col, int ro){
		orientation = orient;
		length = len;
		column = col;
		row = ro;	
	}

	
}