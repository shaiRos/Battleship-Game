package gui;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import board.Board;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import players.Player;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.HPos;
import saveload.*;


/**
* 	This class creates the user interface of the main game where players take turns to attack each other's ships 
*	It's base layout is a BorderPane where only the center, bottom and left regions are used.
*
*	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayang
*/

public class AttackPhase  {

	private Scene gameUI;	
	private BorderPane gameLayout;
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;	
	public static String currentPlayer;
	private Label coordinates = null;
	private boolean displayOnly;	
	
	/**
	*	The constructor of the AttackPhase. It creates the user interface depending on which player is currently attacking.
	*	Using the three regions from BorderPane (center, bottom, right) it displays the current player's guess board at the center 
	*	(where the player sends attacks by clicking a section of the grid). At the right pane contains their guess board, info, and buttons
	*	for saving and quitting. 
	*
	*	@param 		scn - the Scene where the root will be changed to display the next player's attack phase
	*	@param		player - a String that indicates which player the display should accommodate
	*	@param		displayonly - a boolean indicating if this layout out should be display only with no event handlers on the guess board
	*/
	public AttackPhase(Scene scn, String player, boolean displayonly) {

		currentPlayer = player;
		gameUI = scn;	
		ownBoard = new BoardGUI(Board.getBoardSize(), Settings.smallGridWidth);
		guessBoard = new BoardGUI(Board.getBoardSize(), Settings.bigGridWidth);
		//coordinates = coord;
		displayOnly = displayonly;

		if (currentPlayer == "P1") {
			ownBoard.addValuesFromArray(Settings.p1, "gameBoard");
			guessBoard.addValuesFromArray(Settings.p1, "guessBoard");
			}
		else if (currentPlayer == "P2") {	
				ownBoard.addValuesFromArray(Settings.p2, "gameBoard");
				guessBoard.addValuesFromArray(Settings.p2, "guessBoard");		
		}
		
		//Update the Display with the new changes
		gameLayout = new BorderPane();
		gameLayout.setCenter(centerPane());	
		gameLayout.setBottom(botPanel());		
		gameLayout.setRight(rightPanel());	
		gameUI.setRoot(gameLayout);	
		
	}
	

	
	/**
	*	The child node that will be placed in the center region of the main layout. The child node is a TilePane layout that contains a GridPane layout
	*	(created in BoardGUI) which is the display of the player's guess board. This node has also been set as an event listener that calls
	*	the AttackClickHandler class to take in mouse clicks from this node.
	*	
	*	@return 	a TilePane layout 
	*/
	public TilePane centerPane() {	
		
		TilePane centerSlot = new TilePane();
		//attack handler on the big board
		if (displayOnly == false) {
			guessBoard.getBoardGrid().setOnMousePressed(new AttackClickHandler(guessBoard.getGridBlockSize(), gameUI, currentPlayer));
		}
		centerSlot.getChildren().add(guessBoard.getBoardGrid());
		return centerSlot; 	
	}

	/**
	*	The child node that will be placed in the right region of the main layout. Contains the player's own board and other info.
	*
	*	@return 	a TilePane layout
	*/
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(Settings.sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));

		//this is the second tile below the mini board at the right pane
		// contains messages and buttons
		VBox secondTile = new VBox(30);
		secondTile.setAlignment(Pos.TOP_CENTER);
		String whichPlayer = currentPlayer + "'s turn";
		Label thisPlayerTurn = new Label(whichPlayer);
		thisPlayerTurn.setFont(new Font(30));
		thisPlayerTurn.setTextFill(Color.WHITE);

		//button to go to main menu in game. Also the play again button 
		Button mainMenuBt = new Button("Quit");
		mainMenuBt.setOnMouseClicked(event -> {
			Settings.reset();
			MainMenuGUI menu = new MainMenuGUI();
			gameUI.setRoot(menu.getMenuRoot());
		});
		
		
		Button saveGameBt = new Button("Save");
		saveGameBt.setOnMouseClicked(event -> {
			SaveGame.saveProgress(Settings.p1.getPlayerBoard(),Settings.p2.getPlayerBoard());
			Settings.changeMessage("Saved");	
		});
		
		
		secondTile.getChildren().addAll(thisPlayerTurn,saveGameBt, mainMenuBt);
		if(Settings.getMessage() == "You Lose!" || Settings.getMessage() == "You Win!") {
			mainMenuBt.setText("Play again");
		}
		rightPanel.getChildren().addAll(ownBoard.getBoardGrid(),secondTile);			
		return rightPanel;
	}	
	
	/**
	*	The bottom panel consists of messages for the user. Placed at the bottom region of the
	*	main layout.
	*
	*	@return 	a GridPane layout 
	*/
	public GridPane botPanel() {
		
		GridPane botPanel = new GridPane(); 
		botPanel.setPrefHeight(Settings.botHeight);	
		botPanel.setMaxHeight(Settings.botHeight);				
		botPanel.setStyle("-fx-background-color: #ebcd98;");	//Hex color		
		botPanel.setAlignment(Pos.TOP_CENTER);
		botPanel.add(Settings.message,0,0);
		return botPanel;
	}
}			