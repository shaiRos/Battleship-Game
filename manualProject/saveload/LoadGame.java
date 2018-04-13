package saveload;

import java.io.*;
import java.util.Scanner;
import board.Board;
import board.BoardValue;
import players.Player;
import players.HumanPlayer;
import players.ComputerPlayer;
import gui.BattleShipGUI;

/**
*	Temporary class - to be implemented later in gameConfig or
*	similar class so we import already created maps instead of
*	asking users for input
*
*/
public class LoadGame{

    private static String[][] p1SBoard = null;
    private static String[][] p2SBoard = null;
    private static int boardSize;
    private static int numShips;
    private static String mode;
    private static String currentPlayer;
    private static Board p1Board = null;
    private static Board p2Board = null;
    private static Player player1;
    private static Player player2;

    public static void loadBoard()
    {
        //Initiate which file to read
        String fileToRead = "boards.txt";

        
        //Initiate line for ship data from file 
        String line = null;
         try {
            // FileReader reads text files
            FileReader fileReader = new FileReader(fileToRead);
            // Wrap FileReader in BufferedReader to efficiently read chars, lines, etc. (lines in this case)
            BufferedReader reader = new BufferedReader(fileReader);


            while((line = reader.readLine()) != null){

                if (line.equals("Board Size:")){
                    String data = reader.readLine();
                    boardSize = Integer.parseInt(data);
					p1SBoard = new String[boardSize][boardSize];
					p2SBoard = new String[boardSize][boardSize];
					
					p1Board = new Board(boardSize);
					p2Board = new Board(boardSize);

                } else if (line.equals("Number of Ships:")) {
                    String data = reader.readLine();
                    numShips = Integer.parseInt(data);
                    Board.setNumOfShips(numShips);

                } else if (line.equals("Current Game Mode:")){
							
                    mode = reader.readLine();

                } else if (line.equals("Current Turn:")){
					
							
                    currentPlayer = reader.readLine();

                } else if (line.equals("PLAYER1BOARD:")){
                
                    for (int row = 0; row < boardSize; row++){
                        String rowLine = reader.readLine();
                        String[] rowData = rowLine.split(" ");
                        for (int column = 0; column < boardSize; column++){
                            p1SBoard[row][column] = rowData[column];

                        }
                    }

                } else if (line.equals("PLAYER2BOARD:")){

                    for (int row = 0; row < boardSize; row++){
                        String rowLine = reader.readLine();
                        String[] rowData = rowLine.split(" ");
						
                        for (int column = 0; column < boardSize; column++){
                            p2SBoard[row][column] = rowData[column];	
                        }
                    }
                } else if (line.equals("PLAYER1SHIP:")){
                 
					

                    for (int ship = 0; ship < numShips; ship++){
                        String shipLine = reader.readLine();
                        String[] shipData = shipLine.split(" ");
                        int ID = Integer.parseInt(shipData[0]);
                        int len = Integer.parseInt(shipData[1]);
                        char orient = shipData[2].charAt(0);
                        int row = Integer.parseInt(shipData[3]);
                        int column = Integer.parseInt(shipData[4]);
                  
                        p1Board.addShip(ID, len, orient, row, column);
                    }
					p1Board.loadGameBoard(p1SBoard);

                } else if (line.equals("PLAYER2SHIP:")){
                   
                   

                    for (int ship = 0; ship < numShips; ship++){
                        String shipLine = reader.readLine();
                        String[] shipData = shipLine.split(" ");
                        int ID = Integer.parseInt(shipData[0]);
                        int len = Integer.parseInt(shipData[1]);
                        char orient = shipData[2].charAt(0);
                        int row = Integer.parseInt(shipData[3]);
                        int column = Integer.parseInt(shipData[4]);
                 
                        p2Board.addShip(ID, len, orient, row, column);
                    }
					p2Board.loadGameBoard(p2SBoard);
                }

            }

            makeThePlayersForLoad();
			BattleShipGUI.loadGame(player1,player2,currentPlayer, mode);

            reader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileToRead + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileToRead + "'");  
        }


    }

/**
    *   Makes the player instances for the game to load from 
    *
    *   @param      p1Board - a Board instance of player 1's board. gameBoard and shipArray is set (not guessBoard)
    *   @param      p2Board - a Board instance of player 2's board. gameBoard and shipArray is set (not guessBoard)
    *   @param      gameMode - the gameMode that is read from the file 
    */

    public static void makeThePlayersForLoad() {
        
        player1 = new HumanPlayer(p1Board,"P1");
        player2 = null;
		
        p1Board.guessBoard = p2Board.gameBoard;
        p2Board.guessBoard = p1Board.gameBoard;
		
        
        if (mode.equals("Player vs Ai")) {
                player2 = new ComputerPlayer(p2Board,"P2");
        } else {
                player2 = new HumanPlayer(p2Board,"P2");
        }              
    }
}
