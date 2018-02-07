import java.io.*;
import java.util.Scanner;

public class ReadTest{
	public static void main(String[] args)
	{
		String fileToRead = "readmebitch.txt";
        int shipInfo = 0;
        char[] cbuf = new char[5];
        int off = 0;
        int len = 2;
        

		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileToRead);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Reads array and prints each integer 
            while((shipInfo = bufferedReader.read(cbuf, off, len)) != 0) {
                if (shipInfo == -1) {
                   System.out.println("End of ship information.");
                   break;

                }
                else {
                    System.out.println(cbuf);
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