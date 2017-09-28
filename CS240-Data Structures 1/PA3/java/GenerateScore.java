/**
* COPYRIGHT: smanna@cpp.edu
* CS 240 Spring 2016
* !!!!WARNING!!!! Please make no changes to this code. 
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class GenerateScore {

  private HotPotato hotPotato;

  public GenerateScore(String fileName) {
    try {
      File file = new File(fileName);
      Scanner input = new Scanner(file);
      hotPotato = new HotPotato();
      reader(input);
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found!");
    }
  }

  /** Prints a line of '_' with length of 36 and a newline at the end.
  */
  private void printLines() {
    for (int i = 0; i < 36; i++)
      System.out.print("_");
    System.out.print("\n");
  }

  /** Takes a Scanner input and reads in parameters and answers and sets up the
  *   testcases.
  *   @param input Scanner that uses the input equation file
  */
  private void reader(Scanner input) {
    ArrayList<String> parameters = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
    Scanner lineReader;
    String line = "";
    String[] lineSplit;
    int[] scores = new int[2];
    int testNum = 0;

    //collect and sort all the parameters and answers
    while (input.hasNextLine()) {
      line = input.nextLine();
      //splits around the = to separate parameters and answer
      lineSplit = line.split("=");
      parameters.add(lineSplit[0]);
      try {
        answers.add(lineSplit[1].trim());
      } catch (NumberFormatException e) {
        System.out.println("No Answer Present!");
      }
    }

    //pass the collected data to be graded
    scores = testCase(parameters,answers);
    //print the results
    System.out.println("FINAL SCORE: " + scores[0] + "/"
                      + scores[1]);
  }

  /** Grades test case and returns score.
  *   @param parameters ArrayList<String> containing the parameters for
  *                     HotPotato.
  *   @param answers ArrayList<String> containing the answers.
  *   @return int score of test case.
  */
  private int[] testCase(ArrayList<String> parameters, ArrayList<String> answers){
    int num = 0,
        index = 0,
        score = 0,
        possibleScore = 0;
    int[] scores = new int[2];
    String answer,
           correct,
           calculatedAnswer;
    String[] stringParam;
    Queue<String> names = new Queue<String>();

    //make header for scoring
    printLines();
    System.out.printf("%14s|%-11s|%8s|\n", "Calc Answer", "True Answer",
                      "Correct?");
    printLines();

    //for every set of parameters given
    for (String parameter: parameters) {
      stringParam = parameter.split(" ");
      names = new Queue<String>();


      //separate the names from the number and add to queue
      for (int i = 0; i < stringParam.length; i++) {
        stringParam[i] = stringParam[i].trim();
        if (Character.isDigit(stringParam[i].charAt(0)))
          num = Integer.parseInt(stringParam[i]);
        else
          names.enqueue(stringParam[i]);
      }

      //calculate answer using student's code and grade
      try {
        possibleScore++;
        //calculate using student's code
        calculatedAnswer = hotPotato.play(names, num);
        answer = answers.get(index);
        //if correct, add score and set correct accordingly
        if (answer.equals(calculatedAnswer)) {
          correct = "Correct!";
          score++;
        }
        else
          correct = "WRONG";
        //set up printing
        System.out.printf("%2d)%11s|%11s|%8s|\n",index + 1,calculatedAnswer,
                          answer, correct);
        printLines();
      } catch (Exception e) {
        System.out.println("Unexpected Exception\n" + e.getMessage());
      }
      index++;
    }
    scores[0] = score;
    scores[1] = possibleScore;
    return scores;
  }

  public static void main(String[] args) {
    String golden_file;
    if (args.length == 0 || args[0].equals("test")) {
      golden_file = "data/test.txt";
    } else if (args[0].equals("eval")) {
      golden_file = "data/eval.txt";
    } else {
      System.out.println("Unknown option: " + args[0]);
      System.exit(1);
      return;
    }
    new GenerateScore(golden_file);
  }
}
