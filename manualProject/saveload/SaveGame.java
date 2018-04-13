package saveload;

import java.lang.Object;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.*;
import board.Board;
import board.BoardValue;
import gui.Settings;
import board.Ship;
import gui.AttackPhase;
import players.Player;
import players.HumanPlayer;
import players.ComputerPlayer;



public class SaveGame{
	
	public Player p1 = null;
	public Player p2 = null;
	public static String mode = "";


	public static void main(String[] args){
		
		saveBoard();
	}
	
	public static void saveProgress(Board P1Board, Board P2Board, String gamemode){
		String boardFile = "boards.txt";
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

	public static void saveBoard(){
		String boardFile = "boards.txt";

		try{
			  // Assume default encoding.
            FileWriter fileWriter = new FileWriter(boardFile);

            // Always wrap FileWriter in BufferedWriter.
            PrintWriter writer = new PrintWriter(new BufferedWriter(fileWriter));

          
   			writer.println("Board Size: " + Board.getBoardSize());


            writer.println("Current Game Mode: " + mode);

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


	
	








}