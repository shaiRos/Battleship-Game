import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;


public class BattleShipGUI extends Application
{
	
	//DON'T TOUCH 	
	final private int xWindowSize = 1040;
	final private int yWindowSize = 920;
	

	private Stage primaryStage;
	private Scene gameUI;	
	private BorderPane mainMenu;

	public static void main(String [] args)
	{
		launch(args);
	}
								
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		primaryStage = primaryStage;
		mainMenu = new BorderPane();
		gameUI = new Scene(mainMenu, xWindowSize, yWindowSize);
		//enter setup stage
		SetupPhase setup = new SetupPhase(gameUI);
		setup.setupEnd().setOnMousePressed(new EndSetupHandler(gameUI));	
		

		primaryStage.setMaxHeight(yWindowSize);	
		primaryStage.setMaxWidth(xWindowSize + 15);		
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	

}	
	
	