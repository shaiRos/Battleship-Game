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




public class SetupPhase {
	
	private Scene scene;
	private BorderPane root;
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;
	private Button endSetup;
	
	
	public SetupPhase(Scene scene) {
	
		root = new BorderPane();
		root.setCenter(battleField());	
		root.setBottom(botPanel());		
		root.setRight(rightPanel());	
		scene.setRoot(root);	

	}
	
	
	public Button setupEnd() {
		return endSetup;
	}

	
	public TilePane battleField() {	
		
		TilePane centerSlot = new TilePane();
		ownBoard = new BoardGUI(Settings.gridSize, Settings.bigGridWidth); 	
		centerSlot.getChildren().add(ownBoard.getBoardGrid());
		return centerSlot; 	
	}
	
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(Settings.sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));	
	
		guessBoard = new BoardGUI(Settings.gridSize, Settings.smallGridWidth);		
		rightPanel.getChildren().add(guessBoard.getBoardGrid());			
		return rightPanel;
	}	
	
	public TilePane botPanel() {
		
		TilePane botPanel = new TilePane(); 
		botPanel.setPrefHeight(Settings.botHeight);	
		botPanel.setMaxHeight(Settings.botHeight);				
		botPanel.setStyle("-fx-background-color: #CC6600;");	//Hex color		
		endSetup = new Button("Setup DONE");
		botPanel.getChildren().add(endSetup);
		return botPanel;
	}	

	

}	