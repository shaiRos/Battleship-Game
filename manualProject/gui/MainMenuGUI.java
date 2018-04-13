package gui;
import javafx.scene.Scene;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.text.Font;

import saveload.LoadGame;


/**
* 	The GUI of the game starts at the main menu where the players are prompted to set the settings of the game.
*	They also have the option to load a game from a save file. 
*	<p> 
*	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayag
*/
public class MainMenuGUI {
	
	public GridPane root;
	
	
	/**
	*	The constructor creates the layout of the display of the main menu screen. the layout consists of a grid pane with 5 rows and one column
	*	Each row contains a setting, a start button on the 4th row, and a load button at the 5th row.
	*/
	public MainMenuGUI() {
		
		
		root = new GridPane();
		root.setPrefSize(Settings.xWindowSize,Settings.yWindowSize);		
		for (int x = 0; x < 5; x++) {
			RowConstraints row = new RowConstraints();	
			row.setPercentHeight(50);			
			root.getRowConstraints().add(row);
		}
		ColumnConstraints column = new ColumnConstraints();		
		column.setPercentWidth(100);		
		root.getColumnConstraints().add(column);	

		//LOAD BUTTON
		Button loadButton = new Button("Load from save file");
		loadButton.setPrefSize(200,60);
		root.setHalignment(loadButton,HPos.CENTER);	
		
		loadButton.setOnMouseClicked(event -> {
			LoadGame.loadBoard();
		});
		
		
		//START BUTTON
		Button startButton = new Button("Start");
		startButton.setPrefSize(200,60);
		root.setHalignment(startButton,HPos.CENTER);
		
		startButton.setOnMouseClicked(event -> {
			BattleShipGUI.gameSetup();
		});			
		root.add(modeSection(),0,1);
		root.add(boardSizeSection(),0,2);
		root.add(startButton,0,3);
		root.add(loadButton,0,4);				
	}
	
	/**
	*	gets the GridPane layout of the main menu
	*
	*	@return root a GridPane instance of the main menu layout
	*/
	public GridPane getMenuRoot() { 
		return root;
	}
		
	/**
	*	creates a label of the section title of a specific setting
	*
	*	@return	modeTitle a Label instance of the title of a specific setting 
	*/
	public Label sectionTitle(String section) {
		
		Label modeTitle = new Label(section);
		modeTitle.setFont(new Font(50));
		return modeTitle;
	}
	
	/**
	*		The section of the game mode settings in the GridPane layout of the main menu. This section has two parts. 
	*		The section Title at the top, and at the bottom, the left and right buttons with the changing
	*		values in between.
	*
	*		@return	modeVSection a VBox layout which represents the Game mode settings in the menu screen.
	*/
	public VBox modeSection() {
		
		VBox modeVSection = new VBox(10);
		HBox modeSection = new HBox(100);
		Label modeTitle = sectionTitle("Game Mode");
		
		Button modeLeftBt = new Button("<=");
		modeLeftBt.setPrefSize(60,40);	
		Label modeLabel = new Label(Settings.gameMode);
		modeLabel.setFont(new Font(20));
		Button modeRightBt = new Button("=>");	
		modeRightBt.setPrefSize(60,40);
		
		modeRightBt.setOnMouseClicked(event -> {
			Settings.switchMode();
			modeLabel.setText(Settings.gameMode);
		});	
		modeLeftBt.setOnMouseClicked(event -> {
			Settings.switchMode();
			modeLabel.setText(Settings.gameMode);
		});	
		
		modeSection.getChildren().addAll(modeLeftBt,modeLabel,modeRightBt);
		modeSection.setAlignment(Pos.CENTER);
		modeVSection.setAlignment(Pos.CENTER);
		modeVSection.getChildren().addAll(modeTitle,modeSection);	
		
		return modeVSection;
	}	

	/**
	*		The section of the board size settings in the GridPane layout of the main menu. 
	*		The section Title at the top, and at the bottom, the left and right buttons with the changing
	*		values in between. 
	*
	*		@return	boardVSection a VBox layout which represents the Board size settings in the menu screen.
	*/	
	public VBox boardSizeSection() {
		
		
		VBox boardVSection = new VBox(10);
		HBox boardHSection = new HBox(160);
		Label boardTitle = sectionTitle("Board Size");
		
		Button boardLeftBt = new Button("<=");
		boardLeftBt.setPrefSize(60,40);	
		Label boardLabel = new Label(Integer.toString(Settings.boardSize));
		boardLabel.setFont(new Font(20));		
		Button boardRightBt = new Button("=>");	
		boardRightBt.setPrefSize(60,40);
		
		boardRightBt.setOnMouseClicked(event -> {
			Settings.setBoardSize(Settings.boardSize+1);
			boardLabel.setText(Integer.toString(Settings.boardSize));
		});
		
		boardLeftBt.setOnMouseClicked(event -> {
			Settings.setBoardSize(Settings.boardSize-1);
			boardLabel.setText(Integer.toString(Settings.boardSize));
		});		
		
		boardHSection.getChildren().addAll(boardLeftBt,boardLabel,boardRightBt);
		boardHSection.setAlignment(Pos.CENTER);
		boardVSection.setAlignment(Pos.CENTER);
		boardVSection.getChildren().addAll(boardTitle,boardHSection);		
		
		return boardVSection;
	}
}


