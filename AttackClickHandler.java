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

/**
 	The event handler for the board in the display where players click where they want to attack. 
	it deals with taking the player's input, managing and updating the boards of all players, 
	displaying the next player's turn and checking for win condition. 
	<p>
	The event handler ends by calling AttackPhase where the attack phase of the next player is displayed and the board 
	is ready to receive  input from the player which in turn calls this event handler again. This loop ends when the win 
	condition is met. By then. It calls another AttackPhase display where the event listener for the board is disabled 
	which ends the game and stops the loop.
	
	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayang

**/

public class AttackClickHandler implements EventHandler<MouseEvent> {
	
	private Player playerAttacking;	
	private Player playerAttacked;
	private Player player1;
	private Player player2;	
	private String thisPlayer;
	private String nextPlayer;	
	private double blockSize;	
	private int x;
	private int y;
	private Scene scene;
	private Label coordinate = new Label();
	
	
	
	/**
	*	The constructor for this event handler. It sets up which player is attacking and which player is attacked
	*	so that it can modify the boards of the corresponding player.
	*	
	*	@param		BlockSize - a double type value of the size of a single square in the current grid
	*	@param 		scn - the Scene where the root will be changed to display the next player's attack phase
	*	@param		p1 - a Player instance of the first player
	*	@param		p2 - a Player instance of the second payer
	*	@param		attackingPlayer - a String that indicates which player attacked and initiated this event handler
	*/
	public AttackClickHandler(double BlockSize, Scene scn, Player p1, Player p2, String attackingPlayer) {

		player1 = p1;
		player2 = p2;
		scene = scn;
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

	/**	Finds Column and row clicked, checks if it was previously hit (display doesn't continue if it is)
	*	Sends Attack which updates the player's guessBoard and enemy gameBoard
	*	Checks for win condition, if false, displays the outcome of the action first (hit or miss) then 
	*	displays transition modes.
	*/	
	public void handle(MouseEvent myEvent) {
		//find the col and row that was clicked
		x = (int)((myEvent.getX()-10)/(blockSize))+1;
		y = (int)((myEvent.getY()-10)/blockSize)+1;
		System.out.println(x + ", " + y);
		//initiate attack
		
		coordinate.setText(thisPlayer +" attacked coordinates: " + x + ", " + y);
		coordinate.setFont(new Font(40));
		
		boolean checkPrevHit = playerAttacked.checkPreviousHitEnum(playerAttacking.getPlayerBoard(), x, y);	
		
		if (checkPrevHit == true) {													
			System.out.println("prevhit true, Please try again");
			AttackPhase testUI = new AttackPhase(scene,player1,player2, thisPlayer, null);
		} else {
			//Send the attack of this player and change the boards
			playerAttacking.sendAttack(playerAttacking.getPlayerBoard(), x, y);	
			//Win condition
			if ((Game.winCondition(playerAttacked.getPlayerBoard())) == false) {
				//First Display if it Hit or miss
				AttackPhase displayOnly = new AttackPhase(scene,player1,player2, thisPlayer, coordinate);
				//Pause transition to display that waits for prompt for next player turn, or AI making a turn loading screen
				PauseTransition pause = new PauseTransition(Duration.seconds(1));
				//Pause transition differ between each mode
				if (Game.getAIStatus() == false) {
					pause.setOnFinished(event -> scene.setRoot(pvpTurnTransition()));
				}
				else {	
					pause.setOnFinished(event -> scene.setRoot(aiTurnTransition()));
				}
				pause.play();
					
			} else {
				System.out.println(thisPlayer + " has won"); 
				coordinate.setText("You Win!");
				AttackPhase displayOnly = new AttackPhase(scene,player1,player2, thisPlayer, coordinate);				
			}
		}			
				
	}
	
	
	/**	
	*	Transition in PvP mode to hide previous player's display since both players shouldn't 
	* 	see the other player's boards. It also makes a button so that the display doesn't automatically
	*	go to the next player's attack phase until he/she clicks the button. 
	*	
	*	@return a new root for the scene to transition into for the pause for each turn. (with a button)
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
	
	
	
	/**	
	*	Transition in P-v-AI mode to let the player know that the AI is making a move
	* 	initiates playerTurn for AI which makes the attack for the AI and updates the boards.
	* 	After a few seconds, the display returns to the Human player's attack phase with the updated boards.
	*	The class AttackPhase is called to display 
	*
	*	@return a new root for the scene to transition into for the pause for each turn
	*/	
	public BorderPane aiTurnTransition() {

		BorderPane display = new BorderPane();
		Label message = new Label("AI is making a turn...");
		message.setFont(new Font(50));
		display.setCenter(message);
		player2.playerTurn();
		//https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
		PauseTransition pause = new PauseTransition(Duration.seconds(.7));		
		pause.setOnFinished(event -> {
			if ((Game.winCondition(player1.getPlayerBoard())) == false) {			
				AttackPhase nextDisplay = new AttackPhase(scene,player1,player2, thisPlayer, null); 
			} else {
				System.out.println(thisPlayer + " has won"); //it stops the display but clicking still works...		
				coordinate.setText("You Lose!");
				AttackPhase displayOnly = new AttackPhase(scene,player1,player2, thisPlayer, coordinate);				
			} 
		}
		);			
		pause.play();			
		return display;
	}		
}