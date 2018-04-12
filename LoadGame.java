import java.io.*;
import java.util.Scanner;

/**
*	Temporary class - to be implemented later in gameConfig or
*	similar class so we import already created maps instead of
*	asking users for input
*
*/
public class LoadGame{


      public static void loadBoard()
    {
        //Initiate which file to read
        String fileToRead = "boards.txt";

        //Initiate line for ship data from file 
        String line = null;
         try {
            // FileReader reads text files
            FileReader fileReader = new FileReader(fileToRead);
            // Wrap FileReader in BufferedReader to efficiently read chars, lines, etc. (lines in this case)
            BufferedReader reader = new BufferedReader(fileReader);

            while(line = reader.readLine()) != null){

                if ((line == "Number of Ships:"){
                    String data = reader.readLine();
                    int numShips = Integer.parseInt(data);
                }


                //will need to make setBoardSize public
                /* if ((line = reader.readLine()) == "PLAYER1SHIP:"){
                    String data = reader.readLine();

                    for (int i = 0; i < numShips; i++){
                        
                    }

                */

                if  (line == "Current Game Mode:"){
                    
                }


            }

            reader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileToRead + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileToRead + "'");  
        }
    }



    
	//read file and store data in an array
	/*
    public static void loadBoard()
    {
        //Initiate which file to read
        String fileToRead = "boards.txt";

        //Initiate line for ship data from file 
        String data = null;


		try {
            // FileReader reads text files
            FileReader fileReader = new FileReader(fileToRead);
            // Wrap FileReader in BufferedReader to efficiently read chars, lines, etc. (lines in this case)
            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();

            //Extracting data from file
            String[] dataLines;
            int i = 0;
            for (i = 0; i < 7; i++){
                //Extracting data, storing into the dataLines array
                line = dataLines[i];
            }

            //BoardValue[][] currentBoard;
            while (line != null){
                String[] value = line.split();
                for (int x = 0; x < value.length; x++){
                    for (int y = 0; y < value.length; y++){
                        switch(value){

                            case 1:
                                currentValue[y][x] = SHIP;
                                break;
                            case 2:
                                currentValue[y][x] = MISS;
                                break;
                            case 3: 
                                currentValue[y][x] = HIT;
                                break;
                            case 4: 
                                currentValue[y][x] = EMPTY;
                                break;
                        }
                    }
                }

            }


            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileToRead + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileToRead + "'");  
        }
    }

    }
    */

/*
    public void loadBoardSizeGUI(String line){
        int size = Integer.dataLines[0];
        BoardGui.setBoardSizeGUI(size);     
    }

    
    public void loadCurrentMode());
    

    //Getting player.... from a file??? how is that even possble...
    public void loadCurrentPlayer(String line){
        AttackPhase.setCurrentPlayer(line);
    }

    public void loadPlayer1(String line){

    }


*/


}
