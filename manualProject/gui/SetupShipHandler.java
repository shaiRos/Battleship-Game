package gui;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import game.Game;
import game.GameConfig;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import players.Player;
import javafx.geometry.Pos;


public class SetupShipHandler implements EventHandler<MouseEvent> {
	
	private Scene scene;
	private int length;
	private BorderPane root;
	private String orient = "horizontal";
	private char orientation = 'h';
	private Player player;
	private int shipsToSet;
	private BoardGUI boardDisplay;
	private String thisPlayer;

	
	public SetupShipHandler(Scene scn, int shipLen, BorderPane rt, String playerSettingUp, int numOfShips, BoardGUI bigBoard) {
		
		scene = scn;	
		length = shipLen;
		root = rt;
		thisPlayer = playerSettingUp;
		
		if (playerSettingUp == "P1") {
			player = Settings.p1;
		} else if (playerSettingUp == "P2") {
			player = Settings.p2;
		}		
		shipsToSet = numOfShips;
		boardDisplay = bigBoard;
	}
	
	public void handle(MouseEvent event) {
		root.setRight(rightPanel());
		//rightclicks for changing the orientation
		https://stackoverflow.com/questions/12816847/how-to-properly-detect-which-mouse-buttons-are-down-in-javafx
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (t.isSecondaryButtonDown()) {
					if (orient == "horizontal") {
						orient = "vertical";
						orientation = 'v';
					} else if (orient == "vertical") {
						orient = "horizontal";
						orientation = 'h';
					}
					
					root.setRight(rightPanel());
				}
			}
		});
		
		//this activates an event listener for the displayed board (the big one)
		boardDisplay.getBoardGrid().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent myEvent) {

				try {				
					if (myEvent.isPrimaryButtonDown()){
						
						//find the col and row that was clicked and setup the properties of the ship currently held
						int x = (int)((myEvent.getX()-10)/boardDisplay.getGridBlockSize())+1;
						int y = (int)((myEvent.getY()-10)/boardDisplay.getGridBlockSize())+1;
						System.out.println(x + ", " + y);
						//all checks are done here
						GameConfig.validateShipProperties(player.getPlayerBoard(),length,orientation,x,y);
						int shipsLeft = shipsToSet-1;
						int idNum = Settings.shipsToPlace - shipsLeft - 1;	
						//player.getPlayerBoard().addShip(orientation,length,x,y);
						player.getPlayerBoard().addShip(idNum,length, orientation, y, x);
						//need to update the ship count of this length since player placed one ship of this length
						switch(length) {
							case 2: Settings.len2Ships -= 1;
								break;
							case 3: Settings.len3Ships -= 1;
								break;
							case 4: Settings.len4Ships -= 1;
								break;
							case 5: Settings.len5Ships -= 1;
								break;
						}
						
						if (shipsLeft == 0) {
							if (thisPlayer == "P1") {
								if (Game.getAIStatus() == true) {
									SetupPhase nextShipSetup = new SetupPhase(scene,thisPlayer,shipsLeft,true);
									Game.mapFromFiles("map.txt", Settings.p2.getPlayerBoard());
									PauseTransition pause = new PauseTransition(Duration.seconds(1));
									pause.setOnFinished(event -> scene.setRoot(startGameTransitionScreen()));
									pause.play();
								} else {
									Settings.setGeneratedShips(player.getPlayerBoard().getGeneratedShips());
									SetupPhase nextShipSetup = new SetupPhase(scene,thisPlayer,shipsLeft,true);
									PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
									pause.setOnFinished(event -> scene.setRoot(p2Setup()));
									pause.play();
									
									
									
								}
							}	
							else if (thisPlayer == "P2") {
								SetupPhase nextShipSetup = new SetupPhase(scene,thisPlayer,shipsLeft,true);
								PauseTransition pause = new PauseTransition(Duration.seconds(1));
								pause.setOnFinished(event -> scene.setRoot(startGameTransitionScreen()));
								pause.play();
							}
						}
						else {							
							SetupPhase nextShipSetup = new SetupPhase(scene,thisPlayer,shipsLeft,false);
						}							
					}
				}					
				catch (IllegalArgumentException e) {
				//input must meet the requirements. This is done in the validate methods. If it doesn't,the methods throws this
				//exception, exits the loop, and asks the user for a new value that meets the requirements.
				System.out.println(e.getMessage());
				Settings.changeMessage(e.getMessage());
					
				}				
			}
		});			
	}
	
	/**
	*	different layout for right panel when ship is picked. has an indicator for orientation and a cancel button
	*	When cancel button is clicked, it displays the setup phase with no changes on the board and the ship count.
	*
	*	@return		a GridPane layout that is setup to display the orientation of the currently held ship and also adds a cancel button to cancel the ship choice
	*/
	public GridPane rightPanel() {
		
		GridPane rightPanel = new GridPane();
		rightPanel.setPrefWidth(Settings.sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");	
		rightPanel.setPadding(new Insets(10));


		for (int x = 0; x < 4; x++) {
			RowConstraints row = new RowConstraints();	
			row.setPercentHeight(50);			
			rightPanel.getRowConstraints().add(row);
		}
		ColumnConstraints column = new ColumnConstraints();		
		column.setPercentWidth(100);		
		rightPanel.getColumnConstraints().add(column);				
		
		Label orientLabel = new Label("orientation: " + orient);
		orientLabel.setFont(new Font(20));
		orientLabel.setTextFill(Color.WHITE);
		//orientLabel.setAlignment(Pos.CENTER);
		
		//cancel button
		Button cancelBt = new Button("Cancel");
		rightPanel.setAlignment(Pos.CENTER);		
		rightPanel.add(cancelBt,0,3);
		rightPanel.add(orientLabel,0,0);

		rightPanel.setGridLinesVisible(true);		
		cancelBt.setOnMouseClicked(event -> {
			SetupPhase cancelShipSetup = new SetupPhase(scene,thisPlayer,shipsToSet,false);
		});
		return rightPanel;
		
		
		
	}
	
	
	/**
	*	Only for PvP. When player 1 setup phase is done, the display changes into another screen with a button at the center. This is to prevent the second player from
	*	seeing the previous player's board setup. When ready, player two clicks the button the enter his/her own setup phase
	*
	*	@return		a BorderPane layout that displays the transition screen after player one's setup phase is done.
	*/
/* 	public BorderPane p2Setup() {

		BorderPane display = new BorderPane();
		Button continueButton = new Button("Continue");
		display.setCenter(continueButton);
		EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent event) { 
				SetupPhase player2SetupPhase = new SetupPhase(scene,"P2",Settings.shipsToPlace,false);
			}           
		};
		continueButton.setOnMouseClicked(eventHandlerTextField);
		return display;
	} */
	
	public GridPane p2Setup() {

		GridPane display = new GridPane();
		Button continueButton = new Button("Continue");
		display.setAlignment(Pos.CENTER);
		EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent event) { 
				SetupPhase player2SetupPhase = new SetupPhase(scene,"P2",Settings.shipsToPlace,false);
			}           
		};
		continueButton.setOnMouseClicked(eventHandlerTextField);
		display.add(continueButton,0,1);
		return display;
	}	
	

	
	public BorderPane startGameTransitionScreen() {

		BorderPane display = new BorderPane();
		Button continueButton = new Button("Player one ready?");
		display.setCenter(continueButton);
		EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent event) {
				Settings.changeMessage("");
				AttackPhase startAttack = new AttackPhase(scene, Settings.p1, Settings.p2, "P1", false);
			}           
		};
		continueButton.setOnMouseClicked(eventHandlerTextField);
		return display;
	}		
	
	
		
}
		