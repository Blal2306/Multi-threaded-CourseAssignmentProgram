package registrationScheduler.store;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;
import registrationScheduler.util.*;


public class Results implements StdoutDisplayInterface, FileDisplayInterface {

    static Map<Integer, String> outData = new TreeMap<Integer, String>();
    String OUTPUT_FILE_NAME = "";
    
    private static int CUMULATIVE_PREFERENCE = 0;
    
    public Results(String fileName)
    {
        //message for the logger
        Logger.writeOutput("CONSTRUCTOR : Results", 4);
        
        OUTPUT_FILE_NAME = fileName;
    }
    public static void insertPreference(int x)
    {
        CUMULATIVE_PREFERENCE = CUMULATIVE_PREFERENCE + x;
    }
    /**
     * @return returns the average preference calculated over all the assignments
     */
    public static double getAveragePref()
    {
        return CUMULATIVE_PREFERENCE/(double)80;
    }
    public synchronized void addData(int i, String StudentName, int[] grades) 
    {
        String out = StudentName;
        for(int j = 0; j < grades.length; ++j)
        {
            out = out + " " + convertToLetterGrade(grades[j]);
        }
        outData.put(i, out);
    }
    /**
     * @param i represents the integer representation of the course
     * @return character representation of the given course
     */
    public String convertToLetterGrade(int i)
    {
        if(i == 1)
            return "A";
        else if(i == 2)
            return "B";
        else if(i == 3)
            return "C";
        else if(i == 4)
            return "D";
        else if(i == 5)
            return "E";
        else if(i == 6)
            return "F";
        else if(i == 7)
            return "G";
        else
            return "INVALID GRADE";
    }
    public void writeSchedulesToScreen() 
    {
        for(Map.Entry<Integer, String> entry : outData.entrySet())
        {
            String value = entry.getValue();
            System.out.println(value);
        }
    }
    public void writeSchedulesToFile() 
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(OUTPUT_FILE_NAME, "UTF-8");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File could't be created ...");
            System.exit(0);
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("Unsupported Encoding ...");
            System.exit(0);
        }

        for(Map.Entry<Integer, String> entry : outData.entrySet())
        {
            String value = entry.getValue();
            writer.println(value);
        }
        
        writer.close();
        
    }
}
