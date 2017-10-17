public class QuestionHandler
{
    public QuestionHandler()
    {
        // initialise instance variables
       
    }
    
    //single answer question and answer set
    public Question createQuestion()
    {
        return new SingleChoiceQuestion("What is an apple?", createAnswerSet());
    }
    private String[] createAnswerSet()
    {
        String[] ansSet = {"Fruit", "Vegetable", "Potato", "Who cares?"};
        return ansSet;
    }
    
    //multiple answer question
    public Question createMultiAnswerQuestion()
    {
        return new MultipleChoiceQuestion("What is an apple?", createMultiAnswerSet());
    }
    private String[] createMultiAnswerSet()
    {
        String[] ansSet = {"Fruit", "Vegetable", "Potato", "Who cares?", "Apple"};
        return ansSet;
    }
}
