/**
* COPYRIGHT: smanna@cpp.edu
* CS 240 Spring 2016
* !!!!WARNING!!!! Please make no changes to this code. 
*/
public class LinkedList<E> {

  Node cursor;
  Node head;
  int size;

  public LinkedList() {
    size = 0;
  }
  //adds to end of list
  public void append(E elem) {
    if (size == 0) {
      head = new Node(elem);
    }
    else {
      fastForward(size-1).next = new Node(elem);
    }
    size++;
  }
  //adds to beginning of list
  public void prepend(E elem) {
    Node temp;
    if (size == 0) {
      head = new Node(elem);
    }
    else {
      temp = head;
      head = new Node(elem, temp);
    }
    size++;
  }
  // Post insert, elem should be at given index. Index is 0-
  // based. That means, insert(0, 5) is equivalent to prepend(5)
  public void insert(int index, E elem) {
    Node temp;
    if (index == 0) {
      prepend(elem);
    }
    else if (index > size) {
      throw new IndexOutOfBoundsException();
    }
    else {
      cursor = fastForward(index-1);
      temp = cursor.next;
      cursor.next = new Node(elem,temp);
      size++;
    }
  }
  //deletes element at index i(0-based)
  public void deleteElemAt(int i) {
    Node temp;
    if (i == 0) {
      head = head.next;
    }
    else if (i > size-1) {
      throw new IndexOutOfBoundsException();
    }
    else {
      cursor = fastForward(i-1);
      //for any index that is not the tail
      if (i < size) {
        cursor.next = cursor.next.next;
      }
      //for the tail
      else {
        cursor.next = null;
      }
    }
    size--;
  }
  //returns the index of the element found; -1 if not found
  public int findElem(E elem) {
    int output = -1;
    if (size == 0) {
      return output;
    }
    else {
      cursor = head;
      for (int index = 0; index < size; index++) {
        //TODO: if this is eventually made to hold generics change to .equals()
        if (elem == cursor.data) {
          output = index;
          return output;
        }
        cursor = cursor.next;
      }
    }
    return output;
  }
  //returns elements at index i; -1 if not found
  public E readElemAt(int i) {
    return fastForward(i).data;
  }
  //returns space separated list of elements like "1 3 5 2". For empty list it
  //should return ""
  public String toString() {
    String output = "";
    if (size == 1) {
      output += head.data;
    }
    else if (size > 1) {
      cursor = head;
      while (cursor.next != null) {
        output += cursor.data + " ";
        cursor = cursor.next;
      }
      output += cursor.data;
    }
    return output;
  }
  //Sends back the Node at the given index
  public Node fastForward(int index) {
    cursor = head;
    if (index == 0) {
      return cursor;
    }
    else
      for (int i = 0; i < index; i++) {
        cursor = cursor.next;
      }
    return cursor;
  }
  public int size() {
    return size;
  }
  public class Node {
    private E data;
    private Node next;

    //for elements that are at the tail
    public Node(E data) {
      this.data=data;
      next=null;
    }
    //for everything else
    public Node(E data, Node next) {
      this.data=data;
      this.next=next;
    }
    public E getData() {
      return data;
    }
  }
}
