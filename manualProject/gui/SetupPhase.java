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
		//rightPanel.setGridLinesVisible(true);	

		
		if (displayOnly == false) {
			Button fiveLen = new Button("Five");
			Button fourLen = new Button("Four");
			Button threeLen = new Button("Three");
			Button twoLen = new Button("Two");
			
			fiveLen.setOnMouseClicked(new SetupShipHandler(scene, 5, root, thisPlayer, shipsToSet, ownBoard));
			fourLen.setOnMouseClicked(new SetupShipHandler(scene, 4, root, thisPlayer, shipsToSet, ownBoard));
			threeLen.setOnMouseClicked(new SetupShipHandler(scene, 3, root, thisPlayer, shipsToSet, ownBoard));	
			twoLen.setOnMouseClicked(new SetupShipHandler(scene, 2, root, thisPlayer, shipsToSet, ownBoard));			
			
			rightPanel.addColumn(0,fiveLen,fourLen,threeLen,twoLen);
		}
		
		return rightPanel;
	}	
	
	public TilePane botPanel() {
		
		TilePane botPanel = new TilePane(); 
		botPanel.setPrefHeight(Settings.botHeight);	
		botPanel.setMaxHeight(Settings.botHeight);				
		botPanel.setStyle("-fx-background-color: #CC6600;");	//Hex color		

		return botPanel;
	}	

	

}	