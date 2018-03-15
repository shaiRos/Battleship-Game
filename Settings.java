

public class Settings {

	//Window Settings
	final public static int xWindowSize = 1040;
	final public static int yWindowSize = 920;
	final public static int botHeight = 150;
	final public static int smallGridWidth = 250; //including margins
	final public static int sidePanelWidth = 270;	
	final public static int bigGridWidth = 770; //including margins	
	
	//Grid Settings
	public static int gridSize = 6; //max 20  can change just comment out adding stuff to ownboards and uncomment new int....
	public static String mode = "AIvP"; //'PvP' or 'AIvP'		
	public static String setupMode = "mapFromFiless";			
	public static String fileName = "map.txt";
	
	//players
	
	
	public void setGridSize(int value) {
		gridSize = value;
	}
	
	public void setupMode(String mode) {
		setupMode = mode;
	}
		


}
	