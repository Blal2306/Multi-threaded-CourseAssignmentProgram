package registrationScheduler.store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import registrationScheduler.util.Logger;

public class FileProcessor
{
    FileReader fileReader;
    BufferedReader bufferedReader;
    int lineNum = 1;
    public FileProcessor(String fileName)
    {
        //message for the Logger
        Logger.writeOutput("CONSTRUCTOR : File Processor", 4);
        
        try
        {
            fileReader= new FileReader(fileName);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'"); 
        }
        bufferedReader = new BufferedReader(fileReader);
    }
    /**
     * @return returns a line of text read from the input file
     */
    public synchronized String getLine()
    {
        String out = null;
        try
        {
            out = bufferedReader.readLine();
            if(out != null)
            {
                out = Integer.toString(lineNum) + ":"+out;
                lineNum++;
            }
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file..."); 
        }
        return out;
    }
}
