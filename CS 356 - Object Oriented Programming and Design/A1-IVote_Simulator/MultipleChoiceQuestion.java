public class MultipleChoiceQuestion extends Question implements QuestionInterface
{
    public MultipleChoiceQuestion(String question, String[] answerSet)
    {
        super(question, answerSet);
    }
    
    public boolean isMultiAnswer()
    {
        return true;
    }
}
