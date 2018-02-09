import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Game{


	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  

	// This will pause program execution - use what would be a reasonable
	// delay for the user to read the console
	public static void sleepThread(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public static void setupBoard(Board player1Board, Board player2Board) {

		int maxShips = 2;	//max number of ships for each board

		System.out.println("Player 1 setup phase: ");
		ArrayList<Ship> shipArray1 = new ArrayList<Ship>();
			player1Board.returnBoard(1);

		for (int numOfShips = 1; numOfShips <= maxShips ; numOfShips++) {
			//creates the ship object first with 0 values...will be set in placeShips.
			shipArray1.add(new Ship('n', 0, 0, 0));
			// Place the ships into the grid, this is important step because all of the orientation, values of row and column are still saved
			GameConfig.setupInput(shipArray1.get(numOfShips-1), player1Board);
			// return player 1 board
			player1Board.returnBoard(1);
            System.out.println("\n" + (maxShips-numOfShips) + " more ships to place");
            // At this point, the loop will restart, clearing the placeShips variables to 0.
		}

		System.out.println("Player 1 game board successfully set. Player 2 standby...");

		sleepThread(2500);
		clearScreen();



		System.out.println("Player 2 setup phase: ");
		ArrayList<Ship> shipArray2 = new ArrayList<Ship>();
		player2Board.returnBoard(1);
		for (int numOfShips = 1; numOfShips <= maxShips ; numOfShips++) {
			//creates the ship object first with 0 values...will be set in placeShips.
			shipArray2.add(new Ship('n', 0, 0, 0));
			// Place the ships into the grid, this is important step because all of the orientation, values of row and column are still saved
			GameConfig.setupInput(shipArray2.get(numOfShips-1), player2Board);
			// return player 1 board
			player2Board.returnBoard(1);
            System.out.println("\n" + (maxShips-numOfShips) + " more ships to place");
            // At this point, the loop will restart, clearing the placeShips variables to 0.
		}

		System.out.println("Player 2 game board successfully set.");

		sleepThread(2500);
		clearScreen();

		System.out.println("DEBUG\n\n\nPLAYER 1 BOARD\n");
		player1Board.returnBoard(1);
		System.out.println("DEBUG\n\n\nPLAYER 2 BOARD\n");
		player2Board.returnBoard(2);

	}


	// was thinking of moving stuff into here once it was working, but it doesnt

	public static void sendAttack(int xCor, int yCor) {

	}

	public static boolean winCondition(Board board) {
        int shipCounter = 0;
        for (int x = 0; x < board.getBoardSize(); x++) {
            for (int y = 0; y < board.getBoardSize(); y++) {
                if (board.gameBoard[x][y] == '5') {
                    shipCounter++;
                }
            }
        }

        if (shipCounter == 0) {
            return true;
        }
        return false;

    }
   public static void main(String[] args) {
   		// create boards for both the players
        Board player1Board = new Board();
        Board player2Board = new Board();

        // populate boards with battleships
		setupBoard(player1Board, player2Board);

		// Create a new human that can access their boards
		HumanPlayer player1 = new HumanPlayer(player1Board);
		HumanPlayer player2 = new HumanPlayer(player2Board);
		
		
		
		boolean winCondition = false;

		int boardSize = 8;
		do {

			player1Board.guessBoard = player2Board.gameBoard;
			player2Board.guessBoard = player1Board.gameBoard;

			clearScreen();
			System.out.println("Player 1 turn starting....");
			player1.playerTurn();
			// if (winCondition(player2Board) == true) {
			// 	System.out.println("Player 1 has won!");
			// 	sleepThread(5000);
			// 	System.exit(0);
			// }
			sleepThread(2500);
			
			//check win conditions for every turn
			
			clearScreen();
			System.out.println("Player 2 turn starting....");
			player2.playerTurn();
			// if (winCondition(player1Board) == true) {
			// 	System.out.println("Player 2 has won!");
			// 	sleepThread(5000);
			// 	System.exit(0);
			// }
			sleepThread(2500);
			
			//check win conditions maybe make this an exception. throws an exception if winning conditions are met, catches condition and exits loop.
			
		} while (winCondition != true);


        // We can access the ships using this format
        // shipArray.get(0).getLength();
		// shipArray.get(1).getLength();
	
    }
}
	