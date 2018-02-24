import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class BattleShipGUI extends Application
{
	private int windowSize = 500;
	
	
	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		BorderPane uiLayout = new BorderPane();
		Scene gameUI = new Scene(uiLayout, windowSize,windowSize);
		
		
		//uiLayout.setTop(topPanel());		//don't think top and left panel is needed
		uiLayout.setBottom(botPanel());
		uiLayout.setCenter(battleField());	
		//uiLayout.setLeft(leftPanel());
		uiLayout.setRight(rightPanel());


		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	
	
	//using gridPanes since children can span multiple col or rows
	public GridPane battleField() {	
		
		int gridSize = 9;
		
		GridPane battleField = new GridPane();
		
		for (int y = 0; y < gridSize; y++) {
			//this sets the constraints for box size so the size doesn't automatically adjust to child inside
			ColumnConstraints column = new ColumnConstraints();
			RowConstraints row = new RowConstraints();			
			
			//so it fits the parent slot (center) for the grid whatever the size of the grid
			column.setPercentWidth(50);			
			row.setPercentHeight(50);			
			
			battleField.getColumnConstraints().add(column);			
			battleField.getRowConstraints().add(row);
			
			//ONLY FOR DEBUG. find another way to display grid lines. was too dark anyways...	
			battleField.setGridLinesVisible(true);
			
			/* For adding stuff in the grid
			for (int x = 0; x < gridSize; x++) {
				
			//grid isn't formed until you put something in it...
				Label label = new Label("wee");		
				battleField.add(label,y,x);	
			}	*/			
		}	
		
		return battleField;
	}

	public HBox topPanel() {
		
		HBox topPanel = new HBox();	    
		topPanel.setPadding(new Insets(50));
        topPanel.setStyle("-fx-background-color: red;");	
		return topPanel;
	}

	public GridPane botPanel() {
		
		GridPane botPanel = new GridPane();
		botPanel.setGridLinesVisible(true);
		botPanel.setPadding(new Insets(50));     
		botPanel.setStyle("-fx-background-color: red;");			
		
		return botPanel;
	}
	
	public VBox leftPanel() {
		
		VBox leftPanel = new VBox(10);
		leftPanel.setPadding(new Insets(50));
        leftPanel.setStyle("-fx-background-color: blue;");			
		return leftPanel;
	}	
	
	public VBox rightPanel() {
		
		VBox rightPanel = new VBox(10);
		rightPanel.setPadding(new Insets(50));
        rightPanel.setStyle("-fx-background-color: blue;");		
		return rightPanel;
	}
}	
	
	