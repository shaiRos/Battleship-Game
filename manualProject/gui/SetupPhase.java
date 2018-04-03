package gui;
import javafx.scene.Scene;
import javafx.stage.Stage;
import players.Player;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import board.Board;
import javafx.event.ActionEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Pos;
import javafx.scene.text.Font;



public class SetupPhase {
	
	private Scene scene;
	private BorderPane root;
	private BoardGUI ownBoard;
	private Player player;
	private int shipsToSet;
	private String thisPlayer;
	private boolean displayOnly;

	
	
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
		root.setCenter(battleField());	
		root.setBottom(botPanel());		
		root.setRight(rightPanel());	
		scene.setRoot(root);
		

	}

	
	public TilePane battleField() {	
		TilePane centerSlot = new TilePane(); 	
		centerSlot.getChildren().add(ownBoard.getBoardGrid());
		return centerSlot; 	
	}
	
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
		rightPanel.setGridLinesVisible(true);	

		
		if (displayOnly == false) {
			Button fiveLen = buttonForShipLen("Five", Settings.len5Ships,5);
			Button fourLen = buttonForShipLen("Four", Settings.len4Ships,4);
			Button threeLen = buttonForShipLen("Three", Settings.len3Ships,3);
			Button twoLen = buttonForShipLen("Two", Settings.len2Ships,2);		
			
			rightPanel.addColumn(0,fiveLen,fourLen,threeLen,twoLen);
		}
		
		return rightPanel;
	}

	public Button buttonForShipLen(String wordLen,int shipLenCount, int len) {
		Button makeButton = new Button(wordLen + ",  x" + shipLenCount);
		if (shipLenCount != 0) {
			makeButton.setOnMouseClicked(new SetupShipHandler(scene, len, root, thisPlayer, shipsToSet, ownBoard));
		}return makeButton;
		
	}
	
	public GridPane botPanel() {
		
		GridPane botPanel = new GridPane(); 
		botPanel.setPrefHeight(Settings.botHeight);	
		botPanel.setMaxHeight(Settings.botHeight);				
		botPanel.setStyle("-fx-background-color: #ebcd98;");	//Hex color		
		Settings.changeMessage("Ships to set: " + shipsToSet);
		botPanel.setAlignment(Pos.CENTER);
		botPanel.getChildren().add(Settings.message);
		
		
		
		return botPanel;
	}	

	

}	