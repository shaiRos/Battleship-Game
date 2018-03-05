import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class EndSetupHandler implements EventHandler<MouseEvent> {
	
	private Scene scene;
	
	public EndSetupHandler(Scene scenee) {
		
		scene = scenee;		
	}
	
	public void handle(MouseEvent myEvent) {

		//start attack phase of the game	player 1 goes first so it displays p1 first
		AttackPhase start = new AttackPhase(scene, "P1"); 	

	
	}
}
		