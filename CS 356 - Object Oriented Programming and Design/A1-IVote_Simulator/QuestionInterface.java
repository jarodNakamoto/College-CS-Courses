public interface QuestionInterface
{
    public String getQuestion();
    
    public String[] getAnswerSet();
    
    public void setAnswerSet(String[] answerSet);
    
    public boolean isMultiAnswer();
}
