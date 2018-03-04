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


public class BaseGameLayout extends Settings {

	private Scene gameUI;	
	private BorderPane uiLayout;
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;	
	int l;
	
	//constructor for AttackEventHandler to update scene for each turn
	public BaseGameLayout(Scene scene, BoardGUI ownboard, BoardGUI guessboard, int num) {

		ownBoard = ownboard;  //gonna make the new board objects here taken from the arrayListBoard
		guessBoard = guessboard;
		gameUI = scene;
		l += num;
		
		uiLayout = new BorderPane();
		uiLayout.setCenter(battleField());	
		uiLayout.setBottom(botPanel());		
		uiLayout.setRight(rightPanel());	
		gameUI.setRoot(uiLayout);		

	}
		
	//using gridPanes since children can span multiple col or rows
	public TilePane battleField() {	
		
		TilePane centerSlot = new TilePane();
		guessBoard.getBoardGrid().setOnMousePressed(new AttackClickHandler(guessBoard, gameUI,l));
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