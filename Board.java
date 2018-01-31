// make grid look pretty like matrix
import java.util.StringJoiner;
/**
* created January 30, 2018
* @author Betty Zhang
* Blueprint for ship objects
*/

class Board{
	int [][] gameBoard;
	
	public void createBoard(int size){
		gameBoard = new int [size][size];
		
	}

	public void returnBoard() {
		for (int[] row : gameBoard) {
            StringJoiner split = new StringJoiner(" | ");
			for (int column : row) {
				split.add(String.format("%01d", column));
			}
			System.out.println(split.toString());
		}
	}
	
	public void placeShips(){
		//comment
	}
}