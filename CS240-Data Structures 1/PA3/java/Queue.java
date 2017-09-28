/**
* COPYRIGHT: smanna@cpp.edu
* CS 240 Spring 2016
* !!!!WARNING!!!! Please make no changes to this code. 
*/
public class Queue<E> {

  private LinkedList<E> queue;
  private int size;

  public Queue() {
    queue = new LinkedList<>();
    size = 0;
  }

  /** Puts the parameter into the back of the queue.
  *   @param element The element to be inserted into the queue.
  */
  public void enqueue(E element) {
    queue.append(element);
    size++;
  }

  /** Returns the first element of queue without removing it; if not found return
  *   null.
  *   @return elem at head of the queue.
  */

  public E peek() {
    if (size == 0) {
      return null;
    }
    return queue.readElemAt(0);
  }

  /** Takes the first element out of the queue and returns it.
  *   @return elem at head of the queue.
  */
  public E dequeue() {
    if (size == 0) {
      return null;
    }
    E output = queue.readElemAt(0);
    queue.deleteElemAt(0);
    size--;
    return output;
  }

  /** Returns a string representation of the queue.
  *   @return The string representation of the queue.
  */
  public String toString() {
    return queue.toString();
  }

  /** Returns the size of the queue.
  *   @return The size of the queue.
  */
  public int size() {
    return size;
  }

}
