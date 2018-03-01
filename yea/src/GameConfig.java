import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameConfig{
	private static int difficulty;
	private int boardSize;
	Board player1Board = new Board();
	Board player2Board = new Board();
	static int  [] shipsToAdd;
	
	public GameConfig(int boardsz){
		Board.setBoardSize(boardsz);
		boardSize = boardsz;

	}
	public boolean isNotValidTarget(int [] position){
		int row = position[0];
		int col = position[1];
		if (row < 1 || row > boardSize ){
			System.out.println("out of bound");
			return true;
		}
		if (col < 1 || col > boardSize ){
			System.out.println("out of bound");
			return true;
		}
		
		return false;
	}
	public void updateAttack(int [] move, int PlayerNum){
		if (PlayerNum == 1){
			player1Board.makeHit(move);
		}else if (PlayerNum == 2){
			player2Board.makeHit(move);
		}
	}
	
	public void printShipBoard(int playerNum){
		if (playerNum == 1){
			player1Board.printShipBoard();
		}else if(playerNum == 2){
			player2Board.printShipBoard();
		}
		
		
	}
	
	public boolean addShipPlayer1(int [] shipProperties){
		if(player1Board.shipFitsBoard(shipProperties[1], shipProperties[2], shipProperties[3], shipProperties[4])){
			player1Board.setShipArray(shipProperties);
			return true;
		}
		return false;
		
		
	}
	
	public boolean addShipPlayer2(int [] shipProperties){
		if(player2Board.shipFitsBoard(shipProperties[1], shipProperties[2], shipProperties[3], shipProperties[4])){
			player2Board.setShipArray(shipProperties);
			return true;
		}
		return false;
		
		
	}
	
	public void initalizeBoard(){
		player1Board.createEmptyBoards();
		player1Board.setShipArraySize();
		player2Board.createEmptyBoards();
		player2Board.setShipArraySize();
		shipsToAdd = Board.generateShipsToAdd();
	}
	
	
	public static int [] getShipsToAdd(){
		return shipsToAdd;
	}
	
	public void readFile(String mapLevel){
		Board.setNumOfShips(4);
		initalizeBoard();

        //Initiate line for ship data from file 
        String shipInfo = null;
        int lineNum =0;
        try {
            // FileReader reads text files
            FileReader fileReader = new FileReader(mapLevel);

            // Wrap FileReader in BufferedReader to efficiently read chars, lines, etc. (lines in this case)
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            
            //Extracting data from file
            while ((shipInfo = bufferedReader.readLine()) != null) {
                String[] line = shipInfo.split(" ");               
                
                int orientationNum = Integer.parseInt(line[0]);
                int length = Integer.parseInt(line[1]);
                int row = Integer.parseInt(line[2]);
                int column = Integer.parseInt(line[3]);
                player1Board.setShipArray(lineNum,orientationNum,length,row,column);
                player2Board.setShipArray(lineNum,orientationNum,length,row,column);

                lineNum++;
            }
            //Need to always close after done using it
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + mapLevel + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + mapLevel + "'");
            System.out.println(ex.getMessage());
        }

    }
	
	
	
	
	
	
	

}