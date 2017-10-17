public class QuestionHandler
{
    public QuestionHandler()
    {
        // initialise instance variables
       
    }
    
    public Question createQuestion()
    {
        return new Question("What is an apple?", createAnswerSet());
    }
    private String[] createAnswerSet()
    {
        String[] ansSet = {"Fruit", "Vegetable", "Potato", "Who cares?"};
        return ansSet;
    }
}
