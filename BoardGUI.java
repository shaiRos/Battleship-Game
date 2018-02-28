import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;

public class BoardGUI {
	
	private GridPane board;
	private int gridSize;
	private ImageView shipImage;
	private int blockImageSize = 64;	//width & height for each square in grid
	//constructor for making the GridPane Board
	public BoardGUI(int gridsize, int gridWidth) {
		
		gridSize = gridsize;
		board = new GridPane();
		board.setPadding(new Insets(5)); //margin for the slot the grid will be in
		board.setPrefSize(gridWidth, gridWidth);		
		board.setStyle("-fx-background-color: #b2e9f7;");		
		
	
		//sets the grid depending on size
		for (int x = 0; x < gridSize; x++) {
			//this sets the constraints for box size so the size doesn't automatically adjust to child inside
			ColumnConstraints column = new ColumnConstraints();
			RowConstraints row = new RowConstraints();	
			row.setVgrow(Priority.ALWAYS);
			
			//so it fits the parent slot (center) for the grid whatever the size of the grid
			column.setPercentWidth(50);			
			row.setPercentHeight(50);			
			
			board.getColumnConstraints().add(column);			
			board.getRowConstraints().add(row);
			
//========== ONLY INTENDED FOR DEBUG. find another way to display grid lines. ==============================	
			board.setGridLinesVisible(true);
		}
	}
	
	//returns the GridPane object
	public GridPane getBoardGrid() {
		return board;
	}
	
	
	public void addValuesFromArray(int[][] boardArray) {
	
		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				//add the object to this coordinate
				String value = Integer.toString(boardArray[y][x]);
				Label label = new Label(value);		
				board.add(label,x,y);	
			}
		}
	}

	//this should be change to reading a shipArray instead of reading individual ships
	public void setupBoardFromShipObjects(Ship ship) {
		
		//ship Picture
		//NOTE: each ship image in the board are SEPARATE ImageView OBJECTS. that's why this is here vvv
		//i.e make a new ImageView object for every ship you add in the board
		//so this has to be in the loop when doing shipArrays		
		Image shipPic = new Image("ShipImage.jpg");
		shipImage = new ImageView();
		shipImage.setImage(shipPic);	//Format for adding stuff in grid
		shipImage.setFitWidth(blockImageSize);		//board.add(object,x ,y ,xSpan, ySpan) Spans are optional
		shipImage.setFitHeight(blockImageSize);			

		
		char orientation = ship.getOrientation();
		int x = ship.getColumn();
		int y = ship.getRow();
		int length = ship.getLength();
		
		
		//format for adding objects to grid   board.add(object, x, y, xSpan, ySpan) 
		//Span is optional but if you use it, you have to include both
		if (orientation == 'h') {
			//this just stretches the picture depending on length
			shipImage.setFitWidth(blockImageSize * length);			
			//add pic to board spanning it's length depending on orientation
			board.add(shipImage, x , y , length , 1); 
		}
		else if (orientation == 'v') {
			//need to rotate the image vertically here
			shipImage.setFitHeight(blockImageSize * length);			
			board.add(shipImage, x, y, 1, length);
		}
		//make eventlisteners for each ship ImageView object (for attacking)
	
	}
		
}	