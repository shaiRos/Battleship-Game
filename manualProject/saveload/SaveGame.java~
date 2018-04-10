import java.lang.Object;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.*;
import board.Board;
import gui.Settings;



public class SaveGame{



	public static void main(String[] args){
		
		saveBoard();
	}
	
	

	public static void saveBoard(){
		String boardFile = "boards.txt";

		try{
			  // Assume default encoding.
            FileWriter fileWriter = new FileWriter(boardFile);

            // Always wrap FileWriter in BufferedWriter.
            PrintWriter writer = new PrintWriter(new BufferedWriter(fileWriter));

          
   			writer.print("Board Size: " + Board.getBoardSize() + "\n\n");

            writer.print("Current Game Mode: " + (Settings.getCurrentMode()) + "\n\n");
     
            /*
            *	Need to fix
            */
   		//writer.print(AttackPhase.getCurrentPlayer() + "\n\n");
    

            /*Last type of data to store due to varying size of boards 
            *but if we save player including the boards then  youre set
            *SAVE BOARD, SHIP, ARRAYS 
			*/

    //	writer.print(this.getGameBoard());
  
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



	//We are able to get the board and all from the player....... then ad valyues from array 
	//SAve  each enum between spaces 

	/*public int[][] getGameBoard(){
		int[][] boardValues = BoardGUI.getBoardValuesGUI();
		return boardValues;

	}*/








}