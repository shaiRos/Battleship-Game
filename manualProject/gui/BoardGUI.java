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
import players.Player;

/**
*	Creates the display of the boards. The display uses GridPane layout to visually show the players' boards.
*	Also manages the size and adding contents into the grid.
*	@author 	Brandon Lu, Shaina Rosell, Betty Zhang, Charlene Madayang
*/


public class BoardGUI { 
	
	private GridPane board;
	private int gridSize;
	private int gridWidth; //including margins
	private ImageView shipImage;
	final private double blockSize;	//width & height for each square in grid
	
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
		double actualWidth = (double)(gridWidth-15);
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
	public void addValuesFromArray(Player thisPlayer, String boardType) {
		BoardValue[][] boardArray = new BoardValue[1][1];
	
		if (boardType == "gameBoard") {
			boardArray = thisPlayer.getPlayerBoard().gameBoard;
		} else if (boardType == "guessBoard"){
			boardArray = thisPlayer.getPlayerBoard().guessBoard;
		}
	
		for (int x = 0; x < boardArray.length; x++) {
			for (int y = 0; y < boardArray.length; y++) {
				//add the object to this coordinate
				BoardValue value = boardArray[y][x];

				if (value != BoardValue.EMPTY) {
					
					switch(value) {		
						case MISS:
							ImageView missImage = getImage("/images/MissImage.png");							
							board.add(missImage, x, y);
							break;
						case HIT:
							ImageView hitImage = getImage("/images/HitImage.png");							
							board.add(hitImage, x, y);		
							break;
					} 
				}	
			}
		}
		if (boardType != "guessBoard") {
			setShipArrays(thisPlayer.getPlayerBoard().getShipArray(), boardArray);
		}
	}

	/**	
	*	The ship images displayed are determined from the shipArrays of each player. Adding images for the ships in the board display is much
	*	more complex since front and back have different images and images have to be rotated when the ship is horizontal
	*
	*	@param 		array - a Ship array containing a player's ships in his/her current gameboard
	*				boardArray - An array of a player containg boardvalues.
	*/
	public void setShipArrays(Ship[] array, BoardValue[][] boardArray) {
			

 		for (Ship ships : array) {
			if (ships != null){
				//all coordinates this ships occupies
				int[][] coords = ships.getShipCoordinates();

				//going through the coordinates [row,column]
 				for (int body = 0 ; body <= (ships.getLength() - 1) ; body++) {
					
					//FRONT of the ship has different image
					if (body == 0) {
						int[] front = coords[0];
						//different images for front and back ships
						addShipImage("/images/Shipt.png", boardArray, front[0], front[1], ships.getOrientation());
						
					//BACK of the ship has different image	
					} else if (body == ships.getLength()) {
						int[] back = coords[body];
						addShipImage("/images/Shipt.png", boardArray, back[0], back[1], ships.getOrientation());			
						
					//BODY of the ship	
					} else {
						int[] coordinate = coords[body];
						addShipImage("/images/Shipt.png", boardArray, coordinate[0], coordinate[1], ships.getOrientation());			
					}
				}
			}
		}
	}
	
	/**
	*	Adds a specified ship image to a coordinate in the board display. Different images include the ShipHead, ShipBody, and ShipTail
	*	images are rotated if the ship orientation is horizontal
	*
	*	@param 		imgPath - a String specifying what file image should be added into the board display
	*				boardArray - An array of a player containg boardvalues.
	*				row - an integer indicating the row where the ship image should be added
	*				col - an integer indicating the column where the ship image should be added
	*				orientation - a char indicating the orientation of the ship that is to be added into the board display
	*/
	public void addShipImage(String imgPath, BoardValue[][] boardArray, int row , int col, char orientation) {
		if (boardArray[row][col] != BoardValue.HIT) {
			ImageView shipImg = getImage(imgPath);
			//have to rotate image if vertical
			if (orientation == 'h') {
				shipImg.setRotate(90);
			}
			board.add(shipImg, col, row);
		}
	}
		
}	