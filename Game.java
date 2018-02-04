public class Game{

    public static void main(String[] args) {
        int boardSize = 10;
        Board gameMap = new Board();
		int x = gameMap.gameBoard[0][0];
		int maxShips = 4;	//max number of ships for each board
		
		
		for (int numOfShips = 1; numOfShips <= maxShips ; numOfShips++) {
			boolean setUpShip = false;
			while (setUpShip != true){
				setUpShip = gameMap.placeShips();
			}System.out.println("\n" + (maxShips-numOfShips) + " more ships to place");
		}gameMap.returnBoard();
		

        
    }


}