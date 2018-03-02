import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;




public class AttackClickHandler implements EventHandler<MouseEvent> {
	
	double blockSize;
	
	public AttackClickHandler(BoardGUI grid) {
		blockSize = grid.getGridBlockSize();
	}
	
	public void handle(MouseEvent myEvent) {

		System.out.println(((int)((myEvent.getX()-5)/(blockSize))+1) + ", " + ((int)((myEvent.getY()-5)/blockSize)+1));
	}
}