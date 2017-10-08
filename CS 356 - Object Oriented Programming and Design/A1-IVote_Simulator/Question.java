public interface Question
{
    public String getAnswer();
    
    public String getQuestion();
    
    public boolean isCorrect(String response);
}
