import java.util.Scanner;
import java.util.InputMismatchException;

public class MenuScreen {

	// displays the start of the game menu
    public static void printMenu(String[] args) {
        boolean run = true;
        boolean userSelect = true;

        do {

            int userChoice = 0;
            // open user input
            Scanner input = new Scanner(System.in);
            // make sure the user enters a valid input
            while (userSelect != false) {
                System.out.println("Select an option: \n1.) Player vs Player\n2.) Player vs AI\n3.) Exit");
                try {
                    userChoice = input.nextInt();
                    userSelect = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Try again.");
                    input.next();
                }
            }

            switch (userChoice) {
                case 1:
                    System.out.println("Player vs Player");
                    // arg is a boolean indicating if AI will be activated
                    
                    Game game = new Game(false);
                    game.start();
                    run = false;
                    break;
                case 2:
                    System.out.println("Player vs AI");
                    // arg is a boolean indicating if AI will be activated
                    
                    Game aiGame = new Game(true);
                    aiGame.start();
                    run = false;
                    break;
                case 3:
                    System.out.println("Exit.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please re-enter your choice");
            }
        } while (run == true);
    }

    public static void main (String[] args) {
        printMenu(args);
    }





}