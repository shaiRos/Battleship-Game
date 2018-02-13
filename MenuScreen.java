import java.util.Scanner;
import java.util.InputMismatchException;

public class MenuScreen {

    public static void main (String[] args) {
        boolean run = true;
        boolean userSelect = true;

        do {

            int userChoice = 0;
            Scanner input = new Scanner(System.in);
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
                    Game.main(args);
                    run = false;
                    break;
                case 2:
                    System.out.println("Player vs AI");
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





}