import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundImage;


public class BoardGUI { 
	
	private GridPane board;
	private int gridSize;
	private int gridWidth; //including margins
	private double actualWidth;
	private ImageView shipImage;
	private double blockSize;	//width & height for each square in grid
	
	
	public BoardGUI(int gridsize, int gridwidth) {
		
		gridSize = gridsize;
		gridWidth = gridwidth;
		actualWidth = (double)(gridWidth-10);
		blockSize = (actualWidth/(double)gridSize);  
	
		board = new GridPane();
		board.setPrefSize(gridWidth, gridWidth);	
		board.setPadding(new Insets(5)); //margin for the slot the grid will be in
		//https://pngtree.com/freebackground/blue-watercolor-background-material_754790.html
		Image bg = new Image("images/sea.jpg");
		BackgroundImage bgImage = new BackgroundImage(bg,null,null,null,null);
		board.setBackground(new Background(bgImage));
		//sets the grid depending on size
		for (int x = 0; x < gridSize; x++) {
			//this sets the constraints for box size so the size doesn't automatically adjust to child inside
			ColumnConstraints column = new ColumnConstraints();
			RowConstraints row = new RowConstraints();	
			//so it fits the parent slot (center) for the grid whatever the size of the grid
			column.setPercentWidth(50);			
			row.setPercentHeight(50);			
			board.getColumnConstraints().add(column);			
			board.getRowConstraints().add(row);
		}		
//========== ONLY INTENDED FOR DEBUG. find another way to display grid lines. ==============================	
		board.setGridLinesVisible(true);	
	}
	
	public GridPane getBoardGrid() {
		return board;
	}
	
	public int getGridSize() {
		return gridSize;
	}
	
	public int getGridWidth() {
		return gridWidth;
	}
	
	public double getGridBlockSize() {
		return blockSize;
	}
	
	public ImageView getImage(String image) {
		
		Image shippPic = new Image(image);
		ImageView shipImage = new ImageView();
		shipImage.setImage(shippPic);	
		shipImage.setFitWidth(blockSize);		
		shipImage.setFitHeight(blockSize);		
		return shipImage;
	}
		
	
	public void addValuesFromArrayEnum(BoardValue[][] boardArray, String boardType) {
		for (int x = 0; x < boardArray.length; x++) {
			for (int y = 0; y < boardArray.length; y++) {
				//add the object to this coordinate
				//System.out.println(x + ", " + y);
				BoardValue value = boardArray[y][x];

				if (value != BoardValue.EMPTY) {
					
					switch(value) {
						
						case SHIP:
							if (boardType != "guessBoard") {
								ImageView shipImage = getImage("images/Shipt.png");
								board.add(shipImage, x, y);
							}
								break;
							
						case MISS:
							ImageView missImage = getImage("images/MissImage.png");							
							board.add(missImage, x, y);
							break;
						case HIT:
							ImageView hitImage = getImage("images/HitImage.png");							
							board.add(hitImage, x, y);		
							break;
					} 
				} 
			}
		}
	}

	public void addValuesFromArray(int[][] boardArray, String boardType) {
		for (int x = 0; x < boardArray.length; x++) {
			for (int y = 0; y < boardArray.length; y++) {
				//add the object to this coordinate
				//System.out.println(x + ", " + y);
				int value = boardArray[y][x];

				if (value != 0) {
					
					switch(value) {
						
						case 5:
							if (boardType != "guessBoard") {
								ImageView shipImage = getImage("images/Shipt.png");
								board.add(shipImage, x, y);
							}
								break;
							
						case -1:
							ImageView missImage = getImage("images/MissImage.png");							
							board.add(missImage, x, y);
							break;
						case 1:
							ImageView hitImage = getImage("images/HitImage.png");							
							board.add(hitImage, x, y);		
							break;
					}
				}	
			}
		}
	}

	//this should be change to reading a shipArray instead of reading individual ships
	public void setupBoardFromShipObjects(Ship ship) {
		
		//ship Picture
		//NOTE: each ship image in the board are SEPARATE ImageView OBJECTS. 
		//i.e make a new ImageView object for every ship you add in the board			

		char orientation = ship.getOrientation();
		int x = ship.getColumn() - 1; //indexing -1
		int y = ship.getRow() - 1;
		int length = ship.getLength();
		
		//format for adding objects to grid   board.add(object, x, y, xSpan, ySpan) 
		//Span is optional but if you use it, you have to include both
		if (orientation == 'h') {
			
			for (int coord = 0; coord < ship.getLength(); coord++) {
				
				
				ImageView shipImage = getImage("RedCircle.png");				
				board.add(shipImage, (x + coord) , y);
			}

		//this just stretches the picture depending on length
			//shipImage.setFitWidth(blockSize * length);			
			//add pic to board spanning it's length depending on orientation
			//board.add(shipImage, x , y , length , 1); 
		}
		else if (orientation == 'v') {

			for (int coord = 0; coord < ship.getLength(); coord++) {
				
				ImageView shipImage = getImage("RedCircle.png");					
				board.add(shipImage,x , (y+coord));
			}


	
		//need to rotate the image vertically here
		//	shipImage.setFitHeight(blockSize * length);			
		//	board.add(shipImage, x, y, 1, length);
		}
	
	}
		
}	