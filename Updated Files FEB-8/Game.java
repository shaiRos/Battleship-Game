import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Game{


	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  


	public static void setupBoard() {

        Board player1Board = new Board();
        Board player2Board = new Board();

		int maxShips = 2;	//max number of ships for each board

		System.out.println("Player 1 setup phase: ");
		ArrayList<Ship> shipArray1 = new ArrayList<Ship>();
			player1Board.returnBoard();

		for (int numOfShips = 1; numOfShips <= maxShips ; numOfShips++) {
			//creates the ship object first with 0 values...will be set in placeShips.
			shipArray1.add(new Ship('n', 0, 0, 0));
			// Place the ships into the grid, this is important step because all of the orientation, values of row and column are still saved
			GameConfig.setupInput(shipArray1.get(numOfShips-1), player1Board);
			// return player 1 board
			player1Board.returnBoard();
            System.out.println("\n" + (maxShips-numOfShips) + " more ships to place");
            // At this point, the loop will restart, clearing the placeShips variables to 0.
		}
		clearScreen();
		System.out.println("Player 1 game board successfully set. Player 2 standby...");

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Player 2 setup phase: ");
		ArrayList<Ship> shipArray2 = new ArrayList<Ship>();
		player2Board.returnBoard();
		for (int numOfShips = 1; numOfShips <= maxShips ; numOfShips++) {
			//creates the ship object first with 0 values...will be set in placeShips.
			shipArray2.add(new Ship('n', 0, 0, 0));
			// Place the ships into the grid, this is important step because all of the orientation, values of row and column are still saved
			GameConfig.setupInput(shipArray2.get(numOfShips-1), player2Board);
			// return player 1 board
			player2Board.returnBoard();
            System.out.println("\n" + (maxShips-numOfShips) + " more ships to place");
            // At this point, the loop will restart, clearing the placeShips variables to 0.
		}

		clearScreen();

		System.out.println("Player 2 game board successfully set.");

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		System.out.println("DEBUG\n\n\nPLAYER 1 BOARD\n");
		player1Board.returnBoard();
		System.out.println("DEBUG\n\n\nPLAYER 2 BOARD\n");
		player2Board.returnBoard();

	}

   public static void main(String[] args) {

		setupBoard();

		HumanPlayer player1 = new HumanPlayer();
		HumanPlayer player2 = new HumanPlayer();
		
		// do {
		// 	clearScreen();
		// 	player1.playerTurn();
		// 	clearScreen();
		// 	player2.playerTurn();

		// } while (true);


        // We can access the ships using this format
        // shipArray.get(0).getLength();
		// shipArray.get(1).getLength();
	
    }
}
	