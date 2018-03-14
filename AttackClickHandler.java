import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class AttackClickHandler implements EventHandler<MouseEvent> {
	

	private int x;
	private int y;
	private double blockSize;
	private Scene scene;
	private String thisPlayer;
	private String nextPlayer;
	private Player playerAttacking;
	private Player playerAttacked;
	private Player player1;
	private Player player2;	
	private Label coordinate = new Label();
	private Board attackedPlayerBoard;
	
	public AttackClickHandler(double BlockSize, Scene scenee, Player p1, Player p2, String attackingPlayer) {

		player1 = p1;
		player2 = p2;
		scene = scenee;
		blockSize = BlockSize;

		if (attackingPlayer == "P1") {
			
			playerAttacking = p1;
			thisPlayer = "P1";
			playerAttacked = p2;
			nextPlayer = "P2";	
			
		}else if (attackingPlayer == "P2") {
			
			playerAttacking = p2;
			thisPlayer = "P2";
			playerAttacked = p1;
			nextPlayer = "P1";
						
		}
	}

	public void handle(MouseEvent myEvent) {

		//find the col and row that was clicked
		x = (int)((myEvent.getX()-10)/(blockSize))+1;
		y = (int)((myEvent.getY()-10)/blockSize)+1;
		System.out.println(x + ", " + y);
		//initiate attack
		
		coordinate.setText(x + ", " + y);
		coordinate.setFont(new Font(40));
		
		
		if (Game.getAIStatus() == true) {
			attackedPlayerBoard = ((ComputerPlayer)playerAttacked).playerBoard;
		} else {
			attackedPlayerBoard = ((HumanPlayer)playerAttacked).playerBoard;
		}

		
		boolean checkPrevHit = playerAttacked.checkPreviousHit((((HumanPlayer)playerAttacking).playerBoard), x, y);	
		
		if (checkPrevHit == true) {													//if they chose a coordinate they previously hit
			System.out.println("prevhit true, Please try again");
			AttackPhase testUI = new AttackPhase(scene,player1,player2, thisPlayer, null);//display current player again. ONLY FOR SHIPS. Misses don't count.
		} else {
			//Send Attack and update the board arrays ONLY FOR PVP
			playerAttacking.sendAttack((((HumanPlayer)playerAttacking).playerBoard), x, y);	
			//Win condition
			
			
			if ((Game.winCondition(attackedPlayerBoard)) == false) {
				//First Display if it Hit or miss
				AttackPhase displayOnly = new AttackPhase(scene,player1,player2, thisPlayer, coordinate);
				//Pause transition to display that waits for prompt for next player turn, or AI making a turn loading screen
				
				
				PauseTransition pause = new PauseTransition(Duration.seconds(.5));
				if (Game.getAIStatus() == false) {
					pause.setOnFinished(event -> scene.setRoot(pvpTurnTransition()));
				}
				else {	
					((ComputerPlayer) player2).playerTurn();
					pause.setOnFinished(event -> scene.setRoot(aiTurnTransition()));
				}
				pause.play();
					
									
				//AttackPhase nextDisplay = new AttackPhase(scene,player1,player2, nextPlayer, null);
			} else {
				System.out.println(thisPlayer + " has won"); //it stops the display but clicking still works...		
				coordinate.setText("You Win!");
				AttackPhase displayOnly = new AttackPhase(scene,player1,player2, thisPlayer, coordinate);				
			}
		}			
				
	}
	
	
	/**	Transition in PvP mode to hide previous player's display
	* 	makes a button so when clicked, changes the display to the next player's attack phase
	*	@return a new root for the scene to transition into after a pause for each turn
	*/
	public BorderPane pvpTurnTransition() {

		BorderPane display = new BorderPane();
		Button continueButton = new Button("Continue");
		display.setCenter(continueButton);
		EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent event) { 
				AttackPhase nextDisplay = new AttackPhase(scene,player1,player2, nextPlayer, null);
			}           
		};
		continueButton.setOnMouseClicked(eventHandlerTextField);
		return display;
	}
	
	
	
	/**	Transition in P-v-AI mode to let the player know that the AI is making a move
	* 	After a few seconds, the display returns to the Human player's attack phase
	*	@return a new root for the scene to transition into after a pause for each turn
	*/	
	public BorderPane aiTurnTransition() {

		BorderPane display = new BorderPane();
		Label message = new Label("AI is making a turn...");
		message.setFont(new Font(50));
		display.setCenter(message);
		
		//((ComputerPlayer) player2).playerTurn();
		//https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
		PauseTransition pause = new PauseTransition(Duration.seconds(1.5));		
		pause.setOnFinished(event -> {
			AttackPhase nextDisplay = new AttackPhase(scene,player1,player2, thisPlayer, null);          
		} );			
		pause.play();		
		return display;
	}		
}