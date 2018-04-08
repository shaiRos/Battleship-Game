package gui;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
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
	public static int shipsToPlace;
	public static int len2Ships;
	public static int len3Ships;
	public static int len4Ships;
	public static int len5Ships;

	public static Label message = msgLabel();
	
	public static void reset() {
		p1 = null;
		p2 = null;
		boardSize = 5;
		message = msgLabel();
	}
	
	
	public static void setBoardSize(int value) {
		if (value >= 5 && value <= 15) {
			boardSize = value;
		}
		
	}
	
	public static void changeMessage(String msg) {
		message.setText(msg);
	}
	
	public static String getMessage() {
		return message.getText(); 
	}
	
	
	public static void switchMode() {
		
		if (gameMode == "Player vs Player") {
			gameMode = "Player vs Ai";
		} else if (gameMode == "Player vs Ai") {
			gameMode = "Player vs Player";
		}
	}
	
	public static Label msgLabel() {
		
		Label message = new Label("");
		message.setFont(new Font(30));
		//message.setStyle("-fx-background-color: #fcfeff;");
		message.setPadding(new Insets(10));
		return message;
	}
	
	public static void makeMsgLarger() {
		message.setFont(new Font(50));
	}
	
	public static void setGeneratedShips(int[] generatedShipsArray){
		for (int shipListByLen : generatedShipsArray) {
			switch(shipListByLen){
				case 2:
					len2Ships += 1;
					break;
				case 3:
					len3Ships += 1;
					break;
				case 4:
					len4Ships += 1;
					break;
				case 5:
					len5Ships += 1;
					break;
			}
		}
	}
}	
	