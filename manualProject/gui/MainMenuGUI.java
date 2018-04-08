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



public class MainMenuGUI {
	
	public GridPane root;
	
	public MainMenuGUI() {
		
		root = new GridPane();
		root.setPrefSize(Settings.xWindowSize,Settings.yWindowSize);
		setSettingsRoot();
	}
	
	public GridPane getMenuRoot() { 
		return root;
	}
	
	public void setSettingsRoot() {
		
		GridPane settingScreen = new GridPane();
		root.setPrefSize(Settings.xWindowSize,Settings.yWindowSize);		
		for (int x = 0; x < 5; x++) {
			RowConstraints row = new RowConstraints();	
			row.setPercentHeight(50);			
			settingScreen.getRowConstraints().add(row);
		}
		ColumnConstraints column = new ColumnConstraints();		
		column.setPercentWidth(100);		
		settingScreen.getColumnConstraints().add(column);	

		Button loadButton = new Button("Load from save file");
		loadButton.setPrefSize(200,60);
		settingScreen.setHalignment(loadButton,HPos.CENTER);		
		
		

		Button startButton = new Button("Start");
		startButton.setPrefSize(200,60);
		settingScreen.setHalignment(startButton,HPos.CENTER);
		
		startButton.setOnMouseClicked(event -> {
			BattleShipGUI.gameSetup();
		});			
		settingScreen.add(modeSection(),0,1);
		settingScreen.add(boardSizeSection(),0,2);
		settingScreen.add(startButton,0,3);
		settingScreen.add(loadButton,0,4);
		
		root = settingScreen;
	}	
		
	public Label sectionTitle(String section) {
		
		Label modeTitle = new Label(section);
		modeTitle.setFont(new Font(50));
		return modeTitle;
	}
	
	/**
	*		This section has two parts. The section Title at the top, and the left and right buttons with the changing
	*		values in between. The left and right buttons respond to actions which switches the game mode between
	*		player vs player and player vs ai. 
	*
	*		@return		a VBox layout which represents the Game mode settings in the settings menu screen.
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


