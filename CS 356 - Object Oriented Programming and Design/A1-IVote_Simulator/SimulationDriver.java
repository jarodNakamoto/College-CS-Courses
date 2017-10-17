public class SimulationDriver
{
    public static void main(String[] args)
    {
        int numStudents = 30;
        
        //tests single answer questions
        System.out.println("Testing Single Answer Question\n");
        //1) create question and answer set
        QuestionHandler questHandler = new QuestionHandler();
        Question q = questHandler.createQuestion();
        
        //2) create ivoteservice 
        IVoteService ivs = new IVoteService();
        stepsTwoThroughSix(ivs, q, numStudents);
        
        //tests multiple answer questions
        System.out.println("Testing Multiple Answer Question\n");
        //1) create question and answer set
        q = questHandler.createMultiAnswerQuestion();
        
        stepsTwoThroughSix(ivs, q, numStudents);
        
    }
    
    private static void stepsTwoThroughSix(IVoteService ivs, Question q, int numStudents)
    {
        //2) change question in ivoteservice 
        ivs.setQuestion(q);
        
        //3) create students and their answers
        numStudents = 30;
        //generate students that can have multiple answers
        StudentHandler stuHandler = new StudentHandler(numStudents, q.getAnswerSet());
        stuHandler.generateStudents(q.isMultiAnswer());
        
        //4) submit all the students' answers to ivote service
        stuHandler.submitToIVote(ivs);
        
        //5) call ivote service output
        ivs.output();
        
        //6) change an answer 
        System.out.println("Testing Student 7 Resubmitting an Answer\n");
        stuHandler.changeAnswer(ivs, 7);
        ivs.output();
    }
}
