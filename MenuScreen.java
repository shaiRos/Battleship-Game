import java.util.Scanner;

public class MenuScreen {

    public static void main (String[] args) {
        boolean run = true;

        do {

            System.out.println("Choose a menu option: ");
            System.out.println("1.) Player vs Player\n2.) Player vs AI\n3.) Exit");
            Scanner input = new Scanner(System.in);
            int userChoice = input.nextInt();

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