// Jarod Nakamoto
// CS 240 - 1
// 5/3/2016



 /**
 * COPYRIGHT: smanna@cpp.edu
 * CS 240 Spring 2016
 * Programming Assignment 2
 *
 * STUDENTS SHOULD COMPLETE THIS CODE.
 * You will upload this code to Blackboard.
 *
 * If you do not write enough comments, at least two points
 * will be deducted from your assignment. Also make sure you
 * follow the coding conventions.
 *
 *  Students need to implement a Calculator using the framework provided. Feel
 *  free to add your own fields and methods besides ones already provided. Please do not make
 *  any changes to the provided method signatures. If you do so, your code
 * cannot be run automatically, and you will not be graded.
 *
 *  ** YOUR RESULTS SHOULD BE ROUNDED TO THREE DECIMAL PLACES. IF YOU FAIL TO DO
 *  SO, YOU WILL BE PENALIZED BY 1 POINT.
 *  ** YOU SHOULD PROPOERLY COMMENT YOUR CODE, OTHERWISE YOU WILL BE PENALIZED
 *  BY 5 POINTS.
 *
 **/

import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Calculator {
// TODO (Student): your fields here

  Stack <Double> numbers;
  Stack <Character> operator;

  //Constructor
  public Calculator() {
    // TODO (Student)
  }

  //Constructor with manual debug setting
  public Calculator(boolean DEBUG) {
    // TODO (Student): Optional
  }

  /** Returns true if the new character has precedence to be pushed on to the
  *   stack. Returns false if not.
  *   @param previous operator already on the stack
  *   @param newChar operator being added to the stack
  *   @return boolean of whether the operator has precedence
  */
  private boolean hasPrecedence(Character previous, Character newChar) {
    //if the previous has top precedence then the new doesn't
    if (previous == '*' || previous == '/')
      return false;
    //subtract previous first then new
    else if (previous == '-' && newChar == '-')
      return false;
    //subtract previous then add new
    else if (previous == '-' && newChar == '+')
      return false;
    else {
      return true;
    }
  }

  /** Pops two operands and one operator and performs the operation.
  *   @param operands stack of the operands
  *   @param operators stack of the operators
  *   @return answer to the operation
  */
  private double popStackAndSolve(Stack<Double> operands,Stack<Character>
                                  operators) {
    double operand1,operand2,answer;
    char operator;
    operand2 = operands.pop(); //pop the second first because stack ordering
    operand1 = operands.pop();
    operator = operators.pop();
    answer = calc(operand1,operand2,operator);
    return answer;
  }

  /** @param operand1 the first operand in the equation
  *   @param operand2 the second operand in the equation
  *   @param operator the operator to perform on the two operands
  *   @return the answer
  */
  public double calc(double operand1, double operand2, char operator) {
    // checks the operator    

    switch(operator){
       case '*': return operand1 * operand2;
       case '/': return operand1/operand2;
       case '+': return operand1 + operand2;
       case '-': return operand1 - operand2;
       default: break;
    }
    // not an operator
    return 0;
  }

  /** Solves an infix equation and returns the answer as a Double. Limits to 3
  *   decimal places.
  *   @param equation infix String equation to be solved.
  *   @return double answer to equation
  */
  public Double solve(String equation) {
    // will become the answer
    double answer = 0.0;

     //do Math

     for(int i = 0; i < equation.length(); i++)
     {
        // find operator
   
     }

    //return formatted answer
    DecimalFormat format = new DecimalFormat("#.###");
    return Double.parseDouble(formatter.format(answer));
  }
}
