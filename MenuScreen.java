import java.util.Scanner;
import java.util.InputMismatchException;
/**
*   Simple Menu Screen that will display all of the available options, and toggles values that will be enabled with each menu selection
*   @author Brandon Lu, Shaina Rossel, Betty Zhang, Charlene Madayang
**/
public class MenuScreen {
    /**
    *   Prints the basic board view and offers input sanitation to the user
    *
    **/
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
                    System.out.println("Invalid choice. Exiting...");
                    System.exit(0);
            }
        } while (run == true);
    }
    /**
    *   Main method that will be called when this class is run. Will view the menu screen after being called
    *
    **/
    public static void main (String[] args) {
        printMenu(args);
    }


    //add

    private boolean run = true;
    private boolean userSelect = true;
    private int userChoice = 0;

//Method to check for correct input for JUnit testing
    public void setInput(int num){
        while (userSelect != false) {
                //userChoice = num;
                setUserChoice(num);
                if (getUserChoice() == 1 || getUserChoice() == 2 || getUserChoice() == 3){
                    userSelect = false;
                } else {
                    System.out.println("Invalid input. Try again.");
                }
        }
    }

//add
    public void setUserChoice(int num){
        userChoice = num;
    }

    public boolean getUserSelect(){
        //System.out.println(userSelect);
        return userSelect;
    }

    public int getUserChoice(){
        return userChoice;
    }




}