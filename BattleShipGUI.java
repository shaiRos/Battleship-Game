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

public class BattleShipGUI extends Application
{
	private int windowSize = 455;
	
	
	public static void main(String [] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		
		
		BorderPane uiLayout = new BorderPane();
		Scene gameUI = new Scene(uiLayout, windowSize,windowSize);
		
		
		uiLayout.setTop(topPanel());
		uiLayout.setBottom(botPanel());
		uiLayout.setCenter(battleField());	
		uiLayout.setLeft(leftPanel());
		uiLayout.setRight(rightPanel());


		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	
	
	//using gridPanes, objects can span multiple col or rows
	public GridPane battleField() {	
		
		int gridBoxSize = 40;
		
		GridPane battleField = new GridPane();
		battleField.setGridLinesVisible(true); //ONLY FOR DEBUG
		battleField.setHgap(gridBoxSize);
		battleField.setVgap(gridBoxSize);
		
		Label label = new Label(" hello ");		
		battleField.add(label, 8, 8);

		
		
		/*for (int x = 0 ; x < 10 ; x++) {
			Label label = new Label(" ");
			battleField.add(label, x, x);
		}*/

		/*TilePane battleField = new TilePane();	
		battleField.setPadding(new Insets(10));
        battleField.setVgap(5);
        battleField.setHgap(5);
        battleField.setPrefColumns(8);			
		
		for (int x = 0 ; x < 64 ; x++) {
			
		
			TextField text = new TextField();
			text.setMaxWidth(25);
			battleField.getChildren().add(text);
		}*/
		
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
	
	