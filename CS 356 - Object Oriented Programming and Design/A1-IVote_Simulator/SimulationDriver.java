public class SimulationDriver
{
    
    public static void main(String[] args)
    {
        int numStudents = 40;
        Student[] roster = new Student[numStudents];
        IVoteService ivs = new IVoteService();
        ivs.output();
    }
}
