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
	private Player player1;
	private Player player2;
	
	//constructor to display the attackPhase of a player and listens for input events
	
	public AttackPhase(Scene scenee, Player p1, Player p2, String player) {

		attackingPlayer = player;
		gameUI = scenee;	
		ownBoard = new BoardGUI(gridSize, smallGridWidth);
		guessBoard = new BoardGUI(gridSize, bigGridWidth);
		player1 = p1;
		player2 = p2;

		if (attackingPlayer == "P1") {
<<<<<<< HEAD:FORDEMO/AttackPhase.java
			ownBoard.addValuesFromArray(((HumanPlayer)p1).playerBoard.gameBoard);
			guessBoard.addValuesFromArray(((HumanPlayer)p1).playerBoard.guessBoard); //remember guess board also shows ships....in values
=======
			ownBoard.addValuesFromArray(((HumanPlayer)p1).playerBoard.gameBoard, "gameBoard");
			guessBoard.addValuesFromArray(((HumanPlayer)p1).playerBoard.guessBoard, "guessBoard"); //remember guess board also shows ships....in values
>>>>>>> 60e614f262448585db795713739fffc1946671b8:AttackPhase.java

			}
		else if (attackingPlayer == "P2") {

<<<<<<< HEAD:FORDEMO/AttackPhase.java
				ownBoard.addValuesFromArray(((HumanPlayer) p2).playerBoard.gameBoard);	
				guessBoard.addValuesFromArray(((HumanPlayer) p2).playerBoard.guessBoard);
=======
				ownBoard.addValuesFromArray(((HumanPlayer) p2).playerBoard.gameBoard, "gameBoard");	
				guessBoard.addValuesFromArray(((HumanPlayer) p2).playerBoard.guessBoard, "guessBoard");
>>>>>>> 60e614f262448585db795713739fffc1946671b8:AttackPhase.java
			/*} else {
				ownBoard.addValuesFromArray(((ComputerPlayer) player2).playerBoard.getGameBoard());	
				guessBoard.addValuesFromArray(((ComputerPlayer) player2).playerBoard.getGuessBoard());	*/				
				
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
		guessBoard.getBoardGrid().setOnMousePressed(new AttackClickHandler(guessBoard.getGridBlockSize(), gameUI, player1, player2, attackingPlayer));
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