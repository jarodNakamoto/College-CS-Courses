
/**
 * Write a description of class Question here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Question implements QuestionInterface
{
    // instance variables
    private String question;
    private String[] answerSet;
    
    public Question(String question, String[] answerSet)
    {
        // initialise instance variables
        this.question = question;
        this.answerSet = answerSet;
    }
    
    public String[] getAnswerSet()
    {
        return answerSet;
    }
    public String getQuestion()
    {
        return question;
    }
    
    public void setAnswerSet(String[] answerSet)
    {
        this.answerSet = answerSet;
    }
}
