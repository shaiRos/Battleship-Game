import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
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
import javafx.scene.control.Label;



public class BattleShipGUI extends Application
{
	private int windowSize = 900;
	private int gridSize = 10; 
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;
	
	//experimenting with the sizes of everything in the gui
	private int xWindowSize = 900;
	private int yWindowSize = 800;
	private int botHeight = 150;
	private int rightWidth = 250;
	//helo;
	//bigGridSize = (xWindowSize - rightWidth) x (yWindowSize - botHeight)
	
	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		BorderPane uiLayout = new BorderPane();
		Scene gameUI = new Scene(uiLayout, xWindowSize, yWindowSize);
		
		

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
		guessBoard = new BoardGUI(gridSize, rightWidth); //rightwidth doesn't actually do anything since center wraps to parent slot for center.
		//adding values from a 2d array
		guessBoard.addValuesFromArray(Array);
		//battleField.setPrefWidth(200);
		
		return guessBoard.getBoardGrid();  //Definitely some privacy issues here I think....
	}
	
	
	public HBox botPanel() {
		
		
		HBox botPanel = new HBox(10); 
		botPanel.setPrefHeight(botHeight);	
		botPanel.setPadding(new Insets(60));		
		botPanel.setStyle("-fx-background-color: #CC6600;");	//Hex color		


		return botPanel;
	}
	
	
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(rightWidth);
		//rightPanel.setPadding(new Insets(60));
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		ownBoard = new BoardGUI(gridSize, rightWidth);		
		rightPanel.getChildren().add(ownBoard.getBoardGrid());			
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
	
	