import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;
import javafx.scene.layout.Priority;


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


		Button startButton = new Button("Start");
		startButton.setPrefSize(200,60);
		settingScreen.setHalignment(startButton,HPos.CENTER);
		
		startButton.setOnMouseClicked(event -> {
			BattleShipGUI.gameSetup();
		});			
		

		settingScreen.add(modeSection(),0,1);
		settingScreen.add(boardSizeSection(),0,2);
		settingScreen.add(shipsSection(),0,3);
		settingScreen.add(startButton,0,4);		

		
		//settingScreen.setGridLinesVisible(true);
		
		root = settingScreen;
	}	
		
	public Label sectionTitle(String section) {
		
		Label modeTitle = new Label(section);
		modeTitle.setFont(new Font(50));
		
		return modeTitle;
	}
	
	public VBox modeSection() {
		
		VBox modeVSection = new VBox(10);
		HBox modeSection = new HBox(100);
		Button modeLeftBt = new Button("<=");
		modeLeftBt.setPrefSize(60,40);
		Label modeLabel = new Label(Settings.gameMode);
		modeLabel.setFont(new Font(20));
		
		Label modeTitle = sectionTitle("Game Mode");
		
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
		Button boardLeftBt = new Button("<=");
		boardLeftBt.setPrefSize(60,40);
		Label boardLabel = new Label(Integer.toString(Settings.boardSize));
		boardLabel.setFont(new Font(20));
		
		Label boardTitle = sectionTitle("Board Size");
		
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

	public VBox shipsSection() {

		VBox shipsVSection = new VBox(10);
		HBox shipsHSection = new HBox(160);
		Button shipsLeftBt = new Button("<=");
		shipsLeftBt.setPrefSize(60,40);
		Label shipsLabel = new Label(Integer.toString(Settings.shipsToPlace));
		shipsLabel.setFont(new Font(20));
		
		Label shipsTitle = sectionTitle("Number of Ships");
		
		Button shipsRightBt = new Button("=>");	
		shipsRightBt.setPrefSize(60,40);
		
		shipsRightBt.setOnMouseClicked(event -> {
			Settings.setNumOfShips(Settings.shipsToPlace+1);
			shipsLabel.setText(Integer.toString(Settings.shipsToPlace));
		});
		
		shipsLeftBt.setOnMouseClicked(event -> {
			Settings.setNumOfShips(Settings.shipsToPlace-1);
			shipsLabel.setText(Integer.toString(Settings.shipsToPlace));
		});			
		
		
		shipsHSection.getChildren().addAll(shipsLeftBt,shipsLabel,shipsRightBt);
		shipsHSection.setAlignment(Pos.CENTER);
		shipsVSection.setAlignment(Pos.CENTER);
		shipsVSection.getChildren().addAll(shipsTitle,shipsHSection);		
		
		
		return shipsVSection;
	}
	
}


