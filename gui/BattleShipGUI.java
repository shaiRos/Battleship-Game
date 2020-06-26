package gui;

import game.Game;
import board.Board;

import players.Player;
import players.*;
import players.HumanPlayer;
import players.ComputerPlayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;

import board.BoardValue;


/**
 * Application of java fx starts here.
 *
 * @author Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayag
 */

public class BattleShipGUI extends Application {
    public static Stage primaryStage;
    public static Scene gameUI;
    private BorderPane mainMenu;

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * The very first root of the scene is the main menu.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainMenuGUI menu = new MainMenuGUI();
        primaryStage = primaryStage;
        gameUI = new Scene(menu.getMenuRoot(), Settings.xWindowSize, Settings.yWindowSize);

        primaryStage.setMaxHeight(Settings.yWindowSize);
        primaryStage.setMaxWidth(Settings.xWindowSize + 15);
        primaryStage.setTitle("BattleShip");
        primaryStage.setScene(gameUI);
        primaryStage.show();
    }

    /**
     * Initializes objects required for the game by getting the values that were setup in the main menu
     */
    public static void gameSetup() {

        if (Settings.gameMode == "Player vs Ai") {
            Game.enableAI();
        }
        //Board.setBoardSize(Settings.boardSize);
        Board player1Board = new Board(Settings.boardSize);
        Board player2Board = new Board(Settings.boardSize);
        Settings.shipsToPlace = Board.getNumOfShips();
        Settings.setGeneratedShips(Board.getGeneratedShips());


        Player player1 = new HumanPlayer(player1Board, "P1");
        Player player2 = null;

        if (Game.getAIStatus() != true) {
            player2 = new HumanPlayer(player2Board, "P2");
        } else {
            player2 = new ComputerPlayer(player2Board, "P2");
        }
        Settings.p1 = player1;
        Settings.p2 = player2;

        player1Board.guessBoard = player2Board.gameBoard;
        player2Board.guessBoard = player1Board.gameBoard;

        SetupPhase setup = new SetupPhase(gameUI, "P1", Settings.shipsToPlace, false);
    }

    /**
     * Loads the game from a save file.
     *
     * @param player1 - a player instance of player 1
     * @param player2 - a player instance of player 2
     * @param currentPlayer - a String indicating which player's turn it is. ( "P1" / "P2")
     * @param mode - current mode of the game
     */
    public static void loadGame(Player player1, Player player2, String currentPlayer, String mode) {
        Settings.p1 = player1;
        Settings.p2 = player2;
        Settings.setBoardSize(player1.getPlayerBoard().getBoardSize());

        if (mode.equals("Player vs Ai")) {
            Game.enableAI();
            Settings.gameMode = "Player vs Ai";
        } else if (mode.equals("Player vs Player")) {
            Settings.gameMode = "Player vs Player";
        }

        AttackPhase startAttack = new AttackPhase(gameUI, currentPlayer, false);
    }
}	
	
	