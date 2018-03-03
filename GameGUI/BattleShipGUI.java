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
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;




public class BattleShipGUI extends Application
{
	
	//DON'T TOUCH 	
	final private int xWindowSize = 1040;
	final private int yWindowSize = 920;
	final private int botHeight = 150;
	final private int smallGridWidth = 250; //including margins
	final private int rightPanelWidth = 270;	
	final private int bigGridWidth = 770; //including margins	
	

	private int gridSize = 10; //max 20
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;

	private Stage primaryStage;
	private Scene gameUI;	
	private BorderPane uiLayout;
	private SidePane sidePane;
	private BaseGameLayout testUI;
	
	//private Scene testScene;
	
	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		primaryStage = primaryStage;
		uiLayout = new BorderPane();
		//testUI = new BaseGameLayout();
		gameUI = new Scene(uiLayout, xWindowSize, yWindowSize);
		//testScene = new Scene(testUI.BaseGameUI(), xWindowSize, yWindowSize);
		
		uiLayout.setCenter(battleField());	
		uiLayout.setBottom(botPanel());		
		uiLayout.setRight(rightPanel());

		//primaryStage.setResizable(false);			
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
		//centerSlot.setPrefTileWidth(770);
		guessBoard = new BoardGUI(gridSize, bigGridWidth); 

		//had it take a ship object to setup from
		//this should be changed into taking a ship array instead of individual ship objects
		Ship ship1 = new Ship('h', 5, 1, 1);
		Ship ship2 = new Ship('v', 3, 3, 3);			
		guessBoard.setupBoardFromShipObjects(ship1);
		guessBoard.setupBoardFromShipObjects(ship2);		
		
		guessBoard.getBoardGrid().setOnMousePressed(new AttackClickHandler(guessBoard, gameUI,0));
		centerSlot.getChildren().add(guessBoard.getBoardGrid());

		return centerSlot; 	
	}
	
	public TilePane rightPanel() {
		
		TilePane rightPanel = new TilePane();
		rightPanel.setPrefWidth(rightPanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));	
		
		ownBoard = new BoardGUI(gridSize, smallGridWidth);		
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
	
	