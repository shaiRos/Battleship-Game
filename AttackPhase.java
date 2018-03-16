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


public class AttackPhase  {

	private Scene gameUI;	
	private BorderPane gameLayout;
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;	
	private String attackingPlayer;
	private Player player1;
	private Player player2;
	private Label coordinates = null;
	
	//constructor to display the attackPhase of a player and listens for input events
	
	public AttackPhase(Scene scenee, Player p1, Player p2, String player, Label coord) {

		attackingPlayer = player;
		gameUI = scenee;	
		ownBoard = new BoardGUI(p1.getPlayerBoard().getBoardSize(), Settings.smallGridWidth);
		guessBoard = new BoardGUI(p2.getPlayerBoard().getBoardSize(), Settings.bigGridWidth);
		player1 = p1;
		player2 = p2;
		coordinates = coord;

		//System.out.println("\ngameBoard: " + p1.getPlayerBoard().gameBoard.length + "  guessBoard: " + p1.getPlayerBoard().guessBoard.length);
		System.out.println("\ngameBoard: " + p1.getPlayerBoard().gameBoard.length + "  guessBoard: " + p1.getPlayerBoard().guessBoard.length);
		
		if (attackingPlayer == "P1") {
			ownBoard.addValuesFromArray(p1.getPlayerBoard().gameBoard, "gameBoard");
			guessBoard.addValuesFromArray(p1.getPlayerBoard().guessBoard, "guessBoard");

			}
		else if (attackingPlayer == "P2") {

				/* ownBoard.addValuesFromArray(p2.getPlayerBoard().gameBoard, "gameBoard");	
				guessBoard.addValuesFromArray(p2.getPlayerBoard().guessBoard, "guessBoard");		 */	
				
				ownBoard.addValuesFromArray(p2.getPlayerBoard().gameBoard, "gameBoard");
				guessBoard.addValuesFromArray(p2.getPlayerBoard().guessBoard, "guessBoard");
				
		}
		//Update the Display with the new changes
		gameLayout = new BorderPane();
		gameLayout.setCenter(centerPane());	
		gameLayout.setBottom(botPanel());		
		gameLayout.setRight(rightPanel());	
		gameUI.setRoot(gameLayout);	

		System.out.println("\nCurrent player: " + player);	
	}

	
	public BoardGUI getBoardNode() {
		return guessBoard;
	}
	

	public TilePane centerPane() {	
		
		TilePane centerSlot = new TilePane();
		//attack handler on the big board
		if (coordinates == null) {
			guessBoard.getBoardGrid().setOnMousePressed(new AttackClickHandler(guessBoard.getGridBlockSize(), gameUI, player1, player2, attackingPlayer));
		}
		centerSlot.getChildren().add(guessBoard.getBoardGrid());
		return centerSlot; 	
	}

	
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(Settings.sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));	
		rightPanel.getChildren().add(ownBoard.getBoardGrid());			
		return rightPanel;
	}	
	
	
	public GridPane botPanel() {
		
		GridPane botPanel = new GridPane(); 
		botPanel.setPrefHeight(Settings.botHeight);	
		botPanel.setMaxHeight(Settings.botHeight);				
		botPanel.setStyle("-fx-background-color: #CC6600;");	//Hex color		
		
		if (coordinates != null) {
			botPanel.getChildren().add(coordinates);
		}
		return botPanel;
	}
}			