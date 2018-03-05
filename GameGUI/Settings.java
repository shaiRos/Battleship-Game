

public class Settings {

	//Window Settings
	final protected int xWindowSize = 1040;
	final protected int yWindowSize = 920;
	final protected int botHeight = 150;
	final protected int smallGridWidth = 250; //including margins
	final protected int sidePanelWidth = 270;	
	final protected int bigGridWidth = 770; //including margins	
	
	//Grid Settings
	protected int gridSize = 5; //max 20  can change just comment out adding stuff to ownboards and uncomment new int....
	protected String mode = "AIvP"; //'PvP' or 'AIvP'		
	protected String setupMode = "mapFromFiless";			
	protected String fileName = "map.txt";
	
	//players
	protected int player1OwnBoard[][] = {{5,5,5,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};//new int [gridSize][gridSize];
	protected int player2OwnBoard[][] = {{0,0,0,0,0},{0,0,0,0,0},{5,5,5,5,0},{0,0,0,0,0},{0,0,0,0,0}};//new int [gridSize][gridSize];
	protected int player1GuessBoard[][] = new int[gridSize][gridSize];
	protected int player2GuessBoard[][] = new int [gridSize][gridSize];	
	
	
	public void setGridSize(int value) {
		gridSize = value;
	}
	
	public void setupMode(String mode) {
		setupMode = mode;
	}
		


}
	