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
		// We should store the points that the ship takes up, so we can check exactly which
		// parts of the ship have and haven't been hit. Makes checking if it's sunket alot easier too.
		int[] shipPoints = new int [len];
	}
	
	public boolean isHit(id, int x, int y) {

	}

	public boolean isSunken(id, int x, int y) {

	}

	public void setCoordinates(int x, int y, char orientation){
		int [][] coordinates;
		coordinates = new int [length][2];
		
		for(int i=0; i<length; i++){
			for(int j = 0; j < coordinates[i].length; j++){
				switch (orientation){
					case 'v':
						coordinates[i][j] = x+1;
						break;
					case 'h':
						coordinates[i][j] = y+1;
						break;
					default:
						System.out.println("Oops. Something broke. This should never happen.");
				}
			}
		}
		/*code to determine coordinates of the ship (you can decide 
		*how you want to do it and change the parameters
		*/
		
	}
	
	
	
	
}