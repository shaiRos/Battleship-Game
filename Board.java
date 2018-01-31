// make grid look pretty like matrix
import java.util.StringJoiner;
/**
* created January 30, 2018
* @author Betty Zhang
* Blueprint for ship objects
*/

class Board{
	int [][] gameBoard;
	
	public Board(int size){
		gameBoard = new int [size][size];
	}


	// https://stackoverflow.com/questions/34846566/how-to-print-2d-arrays-to-look-like-a-grid-matrix
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