import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class IVoteService implements IVoteServiceInterface
{
    private Question question;
    //               id, answer
    private HashMap<String, String> studentResponses;
    //              answer, count
    private HashMap<String, Integer> answerToCountMap;
    
    //constructor
    public IVoteService()
    {
        // initialise instance variables
        
        
    }
    
    //methods
    
    public void setQuestion(Question question)
    {
        this.question = question;
        String[] answerSet = question.getAnswerSet();
        
        studentResponses = new HashMap<String, String>();
        answerToCountMap = new HashMap<String, Integer>();
        
        for(String ans: answerSet)
        {
            answerToCountMap.put(ans, new Integer(0));
        }
    }
    
    //receive answer of a single student
    public void receiveAnswer(String id, String answer)
    {
        String previousAnswer = studentResponses.get(id);
        //
        //System.out.println("id: " + id + " answer: " + answer);
        //
        
        //if student has answered before
        if(previousAnswer != null)
        {
            //remove previous answer from maps
            studentResponses.remove(id);
            //decrement the count of the previous answer
            int prevCount = answerToCountMap.get(previousAnswer).intValue();
            prevCount--;
            answerToCountMap.put(previousAnswer, new Integer(prevCount));
            System.out.println("id: " + id + " answer: " + answer);
            System.out.println("previous answer: " + previousAnswer);
        }
        
        //add student response to maps
        int count = answerToCountMap.get(answer).intValue();
        count++;
        studentResponses.put(id, answer);
        answerToCountMap.put(answer, new Integer(count));
        
        //System.out.println("after ans count: " + count + "\n");
    }
    
    //receive an array of students and an array of their answers
    public void receiveAnswers(String[] ids, String[] answers)
    {
        for(int i = 0; i < ids.length; i++)
            receiveAnswer(ids[i], answers[i]);
    }
    
    //display the ivote
    public void output()
    {
        //System.out.println("answerToCountMap size: " + answerToCountMap.size());
        //System.out.println("studentResponses size: " + studentResponses.size() + "\n\n");
        System.out.println(question.getQuestion() + "\n");
        
        Set<String> answers = answerToCountMap.keySet();
        Iterator<String> it = answers.iterator();
        
        while(it.hasNext())
        {
            String str = it.next();
            System.out.println(str + " : " + answerToCountMap.get(str));
        }
         System.out.println("\n");
    }
}
