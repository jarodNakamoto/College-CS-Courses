// Jarod Nakamoto
// CS240, 01
// PA 1
// 4/14/16 


 /**
 * COPYRIGHT: smanna@cpp.edu
 * CS 240 Spring 2016
 * Programming Assignment 1
 *
 * STUDENTS SHOULD COMPLETE THIS CODE.
 * You will upload this code to Blackboard.
 *
 * Feel free to include your own utility private fields and methods.
 * But please make sure you do not change the signature
 * of the public methods provided. If you do so, your code
 * cannot be run automatically, and you will not be graded.
 *
 * If you do not write enough comments, at least two points
 * will be deducted from your assignment. Also make sure you
 * follow the coding conventions.
 *
 * Make sure you check all the boundary conditions while implementing
 * your code here.
 *
 **/
public class LinkedList {

//TODO(Student): put your private fields here
    private Node head;
    private int numNodes;

  // constructor
  public LinkedList() {
    // TODO (Student): do the necessary changes here
      head = null;
      numNodes = 0;
  }

  //adds element to end of the list
  public void append(int elem) {
    // TODO (student):
       if (head == null)
       {
           prepend(elem);
       }
       else
       {
          Node curr = head;
          while (curr.next != null)
          {
             curr = curr.next;
          } 
          curr.next = new Node(elem);
          numNodes++;
       }

  }

  //adds element to the beginning of the list
  public void prepend(int elem) {
    // TODO (student)
         head = new Node(elem, head);
         numNodes++;
  }

  // Post insert, element should be at a given index. Index is 0-
  // based. That means, insert(0, 5) is equivalent to prepend(5)
  public void insert(int index, int elem) {
    // TODO (student)
      if(index == 0 || numNodes == 0)
      {
         prepend(elem);
      }
      else
      {
         Node curr = head;
         for(int i = 1; i < index; i++)
         {
            curr = curr.next;
         }
         Node n = new Node(elem, curr.next);
         curr.next = n;
         numNodes++;
      }
  }

  //deletes element at index i(0-based)
  public void deleteElemAt(int i) {
    // TODO (student)
      if(i == 0)
      {
         head = head.next;
      }
      else if(numNodes >= i)
      {
         Node curr = head;
         for(int j = 0; j < i -1; j++)
            curr = curr.next;
         curr.next = curr.next.next;
      }
  }

  //returns the index of the element found; return -1 if not found
  public int findElem(int elem) {
    // TODO (student)
      int i = 0;
      Node curr = head;
      while(curr != null)
      {
          if(curr.getData() == elem)
             return i;
          i++;
          curr = curr.next; 
      } 
      return -1; // make sure you change this
  }

  //returns element at index i; return -1 if not found
  public int readElemAt(int i) {
    // TODO (student)
      if(numNodes < i)
        return -1;
      
      Node curr = head;
      for(int j = 0; j < i; j++)
      {
         curr = curr.next;
      }
      return curr.getData();
  }

  //returns space separated list of elements such as "1 3 5 2".
  //For empty list it should return ""
  public String toString() {
    // TODO (student)
      String str = "";
      Node curr = head;
      while(curr != null)
      {
         str += curr.getData() + " ";
         curr = curr.next;
      }
      return str; // make sure you change this
  }


  // Defining node here
  // begin: node class
  //  WARNING: DO NOT MAKE ANY CHANGES TO THE NODE CLASS
  public class Node {
    private int data;
    private Node next;


    //for elements that are at the tail
    public Node(int data) {
      this.data=data;
      next=null;
    }

    //for everything else
    public Node(int data, Node next) {
      this.data=data;
      this.next=next;
    }

    public int getData() {
      return data;
    }
  } // end: node class
}
