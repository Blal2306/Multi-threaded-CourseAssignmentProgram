package registrationScheduler.threadMgmt;

import registrationScheduler.store.*;
import registrationScheduler.util.Logger;

public class CreateWorkers
{
    private FileProcessor filePro;
    private Results res;
    private ObjectPool po;
    private int NUM_THREADS;
    
    //Reference to the FileProcessor, Results
    public CreateWorkers(FileProcessor f, Results r, ObjectPool p)
    {
        //logger message
        Logger.writeOutput("CONSTRUCTOR : Create Workers", 4);
        
        filePro = f;
        res = r;
        po = p;
    }
    //total number of threads passed
    public void startWorkers(int Threads)
    {
        NUM_THREADS = Threads;
        
        //Create the number of threads passed as argument
        Thread[] threads = new Thread[NUM_THREADS];
        
        for(int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(new WorkerThread(filePro, res, po, i));
        }
        
        //start all the threads
        for(int i = 0; i < threads.length; i++)
        {
            threads[i].start();
        }
        
        //join all the threads
        for(int i = 0; i < threads.length; i++)
        {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted ...");
            }
        }
    }
}
