package registrationScheduler.store;

import registrationScheduler.util.Logger;

public class Student
{
    private int StudentOrder;
    private String StudentName = "";
    
    //preferences for all seven courses
    int[] CoursePrefs = new int[7];
    
    //previous lowest preference
    int previousPref = 0;
    
    public Student(int order, String[] nameAndPrefs)
    {
        
        StudentOrder = order;
        StudentName = nameAndPrefs[0];
        
        //message for the Logger
        Logger.writeOutput("CONSTRUCTOR : Student - "+StudentName, 4);
        
        CoursePrefs[0] = Integer.parseInt(nameAndPrefs[1]);
        CoursePrefs[1] = Integer.parseInt(nameAndPrefs[2]);
        CoursePrefs[2] = Integer.parseInt(nameAndPrefs[3]);
        CoursePrefs[3] = Integer.parseInt(nameAndPrefs[4]);
        CoursePrefs[4] = Integer.parseInt(nameAndPrefs[5]);
        CoursePrefs[5] = Integer.parseInt(nameAndPrefs[6]);
        CoursePrefs[6] = Integer.parseInt(nameAndPrefs[7]);
    }
    /**
     * @return returns the name of the current student
     */
    public String getName()
    {
        return StudentName;
    }
    /**
     * @return next preferred course for the student
     */
    public int getNextPreference()
    {
        int out = -1;
        EXIT:
        for(int i = 0; i < CoursePrefs.length;i++)
        {
            if(CoursePrefs[i] == (previousPref+1))
            {
                ++previousPref;
                out = i+1;
                break EXIT;
            }
        }
        return out;
    }
}
