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
import javafx.scene.layout.Region;




public class BattleShipGUI extends Application
{
	private int gridSize = 20; //can change size
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;
	private Scene gameUI;
	
	//DON'T TOUCH or it'll BREAK
	final private int xWindowSize = 1040;
	final private int yWindowSize = 920;
	final private int botHeight = 150;
	final private int rightWidth = 250; 
	final private int bigGridWidth = xWindowSize - rightWidth - 20; //20 = rightWidth inset
	//bigGridSize = (xWindowSize - rightWidth - 10) x (yWindowSize - botHeight + 10)
	//current 770 x 770 
	
	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		BorderPane uiLayout = new BorderPane();
		gameUI = new Scene(uiLayout, 1040, yWindowSize);

		uiLayout.setBottom(botPanel());
		uiLayout.setCenter(battleField());	
		uiLayout.setRight(rightPanel());
		//uiLayout.setPadding(new Insets(5));
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	
	
	//using gridPanes since children can span multiple col or rows
	public TilePane battleField() {	
		
		TilePane centerSlot = new TilePane();
		int Array[][] = new int [gridSize][gridSize];
		Array[0][0] = 5; //WIP laying out one ship image for consecutive values of 5..
		Array[0][1] = 5; //from 2d Array
		Array[0][2] = 5; 
		
		//create the GridPane object for guess board
		guessBoard = new BoardGUI(gridSize, 0, bigGridWidth); //rightwidth doesn't actually do anything since center wraps to parent slot for center.
		//adding values from a 2d array
		//guessBoard.addValuesFromArray(Array);

		//had it take a ship object to setup from
		//this should be changed into taking a ship array instead of individual ship objects
		Ship ship1 = new Ship('h', 5, 0, 0);
		guessBoard.setupBoardFromShipObjects(ship1);
		
		Ship ship2 = new Ship('v', 3, 3, 3);		
		guessBoard.setupBoardFromShipObjects(ship2);		
		
		//sets dimensions of Large grid
		centerSlot.setPrefTileWidth(xWindowSize - rightWidth - 10);	
		centerSlot.setPrefTileHeight(yWindowSize - botHeight + 10); //this measurement is broken because of bot panel but is currently working for now
		centerSlot.getChildren().add(guessBoard.getBoardGrid());
		return centerSlot;  //Definitely some privacy issues here I think....
	}
	
	
	public HBox botPanel() {
		
		
		HBox botPanel = new HBox(10); 
		botPanel.setPrefHeight(botHeight);	
		botPanel.setMaxHeight(botHeight);			
		botPanel.setPadding(new Insets(60));		
		botPanel.setStyle("-fx-background-color: #CC6600;");	//Hex color		
		return botPanel;
	}
	
	
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(rightWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));
		rightPanel.setPrefTileWidth(rightWidth);	
		
		ownBoard = new BoardGUI(gridSize, rightWidth, bigGridWidth);		
		//rightPanel.setPrefTileHeight(yWindowSize - botHeight);		
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
	
	