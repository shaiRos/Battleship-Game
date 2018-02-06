import java.util.Scanner;

public class Game{

    public static void main(String[] args) {
        int boardSize = 10;
        Board gameMap = new Board();
		int x = gameMap.gameBoard[0][0];
		int maxShips = 4;	//max number of ships for each board
		
		
		for (int numOfShips = 1; numOfShips <= maxShips ; numOfShips++) {

			boolean setUpShip = false;

			while (setUpShip != true) {
				setUpShip = gameMap.placeShips();
			}

            System.out.println("\n" + (maxShips-numOfShips) + " more ships to place");

		}

        gameMap.returnBoard();
	
    }
	
	
	
	public static int askForInput(String askThis) {
		switch(askThis) {
			case "row": {
				System.out.print("\nRow Coordinate: ");
				break;
			}
			case "column": {
				System.out.print("\nColumn Coordinate: ");	
				break;
			}
			case "length": {
				System.out.print("\nLength of ship (2-5): ");
				break;
			}
			case "orientation": {
				System.out.print("\nOrientation of ship \n1) Horizontal \n2) Vertical\nOrientation: ");	
				break;
			}
		}Scanner keyboard = new Scanner(System.in);	
		int number = keyboard.nextInt();	//EXCEPTION MAKE SURE IT'S A NUMBER
		return number;
		
	}


}