import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class AttackClickHandler implements EventHandler<MouseEvent> {
	
	double blockSize;
	int x;
	int y;
	Scene scne;
	BoardGUI nextPlayerOwnBoard;
	BoardGUI nextPlayerGuessBoard;
	
	int l;
	
	public AttackClickHandler(BoardGUI grid, Scene stage, int num) {
		
		nextPlayerGuessBoard = new BoardGUI(10, 770);
		nextPlayerOwnBoard = new BoardGUI(10, 250);
		scne = stage;
		blockSize = grid.getGridBlockSize();
		
		
		l = num;
	}
	
	public void handle(MouseEvent myEvent) {
		
		x = (int)((myEvent.getX()-10)/(blockSize))+1;
		y = (int)((myEvent.getY()-10)/blockSize)+1;
		System.out.println(x + ", " + y);
		
		l += 1;
		
		Ship ship1 = new Ship('h', 5, l, 1);
		Ship ship2 = new Ship('v', 3, 3, 3);			
		nextPlayerOwnBoard.setupBoardFromShipObjects(ship1);
		nextPlayerOwnBoard.setupBoardFromShipObjects(ship2);	


		
		
		
		BaseGameLayout testUI = new BaseGameLayout(scne, nextPlayerOwnBoard, nextPlayerGuessBoard, l);
		//Scene testScene = new Scene(testUI.BaseGameUI(), 1040, 920);
		
		
		scne.setRoot(testUI.BaseGameUI());

		//
		
		
	}
}