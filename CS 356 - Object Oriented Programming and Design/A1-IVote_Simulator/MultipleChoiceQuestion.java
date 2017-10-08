public class MultipleChoiceQuestion implements Question
{
    // instance variables - replace the example below with your own
    private String question;
    private String answer;
    
    public MultipleChoiceQuestion(String question, String answer)
    {
        // initialise instance variables
        this.question = question;
        this.answer = answer;
    }

    
    public String getAnswer()
    {
        return answer;
    }
    public String getQuestion()
    {
        return question;
    }
    
    public boolean isCorrect(String response)
    {
        return answer.equals(response);
    }
}
