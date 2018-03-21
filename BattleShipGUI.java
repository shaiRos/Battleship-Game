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
	public static Stage primaryStage;
	public static Scene gameUI;
	private BorderPane mainMenu;

	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		MainMenuGUI menu = new MainMenuGUI();
		primaryStage = primaryStage;
		mainMenu = new BorderPane();	
		gameUI = new Scene(menu.getMenuRoot(), Settings.xWindowSize, Settings.yWindowSize);

/*
		Game.enableAI(); 	


		int userBoardSize = 5;
        int userShipCount = 2;
		
		
        Board.setBoardSize(userBoardSize);
        Board player1Board = new Board();
        Board player2Board = new Board();


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
		
		
		Settings.p1 = player1;
		Settings.p2 = player2; */
		
		//SETUP BOARD SIZE AND AMOUNT OF SHIPS TO SETUP BEFORE SETUP
	
		//enter setup stage
		//SetupPhase setup = new SetupPhase(gameUI, "P1",Settings.shipsToPlace,false);

		
//=============================================================
		primaryStage.setMaxHeight(Settings.yWindowSize);	
		primaryStage.setMaxWidth(Settings.xWindowSize + 15);		
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	
	public static void gameSetup() {
		
		if (Settings.gameMode == "Player vs Ai") {
			Game.enableAI(); 
		}
			
		
		
        Board.setBoardSize(Settings.boardSize);
        Board player1Board = new Board();
        Board player2Board = new Board();


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
		
		
		Settings.p1 = player1;
		Settings.p2 = player2;	
		
		SetupPhase setup = new SetupPhase(gameUI, "P1",Settings.shipsToPlace,false);
	}
	
		
	

	

}	
	
	