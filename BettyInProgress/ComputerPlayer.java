import java.util.Random;

public class ComputerPlayer extends Player{
	private Random move = new Random();
	
	public ComputerPlayer(String newName){
		super(newName);
	}
	
	public void setShips(int shipID,int shipLength){
		boolean validPlacement = false;
		//char orientation;
		
		while(validPlacement != true){
			try{
				
				char orientation = getOrientation();
		
				int row = getPosition(1,Board.getBoardSize());
				int column = getPosition(1,Board.getBoardSize());
				super.addShip(shipID, shipLength, orientation, column, row);

				validPlacement = true;
				
			}catch (IllegalArgumentException e) {
				validPlacement = false;
			}catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
				//possible errors when doing the conversions of the string input
				validPlacement = false;
			}
		}
	}
	
	private char getOrientation(){
		int min = 0;
		int max = 1;
		char orient = 'h';
		int randomNum = move.nextInt((max - min) + 1) + min;
		System.out.println("orientation" + randomNum);
		
		switch(randomNum){
			case 0: orient = 'h';
			break;
			case 1: orient = 'v';
			break;
		}
		return orient;
	}
	
	private int getPosition(int min, int max){
		int randomNum = move.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public void attack(){
	boolean validFormat = false;
	while(validFormat != true){
		try{
			int row = getPosition(1,Board.getBoardSize());
			int column = getPosition(1,Board.getBoardSize());
			System.out.println("Computer attacking" + row + "," + column);
			super.makeAttack(row,column);
			validFormat = true;
			
		}catch (IllegalArgumentException e) {
			validFormat = false;
		}
	}
	}

}