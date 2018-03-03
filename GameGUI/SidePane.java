import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.geometry.Insets;

public class SidePane {
	
	public TilePane sidePane;
	private BoardGUI sideBoard;
	
	public SidePane(TilePane tilepane, int paneWidth, int gridSize, int sideBoardWidth, BoardGUI board) {
		
		sideBoard = board;
		
		TilePane sidePane = tilepane;
		sidePane.setPrefWidth(paneWidth);
		sidePane.setStyle("-fx-background-color: #0066CC;");	
		sidePane.setPadding(new Insets(10));			
		
		
		sideBoard = new BoardGUI(gridSize, sideBoardWidth);				
	
		
	}
	
	public TilePane getChildrenn() {
		sidePane.getChildren().add(sideBoard.getBoardGrid());	
		return sidePane;
		
	}
}	