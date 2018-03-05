import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class AttackClickHandler implements EventHandler<MouseEvent> {
	

	private int x;
	private int y;
	private double blockSize;
	private Scene scne;
	private String nextPlayer;
	
	
	public AttackClickHandler(BoardGUI grid, Scene scene, String attackingPlayer) {
		

		scne = scene;
		blockSize = grid.getGridBlockSize();	//only needed the grid to get block size
		
		if (attackingPlayer == "P1") {
			nextPlayer = "P2";
		}else if (attackingPlayer == "P2") {
			nextPlayer = "P1";
		}
	}
	
	public void handle(MouseEvent myEvent) {

		//find the col and row that was clicked
		x = (int)((myEvent.getX()-10)/(blockSize))+1;
		y = (int)((myEvent.getY()-10)/blockSize)+1;
		System.out.println(x + ", " + y);
		


		//FEED TO PLAYERTURN METHOD IN TXT VERSION
		
		
		//Change the display into the next player's attack phase
		AttackPhase testUI = new AttackPhase(scne, nextPlayer);
		
	}
}