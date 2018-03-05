import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class EndSetupHandler implements EventHandler<MouseEvent> {
	
	Button doneButton;
	BoardGUI FirstTurnGuessBoard;
	BoardGUI FirstTurnOwnBoard;
	int gridSize;
	Scene scne;



	public EndSetupHandler(Scene scene) {
		
		scne = scene;
		
		Settings setting = new Settings();
		gridSize = setting.gridSize;		
		
		FirstTurnGuessBoard = new BoardGUI(gridSize, 770);
		FirstTurnOwnBoard = new BoardGUI(gridSize, 250);
	}
	
	public void handle(MouseEvent myEvent) {
		
		Ship ship1 = new Ship('h', 2, 2, 1);
		Ship ship2 = new Ship('v', 3, 3, 3);			
		FirstTurnOwnBoard.setupBoardFromShipObjects(ship1);
		FirstTurnOwnBoard.setupBoardFromShipObjects(ship2);			


	
		AttackPhase change = new AttackPhase(scne, FirstTurnOwnBoard, FirstTurnGuessBoard, 0);	

	
	}
}
		