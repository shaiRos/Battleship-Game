package gui;
import javafx.scene.layout.BorderPane;
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
	private String attackingPlayer;
	private Label coordinates = null;
	private boolean displayOnly;	
	
	/**
	*	The constructor of the AttackPhase. It creates the user interface depending on which player is currently attacking.
	*	Using the three regions from BorderPane (center, bottom, left) it displays the current player's own board at the center 
	*	(where the player sends attacks by clicking a section of the grid), their guess board at the very top of the right pane
	* 	region and messages and stats on the bottom pane. 
	*
	*	@param 		scn - the Scene where the root will be changed to display the next player's attack phase
	*	@param		p1 - a Player instance of the first player
	*	@param		p2 - a Player instance of the second payer
	*	@param		player - a String that indicates which player the display should accommodate
	*	@param		displayonly - a boolean indicating if this layout out should be display only with no event handlers on the guess board
	*/
	public AttackPhase(Scene scn, Player p1, Player p2, String player, boolean displayonly) {

		attackingPlayer = player;
		gameUI = scn;	
		ownBoard = new BoardGUI(Board.getBoardSize(), Settings.smallGridWidth);
		guessBoard = new BoardGUI(Board.getBoardSize(), Settings.bigGridWidth);
		//coordinates = coord;
		displayOnly = displayonly;

		if (attackingPlayer == "P1") {
			ownBoard.addValuesFromArray(Settings.p1, "gameBoard");
			guessBoard.addValuesFromArray(Settings.p1, "guessBoard");
			}
		else if (attackingPlayer == "P2") {	
				ownBoard.addValuesFromArray(Settings.p2, "gameBoard");
				guessBoard.addValuesFromArray(Settings.p2, "guessBoard");		
		}
		
		//Update the Display with the new changes
		gameLayout = new BorderPane();
		gameLayout.setCenter(centerPane());	
		gameLayout.setBottom(botPanel());		
		gameLayout.setRight(rightPanel());	
		gameUI.setRoot(gameLayout);	

		System.out.println("\nCurrent player: " + player);	
	}

	/**
	*	@return a BoardGUI instance of the guess board display
	*/
	public BoardGUI getBoardNode() {
		return guessBoard;
	}
	
	/**
	*	The child node that will be placed in the center region of the main layout. The child node is a TilePane layout that contains a GridPane layout
	*	(created in BoardGUI) which is the display of the player's guess board. This node has also been set as an event listener that calls
	*	the AttackClickHandler class to take in mouse clicks from this node.
	*	
	*	@return 	a TilePane layout that displays the current player's guessing board
	*/
	public TilePane centerPane() {	
		
		TilePane centerSlot = new TilePane();
		//attack handler on the big board
		if (displayOnly == false) {
			guessBoard.getBoardGrid().setOnMousePressed(new AttackClickHandler(guessBoard.getGridBlockSize(), gameUI, Settings.p1, Settings.p2, attackingPlayer));
		}
		centerSlot.getChildren().add(guessBoard.getBoardGrid());
		return centerSlot; 	
	}

	/**
	*	The child node that will be placed in the right region of the main layout.
	*	@return 	a TilePane layout
	*/
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(Settings.sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));	
		rightPanel.getChildren().add(ownBoard.getBoardGrid());			
		return rightPanel;
	}	
	
	/**
	*	The child node that will be placed in the bottom region of the main layout.
	*	@return a GridPane layout
	*/	
	public GridPane botPanel() {
		
		GridPane botPanel = new GridPane(); 
		botPanel.setPrefHeight(Settings.botHeight);	
		botPanel.setMaxHeight(Settings.botHeight);				
		botPanel.setStyle("-fx-background-color: #ebcd98;");	//Hex color		
		botPanel.getChildren().add(Settings.message);
		return botPanel;
	}
}			