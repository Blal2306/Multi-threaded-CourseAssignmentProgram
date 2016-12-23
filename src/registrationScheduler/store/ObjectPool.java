package registrationScheduler.store;

import java.util.ArrayList;
import registrationScheduler.store.*;
import registrationScheduler.util.Logger;

//*** OBJECT POOL ***//
public class ObjectPool
{
    //total size of the pool
    public static final int DEFAULT_POOL_SIZE = 5;
    
    //can have only one instance of the pool
    protected static ObjectPool instance = null;
    
    //array list of all the objects
    private ArrayList<SeatRequestor> reusables;
    private int maxPoolSize = DEFAULT_POOL_SIZE;
    
    protected ObjectPool()
    {
        //message for logger
        Logger.writeOutput("CONSTRUCTOR : Object Pool", 4);
        reusables = new ArrayList<SeatRequestor> ();
    }
    /**
     * @return returns the maximum capacity of the object pool
     */
    public int getMaxPoolSize()
    {
        return maxPoolSize;
    }
    public void setMaxPoolSize(int max)
    {
        this.maxPoolSize = max;
    }
    /**
     * @return Returns the object held in the object pool
     */
    public synchronized SeatRequestor acquireObject()
    {
        //try to find object not being used
        for(SeatRequestor x: reusables)
        {
            if(!x.isInUse())
            {
                x.setInUse(true);
                return x;
            }
        }
        
        //it wasn't possible to get the object
        if(reusables.size() >= getMaxPoolSize())
        {
            return null;
        }
        
        //create a new object
        SeatRequestor newObject = new SeatRequestor();
        newObject.setInUse(true);
        reusables.add(newObject);
        
        return newObject;
    }
    public synchronized void releaseObject(SeatRequestor sub)
    {
        int idx = reusables.indexOf(sub);
        
        //invalid response
        if(idx == -1)
        {
            return;
        }
        SeatRequestor obj = reusables.get(idx);
        obj.setInUse(false);
    }
    /**
     * @return Returns the instance of the Object Pool
     */
    public static ObjectPool getInstance()
    {
        if(instance == null)
        {
            synchronized (ObjectPool.class)
            {
                if(instance == null)
                {
                    instance = new ObjectPool();
                }
            }
        }
        return instance;
    }
   
}
