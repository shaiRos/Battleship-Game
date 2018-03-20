import java.util.Scanner;

/**
*   Holds the code the main player will utilize. Contains the logic necessary to execute a game 'turn'
*
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
**/
public class HumanPlayer extends Player {
    Board playerBoard = null;

    /**
    *   Main constructor for HumanPlayer object
    *   @param board - Board object that will be linked to the current player
    **/
    // constructor with board, human not suppose to touch board, change later on
    public HumanPlayer(Board board) {
        this.playerBoard = board;
    }
	/**
    *   getter for playerBoard object
    *   @return playerBoard - Board object linked to player
    **/
	public Board getPlayerBoard() {
		return playerBoard;
	}

    /**
    *   Main logic to execute a game 'turn'. Will include all prompts, input checks and validations required for a user to complete his attack
    *   
    **/

    public String playerTurn() {
    		int row = -1;
    		int column = -1;

        boolean formatted = false;
        while (formatted != true) {
            try {
                System.out.println("Current game board");
                // return the game board
				playerBoard.returnBoard(1);		


                System.out.println("\n");
                System.out.println("Current guessing board");
                // return the guessing board
                //playerBoard.returnBoard(2);
				playerBoard.returnBoard(2);

                System.out.println("Enter coordinates to attack (row column): ");

                // Stolen from other method - Take row and column from input
                Scanner input = new Scanner(System.in);
                String inputLine = input.nextLine();
                String[] inputInfo = inputLine.split(" ");
                char tempRow = inputInfo[0].toUpperCase().charAt(0);
                row = (((int)(tempRow) - 65 ) + 1);
                column = Integer.parseInt(inputInfo[1]);

                // check to make sure its a legit value
                if ((row > playerBoard.getBoardSize()) || (column > playerBoard.getBoardSize()) || (row < 0) || (column < 0)) {
                    System.out.println("Invalid coordinates");
                } else if (checkPreviousHitEnum(playerBoard, row, column) == true) {
                		System.out.println("Previously guessed! Try again");
                } else {
                    formatted = true;
                    // Specify where the attack has went
                    System.out.println("Sending attack to (" + (char)((row + 65) - 1) + "," + column + ")" );

                    // Send the attack. Check if the attack hits or misses
                    //sendAttack(playerBoard, row, column);
                }

            }
            // error checking
            catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong format");
                formatted = false;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                formatted = false;              
            
            }
        }
        return (coordToString(row, column));
    }



}