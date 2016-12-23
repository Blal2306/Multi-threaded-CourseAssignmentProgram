package registrationScheduler.driver;

import java.io.IOException;
import registrationScheduler.store.*;
import registrationScheduler.threadMgmt.*;
import registrationScheduler.util.Logger;


public class Driver {

    static FileProcessor reader;
    static Results writer;
    static ObjectPool pool;

    //*** COMMAND LINE ARGUMENTS ***//
    
    static String INPUT_FILE = null;
    static String OUTPUT_FILE = null;
    static int NUM_THREADS = -1;
    static int DEBUG_VALUE = -1;
    
    //******************************//
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        //validate correct number of arguments have been passed
        if(args.length < 4)
        {
            System.out.println("Arguments Passed : " +args.length);
            System.out.println("Invalid number of arguments passed ...");
            System.exit(0);
        }
        //***** SET ALL THE COMMAND LINE ARGUMENTS ****//
        
        INPUT_FILE = args[0];
        OUTPUT_FILE = args[1];
        NUM_THREADS = Integer.parseInt(args[2]);
        DEBUG_VALUE = Integer.parseInt(args[3]);
        
        //*********** VERIFY ARGUMENTS *****************//
        
        if(NUM_THREADS < 1 || NUM_THREADS > 3 || DEBUG_VALUE < 0 || DEBUG_VALUE > 4)
        {
            System.out.println("Arguments not valid ....");
            System.exit(0);
        }
        //**********************************************//
        
        //set the logger level
        Logger.setDebugValue(DEBUG_VALUE);
        
        reader = new FileProcessor(INPUT_FILE);
        writer = new Results(OUTPUT_FILE);
        pool = ObjectPool.getInstance();
        
        //set the total number of threads
        int TOTAL_THREAD = NUM_THREADS;
        
        CreateWorkers test = new CreateWorkers(reader, writer, pool);
        
        test.startWorkers(TOTAL_THREAD);
        
        //print contents of the data structure to the screen
        if(Logger.getDebugValue() == 1)
        {
            writer.writeSchedulesToScreen();
        }
        
        //print the average preference score
        if(Logger.getDebugValue() == 0)
        {
            System.out.println("The average preference value is "+Results.getAveragePref());
        }
        
        //write everthing to the screen
        writer.writeSchedulesToFile();
        
    }
}