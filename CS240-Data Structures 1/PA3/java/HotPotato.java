// Jarod Nakamoto
// CS 240
// 5/9/2016



/**
 * Copyright 2016: smanna@cpp.edu
 * CS 240 Spring 2016
 *
 * STUDENTS SHOULD COMPLETE THIS CODE.
 * You will upload this code to Blackboard.
 *
 * If you do not write enough comments, at least two points
 * will be deducted from your assignment. Also make sure you
 * follow the coding conventions.
 *
 * The main aim is to simulate a HotPotato game using
 * Queue. Please read through the details from the instructions.
 *
 */

public class HotPotato {

  // TODO (Student): fields

  // Constructor
  public HotPotato() {
  // TODO (Student)
  }

  /** Simulating HotPotato game.
   *  @param names Takes names of players
   *  @param num   number of times the potato is passed
   *  @return the name of the winner
   */
  public String play(Queue<String> names, int num) {
      // if not playing because no is in the queue
      if(names.size() == 0)
         return null; // make sure to change this
      if(names.size() == 1)
         return names.peek();
         
      for(int i = 0; i < num; i++)
         names.enqueue(names.dequeue()); 
         
      names.dequeue();
      
      return play(names, num);
  }

}
