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
	private Player playerAttacking;
	private Player player1;
	private Player player2;	
	//private int[][] playerBoard;
	
	public AttackClickHandler(BoardGUI grid, Scene scene, String attackingPlayer) {
		

		scne = scene;
		blockSize = grid.getGridBlockSize();	//only needed the grid to get block size
		
		if (attackingPlayer == "P1") {
			nextPlayer = "P2";
		}else if (attackingPlayer == "P2") {
			nextPlayer = "P1";
		}
	}
	
	public AttackClickHandler(double BlockSize, Scene scenee, Player p1, Player p2, String attackingPlayer) {

		player1 = p1;
		player2 = p2;
		scne = scenee;
		blockSize = BlockSize;
		if (attackingPlayer == "P1") {
			nextPlayer = "P2";
			playerAttacking = p1;
			//playerBoard = ((HumanPlayer)p1).playerBoard;

		}else if (attackingPlayer == "P2") {
			nextPlayer = "P1";
			playerAttacking = p2;
			//playerBoard = ((HumanPlayer)p2).playerBoard.guessBoard;			
		}
		//do the player turn thing that updates both players' boards then send nextplayer to attackphase
	}
	
	
	public void handle(MouseEvent myEvent) {

		//find the col and row that was clicked
		x = (int)((myEvent.getX()-10)/(blockSize))+1;
		y = (int)((myEvent.getY()-10)/blockSize)+1;
		System.out.println(x + ", " + y);
		//int actualX = x - 1;
		//int actualY = y - 1;
		
		//player turns cannot work for txt version because of it's current state
		//FEED TO PLAYERTURN METHOD IN TXT VERSION
		//Player.sendAttack((((HumanPlayer)playerAttacking).playerBoard), y, x);		
		//using dummy own and guess boards for p1 and p2 in settings and will switch back 
		//and forth between them for turns for now.
		//Change the display into the next player's attack phase
		//Player.sendAttack    (((HumanPlayer)playerAttacking).playerBoard.guessBoard)
		System.out.println("heeeeeeee" + (((HumanPlayer)playerAttacking).playerBoard.guessBoard)[0][0]);
		playerAttacking.sendAttack((((HumanPlayer)playerAttacking).playerBoard), x, y);
		System.out.println(nextPlayer);
		AttackPhase testUI = new AttackPhase(scne,player1,player2, nextPlayer);
		
	}
}