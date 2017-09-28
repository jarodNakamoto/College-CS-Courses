/**
* COPYRIGHT: smanna@cpp.edu
* CS 240 Spring 2016
* !!!!WARNING!!!! You are not allowed to change this code. 
*/

public class Stack<E> {

  private LinkedList<E> stack;
  private int size; //amount of objects in stack

  public Stack() {
    stack = new LinkedList<E>();
    size = 0;
  }

  /** Looks at the int on top of the stack without removing it.
  *   @return int int stored on top of the stack.
  */
  public E peek() {
    if (empty())
      throw new java.util.EmptyStackException();
    else
      return stack.readElemAt(0);
  }

  /** Removes the int at the top of the stack and returns it.
  *   @return int int at the top of the stack.
  */
  public E pop() {
    E item;
    if (empty())
      throw new java.util.EmptyStackException();
    else {
       item = stack.readElemAt(0);
       stack.deleteElemAt(0);
       size--;
       return item;
    }
  }

  /** Pushes the int passed to this method onto the top of the stack.
  *   @param int int to be put on the stack
  */
  public void push(E item) {
    stack.prepend(item);
    size++;
  }

  /** Tests if the stack is empty.
  *   @return boolean boolean telling if the stack is empty.
  */
  public boolean empty() {
    if (size == 0)
      return true;
    else
      return false;
  }

  /** Returns a String representation of the stack.
  *   @return String String representing the stack.
  */
  public String toString() {
    String output = "";
    output = stack.toString();
    return output;
  }

  public int size() {
    return size;
  }
}
