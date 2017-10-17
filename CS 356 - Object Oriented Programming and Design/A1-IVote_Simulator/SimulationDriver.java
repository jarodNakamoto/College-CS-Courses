public class SimulationDriver
{
    public static void main(String[] args)
    {
        //1) create question and answer set
        QuestionHandler questHandler = new QuestionHandler();
        Question q = questHandler.createQuestion();
        String[] answerSet = q.getAnswerSet();
        
        //2) create ivoteservice 
        IVoteService ivs = new IVoteService();
        ivs.setQuestion(q);
        
        //3) create students and their answers
        int numStudents = 30;
        StudentHandler stuHandler = new StudentHandler(numStudents, answerSet);
        stuHandler.generateStudents();
        
        //4) submit all the students' answers to ivote service
        stuHandler.submitToIVote(ivs);
        
        //5) call ivote service output
        ivs.output();
        
        //6) change an answer 
        System.out.println("\n\nTesting Student 7 Resubmitting an Answer\n");
        stuHandler.changeAnswer(ivs, 7);
        ivs.output();
    }
}
