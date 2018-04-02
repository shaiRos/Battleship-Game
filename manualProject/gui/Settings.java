package gui;
import javafx.scene.Scene;
import javafx.stage.Stage;
import players.Player;

/**
*	Settings for the game window
*/

public class Settings {

	//Window Settings
	final public static int xWindowSize = 1040;
	final public static int yWindowSize = 920;
	final public static int botHeight = 150;
	final public static int smallGridWidth = 250; //including margins
	final public static int sidePanelWidth = 270;	
	final public static int bigGridWidth = 770; //including margins	

	public static Player p1;
	public static Player p2;

	public static String gameMode = "Player vs Ai";
	public static int boardSize = 5;
	public static int shipsToPlace = 2;
	public static int len2Ships;
	public static int len3Ships;
	public static int len4Ships;
	public static int len5Ships;	
	
	
	public static void setBoardSize(int value) {
		if (value >= 5 && value <= 15) {
			boardSize = value;
		}
		
	}

	public static void setNumOfShips(int value) {
		if (value >= 1 && value <= 8) {
			shipsToPlace = value;
		}
		
	}
	
	
	public static void switchMode() {
		
		if (gameMode == "Player vs Player") {
			gameMode = "Player vs Ai";
		} else if (gameMode == "Player vs Ai") {
			gameMode = "Player vs Player";
		}
	}
	
	public static void setGeneratedShips(int[] generatedShipsArray){
		for (int shipListByLen : generatedShipsArray) {
			switch(shipListByLen){
				case 2:
					len2Ships += 1;
				case 3:
					len3Ships += 1;
				case 4:
					len4Ships += 1;
				case 5:
					len5Ships += 1;
			}
					
		}
		
	}
	
}	
	