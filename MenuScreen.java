import java.util.Scanner;

public class MenuScreen {

    public static void main (String[] args) {

        System.out.println("Test thingy");
        System.out.println("Choose a menu thingy: ");
        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();

        switch (userChoice) {
            case 1:
                System.out.println("Case 1");
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                System.out.println("Other cases. yeah.");

        }

    }





}