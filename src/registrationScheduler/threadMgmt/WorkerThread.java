package registrationScheduler.threadMgmt;

import registrationScheduler.store.FileProcessor;
import registrationScheduler.store.Results;
import registrationScheduler.store.ObjectPool;
import registrationScheduler.util.Logger;
import registrationScheduler.store.Student;
import registrationScheduler.store.SeatRequestor;

public class WorkerThread implements Runnable
{
    //Reference to the File Processor
    //Reference to the result object
    //Reference to the ObjectPool which will get the course assigned
    //Current Thread Number
    
    private FileProcessor fPro;
    private Results res;
    private ObjectPool p;
    private int tNum;
    
    public WorkerThread(FileProcessor f, Results r, ObjectPool pool, int ThreadNum)
    {
        //message for the Logger
        Logger.writeOutput("CONSTRUCTOR : Worker Thread "+ThreadNum, 4);
        
        fPro = f;
        res = r;
        p = pool;
        tNum = ThreadNum;
    }
    public void run()
    {
        //Message for the Logger
        Logger.writeOutput("RUN METHOD : Thread - "+tNum, 3);
        
        String temp = "";
        
        //continue reading until end of file is reached
        while((temp = fPro.getLine()) != null)
        {
            //remove all the white space at both ends fo the string
            temp = temp.trim();
            
            //get the order in which the the line was read
            String[] temp1 = temp.split(":");
            int order = Integer.parseInt(temp1[0]);
            
            //get the name and the preferences
            String[] temp2 = temp1[1].split(" ");
            
            //generate the preference calculator
            int[] preferences = preferenceBuilder(temp2);
            
            //build the student object
            Student st = new Student(order, temp2);
            
            //get the object to assign the course from the object pool
            SeatRequestor req = null;
            while(req == null)
            {
                //wait for 1/2 second before trying again
                try 
                {
                    Thread.sleep(100);
                } catch (InterruptedException ex) 
                {
                    System.out.println("Thread Interrupted ...");
                }
                
                //try to acquire the object again
                req = p.acquireObject();
            }
            
            //get the required courses for the student
            int[] cou = new int[5] ;
            int index = 0; //where to put the course
            int count = 0; //how many courses have we got
            int pref = -1;
            while(count < 5)
            {
                //what is the next preference
                pref = st.getNextPreference();
                boolean avai = req.requestSeatIn(pref);
                
                //if the course is available, then assign the course
                if(avai)
                {
                    //insert the course
                    cou[index] = pref;
                    index++;
                    count++;
                    
                    //what was the preference for this 
                    int pref_for_course = preferences[pref-1];
                    
                    //add the preference to the result for
                    //cumulative calculation
                    Results.insertPreference(pref_for_course);
                }
                //no more courses left to be assigned
                else if(pref == -1)
                    break;
            }
            
            //insert the coure into the result object
            res.addData(order, st.getName(), cou);
            
            //message for the Logger
            Logger.writeOutput("ENTRY ADDED TO RESULT : Thread "+tNum+", "
                    + "Student - "+st.getName(), 2);
            
            //release the object back to the Object Pool
            p.releaseObject(req);
            
        }
    }
    //To build the preference finder
    /**
     * @param in the RAW input of the course preferences
     * @return array representing the course preferences
     */
    public int[] preferenceBuilder(String[] in)
    {
        int[] out = new int[7];
        
        out[0] = Integer.parseInt(in[1]);
        out[1] = Integer.parseInt(in[2]);
        out[2] = Integer.parseInt(in[3]);
        out[3] = Integer.parseInt(in[4]);
        out[4] = Integer.parseInt(in[5]);
        out[5] = Integer.parseInt(in[6]);
        out[6] = Integer.parseInt(in[7]);
        
        return out;
    }
}
