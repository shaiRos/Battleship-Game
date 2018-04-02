package gui;
import board.Board;
import game.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import players.*;

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
		gameUI = new Scene(menu.getMenuRoot(), Settings.xWindowSize, Settings.yWindowSize);
		
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
		Settings.shipsToPlace = Board.getNumOfShips();
		Settings.setGeneratedShips(Board.getGeneratedShips());
        Board player1Board = new Board();
        Board player2Board = new Board();
		

		Player player1 = new HumanPlayer(player1Board);
		Player player2 = null;
		
        if (Game.getAIStatus() != true) {
	    		player2 = new HumanPlayer(player2Board);
        } else {
        		player2 = new ComputerPlayer(player2Board);
        }		
		Settings.p1 = player1;
		Settings.p2 = player2;	
		
		player1Board.guessBoard = player2Board.gameBoard;
		player2Board.guessBoard = player1Board.gameBoard;
	
		SetupPhase setup = new SetupPhase(gameUI, "P1",Settings.shipsToPlace,false);
	}
}	
	
	