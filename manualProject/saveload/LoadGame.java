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
			System.out.println("heeelo");


            while((line = reader.readLine()) != null){

                if (line.equals("Board Size:")){
                    //System.out.println(line);
                    String data = reader.readLine();
                    //System.out.println(data);
                    boardSize = Integer.parseInt(data);
					p1SBoard = new String[boardSize][boardSize];
					p2SBoard = new String[boardSize][boardSize];
					
					p1Board = new Board(boardSize);
					p2Board = new Board(boardSize);
					
					System.out.println("UMMWORK? " + boardSize);


                } else if (line.equals("Number of Ships:")) {
                    //System.out.println("after " + line );
						
					
                    String data = reader.readLine();
                    //System.out.println(data);
                    numShips = Integer.parseInt(data);
                    Board.setNumOfShips(numShips);

                } else if (line.equals("Current Game Mode:")){
					
							
                    mode = reader.readLine();

                } else if (line.equals("Current Turn:")){
					
							
                    currentPlayer = reader.readLine();

                } else if (line.equals("PLAYER1BOARD:")){
                
					System.out.println("y u no work");
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
							System.out.println(p1SBoard[row][column]);	
							
                        }
                    }


                } else if (line.equals("PLAYER1SHIP:")){
                 
					p1Board.loadGameBoard(p1SBoard);

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

                } else if (line.equals("PLAYER2SHIP:")){
                   
                    p2Board.loadGameBoard(p2SBoard);

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
                }

            }

            makeThePlayersForLoad();
			BattleShipGUI.loadGame(loadPlayer1(),loadPlayer2(),getCurrentPlayer());

            /*
            while (line != null){

                if (line == "PLAYER1BOARD"){
                    for (int row = 0; row < Board.getBoardSize(); row++){
                    for (int column = 0; column < Board.getBoardSize(); column++){
                        if (column == (Board.getBoardSize() - 1)){
                            writer.println((P1Board.gameBoard[row][column]) + " ");
                        } else {
                            writer.append((P1Board.gameBoard[row][column]) + " ");
                        }
                    }
                    }
                }

            } */
                //will need to make setBoardSize public
                /* if ((line = reader.readLine()) == "PLAYER1SHIP:"){
                    String data = reader.readLine();

                    for (int i = 0; i < numShips; i++){
                        
                    }

                

                if  (line == "Current Game Mode:"){
                    
                } */


            reader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileToRead + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileToRead + "'");  
        }


    }


/*
    public void loadPlayer(String name, Board playerBoard){

    }
*/


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
        
        if (mode == "Player vs Ai") {
                player2 = new ComputerPlayer(p2Board,"P2");
        } else {
                player2 = new HumanPlayer(p2Board,"P2");
        }           
        
        p1Board.guessBoard = p2Board.gameBoard;
        p2Board.guessBoard = p1Board.gameBoard;       
        
    }

    public static String getCurrentPlayer(){
        return currentPlayer;
    }

     public static void setPlayer1(Player p1){
        player1 = p1;
    }

    public static Player loadPlayer1(){
        return player1;
    }

     public static void setPlayer2(Player p2){
         player2 = p2;
    }

    public static Player loadPlayer2(){
        return player2; 
    }
    
	//read file and store data in an array
	/*
    public static void loadBoard()
    {
        //Initiate which file to read
        String fileToRead = "boards.txt";

        //Initiate line for ship data from file 
        String data = null;


		try {
            // FileReader reads text files
            FileReader fileReader = new FileReader(fileToRead);
            // Wrap FileReader in BufferedReader to efficiently read chars, lines, etc. (lines in this case)
            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();

            //Extracting data from file
            String[] dataLines;
            int i = 0;
            for (i = 0; i < 7; i++){
                //Extracting data, storing into the dataLines array
                line = dataLines[i];
            }

            //BoardValue[][] currentBoard;
            while (line != null){
                String[] value = line.split();
                for (int x = 0; x < value.length; x++){
                    for (int y = 0; y < value.length; y++){
                        switch(value){

                            case 1:
                                currentValue[y][x] = SHIP;
                                break;
                            case 2:
                                currentValue[y][x] = MISS;
                                break;
                            case 3: 
                                currentValue[y][x] = HIT;
                                break;
                            case 4: 
                                currentValue[y][x] = EMPTY;
                                break;
                        }
                    }
                }

            }


            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileToRead + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileToRead + "'");  
        }
    }

    }
    */

/*
    public void loadBoardSizeGUI(String line){
        int size = Integer.dataLines[0];
        BoardGui.setBoardSizeGUI(size);     
    }

    
    public void loadCurrentMode());
    

    //Getting player.... from a file??? how is that even possble...
    public void loadCurrentPlayer(String line){
        AttackPhase.setCurrentPlayer(line);
    }

    public void loadPlayer1(String line){

    }


*/


}
