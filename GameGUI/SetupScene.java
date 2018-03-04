import javafx.scene.Scene;
import javafx.stage.Stage;
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
import javafx.event.ActionEvent;




public class SetupScene extends Settings{
	
	private Scene scene;
	
	private BorderPane root;
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;
	
	public SetupScene(Scene scene) {
		
		root = new BorderPane();
		root.setCenter(battleField());	
		root.setBottom(botPanel());		
		root.setRight(rightPanel());	
		scene.setRoot(root);		
		//Once setup is done, call this vvv
		ownBoard.getBoardGrid().setOnMousePressed(new AttackClickHandler(ownBoard, scene,0));
	}
	

	public TilePane battleField() {	
		
		TilePane centerSlot = new TilePane();
		int Array[][] = new int [gridSize][gridSize];
		Array[0][0] = 5; //WIP laying out one ship image for consecutive values of 5..
		Array[0][1] = 5; //from 2d Array
		Array[0][2] = 5; 
		ownBoard = new BoardGUI(gridSize, bigGridWidth); 
		//ownBoard.addValuesFromArray(Array);

		Ship ship1 = new Ship('h', 5, 1, 1);
		Ship ship2 = new Ship('v', 3, 3, 3);			
		ownBoard.setupBoardFromShipObjects(ship1);
		ownBoard.setupBoardFromShipObjects(ship2);		
	
		centerSlot.getChildren().add(ownBoard.getBoardGrid());

		return centerSlot; 	
	}
	
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));	
		
		guessBoard = new BoardGUI(gridSize, smallGridWidth);		
		rightPanel.getChildren().add(guessBoard.getBoardGrid());			
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