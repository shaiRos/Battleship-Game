public class Player{
	protected String playerName;
	private int score=0;
	//public Board playerBoards;
	
	public Player(String newName){
		this.playerName = newName;
		//this.playerBoards = new Board();
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
/*	public int [] getShipBoard(){
		return playerBoards.generateShipsToAdd();
	}*/
/*	
	public void addShip(int ID, int length, char orientation, int col, int row){
		playerBoards.shipFitsBoard(orientation,length, col, row);
		playerBoards.setShipArray(ID, length, orientation, col, row);
		playerBoards.getShipArray();
	}
	
	public void makeAttack(int row, int col){
		playerBoards.makeHit(col,row);
	}
	
	//validates coordinates are reasonable depending on board size
	public void validateCoordinate(int xCoord, int yCoord){
		int size = Board.getBoardSize();
		if ((xCoord > size || xCoord <= 0) || (yCoord > size || yCoord <= 0)) { //check if coordinates are within the board
			//throws error so it doesn't continue the procedures in the setUpInput exception. since input doesn't meet requirements
			throw new IllegalArgumentException("\nCoordinate is not on the board");
		}
		
	}*/
	
	
}