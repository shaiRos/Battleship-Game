package gui;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.control.Label;
import board.BoardValue;
import board.Ship;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

/**
*	Creates the display of the boards. The display uses GridPane layout to visually show the players' boards.
*	Also manages the size and adding contents into the grid.
*	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayang
*/


public class BoardGUI { 
	
	private GridPane board;
	private int gridSize;
	private int gridWidth; //including margins
	private double actualWidth;
	private ImageView shipImage;
	private double blockSize;	//width & height for each square in grid
	
	/**
	*	The constructor. Creates the Grid displays depending on the indicated size in the parameters. Also sets the constraints
	*	and adjusts the sizes to accomodate for the window size.
	*
	*	@param 		gridSize - an integer indicating what board size to create
	*	@param 		gridwidth - an integer indicating the width the display of the board occupies in the window.(Includes the margins) This is a final value set on Settings class.
	*/
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
	
	/**
	*	@return		the GridPane instance of this board display
	*/
	public GridPane getBoardGrid() {
		return board;
	}
	
	/**
	*	@return		the grid size of this board display
	*/	
	public int getGridSize() {
		return gridSize;
	}
	
	/**
	*	@return		the gridWidth (in pixels) of this board display. (Includes the margins)
	*/	
	public int getGridWidth() {
		return gridWidth;
	}
	
	/**
	*	@return		the pixel size of one block of the grid of this board display
	*/	
	public double getGridBlockSize() {
		return blockSize;
	}


	/**
	*	Creates an ImageView instance for the images that will be placed on each single block of the grid. This includes the image components of the ships,
	*	the images for misses, and the images for hits. This formats the images' sizes to fit the block size of the current grid.
	*
	*	@param		image - a string referencing an image
	*	@return		an ImageView instance that will be placed in the grid blocks
	*/
	public ImageView getImage(String image) {
		
		Image shippPic = new Image(image);
		ImageView shipImage = new ImageView();
		shipImage.setImage(shippPic);	
		shipImage.setFitWidth(blockSize);		
		shipImage.setFitHeight(blockSize);		
		return shipImage;
	}
		

	/**
	*	Reads an array that represents a player's board and creates the visual representation of it. Using the getImage() method,
	*	it adds the image representation of ships, hits and misses into the GridPane Layout display of the player's board.
	*
	*	@param 		boardArray - an array containing enum values of ships, hits and misses
	*	@param		boardType - a String indicating what type this GridPane represents. ("gameBoard" or "guessBoard"). In guessBoard, the ships are hidden
	*/
	public void addValuesFromArray(BoardValue[][] boardArray, String boardType) {
		for (int x = 0; x < boardArray.length; x++) {
			for (int y = 0; y < boardArray.length; y++) {
				//add the object to this coordinate
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