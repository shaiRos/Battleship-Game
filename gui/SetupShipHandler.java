package gui;

import game.Game;
import game.GameConfig;
import players.Player;

import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

/**
 * Called when player clicks a button in the right panel.
 * <p>
 *
 * @author Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayag
 */
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


    /**
     * Sets up the instance variables of this class depending on which player is in the setup phase
     *
     * @param scn             - The scene of the game. changes the root as the display changes.
     * @param shipLen         - an integer of the ship length of the button clicked in the setup phase
     * @param rt              - a BorderPane layout. This is the layout made in SetupPhase class
     * @param playerSettingUp - a String indicating which player is setting up
     * @param        numOfShips - an integer indicating the number of ships left to setup.
     * @param        bigBoard - a BoardGUI instance of the large board representing the player's own board
     */
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

    /**
     * When a button is clicked from the right pane of the setup display, it sets the info of the ship based on what button was clicked.
     * All checks are done so that ships can be placed with no conflicts.
     * will change to the next player a turn is done. When mode is player vs ai, it continues directly into the game i.e the attack phase.
     */
    public void handle(MouseEvent event) {
        root.setRight(rightPanel());
        //rightclicks for changing the orientation
        https:
//stackoverflow.com/questions/12816847/how-to-properly-detect-which-mouse-buttons-are-down-in-javafx
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

        //this activates an event listener for the displayed board in setup phase gui
        boardDisplay.getBoardGrid().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent myEvent) {

                try {
                    if (myEvent.isPrimaryButtonDown()) {

                        //find the col and row that was clicked and setup the properties of the ship currently held
                        int x = (int) ((myEvent.getX() - 10) / boardDisplay.getGridBlockSize()) + 1;
                        int y = (int) ((myEvent.getY() - 10) / boardDisplay.getGridBlockSize()) + 1;
                        //checks for ship placement. May throw an IllegalArgumentException if input from the user doesn't meet the game's requirements
                        //for setting up
                        GameConfig.validateShipProperties(player.getPlayerBoard(), length, orientation, x, y);
                        int shipsLeft = shipsToSet - 1;
                        int idNum = Settings.shipsToPlace - shipsLeft - 1;
                        //player.getPlayerBoard().addShip(orientation,length,x,y);
                        player.getPlayerBoard().addShip(idNum, length, orientation, y, x);
                        //need to update the ship count of this length since player placed one ship of this length
                        switch (length) {
                            case 2:
                                Settings.len2Ships -= 1;
                                break;
                            case 3:
                                Settings.len3Ships -= 1;
                                break;
                            case 4:
                                Settings.len4Ships -= 1;
                                break;
                            case 5:
                                Settings.len5Ships -= 1;
                                break;
                        }

                        //when setup is done for this player, i.e given number of ships are all set up
                        if (shipsLeft == 0) {
                            //If player 1 finished the setup, if mode is player vs ai continue to the game
                            //else if mode is player vs player, continue to player 2 setup
                            if (thisPlayer == "P1") {
                                //mode: player vs ai
                                if (Game.getAIStatus() == true) {
                                    SetupPhase nextShipSetup = new SetupPhase(scene, thisPlayer, shipsLeft, true);
                                    Game.userPlaceShip(Settings.p2.getPlayerBoard(), Settings.p2);
                                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                                    pause.setOnFinished(event -> scene.setRoot(startGameTransitionScreen()));
                                    pause.play();
                                    //mode: player vs player
                                } else {
                                    Settings.setGeneratedShips(player.getPlayerBoard().getGeneratedShips());
                                    SetupPhase nextShipSetup = new SetupPhase(scene, thisPlayer, shipsLeft, true);
                                    PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                                    pause.setOnFinished(event -> scene.setRoot(p2Setup()));
                                    pause.play();
                                }
                            }
                            //for player vs player. When player 2 is done setting up, continue to the game
                            else if (thisPlayer == "P2") {
                                SetupPhase nextShipSetup = new SetupPhase(scene, thisPlayer, shipsLeft, true);
                                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                                pause.setOnFinished(event -> scene.setRoot(startGameTransitionScreen()));
                                pause.play();
                            }
                        }
                        //Given number of ships are still yet to be placed. Continue to setup phase display of this player
                        //and update the number of ships to be placed (shipsLeft)
                        else {
                            SetupPhase nextShipSetup = new SetupPhase(scene, thisPlayer, shipsLeft, false);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    //validating ship placement throws this exception .
                    Settings.changeMessage(e.getMessage());

                }
            }
        });
    }


    /**
     * different layout for right panel when ship is picked. has an indicator for orientation and a cancel button
     * When cancel button is clicked, it displays the setup phase with no changes on the board and the ship count.
     *
     * @return a GridPane layout that is setup to display the orientation of the currently held ship and also adds a cancel button to cancel the ship choice
     */
    public GridPane rightPanel() {

        GridPane rightPanel = new GridPane();
        rightPanel.setPrefWidth(Settings.sidePanelWidth);
        rightPanel.setStyle("-fx-background-color: #0066CC;");
        rightPanel.setPadding(new Insets(10));

        //creates four rows in the right pane gridPane layout
        for (int x = 0; x < 4; x++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(50);
            rightPanel.getRowConstraints().add(row);
        }
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(100);
        rightPanel.getColumnConstraints().add(column);

        //label for the orientation
        Label orientLabel = new Label("orientation: \n" + orient);
        orientLabel.setFont(new Font(20));
        orientLabel.setTextFill(Color.WHITE);

        //image for right clicking and orientation label are side by side
        HBox orientHBox = new HBox(20);
        orientHBox.setAlignment(Pos.CENTER);
        Image rClick = new Image("/images/RightClickImg.png");
        ImageView rClickView = new ImageView();
        rClickView.setImage(rClick);
        rClickView.setFitWidth(50);
        rClickView.setFitHeight(50);
        orientHBox.getChildren().addAll(rClickView, orientLabel);

        //cancel button
        Button cancelBt = new Button("Cancel");
        rightPanel.add(cancelBt, 0, 3);
        rightPanel.add(orientHBox, 0, 0);

        //rightPanel.setGridLinesVisible(true);
        cancelBt.setOnMouseClicked(event -> {
            SetupPhase cancelShipSetup = new SetupPhase(scene, thisPlayer, shipsToSet, false);
        });
        return rightPanel;
    }


    /**
     * Only for PvP. When player 1 setup phase is done, the display changes into another screen with a button at the center. This is to prevent the second player from
     * seeing the previous player's board setup. When ready, player 2 clicks the button the start his/her own setup phase
     *
     * @return a BorderPane layout that displays the transition screen after player one's setup phase is done.
     */
    public VBox p2Setup() {


        VBox display = new VBox(100);
        Button continueButton = new Button("Continue");
        continueButton.setPrefSize(200, 60);
        Label aLabel = new Label("Player 2 Setup");
        aLabel.setFont(new Font(50));


        display.setAlignment(Pos.CENTER);
        EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SetupPhase player2SetupPhase = new SetupPhase(scene, "P2", Settings.shipsToPlace, false);
            }
        };
        continueButton.setOnMouseClicked(eventHandlerTextField);
        display.getChildren().addAll(aLabel, continueButton);
        return display;
    }


    /**
     * When setup is finished, this transition screen informs the players that the game will start with player 1 making the turn first
     * Then it calls AttackPhase when the continue button is clicked.
     *
     * @return a VBox layout
     */
    public VBox startGameTransitionScreen() {

        VBox display = new VBox(100);
        Button continueButton = new Button("Continue");
        continueButton.setPrefSize(200, 60);
        Label aLabel = new Label("Player 1 ready?");
        aLabel.setFont(new Font(50));
        display.setAlignment(Pos.CENTER);
        EventHandler<MouseEvent> eventHandlerTextField = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Settings.changeMessage("");
                AttackPhase startAttack = new AttackPhase(scene, "P1", false);
            }
        };
        continueButton.setOnMouseClicked(eventHandlerTextField);
        display.getChildren().addAll(aLabel, continueButton);
        return display;
    }
}
		