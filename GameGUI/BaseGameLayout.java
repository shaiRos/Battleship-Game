
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


public class BaseGameLayout {
	
	final private int xWindowSize = 1040;
	final private int yWindowSize = 920;
	final private int botHeight = 150;
	final private int smallGridWidth = 250; //including margins
	final private int rightPanelWidth = 270;	
	final private int bigGridWidth = 770; //including margins		
	
	private int gridSize = 10; //max 20
	private BoardGUI guessBoard;
	private BoardGUI ownBoard;

	private Scene gameUI;	

	private SidePane sidePane;
	private BorderPane uiLayout;
	
	int l;
	
	public BaseGameLayout(Scene scene, BoardGUI ownboard, BoardGUI guessboard, int num) {
		
		ownBoard = ownboard;
		guessBoard = guessboard;
		gameUI = scene;
		l += num;
	}
	
	
	
	
	public BorderPane BaseGameUI() {
	
		uiLayout = new BorderPane();
		uiLayout.setCenter(battleField());	
		uiLayout.setBottom(botPanel());		
		uiLayout.setRight(rightPanel());	
		return uiLayout;
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
		rightPanel.setPrefWidth(rightPanelWidth);
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