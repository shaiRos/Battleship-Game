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




public class SetupPhase extends Settings{
	
	private Scene scene;
	private BorderPane root;
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;
	private Button endSetup;
	private Board player;
	
	
	public SetupPhase(Scene scene) {
	
		root = new BorderPane();
		root.setCenter(battleField());	
		root.setBottom(botPanel());		
		root.setRight(rightPanel());	
		scene.setRoot(root);
		player = new Board();	

	}
	
	
	public Button setupEnd() {
		return endSetup;
	}

	
	public TilePane battleField() {	
		
		TilePane centerSlot = new TilePane();
		ownBoard = new BoardGUI(gridSize, bigGridWidth); 	
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
		endSetup = new Button("Setup DONE");
		botPanel.getChildren().add(endSetup);
		return botPanel;
	}	

	

}	