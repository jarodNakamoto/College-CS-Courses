/**
* COPYRIGHT: smanna@cpp.edu
* CS 240 Spring 2016
* !!!!WARNING!!!! You are not allowed to change this code. 
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenerateScore {
  private static int total_score;
  private static final boolean DEBUG = false;

  public static void main(String[] args) throws FileNotFoundException {
	  String golden_file;
//    if (args.length == 0) {
//      System.out.println("USAGE: Filename of test case");
//    }
	  if (args.length == 0 || args[0].equals("test")) {
			golden_file = "data/test.txt";
		} else if (args[0].equals("eval")) {
			golden_file = "data/eval.txt";
		} else {
			System.out.println("Unknown option: " + args[0]);
			System.exit(1);
			return;
		}
//	  else {
      LinkedList list = new LinkedList();
      File testcase = new File(golden_file);
      Scanner input = new Scanner(testcase);
      Scanner lineReader;
      String calcResult = "";
      String result = "";
      String line = "";
      String cmd = "";
      Integer calcAnswer = 0;
      Integer answer = 0;
      Integer score = 0;
      Integer possibleScore = 0;
      Integer testNumber = 0;
      int size = 0;
      int askedIndex = 0;
      //Go through file line-by-line
      while (input.hasNextLine()) {
        line = input.nextLine();
        lineReader = new Scanner(line);
        cmd = lineReader.next();
        //if the cmd is TESTCASE
        if (cmd.equals("TESTCASE")) {
          if (testNumber != 0) {
            System.out.println("TEST: " +testNumber+ " SCORE: "+score+"/"+
                               possibleScore);
            total_score += score;
          }
          list = new LinkedList();
          testNumber = lineReader.nextInt();
          lineReader.close();
          possibleScore = 0;
          size = 0;
          score = 0;
        }
        //if it is another cmd
        else {
            try {
              switch (cmd) {
              case "append":
              list.append(lineReader.nextInt());
              size++;
              break;
              case "prepend":
              list.prepend(lineReader.nextInt());
              size++;
              break;
              case "insert":
              askedIndex = lineReader.nextInt();
              if (askedIndex > size+1) {
                possibleScore++;
              }
              list.insert(askedIndex,lineReader.nextInt());
              size++;
              break;
              case "deleteElemAt":
              askedIndex = lineReader.nextInt();
              if (askedIndex > size) {
                possibleScore++;
              }
              list.deleteElemAt(askedIndex);
              size--;
              break;
              case "findElem":
              possibleScore++;
              calcAnswer = list.findElem(lineReader.nextInt());
              //if there is an answer collect it otherwise set to -1 (false)
              if (lineReader.hasNext()) {
                lineReader.next();
                answer = lineReader.nextInt();}
              else {
                answer = -1;
              }
              if (DEBUG)
                System.out.println("Does (" +calcAnswer+") = ("+answer+")");
              if (answer.equals(calcAnswer)) { //score the answer
                if (DEBUG)
                  System.out.println("Add Score");
                score++;
              }
              else {
                if (DEBUG)
                  System.out.println("Subtract Score");
                score--;
              }
              break;
              case "readElemAt":
              possibleScore++;
              calcAnswer = list.readElemAt(lineReader.nextInt());
              //if answer is provided save it; otherwise it isn't found
              if (lineReader.hasNext()) {
                lineReader.next();
                answer = lineReader.nextInt();
              }
              else {
                answer = -1;
              }
              if (DEBUG)
                System.out.println("Does (" +calcAnswer+") = ("+answer+")");
              if (answer.equals(calcAnswer)) { //score the answer
                if (DEBUG)
                  System.out.println("Add Score");
                score++;
              }
              else {
                if (DEBUG)
                  System.out.println("Subtract Score");
                score--;
              }
              break;
              case "print":
              possibleScore++;
              //remove all non numbers and make formatting the same
              result = list.toString().replaceAll("[^0-9]+", "");
              lineReader.next();
              //depending on the format (print =) could have nothing after the =
              //if that is the case the String should be empty
              try {
                calcResult = lineReader.nextLine().replaceAll("[^0-9]+", "");
              } catch (java.util.NoSuchElementException e) {
                calcResult = "";
              }
              if (DEBUG)
                System.out.println("Does (" +result+") = ("+calcResult+")");
              if (result.equals(calcResult)) { //score the answer
                if (DEBUG)
                  System.out.println("Add Score");
                score++;
              }
              else {
                if (DEBUG)
                  System.out.println("Subtract Score");
                score--;
              }
              break;
            }
          } catch (IndexOutOfBoundsException a) {
            if (askedIndex > size-1) {
              score++;
            }
            else {
              score--;
            }
          } catch (Exception e) {
            //TODO: subtract correct amount of points for throwing exception
            System.out.println("Unexpected Exception Thrown!\n"+e.getMessage());
            score = score - 1;
          }
        }
      }
      System.out.println("TEST: " +testNumber+ " SCORE: "+score+"/"+
                         possibleScore);
      total_score += score;
      System.out.println("Total Score: " + total_score);
      input.close();
//    }
  }
}
