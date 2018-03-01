import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	private static GameConfig currentConfig;
	private static int difficulty;
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
    // Debug tool while also hiding enemy boards!
	public static void clearScreen() {
        // ASCII escape codes  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  

	// This will pause program execution - use what would be a reasonable
	// delay for the user to read the console
	public static void sleepThread(int milliseconds) {
        // Try sleeping for specified time given in ms
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static int mainMenu() {
		boolean run = true;
        boolean userSelect = true;
        int userChoice = 0;
        do {
        	userChoice = 1;
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
            
            if(userChoice<= 0 || userChoice >3) {
                System.out.println("Invalid choice. Please re-enter your choice");
                run = true;
                userSelect = true;

            }else {
            	run = false;
            }
        } while (run == true);
        return userChoice;
        
	}
	private static void setShips(int choice, HumanPlayer user1, HumanPlayer user2){
		if (choice == 0){
			//config.playerSetShip();
			int boardsz = SetUp.getBoardSizeInput();
			currentConfig = new GameConfig(boardsz);
			currentConfig.initalizeBoard();
			int [] shipsToAdd = currentConfig.getShipsToAdd();
			
			clearScreen();
			System.out.println(user1.getPlayerName() + "'s Turn" );
			for (int i = 0;i < shipsToAdd.length;i++){
				boolean validInput = false;
				do{
					int [] properties = user1.setShips(i,shipsToAdd[i]);
					validInput = currentConfig.addShipPlayer1(properties);
				}while(validInput != true);
			}
			currentConfig.printShipBoard(1);

			sleepThread(2500);

			clearScreen();
			System.out.println(user2.getPlayerName() + "'s Turn" );
			for (int i = 0;i < shipsToAdd.length;i++){
				boolean validInput = false;
				do{
					int [] properties = user2.setShips(i,shipsToAdd[i]);
					validInput = currentConfig.addShipPlayer2(properties);
				}while(validInput != true);
			}
			
		}else{
			//config.readFile(SetUp.setMap(choice));
			currentConfig = new GameConfig(10);
			currentConfig.readFile(SetUp.setMap(choice));
			currentConfig.printShipBoard(1);
			currentConfig.printShipBoard(2);

		}
	}
	
	private static void gameLoop(HumanPlayer player1, HumanPlayer player2){
		int [] attackPosition = player1.attack();
		while(currentConfig.isNotValidTarget(attackPosition)){
			attackPosition = player1.attack();
		}
		currentConfig.updateAttack(attackPosition, 1);
		
		
		attackPosition = player2.attack();
		while(currentConfig.isNotValidTarget(attackPosition)){
			attackPosition = player2.attack();
		}
		currentConfig.updateAttack(attackPosition, 2);
	}
	
	private static void playerVsPlayer() {
		HumanPlayer player1 = new HumanPlayer ("Player 1");
		HumanPlayer player2 = new HumanPlayer("Player 2");
		difficulty =SetUp.setDifficulty();
		setShips(difficulty, player1, player2);
		
		
		
	}
	
	private static void playerVsAI(){
		HumanPlayer player1 = new HumanPlayer ("Player 1");
		ComputerPlayer player2 = new ComputerPlayer("AI");
		difficulty = SetUp.setDifficulty();
	}
	
	private static void GUISetUp(){
		HumanPlayer player1 = new HumanPlayer ("Player 1");
		HumanPlayer player2 = new HumanPlayer("Player 2");
		difficulty = 1;
		setShips(difficulty, player1, player2);
		gameLoop(player1,player2);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        
		int gameMode = mainMenu();
		
		gameMode = 1; //forGUIDemo
		
		switch(gameMode) {
		case 1:
            System.out.println("Player vs Player");
            //playerVsPlayer();
            GUISetUp(); //for GUI demo
            break;
        case 2:
            System.out.println("Player vs AI");
            playerVsAI();
            break; 
        case 3:
            System.out.println("Exit.");
            System.exit(0);
            break;
		}

	}

}
