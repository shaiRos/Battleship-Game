package gui;
import game.Game;
import players.Player;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.geometry.Insets;



/**
*	Settings for the game window and the game itself. The GUI depends on the values in the settings to run the game.
*	<p>
*	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayang
*/

public class Settings {

	//Window Settings
	final public static int xWindowSize = 1040;
	final public static int yWindowSize = 920;
	final public static int botHeight = 150;
	final public static int smallGridWidth = 250; //including margins
	final public static int sidePanelWidth = 270;	
	final public static int bigGridWidth = 770; //including margins	

	//player instance
	public static Player p1;
	public static Player p2;

	//game and setup settings
	public static String gameMode = "Player vs Ai";
	public static int boardSize = 5;
	public static int shipsToPlace;
	//these values indicates the number of ships (of a certain length) 
	//the players can place when setting up their boards
	public static int len2Ships;
	public static int len3Ships;
	public static int len4Ships;
	public static int len5Ships;
	//message label at the bottom pane of the display
	public static Label message = msgLabel();
	/**
	*	resets values to default settings
	*/
	public static void reset() {
		p1 = null;
		p2 = null;
		Game.disableAI();
		gameMode = "Player vs Ai";
		boardSize = 5;
		message = msgLabel();
	}
	
	/**
	*	sets board size with restrictions
	*
	*	@param		value - an int for the value of the desired board size
	*/
	public static void setBoardSize(int value) {
		if (value >= 5 && value <= 15) {
			boardSize = value;
		}
		
	}
	
	/**
	*	Changes the message in the bottom pane of the display
	*
	*	@param 		msg - a string that contains the message
	*/
	public static void changeMessage(String msg) {
		message.setText(msg);
	}
	
	/**
	*	Gets the current message displayed in the bottom pane of the display
	*/
	public static String getMessage() {
		return message.getText(); 
	}
	
	/**
	*	get the current game mode
	*/
	public static String getMode() {
		return gameMode;
	}
	
	/**
	*	switch the current game mode. Switches between "Player vs Player" and "Player vs Ai".
	*/
	public static void switchMode() {
		
		if (gameMode == "Player vs Player") {
			gameMode = "Player vs Ai";
		} else if (gameMode == "Player vs Ai") {
			gameMode = "Player vs Player";
		}
	}
	

	public static String getCurrentMode(){
		return gameMode;
	}
	
	/**
	*	creates the label instance of the message label. 
	*/
	public static Label msgLabel() {
		
		Label message = new Label("");
		message.setFont(new Font(30));
		//message.setStyle("-fx-background-color: #fcfeff;");
		message.setPadding(new Insets(10));
		return message;
	}
	
	/**
	* 	makes the font of the message label in the bottom pane larger
	*/
	public static void makeMsgLarger() {
		message.setFont(new Font(50));
	}
	
	/**
	*	sets the values of the number of ships (of a certain length) that players can place 
	*	in their setup phase
	*
	*	@param		generatedShipsArray - an int array taken from Board class containing values of ship lengths. 
	*				The number of occurances of a ship's length indicate the number of ships (of that length) 
	*				the players are given for their setup phase
	*/
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
	
