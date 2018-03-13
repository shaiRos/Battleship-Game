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
	private Scene scene;
	private String thisPlayer;
	private String nextPlayer;
	private Player playerAttacking;
	private Player playerAttacked;
	private Player player1;
	private Player player2;	
	
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
		
		boolean checkPrevHit = playerAttacked.checkPreviousHit((((HumanPlayer)playerAttacking).playerBoard), x, y);	
		
		if (checkPrevHit == true) {													//if they chose a coordinate they previously hit
			System.out.println("prevhit true, Please try again");
			AttackPhase testUI = new AttackPhase(scene,player1,player2, thisPlayer);//display current player again. ONLY FOR SHIPS. Misses don't count.
		} else {
			playerAttacking.sendAttack((((HumanPlayer)playerAttacking).playerBoard), x, y);	
			if (Game.winCondition(((HumanPlayer)playerAttacked).playerBoard) == false) {
				//display the next player turn
				AttackPhase testUI = new AttackPhase(scene,player1,player2, nextPlayer);
			} else {
				System.out.println(thisPlayer + " has won"); //it stops the display but clicking still works...		
			}				
		}			
	}
}