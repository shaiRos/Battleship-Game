import java.util.Scanner;


public class HumanPlayer {
    Board playerBoard = null;


    // constructor with board, human not suppose to touch board, change later on
    public HumanPlayer(Board board) {
        this.playerBoard = board;
    }


    public void playerTurn() {
        boolean formatted = false;
        while (formatted != true) {
            try {
                System.out.println("Current game board");
                // return the game board
                playerBoard.returnBoard(1);
                System.out.println("\n");
                System.out.println("Current guessing board");
                // return the guessing board
                playerBoard.returnBoard(2);
                System.out.println("Enter coordinates to attack (row column): ");

                // Stolen from other method - Take row and column from input
                Scanner input = new Scanner(System.in);
                String inputLine = input.nextLine();
                String[] inputInfo = inputLine.split(" ");
                char tempColumn = inputInfo[0].toUpperCase().charAt(0);
                int column = (((int)(tempColumn) - 65 ) + 1);
                int row = Integer.parseInt(inputInfo[1]);

                // check to make sure its a legit value
                if ((row > playerBoard.getBoardSize()) || (column > playerBoard.getBoardSize()) || (row < 0) || (column < 0)) {
                    System.out.println("Invalid coordinates");
                } else {
                    formatted = true;
                }
                // Specify where the attack has went
                System.out.println("Sending attack to (" + (char)((column + 65) - 1) + "," + row + ")" );

                // Send the attack. Check if the attack hits or misses
                Game.sendAttack(playerBoard, row, column);

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

    }



}