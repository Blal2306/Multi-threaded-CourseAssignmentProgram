package registrationScheduler.store;

public class coursesDataBase
{
    //1 = A
    //2 = B
    //3 = C
    //4 = D
    //5 = E
    //6 = F
    //7 = G
    static int[] courses = {0,60,60,60,60,60,60,60};
    /**
     * @param i the requested course
     * @return TRUE or FALSE depending on whether or not the course is available
     */
    public static synchronized boolean getCourse(int i)
    {
        if(courses[i] > 0)
        {
            courses[i]--;
            return true;
        }
        else
            return false;
    }
    public static void printAvailableCourses()
    {
        System.out.println("COURSES DATABASE STATUS : ");
        for(int i = 1; i < courses.length; i++)
        {
            System.out.println(i + " : "+courses[i]);
        }
    }
}
