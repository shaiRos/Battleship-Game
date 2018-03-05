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
//=========================================================================================================  

	//Settings should be set in main menu
		Settings settings = new Settings();
		settings.setGridSize(5);
        int userShipCount = 2;
		settings.setupMode("mapFromFiles", "map.txt");




        Board player1Board = new Board();
        player1Board.setBoardSize(settings.gridSize);
        Board player2Board = new Board();
        player2Board.setBoardSize(settings.gridSize);

		
		
		// setupBoard(player1Board, player2Board, userShipCount);
        Game.mapFromFiles(settings.fileName, player1Board);
        Game.mapFromFiles(settings.fileName, player2Board);
//=========================================================================================================
		SetupPhase setup = new SetupPhase(gameUI, player1Board, player2Board);
		setup.setupEnd().setOnMousePressed(new EndSetupHandler(gameUI));	
		

		primaryStage.setMaxHeight(yWindowSize);	
		primaryStage.setMaxWidth(xWindowSize + 15);		
		primaryStage.setTitle("BattleShip");
		primaryStage.setScene(gameUI);
		primaryStage.show();
	}
	

}	
	
	