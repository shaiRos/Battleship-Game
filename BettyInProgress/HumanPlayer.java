import java.util.Scanner;
public class HumanPlayer extends Player{
	private Scanner userInput = new Scanner(System.in);
	
	public HumanPlayer(String newName){
		super(newName);
	}
	
	public void setShips(int shipID,int shipLength){
		boolean validFormat = false;
		//char orientation;
		
		while(validFormat != true){
			try{
				System.out.println("Placing ship with length " + shipLength);
				char orientation = getOrientation();
		
				int [] tempPosition = getShipPosition();
				int row = tempPosition[0];
				int column = tempPosition[1];
				super.addShip(shipID, shipLength, orientation, column, row);

				validFormat = true;
				
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				validFormat = false;
			}
		}
	}
	
	
	private char getOrientation(){
		boolean validInput = false;
		char orientation = 'h';
		while(validInput != true){
			try{
				System.out.print("Indicate orientation (h or v): ");
				orientation = userInput.nextLine().toLowerCase().charAt(0);
						
				if (orientation != 'h' && orientation != 'v') { //Only takes choices between horizontal or vertical.
					throw new IllegalArgumentException("\nPlease enter h or v");
				}
				                                  
				validInput = true;
			}catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
					validInput = false;	
				}
		}
		
		return orientation;
	}
	
	private int[] getShipPosition(){
		boolean validInput = false;
		int [] shipPosition = new int[2];
		while(validInput != true){
			try{
				System.out.print("Indicate row and column (i.e. A1): ");
				String position = userInput.nextLine();
				position = position.replaceAll("\\s","");
				shipPosition[0]= getRowNumber(position);
				shipPosition[1] = getColumnNumber(position);
				super.validateCoordinate(shipPosition[1], shipPosition[0]);
				validInput = true;
			}catch(IllegalArgumentException e) {
				System.out.print("Please enter correct format of row and column: ");
				System.out.println(e.getMessage());
				validInput = false;	
			}catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
				//possible errors when doing the conversions of the string input
				System.out.print("Please enter correct format of row and column: ");
				validInput = false;
			}
		}
		return shipPosition;
	}
	
	public void attack(){
		boolean validFormat = false;
		while(validFormat != true){
			try{
				System.out.println(getPlayerName() + "'s turn to attack");
				int [] tempPosition = getShipPosition();
				int row = tempPosition[0];
				int column = tempPosition[1];
				super.makeAttack(row,column);
				validFormat = true;
				
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				validFormat = false;
			}
		}
	}
	
	private int getRowNumber(String position){
		char rowLetter = position.toUpperCase().charAt(0);
		if (rowLetter < 'A' || rowLetter > 'Z'){
			throw new IllegalArgumentException("\nPlease a letter for row");
		}
		int rowNumber = (((int)(rowLetter) - 65 ) + 1);
		return rowNumber;
	}
	
	private int getColumnNumber(String position){
		//extract number(which represents column) from position input
		String tempColumn = position.replaceAll("[^0-9]", "");
		return (Integer.parseInt(tempColumn));
	}
	
	
	
	
}