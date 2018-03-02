import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;




public class AttackClickHandler implements EventHandler<MouseEvent> {
	
	double blockSize;
	int x;
	int y;
	
	public AttackClickHandler(BoardGUI grid) {
		blockSize = grid.getGridBlockSize();
	}
	
	public void handle(MouseEvent myEvent) {
		
		x = (int)((myEvent.getX()-10)/(blockSize))+1;
		y = (int)((myEvent.getY()-10)/blockSize)+1;
		System.out.println(x + ", " + y);
	}
}