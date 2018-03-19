import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;

/**
* 	This is where application of java fx starts. 
*	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayang
**/

public class BattleShipGUI extends Application
{
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
		gameUI = new Scene(mainMenu, Settings.xWindowSize, Settings.yWindowSize);

		//enter setup stage
		//SetupPhase setup = new SetupPhase(gameUI);
		//setup.setupEnd().setOnMousePressed(new EndSetupHandler(gameUI));
//============================= Uncomment this for PvP ===========================================================================		
		Game.enableAI(); 		
//================== Copied From Text Version main =================================================================================
       
		int userBoardSize = 5;
        int userShipCount = 2;

        String fileName = "map.txt";
        Board.setBoardSize(userBoardSize);
        Board player1Board = new Board();
        Board player2Board = new Board();

        Game.mapFromFiles(fileName, player1Board);
        Game.mapFromFiles(fileName, player2Board);

		Player player1 = new HumanPlayer(player1Board);
		Player player2 = null;
		
        if (Game.getAIStatus() != true) {
	    		player2 = new HumanPlayer(player2Board);
        } else {
        		player2 = new ComputerPlayer(player2Board);
        }		

		boolean winCondition = false;
		
		player1Board.guessBoard = player2Board.gameBoard;
		player2Board.guessBoard = player1Board.gameBoard;
			
		
		//Start attack Phase by calling the class AttackPhase where it changes the root of the scene. Player 1 always goes first
		AttackPhase startAttack = new AttackPhase(gameUI, player1, player2, "P1", null); 
		
//=============================================================
		primaryStage.setMaxHeight(Settings.yWindowSize);	
		primaryStage.setMaxWidth(Settings.xWindowSize + 15);		
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	

}	
	
	