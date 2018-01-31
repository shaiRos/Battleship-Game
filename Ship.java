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
		
	}
	
	
	
	
}