import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;


public class BattleShipGUI extends Application
{
	
	//DON'T TOUCH 	
	final private int xWindowSize = 1040;
	final private int yWindowSize = 920;
	

	private Stage primaryStage;
	private Scene gameUI;	
	private BorderPane uiLayout;

	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		primaryStage = primaryStage;
		uiLayout = new BorderPane();
		gameUI = new Scene(uiLayout, xWindowSize, yWindowSize);
		//enter setup stage
		SetupScene test = new SetupScene(gameUI);

		primaryStage.setMaxHeight(yWindowSize);	
		primaryStage.setMaxWidth(xWindowSize + 15);		
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	

}	
	
	