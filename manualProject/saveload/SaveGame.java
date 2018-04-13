package saveload;

import java.lang.Object;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.*;
import board.*;
import gui.Settings;
import gui.AttackPhase;
import players.*;

/**
* Saves the current progress of the game in a text file file
* implemented in the GUI version of the game
*/
public class SaveGame{
	
	public Player p1 = null;
	public Player p2 = null;
	public static String mode = "";

	/**
	* 
	* @param P1Board the board object for user 1
	* @param P2Board
	* @param gamemode a string representing whether the game was PVP or PVComputer
	*/
	public static void saveProgress(Board P1Board, Board P2Board, String gamemode){
		//saved game file is in pacakge/folder "saveload"
		String boardFile = "saveload/savedGame.txt";
		mode = gamemode;

		try{
			  // Assume default encoding.
            FileWriter fileWriter = new FileWriter(boardFile);

            // Always wrap FileWriter in BufferedWriter.
            PrintWriter writer = new PrintWriter(new BufferedWriter(fileWriter));

          	
   			writer.println("Board Size:");
   			writer.println(Board.getBoardSize());

   			writer.println("Number of Ships:");
   			writer.println(Board.getNumOfShips());

            writer.println("Current Game Mode:");
           	writer.println(mode);

            writer.println("Current Turn:");
            writer.println(AttackPhase.currentPlayer);



            	writer.println("PLAYER1BOARD:");
             for (int row = 0; row < Board.getBoardSize(); row++){
             	for (int column = 0; column < Board.getBoardSize(); column++){
             		if (column == (Board.getBoardSize() - 1)){
             			writer.println((P1Board.gameBoard[row][column]) + " ");
             		} else {
             			writer.append((P1Board.gameBoard[row][column]) + " ");
             		}
             	}
             }            
				//save the ship placements of current ship board
				writer.println("PLAYER1SHIP:");
				Ship [] P1Ships = P1Board.getShipArray();   
            for (int i = 0; i < P1Ships.length; i++){
            	writer.println(P1Ships[i].toString());
            }
				          
            	writer.println("PLAYER2BOARD:");
             for (int row = 0; row < Board.getBoardSize(); row++){
             	for (int column = 0; column < Board.getBoardSize(); column++){
             		if (column == (Board.getBoardSize() - 1)){
             			writer.println((P2Board.gameBoard[row][column]) + " ");
             		} else {
             			writer.append((P2Board.gameBoard[row][column]) + " ");
             		}
             	}
             }

             	writer.println("PLAYER2SHIP:");
     			Ship [] P2Ships = P2Board.getShipArray();   
            for (int i = 0; i < P2Ships.length; i++){
            	writer.println(P2Ships[i].toString());
            }

           
  
            // Always close files.
            writer.flush();
            writer.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + boardFile + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}

}