
/**
 * Write a description of interface IVoteServiceInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IVoteServiceInterface
{
    public void output();
    
    public void receiveAnswer(String id, String answer);
    
    public void receiveAnswers(String[] ids, String[] answers);
}
