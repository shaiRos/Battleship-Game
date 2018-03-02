import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author betty.zhang
 *
 */
public class SetUp {
	private static int minBoardSize = 5;
	private static int maxBoardSize = 10;
	
	public static int getBoardSizeInput() {
		boolean run = true;
		int sz = 0;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("Enter Board Size between " + minBoardSize +
					" and " + maxBoardSize);
			try {
				sz = input.nextInt();
				if (sz >= minBoardSize && sz <= maxBoardSize) {
					run = false;
				} else {
					run = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.");
				input.next();
			}
		} while (run == true);

		return sz;
	}
	
	public static int setDifficulty(){
		boolean run = true;
        boolean userSelect = true;
    	int difficulty = 0;
    	Scanner input = new Scanner(System.in);
		do {
			System.out.println("Set up Board (enter corresponding number)"
    				+ "\n 0. Set up your own Board"
    				+ "\n 1. Easy map"
    				+ "\n 2. Medium map"
    				+ "\n 3. Hard map");
			try {
				difficulty = input.nextInt();
				if(difficulty<0 || difficulty >3) {
					System.out.println("Invalid option, please enter an integer between 0 and 3");
					run = true;
				} else {
					run = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.");
				input.next();
			}
		} while (run == true);
        
        return difficulty;
	}
	
	public void plaryerSetup(HumanPlayer plary1, HumanPlayer player2, GameConfig config) {
		System.out.println("hi");
	}
	
	public static String setMap(int difficultyLevel) {
		String mapToUse;
		switch (difficultyLevel) {
		case 1: 
			//set board size
			mapToUse = "easyMap.txt";
			//easy
			break;
		case 2:
			mapToUse ="mediumMap.txt";
			//medium
			break;
		case 3:			
			mapToUse = "hardMap.txt";
			//hard
			break;
		default:
			mapToUse = "defaultMap.txt";
			//default map
			break;
		}
		return mapToUse;
		
	}
	

	
}
