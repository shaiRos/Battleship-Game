import java.util.Scanner;
import java.util.ArrayList;

public class Game{

    public static void main(String[] args) {
        int boardSize = 10;
        Board gameMap = new Board();
		int x = gameMap.gameBoard[0][0];
		int maxShips = 4;	//max number of ships for each board



		// Arraylist for the ships, all of our ships are stored in this list
		ArrayList<Ship> shipArray = new ArrayList<Ship>();
		
		
		for (int numOfShips = 1; numOfShips <= maxShips ; numOfShips++) {

			// Place the ships into the grid, this is important step because all of the orientation, values of row and column are still saved
			gameMap.placeShips();


			// Rip the values of the orientation and stuff from the Board variables
			char orientation = gameMap.orientation;
			int length = gameMap.length;
			int row = gameMap.row;
			int column = gameMap.column;


			// We want to add a ship using this information we took
			shipArray.add(new Ship(orientation, length, row, column));

            System.out.println("\n" + (maxShips-numOfShips) + " more ships to place");


            // At this point, the loop will restart, clearing the placeShips variables to 0.
		}



        gameMap.returnBoard();


        // We can access the ships using this format
        shipArray.get(2).getLength();

	
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
		//keyboard.close();
		return number;
		
	}


}