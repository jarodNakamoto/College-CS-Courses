/**
 * COPYRIGHT 2014: smanna@csupomona.edu
 * CS 240
 *
 * !!!WARNING!!! Students are not allowed to change this code.
 * Changes made in this code will not be accepted.
 * */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class GenerateScore {

  private int totalScore;
  private Calculator calc;

  //Constructor
  public GenerateScore(String fileName) {
    try {
      File file = new File(fileName);
      Scanner input = new Scanner(file);
      calc = new Calculator();
      totalScore = 0;
      reader(input);
      System.out.println("Total Score: "+ totalScore);
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

  /** Takes a Scanner input and reads in equations and answers and sets up the
  *   testcases.
  *   @param input Scanner that uses the input equation file
  */
  public void reader(Scanner input) {
    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<Double> answers = new ArrayList<Double>();
    Scanner lineReader;
    String line = "";
    String[] equation;
    int[] scores = new int[2];
    int testNum = 0;
    //for <n test cases
    while (input.hasNextLine()) {
      line = input.nextLine();
      lineReader = new Scanner(line);
      //If it is a new testcase
      if (lineReader.next().equals("TESTCASE")) {
        //if this is not the first testcase, determine the prior's score
        if (testNum != 0) {
          scores = testCase(questions,answers);
          System.out.println("TEST: " + testNum + " SCORE: " + scores[0] + "/"
                             +scores[1]);
          totalScore += scores[0];
          questions = new ArrayList<String>();
          answers = new ArrayList<Double>();
        }
        testNum = lineReader.nextInt();
        lineReader.close();
        scores = new int[2];
      }
      else {
        //splits around the = to separate question and answer
        equation = line.split("=");
        questions.add(equation[0]);
        try {
          answers.add(Double.parseDouble(equation[1]));
        } catch (NumberFormatException e) {
          System.out.println("No Answer Present!");
        }
      }
    }
    //This is for the last testcase
    scores = testCase(questions,answers);
    totalScore += scores[0];
    System.out.println("TEST: " + testNum + " SCORE: " + scores[0] + "/"
                       + scores[1]);
  }

  /** Grades test case and returns score.
  *   @param questions ArrayList<String> containing the questions.
  *   @param answers ArrayList<Double> containing the answers.
  *   @return int score of test case.
  */
  public int[] testCase(ArrayList<String> questions, ArrayList<Double> answers){
    int index = 0,
        score = 0,
        possibleScore = 0;
    int[] scores = new int[2];
    Double answer,
           calculatedAnswer;
    String correct = "";
    //make header for scoring
    printLines();
    System.out.printf("%14s|%-11s|%8s|\n", "Calc Answer", "True Answer",
                      "Correct?");
    printLines();
    //for all of the questions in the testcase
    for (String question: questions) {
      try {
        possibleScore++;
        calculatedAnswer = calc.solve(question);
        answer = answers.get(index);
        //if correct, add score and set correct accordingly
        if (answer.equals(calculatedAnswer)) {
          correct = "Correct!";
          score++;
        }
        else
          correct = "WRONG";
        //set up printing
        System.out.printf("%2d)%11.3f|%11.3f|%8s|\n",index + 1,calculatedAnswer,
                          answer, correct);
        printLines();
        index++;
      } catch (Exception e) {
        //if score for this testcase is + subtract a point
        if (score > 0)
          score--;
        System.out.println("Unexpected Exception\n" + e.getMessage());
      }
    }
    scores[0] += score;
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
