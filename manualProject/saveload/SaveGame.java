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


	public static void main(String[] args){
		
		saveBoard();
	}
	
	public static void saveProgress(Board P1Board, Board P2Board){
		String boardFile = "boards.txt";

		try{
			  // Assume default encoding.
            FileWriter fileWriter = new FileWriter(boardFile);

            // Always wrap FileWriter in BufferedWriter.
            PrintWriter writer = new PrintWriter(new BufferedWriter(fileWriter));

          
   			writer.println("Board Size: " + Board.getBoardSize());

            writer.println("Current Game Mode: " + (Settings.getCurrentMode()));
            writer.println(AttackPhase.currentPlayer);
            
				//save the ship placements of current ship board
				writer.println("PLAYER1SHIP");
				Ship [] P1Ships = P1Board.getShipArray();   
            for (int i = 0; i < P1Ships.length; i++){
            	writer.println(P1Ships[i].toString());
            }
				            
            
     
            /*
            *	Need to fix
            */
   		//writer.print(AttackPhase.getCurrentPlayer() + "\n\n);
    

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

	public static void saveBoard(){
		String boardFile = "boards.txt";

		try{
			  // Assume default encoding.
            FileWriter fileWriter = new FileWriter(boardFile);

            // Always wrap FileWriter in BufferedWriter.
            PrintWriter writer = new PrintWriter(new BufferedWriter(fileWriter));

          
   			writer.println("Board Size: " + Board.getBoardSize());


            writer.println("Current Game Mode: " + (Settings.getCurrentMode()));
     

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
	/*
	public BoardValue[][] getGameBoard(){
		BoardValue][] boardValues = BoardValue[][] 
		return boardValues;

	}
	*/ /*//when loading from txt file, MAKE the p1 GAMEBOARD and p2 GAMEBOARD
			
			new constructor on Board?...
			Board p1Board = new Board(boardSize, shipArray, gameBoard)
			Board p2Board = new Board(boardSize, shipArray, gameBoard)
			
			then call vvv that method
	
	
	
	/**
	*	Makes the player instances for the game to load from 
	*
	*	@param		p1Board - a Board instance of player 1's board. gameBoard and shipArray is set (not guessBoard)
	*	@param 		p2Board - a Board instance of player 2's board. gameBoard and shipArray is set (not guessBoard)
	*	@param 		gameMode - the gameMode that is read from the file 
	*/
	public void makeThePlayersForLoad(Board p1Board, Board p2Board, String gameMode) {
		
        Board player1Board = p1Board;
        Board player2Board = p2Board;

		Player player1 = new HumanPlayer(player1Board,"P1");
		Player player2 = null;
		
        if (gameMode == "Player vs Ai") {
	    		player2 = new ComputerPlayer(player2Board,"P2");
        } else {
        		player2 = new HumanPlayer(player2Board,"P2");
        }			
		
		player1Board.guessBoard = player2Board.gameBoard;
		player2Board.guessBoard = player1Board.gameBoard;		
		
	}







}