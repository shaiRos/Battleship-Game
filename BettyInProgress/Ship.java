
public class Ship{
	private int shipID;
	private int length;
	private char orientation;
	private int yCoord;
	private int xCoord;
	private boolean isSunken = false;
	private int [][] shipCoordinates;
	
	public Ship(int ID, int len, char orient, int col, int row){
		shipID = ID;
		length = len;
		orientation = orient;
		xCoord = col-1;
		yCoord = row-1;
		shipCoordinates = new int[len][2];
		//temp
		setShipPositions();
	}
	
	private void setShipPositions(){
		if(orientation == 'h') {
			for(int x = 0; x<length;x++){
				shipCoordinates[x][0] = xCoord+x;
				shipCoordinates[x][1] = yCoord;

			}
			
		}else if (orientation == 'v'){
			for(int x = 0; x<length;x++){
				shipCoordinates[x][0] = xCoord;
				shipCoordinates[x][1] = yCoord+x;
			}
		}
	
	}
	
	public int[][] getShipCoordinates(){
		return shipCoordinates;
	}
	
	public void printCoordinates(){
		for(int x = 0; x<shipCoordinates.length;x++){
			for (int y = 0; y < shipCoordinates[x].length; y ++){
				System.out.print(shipCoordinates[x][y]);
			}
			System.out.println(",");
		}
	}
	
	public String toString(){
		String info = shipID + "," + length + "," + orientation + "," + yCoord + "," + xCoord;
		return info;
	}
	
}