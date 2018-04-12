package gui;
import javafx.scene.Scene;
import players.Player;
import board.Board;

import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;


/**
* 	The setup phase where players setup their ships in their board. Players are given a number of ships for each
*	specific ship length. Players have to setup all the given ships.
*	<p>
*	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayang
*/
public class SetupPhase {
	
	private Scene scene;
	private BorderPane root;
	private BoardGUI ownBoard;
	private Player player;
	private int shipsToSet;
	private String thisPlayer;
	private boolean displayOnly;

	
	/**
	*	The constructor of the SetupPhase. It creates the display layout of the setup phase depending on which player is currently setting up.
	*	Using the three regions from BorderPane (center, bottom, right) it displays the current player's own board at the center 
	*	region where they will be setting up. The right region contains a pane with buttons representing a ship length. Players will be choosing
	*	ships to place from the right pane and clicking at their own board to place it. 
	*
	*	@param 		scn - The scene of the game. changes the root as the setupPhase display changes.
	*	@param		playerSettingUp - a string indicating which player is setting up
	*	@param		numOfShips - an integer indicating the number of ships the player has to place in his/her board
	*	@param		displayOnly - a boolean indicating if this layout will only be used for display. (event handlers are disabled)
	*/	
	public SetupPhase(Scene scn, String playerSettingUp, int numOfShips, boolean displayonly) {
		
		scene = scn;
		thisPlayer = playerSettingUp;
		displayOnly = displayonly;
		if (playerSettingUp == "P1") {
			player = Settings.p1;
		} else if (playerSettingUp == "P2") {
			player = Settings.p2;
		}
		shipsToSet = numOfShips;
		ownBoard = new BoardGUI(Board.getBoardSize(), Settings.bigGridWidth);		
		ownBoard.addValuesFromArray(player, "gameBoard");		
		root = new BorderPane();
		root.setCenter(centerPane());	
		root.setBottom(botPanel());		
		root.setRight(rightPanel());	
		scene.setRoot(root);
		

	}

	/**
	*	The child node that will be placed in the center region of the main layout. The child node is a TilePane layout that contains a GridPane layout
	*	(created in BoardGUI) which is the display of the player's own board.
	*	
	*	@return 	a TilePane layout that displays the current player's own board
	*/	
	public TilePane centerPane() {	
		TilePane centerSlot = new TilePane(); 	
		centerSlot.getChildren().add(ownBoard.getBoardGrid());
		return centerSlot; 	
	}
	
	
	/**
	*	The child node that will be placed in the right region of the main layout. It consists of a GridPane layout with
	*	4 rows and one column. Each row represents a section for a specific length of a ship. When displayOnly is true, buttons
	*	are hidden and this region only contains nothing but a background color. Button actions are directed into the SetupShipHandler.
	*
	*	@return		a GridPane layout that displays the ship lengths and the number of ships of a specific length the player can place. (when displayOnly is false)
	*/
	public GridPane rightPanel() {
		
		GridPane rightPanel = new GridPane();
		rightPanel.setPrefWidth(Settings.sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));	

		for (int x = 0; x < 4; x++) {
			RowConstraints row = new RowConstraints();	
			row.setPercentHeight(50);			
			rightPanel.getRowConstraints().add(row);
		}
		ColumnConstraints column = new ColumnConstraints();		
		column.setPercentWidth(100);		
		rightPanel.getColumnConstraints().add(column);			
		
		if (displayOnly == false) {
			VBox fiveLen = buttonForShipLen("Five", Settings.len5Ships,5);
			VBox fourLen = buttonForShipLen("Four", Settings.len4Ships,4);
			VBox threeLen = buttonForShipLen("Three", Settings.len3Ships,3);
			VBox twoLen = buttonForShipLen("Two", Settings.len2Ships,2);		
			rightPanel.addColumn(0,fiveLen,fourLen,threeLen,twoLen);
		}
		
		return rightPanel;
	}

	
	/**
	*	Creates the VBox layout for each ship length. The top section indicates the ship count of that specific length. 
	*	At the bottom is the button.The button will be disabled if the ship count of the specific ship length
	*	is 0. The ship count will be used up when the player successfully sets up that specific ship length into his/her board. 
	*	The ship count is created in settings which is taken from the array generatedShipsArray in Board class.
	*
	*	@return 	a VBox layout of a section of a ship length 
	*/
	public VBox buttonForShipLen(String wordLen,int shipLenCount, int len) {
		Button makeButton = new Button(wordLen);
		if (shipLenCount != 0) {
			makeButton.setOnMouseClicked(new SetupShipHandler(scene, len, root, thisPlayer, shipsToSet, ownBoard));
		}
			
		VBox section = new VBox(20);
		section.setAlignment(Pos.CENTER);
		Label label = new Label("x" + shipLenCount);
		label.setFont(new Font(40));
		label.setTextFill(Color.WHITE);
		
		section.getChildren().addAll(label,makeButton);			
		return section;	
	}
	
	/**
	*	The bottom panel consists of messages for the user.
	*
	*	@return 	a GridPane layout 
	*/
	public GridPane botPanel() {
		
		GridPane botPanel = new GridPane(); 
		botPanel.setPrefHeight(Settings.botHeight);	
		botPanel.setMaxHeight(Settings.botHeight);				
		botPanel.setStyle("-fx-background-color: #ebcd98;");	//Hex color		
		Settings.changeMessage("Ships remaining: " + shipsToSet);
		botPanel.setAlignment(Pos.TOP_CENTER);
		botPanel.getChildren().add(Settings.message);
		return botPanel;
	}	
}	