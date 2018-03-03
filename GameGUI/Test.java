public class Test {
	
	public BoardGUI guessBoard;
	public BoardGUI ownBoard;

	public Test() {
		
		int Array[][] = new int [10][10];
		Array[0][0] = 5; //WIP laying out one ship image for consecutive values of 5..
		Array[0][1] = 5; //from 2d Array
		Array[0][2] = 5; 
		//centerSlot.setPrefTileWidth(770);
		BoardGUI guessBoard = new BoardGUI(10, 770); 

		//had it take a ship object to setup from
		//this should be changed into taking a ship array instead of individual ship objects
		Ship ship1 = new Ship('h', 5, 1, 1);
		Ship ship2 = new Ship('v', 3, 3, 3);			
		guessBoard.setupBoardFromShipObjects(ship1);
		guessBoard.setupBoardFromShipObjects(ship2);		
		
		ownBoard = new BoardGUI(10, 250);			
		
	}
	
	public BoardGUI getOwnBoard() {
		return ownBoard;
	}
	
	public BoardGUI getGuessBoard() {
		return guessBoard;
	}
}