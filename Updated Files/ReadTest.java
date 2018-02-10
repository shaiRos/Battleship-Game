import java.io.*;
import java.util.Scanner;

public class ReadTest{
	public static void main(String[] args)
	{
		getShipInfo();
    }
    

    public static void getShipInfo()
    {
        //Initiate which file to read
        String fileToRead = "map.txt";

        //Initiate line for ship data from file 
        String shipInfo = null;


		try {
            // FileReader reads text files
            FileReader fileReader = new FileReader(fileToRead);

            // Wrap FileReader in BufferedReader to efficiently read chars, lines, etc. (lines in this case)
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Initiate array that stores ship info 
            int shipArray[][] = new int[4][4];

            //Initiate counter for shipArray
            int shipCounter = 0;

            //Extracting data from file
            while ((shipInfo = bufferedReader.readLine()) != null) {
               
                /* Spliting line by spaces. Depending on how we want to display the data we can 
                ** determine how to split info
                */
                String[] line = shipInfo.split(" ");

                //Stores ship data in seperate variables 
                int orientation = Integer.parseInt(line[0]);
                int length = Integer.parseInt(line[1]);
                int column = Integer.parseInt(line[2]);
                int row = Integer.parseInt(line[3]);

                //Stores ship data in an array that will soon be stored into a 2D array
                int[] info = {orientation,length,column,row};

                //Extracting data, storing into array
                shipArray[shipCounter] = info;

                //Ship data check
                System.out.println("Ship " + shipCounter + ": " + orientation + " " +
                         length + " " + column + " " + row + " ");
                shipCounter++;

            }

            /*Error checking in shipArray info
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                    System.out.println(shipArray[i][j]) ;
            }
            */

            //Need to always close after done using it
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
