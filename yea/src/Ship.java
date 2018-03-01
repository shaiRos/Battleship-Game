
public class Ship{
	private int shipID;
	private int length;
	private int orientation;
	private int yCoord;
	private int xCoord;
	private boolean isSunken = false;
	private int [][] shipCoordinates;
	
	public Ship(int ID, int orient, int len, int row, int col){
		shipID = ID;
		length = len;
		orientation = orient;
		xCoord = col-1;
		yCoord = row-1;
		shipCoordinates = new int[len][2];
		setShipPositions();
	}
	
	private void setShipPositions(){
		if(orientation == 0) {
			for(int x = 0; x<length;x++){
				shipCoordinates[x][0] = xCoord+x;
				shipCoordinates[x][1] = yCoord;

			}
			
		}else if (orientation == 1){
			for(int x = 0; x<length;x++){
				shipCoordinates[x][0] = xCoord;
				shipCoordinates[x][1] = yCoord+x;
			}
		}
	
	}
	
	public int[][] getShipCoordinates(){
		return shipCoordinates;
	}
	
	//temp
	public void printCoordinates(){
		for(int x = 0; x<shipCoordinates.length;x++){
			for (int y = 0; y < shipCoordinates[x].length; y ++){
				System.out.print(shipCoordinates[x][y]);
			}
			System.out.println(",");
		}
	}
	
	public String toString(){
		String info = shipID + "," + orientation + "," + length + "," + yCoord + "," + xCoord;
		return info;
	}
	
	public boolean checkShipisSunken(int [] data) {
		return isSunken;
	
	}
	
}