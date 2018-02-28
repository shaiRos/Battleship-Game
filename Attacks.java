
public enum Attacks{
	HIT (5), 
	MISS (0),
	PREVMISS (-1), 
	PREVHIT (1);

	//Instantiate boardvalue 
	private final int boardValue; 
	//= (playerBoard.guessBoard[column - 1][row - 1]);

	//Constructor 
	public Attacks(int boardValue){
		this.boardValue = boardValue;
	}
}


public class EnumAttack{
	Attacks attack;

	public EnumAttack(Attacks attack){
		this.attack = attack;
	}

	public void attackActions(){
		switch (attack){
			case HIT:
				playerBoard.guessBoard[column - 1][row - 1] = 1;
				System.out.println("Hit!");

			case MISS: case PREVMISS:
				 playerBoard.guessBoard[column - 1][row - 1] = -1;
				 System.out.println("Miss!");

			case PREVHIT:
				System.out.println("Previously hit!");

			default:
				System.out.println("I broke something whoops");
            	System.out.println("Debuggies");
            	System.out.println(boardValue);
		}
	}
}
