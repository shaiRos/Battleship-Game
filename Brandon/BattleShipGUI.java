import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;


public class BattleShipGUI extends Application
{
	
	//DON'T TOUCH 	
	final private int xWindowSize = 1040;
	final private int yWindowSize = 920;
	

	private Stage primaryStage;
	private Scene gameUI;	
	private BorderPane mainMenu;

	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		primaryStage = primaryStage;
		mainMenu = new BorderPane();
		gameUI = new Scene(mainMenu, xWindowSize, yWindowSize);

		//enter setup stage
		//SetupPhase setup = new SetupPhase(gameUI);
		//setup.setupEnd().setOnMousePressed(new EndSetupHandler(gameUI));		
		
//================== Copied From Text Version main =================================================================================
        int userBoardSize = 5;
        int userShipCount = 2;

        String fileName = "map.txt";

        // Initialize the boards and set the board sizes
        // WIP:
        //      - Re-create the board using the new boardSize values
        Board player1Board = new Board();
        player1Board.setBoardSize(userBoardSize);
        Board player2Board = new Board();
        player2Board.setBoardSize(userBoardSize);

        Game.mapFromFiles(fileName, player1Board);
        Game.mapFromFiles(fileName, player2Board);

		Game.enableAI();		
		
		
		Player player1 = new HumanPlayer(player1Board);
		Player player2 = null;
		
        if (Game.getAIStatus() != true) {
	    		player2 = new HumanPlayer(player2Board);
				System.out.println("human");
        } else {
        		player2 = new ComputerPlayer(player2Board);
				System.out.println("ai");
        }		

		boolean winCondition = false;
		
		player1Board.guessBoard = player2Board.gameBoard;
		player2Board.guessBoard = player1Board.gameBoard;	//GUESSBOARD IS MESSED UP
		Game.clearScreen();
		System.out.println("Player 1 turn starting....");
            // Take the user coordinates and attack
			// DO NOTE
			// Currently, you need to typecast the type the player is to access the playerTurn method
		//((HumanPlayer) player1).playerTurn();		

		//Start attack Phase
		AttackPhase startAttack = new AttackPhase(gameUI, player1, player2, "P1", null);
		//send humanPlayer  
		
//=============================================================


		primaryStage.setMaxHeight(yWindowSize);	
		primaryStage.setMaxWidth(xWindowSize + 15);		
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	

}	
	
	