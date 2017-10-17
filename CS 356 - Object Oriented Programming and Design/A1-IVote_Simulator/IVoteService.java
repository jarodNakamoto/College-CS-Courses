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
        
        //if student has answered before
        
        //System.out.println("previous student answer: " + previousAnswer);
        if(previousAnswer != null)
        {
            //remove previous answer from maps
            studentResponses.remove(id);
            //decrement the count of all the previous answers
            splitAnswerAndUpdateCount(previousAnswer, -1);
            
            System.out.println("id: " + id + " answer: " + answer);
            System.out.println("previous answer: " + previousAnswer);
        }
        
        //add student response to maps
        //System.out.println("id: " + id + " answer: " + answer);
        splitAnswerAndUpdateCount(answer, 1);
        studentResponses.put(id, answer);
        
        //System.out.println("after ans count: " + count + "\n");
    }
    
    private void splitAnswerAndUpdateCount(String ans, int delta)
    {
        //should split it into multiple answers if there is more than one
        String[] answerSet = ans.split(";");
        for(int i = 0; i < answerSet.length; i++)
        {
            //if not empty string
            if(!answerSet[i].equals(""))
            {
                int count = answerToCountMap.get(answerSet[i]).intValue();
                //System.out.println("b4: " + count);
                count += delta;
                //System.out.println("after: " + count);
                answerToCountMap.put(answerSet[i], new Integer(count));
            }
        }
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
