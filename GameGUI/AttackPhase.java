import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;


public class AttackPhase extends Settings {

	private Scene gameUI;	
	private BorderPane gameLayout;
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;	
	private String attackingPlayer;
	
	//constructor to display the attackPhase of a player
	public AttackPhase(Scene scene, String player) {

		attackingPlayer = player;
		gameUI = scene;

		ownBoard = new BoardGUI(gridSize, smallGridWidth);
		guessBoard = new BoardGUI(gridSize, bigGridWidth);

		//display the current state of the player's (attacking player) guess and own board
		if (attackingPlayer == "P1") {
			ownBoard.addValuesFromArray(player1OwnBoard);
			guessBoard.addValuesFromArray(player1GuessBoard);

		}
		else if (attackingPlayer == "P2") {
			ownBoard.addValuesFromArray(player2OwnBoard);	
			guessBoard.addValuesFromArray(player2GuessBoard);
		}					

		//Update the Display with the new changes
		gameLayout = new BorderPane();
		gameLayout.setCenter(centerPane());	
		gameLayout.setBottom(botPanel());		
		gameLayout.setRight(rightPanel());	
		gameUI.setRoot(gameLayout);		

		System.out.println("\nCurrent player: " + player);	
	}
		

	public TilePane centerPane() {	
		
		TilePane centerSlot = new TilePane();
		//attack handler on the big board
		guessBoard.getBoardGrid().setOnMousePressed(new AttackClickHandler(guessBoard, gameUI,attackingPlayer));
		centerSlot.getChildren().add(guessBoard.getBoardGrid());
		return centerSlot; 	
	}

	
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));	
		
		rightPanel.getChildren().add(ownBoard.getBoardGrid());			
		return rightPanel;
	}	
	
	
	public TilePane botPanel() {
		
		TilePane botPanel = new TilePane(); 
		botPanel.setPrefHeight(botHeight);	
		botPanel.setMaxHeight(botHeight);				
		botPanel.setStyle("-fx-background-color: #CC6600;");	//Hex color		
		return botPanel;
	}
	


	

}			