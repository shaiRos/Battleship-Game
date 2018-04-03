package board;

/**
 * created January 30, 2018
 * 
 * @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang Blueprint
 *         for ship objects
 */

public class Ship {
	private int shipID;
	private int length = 0;
	private char orientation = 0;
	private int column = 0;
	private int row = 0;
	private boolean[] isHit;
	private boolean isSunken = false;
	private int[][] shipCoordinates;

	/**
	 * Main constructor of the Ship, given the properties of the ship
	 * 
	 * @param ID an int that is unique to each ship, tied its index in the ship array
	 * @param orient orientation of the ship representated by 'h' or 'v'
	 * @param len number of coordinate of the ship(length)
	 * @param col smallest x value of the coordinate
	 * @param ro smallest y value of the coordinate
	 */
	public Ship(int ID, int len, char orient, int ro, int col) {
		shipID = ID;
		length = len;
		orientation = orient;
		column = col;
		row = ro;
		shipCoordinates = new int[len][2];
		isHit = new boolean[len];
		setShipPositions();
	}

	/**
	 * determine the coordinates of a ship and store it in an aray 1st dimension is
	 * each coordinate, 0 of second dimension is row number, 1 of second dimension
	 * is column number
	 */
	private void setShipPositions() {
		if (orientation == 'h') {
			for (int x = 0; x < length; x++) {
				shipCoordinates[x][0] = row - 1;
				shipCoordinates[x][1] = column - 1 + x;
			}

		} else if (orientation == 'v') {
			for (int x = 0; x < length; x++) {
				shipCoordinates[x][0] = row - 1 + x;
				shipCoordinates[x][1] = column - 1;
			}
		}

	}
	
	/**
	 * getters of Ship properties
	 * @return column ship's smallest x coordinate
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * getters of Ship properties
	 * @return row ship's smallest y coordinate
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * getters of Ship properties
	 * @return the ship's orientation represented by a char
	 */
	public char getOrientation() {
		return this.orientation;
	}

	/**
	 * getters of Ship properties
	 * @return the ship's length
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * getters of Ship properties
	 * @return shipCoordinates an 2D array of the ship's coordinate on the board
	 */
	public int[][] getShipCoordinates() {
		return shipCoordinates;
	}

	/*
	 * take in the coordinate being attacked and update boolean board if it had been
	 * hit
	 * @param rowAttacked y coordinate of the position player attacked
	 * @param colAttacked x coordinate of the position being attacked
	 * @return true if a ship has been hit
	 */
	public boolean takeHit(int rowAttacked, int colAttacked) {
		for (int i = 0; i < shipCoordinates.length; i++) {
			if (shipCoordinates[i][0] == rowAttacked && shipCoordinates[i][1] == colAttacked) {
				isHit[i] = true;
				return true;
			}
		}
		return false;

	}

	/*
	 * check if a ship is sunken
	 * @return if all of the ship's coordinate has been hit, also update the variable isSunken
	 */
	public boolean checkShipIsSunken() {
		if (this.isSunken != true) {
			for (int j = 0; j < isHit.length; j++) {
				if (this.isHit[j] == false) {
					return false;
				}
			}
			this.isSunken = true;
		}
		return this.isSunken;
	}



}