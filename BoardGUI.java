import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

public class BoardGUI {
	
	GridPane board;
	int gridSize;
	
	//constructor for making the GridPane Board
	public BoardGUI(int gridsize, int gridWidth) {
		
		gridSize = gridsize;
		board = new GridPane();
		board.setPadding(new Insets(5)); //margin for the slot the grid will be in
		board.setPrefSize(gridWidth, gridWidth);		
		
		for (int x = 0; x < gridSize; x++) {
			//this sets the constraints for box size so the size doesn't automatically adjust to child inside
			ColumnConstraints column = new ColumnConstraints();
			RowConstraints row = new RowConstraints();			
			
			//so it fits the parent slot (center) for the grid whatever the size of the grid
			column.setPercentWidth(50);			
			row.setPercentHeight(50);			
			
			board.getColumnConstraints().add(column);			
			board.getRowConstraints().add(row);
			
			//ONLY FOR DEBUG. find another way to display grid lines. was too dark anyways...	
			board.setGridLinesVisible(true);
		}
	}
	
	//returns the GridPane object
	public GridPane getBoardGrid() {
		return board;
	}
	
	//method still usable if objects is not in an array.
	//Adding objects into the grid is similar when doing it on 2d arrays...		
	public void addValuesFromArray(int[][] boardArray) {
		
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				//add the object to this coordinate
				String object = Integer.toString(boardArray[y][x]);
				Label label = new Label(object);		
				board.add(label,x,y);	
			}
		}
	}		
		
}	