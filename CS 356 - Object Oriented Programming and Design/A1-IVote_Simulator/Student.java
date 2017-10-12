public class Student
{
    // instance variables
    private String id;
    private String answer;
    
    //constructor
    public Student(String id, String answer)
    {
        // initialise instance variables
        this.id = id;
        this.answer = answer;
    }

    //returns variable values
    public String getAnswer()
    {
        return answer;
    }
    public String getID()
    {
        return id;
    }
    
    //changes value and returns old value
    public String setAnswer(String answer)
    {
        String temp = this.answer;
        this.answer = answer;
        return temp;
    }
    public String setID(String id)
    {
        String temp = this.id;
        this.id = id;
        return temp;
    }
}
