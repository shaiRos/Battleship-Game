public class Test{
	private static int difficulty = 5;
	
	public static void main (String [] args){
		
		Board.setBoardSize(difficulty);
		int [] ships = Board.generateShipsToAdd();

		HumanPlayer user1 = new HumanPlayer("user1");
		ComputerPlayer user2 = new ComputerPlayer("user2");
		
		for (int i = 0;i < ships.length;i++){
			user1.setShips(i,ships[i]);
		}
		
		for (int i = 0;i < ships.length;i++){
			user2.setShips(i,ships[i],difficulty);
		}
		

		
		for (int k = 0; k < 9; k++){
			user1.attack();
			/*user1.playerBoards.printShipBoard();
			user1.playerBoards.printHitBoard();	*/		
			user2.attack(difficulty);
			/*user2.playerBoards.printShipBoard();
			user2.playerBoards.printHitBoard();*/
		}

		
	}
	
	
}