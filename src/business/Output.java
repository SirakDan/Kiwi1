/*
 * The main output of the program. This class manages the Syntax file that the
 * application will create. In this version, it is only able to create .txt files.
 */
package business;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kiwi
 */
public class Output {
    
	
	/**
	*This method takes a string (the output) and creates a .txt file with it.
	* @param result: the string generated by the application.
	*/
	public static void publishOutput(String text, String title) {
                if(title.equals(""))
                    title="Syntax";
                File f = new File(title+".txt");
		
		FileWriter w = null;
                try {
                    w = new FileWriter(f);
                } catch (IOException ex) {
                    Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedWriter bw = new BufferedWriter(w);
                PrintWriter wr = new PrintWriter(bw);  
                try{

                wr.write(text); //It writes the file

                wr.close();
                bw.close();
                
                }catch(IOException e){}

	}
}