package saveload;

import java.io.*;
import java.util.Scanner;
import board.Board;
import board.BoardValue;

/**
*	Temporary class - to be implemented later in gameConfig or
*	similar class so we import already created maps instead of
*	asking users for input
*
*/
public class LoadGame{

    private String[][] p1Board = null;
    private String[][] p2Board = null;

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
                    //System.out.println(line);
                    String data = reader.readLine();
                    //System.out.println(data);
                    int boardSize = Integer.parseInt(data);

                    

                } else if (line.equals("Number of Ships:")) {
                    //System.out.println("after " + line );
                    String data = reader.readLine();
                    //System.out.println(data);
                    int numShips = Integer.parseInt(data);
                    Board.setNumOfShips(numShips);

                } else if (line.equals("PLAYER1BOARD")){
                    for (int row = 0; row < boardSize; row++){
                        String rowLine = reader.readLine();
                        String[] rowData = rowLine.split(" ");
                        for (int column = 0; column < boardSize; column++){
                            p1Board[row][column] = rowData[column];
                        }
                    }

                } else if (line.equals("PLAYER2BOARD")){
                    for (int row = 0; row < boardSize; row++){
                        String rowLine = reader.readLine();
                        String[] rowData = rowLine.split(" ");
                        for (int column = 0; column < boardSize; column++){
                            p2Board[row][column] = rowData[column];
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
                        //board object
                        p1Board.addShip(ID, len, orient, row, column);
                    }

                }

            }


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
