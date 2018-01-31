/**
* created January 30, 2018
* @author Betty Zhang
* Blueprint for ship objects
*/

class Ship{
	int id;
	int length;
	boolean hit = false;
	boolean sunken = false;
	
	public Ship(int num, int len){
		id = num;
		length = len;
	}
	
	public void setCoordinates(int x, int y, char orientation){
		int [][] coordinates;
		coordinates = new int [length][2];
		
		/*code to determine coordinates of the ship (you can decide 
		*how you want to do it and change the parameters
		*/
		
	}
	
	
	
	
}