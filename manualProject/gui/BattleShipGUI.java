package gui;
import game.Game;
import board.Board;

import players.Player;
import players.*;
import players.HumanPlayer;
import players.ComputerPlayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;


/**
* 	Application of java fx starts here. 
*	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayag
*/

public class BattleShipGUI extends Application
{
	public static Stage primaryStage;
	public static Scene gameUI;
	private BorderPane mainMenu;

	public static void main(String [] args)
	{
		launch(args);
	}
	

	/**
	*	The very first root of the scene is the main menu. 
	*/
	
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
	
	/**
	* 	Initializes objects required for the game by getting the values that were setup in the main menu
	*/
	public static void gameSetup() {
		
		if (Settings.gameMode == "Player vs Ai") {
			Game.enableAI(); 
		}
        //Board.setBoardSize(Settings.boardSize);
        Board player1Board = new Board(Settings.boardSize);
        Board player2Board = new Board(Settings.boardSize);
		Settings.shipsToPlace = Board.getNumOfShips();
		Settings.setGeneratedShips(Board.getGeneratedShips());


		Player player1 = new HumanPlayer(player1Board,"P1");
		Player player2 = null;
		
        if (Game.getAIStatus() != true) {
	    		player2 = new HumanPlayer(player2Board,"P2");
        } else {
        		player2 = new ComputerPlayer(player2Board,"P2");
        }		
		Settings.p1 = player1;
		Settings.p2 = player2;	
		
		player1Board.guessBoard = player2Board.gameBoard;
		player2Board.guessBoard = player1Board.gameBoard;
	
		SetupPhase setup = new SetupPhase(gameUI, "P1",Settings.shipsToPlace,false);
	}
	
	/**
	*	Loads the game from a save file.
	*
	*	@param		p1 - a player instance of player 1
	*	@param		p2 - a player instance of player 2
	*	@param		currentPlayer - a String indicating which player's turn it is. ( "P1" / "P2")
	*/
	public static void loadGame(Player p1, Player p2, String currentPlayer) {
		//currentPlayer ( "P1" or "P2")
		//player objects must be setup. both their own board and guess boards
		
		Settings.p1 = p1;
		Settings.p2 = p2;
		Settings.setBoardSize(p1.getPlayerBoard().getBoardSize());
		
		if (p2 instanceof ComputerPlayer) {
			Game.enableAI();
			if (Settings.gameMode != "Player vs Ai") {
				Settings.switchMode();	
			}					
		}
		AttackPhase startAttack = new AttackPhase(gameUI, currentPlayer, false);
	}
}	
	
	