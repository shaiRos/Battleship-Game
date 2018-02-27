import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;



public class BattleShipGUI extends Application
{
	private int windowSize = 500;
	private int gridSize = 10; //Default size
	//GridSize: 20 max for  WindowSize: 500 , increase window size if want a bigger gridSize  
	
	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		BorderPane uiLayout = new BorderPane();
		Scene gameUI = new Scene(uiLayout, windowSize,windowSize);
		
		

		uiLayout.setBottom(botPanel());
		uiLayout.setCenter(battleField());	
		uiLayout.setRight(rightPanel());
		//uiLayout.setPadding(new Insets(5));
		
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	
	
	//using gridPanes since children can span multiple col or rows
	public GridPane battleField() {	
		
		int Array[][] = new int [gridSize][gridSize];
		String object;
		
		//create the GridPane object for guess board
		BoardGUI battleField = new BoardGUI(gridSize);
		//adding values from a 2d array
		battleField.addValuesFromArray(Array);
		
		return battleField.getBoardGrid();
	}
	
	
	public HBox botPanel() {
		
		
		HBox botPanel = new HBox();   
		botPanel.setStyle("-fx-background-color: #CC6600;");	//Hex color		
		
		//make GridPane for own board
		BoardGUI ownBoard = new BoardGUI(gridSize);		
		botPanel.getChildren().add(ownBoard.getBoardGrid());	

		return botPanel;
	}
	
	
	public VBox rightPanel() {
		
		VBox rightPanel = new VBox(10);
		rightPanel.setPadding(new Insets(60));
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		
		return rightPanel;
	}	
	
	
	public HBox topPanel() {
		
		HBox topPanel = new HBox();	    
		topPanel.setPadding(new Insets(50));
        topPanel.setStyle("-fx-background-color: red;");	
		return topPanel;
	}


	
	public VBox leftPanel() {
		
		VBox leftPanel = new VBox(10);
		leftPanel.setPadding(new Insets(100));
        leftPanel.setStyle("-fx-background-color: blue;");			
		return leftPanel;
	}	
	

}	
	
	