package gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import game.Game;
import game.GameConfig;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import players.ComputerPlayer;
import players.Player;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

/**
 	The event handler for the board in the display where players click where they want to attack. 
	it deals with taking the player's input, managing and updating the boards of all players, 
	displaying the next player's turn and checking for win condition. 
	<p>
	The event handler ends by calling AttackPhase where the attack phase of the next player is displayed and the board 
	is ready to receive  input from the player which in turn calls this event handler again. This loop ends when the win 
	condition is met. By then. It calls another AttackPhase display where the event listener for the board is disabled 
	which ends the game and stops the loop.
	
	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayag

*/

public class AttackClickHandler implements EventHandler<MouseEvent> {
	
	private Player playerAttacking;	
	private Player playerAttacked;
	private String nextPlayer;	
	private double blockSize;	
	private int x;
	private int y;
	private Scene scene;
	private static Boolean shipSunk = false;
	
	
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
	public AttackClickHandler(double BlockSize, Scene scn, String attackingPlayer) {

		scene = scn;
		blockSize = BlockSize;

		if (attackingPlayer.equals("P1")) {
			
			playerAttacking = Settings.p1;
			playerAttacked = Settings.p2;
			nextPlayer = "P2";				
			
		}else if (attackingPlayer.equals("P2")) {
			
			playerAttacking = Settings.p2;
			playerAttacked = Settings.p1;
			nextPlayer = "P1";
						
		}		
	}

	/**	Finds Column and row clicked, checks if it was previously hit )
	*	Sends Attack which updates the player's guessBoard and enemy gameBoard
	*	Checks for win condition then displays transition modes.
	*/	
	public void handle(MouseEvent myEvent) {
		//find the col and row that was clicked
		x = (int)((myEvent.getX()-10)/(blockSize))+1;
		y = (int)((myEvent.getY()-10)/blockSize)+1;
		//initiate attack
		
		if (x >= 1 && x <= Settings.boardSize && y >= 1 && y <= Settings.boardSize) {
			
			boolean checkPrevHit = playerAttacked.checkPreviousHitEnum(playerAttacking.getPlayerBoard(), y, x);	
			
			if (checkPrevHit == true) {		
				Settings.changeMessage("Previously hit");
			} else {
				//Send the attack of this player and change the boards
				GameConfig.sendAttack(playerAttacking.getPlayerBoard(), y, x);	
				//Win condition
				
				shipSunk = GameConfig.checkSunken(playerAttacked.getPlayerBoard(),y,x);
				
				if ((Game.winCondition(playerAttacked.getPlayerBoard())) == false) {
					//First Display if it Hit or miss
					Settings.changeMessage(playerAttacking.getName() +" attacked coordinates: " + y + ", " + x);
					if (shipSunk == true) {
						Settings.changeMessage("You sunk a ship!");
					}
					AttackPhase displayOnly = new AttackPhase(scene, playerAttacking.getName(), true);
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
					Settings.makeMsgLarger();
					Settings.changeMessage("You Win!");
					AttackPhase displayOnly = new AttackPhase(scene, playerAttacking.getName(), true);				
				}
			}	
		}			
				
	}
	
	
	/**	
	*	Transition in PvP mode to hide previous player's display since both players shouldn't 
	* 	see the other player's boards. 
	*	
	*	@return a new root for the scene to transition into for the pause for each turn. (with a button)
	*/
	public VBox pvpTurnTransition() {

		VBox display = new VBox(100);
		Button continueButton = new Button("Continue");
		continueButton.setPrefSize(200,60);
		Label aLabel = new Label(nextPlayer + "'s turn");
		aLabel.setFont(new Font(50));
		
		
		display.setAlignment(Pos.CENTER);
		EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent event) { 
				AttackPhase nextDisplay = new AttackPhase(scene, nextPlayer, false);
			}           
		};
		continueButton.setOnMouseClicked(eventHandlerTextField);
		display.getChildren().addAll(aLabel, continueButton);		
		return display;
	}
	
	
	
	/**	
	*	Transition in P-v-AI mode to let the player know that the AI is making a move
	* 	initiates playerTurn for AI which makes the attack for the AI and updates the boards.
	* 	After a few seconds, the display returns to the Human player's attack phase with the updated boards.
	*
	*	@return a new root for the scene to transition into for the pause for each turn
	*/	
	public BorderPane aiTurnTransition() {
		
		Settings.changeMessage("");
		BorderPane display = new BorderPane();
		Label message = new Label("AI is making a turn...");
		message.setFont(new Font(50));
		display.setCenter(message);
		
		String coordEnemy = Settings.p2.playerTurn();
		String[] coordFormattedEnemy = coordEnemy.split(",");
		int column2 = Integer.parseInt(coordFormattedEnemy[1]);
		int row2 = Integer.parseInt(coordFormattedEnemy[0]);
		GameConfig.sendAttack(Settings.p2.getPlayerBoard(),row2,column2);
		shipSunk = GameConfig.checkSunken(Settings.p1.getPlayerBoard(),row2,column2);
        // if this is a hit, we want all the ships around the guessed ship to be added to the queue
        if (Game.getHitSuccess() == true && Game.getAIStatus() == true) {
        		((ComputerPlayer)Settings.p2).makeQueue(row2, column2);
        } 
        if (shipSunk == true) {
    			((ComputerPlayer) Settings.p2).clearQueue();
    			shipSunk = false;
        }

		//https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
		PauseTransition pause = new PauseTransition(Duration.seconds(.7));		
		pause.setOnFinished(event -> {
			if ((Game.winCondition(Settings.p1.getPlayerBoard())) == false) {			
				AttackPhase nextDisplay = new AttackPhase(scene, playerAttacking.getName(), false); 
			} else {
				Settings.makeMsgLarger();
				Settings.changeMessage("You Lose!");
				AttackPhase displayOnly = new AttackPhase(scene, playerAttacking.getName(), true);				
			} 
		}
		);			
		pause.play();			
		return display;
	}		
}