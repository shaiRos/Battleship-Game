import java.util.Scanner;

public class MenuScreen {

    public static void main (String[] args) {
        boolean run = true;

        do {

            int userChoice = 1;

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