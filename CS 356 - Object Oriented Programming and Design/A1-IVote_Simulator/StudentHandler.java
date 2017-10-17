public class StudentHandler
{
    private Student[] roster;
    private String[] answerSet;

    /**
     * Constructor for objects of class StudentHandler
     */
    public StudentHandler(int numStudents, String[] answerSet)
    {
        // initialise instance variables
        roster = new Student[numStudents];
        this.answerSet = answerSet;
    }

    //fills the array of students with an answer
    public void generateStudents()
    {
        for(int i = 0; i < roster.length; i++)
        {
            roster[i] = new Student(i+"", generateAnswer());
        }
    }
    
    //submit all students answers to ivote service
    public void submitToIVote(IVoteService iVote)
    {
        //populate arrays with corresponding student ids and answers
        String[] ids = new String[roster.length];
        String[] answers = new String[roster.length];
        
        for(int i = 0; i < roster.length; i++)
        {
            ids[i] = roster[i].getID();
            answers[i] = roster[i].getAnswer();
        }
        
        iVote.receiveAnswers(ids, answers);
    }
    
    //randomly changes the answer of the student in the roster with the id
    //returns old answer
    public String changeAnswer(IVoteService iVote, int id)
    {
        String newAnswer = generateAnswer();
        String previousAnswer = roster[id].setAnswer(newAnswer);
        iVote.receiveAnswer(id+"", newAnswer);
        return previousAnswer;
    }
    
    //pick a random answer from the answer set
    public String generateAnswer()
    {
        return answerSet[(int)(Math.random()*answerSet.length)];
    }
}
