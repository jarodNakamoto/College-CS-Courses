import java.util.ArrayList;


public class IVoteService
{
    Question type;
    ArrayList<String> ids;
    
    //constructor
    public IVoteService()
    {
        // initialise instance variables
    }
    
    public void output()
    {
        for(int i = 0; i < ids.size(); i++)
        {
            System.out.println();
        }
    }
}
