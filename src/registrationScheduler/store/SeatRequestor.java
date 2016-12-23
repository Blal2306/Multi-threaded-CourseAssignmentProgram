package registrationScheduler.store;

import registrationScheduler.store.*;

//*** OBJECT STORED IN THE POOL ****//
public class SeatRequestor
{
    //to indicate if the object is in use or not
    private boolean inUse = false;
    
    public boolean requestSeatIn(int course)
    {
        boolean out = coursesDataBase.getCourse(course);
        return out;
    }
    
    public boolean isInUse()
    {
        return inUse;
    }
    public void setInUse(boolean x)
    {
        this.inUse = x;
    }
}
