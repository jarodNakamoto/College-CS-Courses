
/**
 * Write a description of class NakamotoJp1 here.
 * 
 * @author (Jarod Nakamoto) 
 * @version (2)
 */
import java.util.*;
public class NakamotoJp1
{
   private Node root;
   
   public NakamotoJp1()
   {
       root = null;
   }
   
   public static void main(String[] args)
   {
       //takes in input and does commands if valid
       Scanner sc = new Scanner(System.in);
       
       String in, command, next;
       // assume there is no value
       int val = 0;
       NakamotoJp1 tree = new NakamotoJp1();
       do
       {
           System.out.print("Input: ");
           in = sc.nextLine();
           String[] input = in.split(" ");
           command = input[0].substring(0,1);
           command = command.toUpperCase();
           
           if(input.length > 1)
           {
              // more than just a command
              // value was seperated by space in input[1]
              // convert from string to an integer
              val = Integer.parseInt(input[1]);
           }
           else if(input[0].length() > 1)
           {
               //no spaces were in the input
               // so it value and command weren't seperated
               //there is more than just a command if input[0].length() > 1
               //assume the input is valid and was a number
               // convert from string to an integer
               val = Integer.parseInt(input[0].substring(1));
           }
           
           switch(command)
           {
                default: System.out.println("Invalid input");
                         break;
                case "A": tree.add(val);
                         break;
                case "D": tree.delete(tree.root, val);
                         break;
                case "P": tree.print();
                         break;
                case "Q": System.out.println();
                         break;
           }
       }
       while(!command.equals("Q"));
       
       System.out.println("Program is over");
   }
   
   private void add(int value)
   {
       if(root == null)
       {
          root = new Node(value, null, null);
       }
       else
       {
           Node temp = root;
           Node next = root;
           while(next != null)
           {
               if(temp.getData() < value)
               {
                   //value is greater than node data
                   next = temp.getRight();
                   if(next == null)
                   {
                      temp.setRight(new Node(value, null, null));
                   }
               }
               else if(temp.getData() == value)
               {
                   // value is equal to node data
                   System.out.println("Value already in tree");
                   return;
               }
               else
               {
                   //value is less than node data
                   next = temp.getLeft();
                   if(next == null)
                   {
                      temp.setLeft(new Node(value, null, null));
                   }
               }
               temp = next;
           }
       }
   }
   
   private Node findLargest(Node temp)
   {
       if(temp.getRight() != null)
            temp = findLargest(temp.getRight());
       else 
            temp = null;
       return temp;
   }
   
   private Node removeLargest(Node temp)
   {
       if(temp.getRight() != null)
       {
            Node right = temp.getRight();
            right = removeLargest(right);
            temp.setRight(right);
       }
       return temp;
   }
   
   private Node removeFromRoot(Node temp)
   {
       if(temp.getRight() != null && temp.getLeft() != null)
       {
           Node left = temp.getLeft();
           //find largest
           Node largest = findLargest(temp);
           temp.setData(largest.getData());
           temp.setLeft(removeLargest(left));
       }
       //root has one child or less
       else if(temp.getRight() != null)
       {
           temp = temp.getRight();
       }
       else
       {
           temp = temp.getLeft(); // possibly null
       }
       // if was leaf is now null
       return temp;
   }
   
    private Node delete(Node temp, int value)
   {
       if(root == null)
       {
          System.out.println("Root is null cannot delete anything");
       }
       else if(temp == null)
       {
           System.out.println("Value not found");
       }
       else if(value == root.getData())
       {
           root = removeFromRoot(temp);
       }
       else
       {
            if(temp.getData() == value)
            {
                temp = removeFromRoot(temp);
            }
            else if(temp.getData() < value)
            {
                //value is greater than node data
                //look in right subtree
                temp.setRight(delete(temp.getRight(), value));
            }
            else
            {
                //value is less than node data
                // look in left subtree
                temp.setLeft(delete(temp.getLeft(), value));
            }
       }
       return temp;
   }
   
   
   private void print()
   {
       if(root == null)
       {
            // checks if tree is empty
            System.out.println("Root is null");
       }
       else
       {
            // prints tree in preorder, inorder, postorder
            System.out.println("Preorder: ");
            preOrder(root);
            System.out.println();
            System.out.println("Inorder: ");
            inOrder(root);
            System.out.println();
            System.out.println("Postorder: ");
            postOrder(root);
            System.out.println();
       }
   }
  
   private void preOrder(Node n)
   {
       if(n != null)
       {
           System.out.print(n.getData() + " ");
           preOrder(n.getLeft());
           preOrder(n.getRight());
       }
       // else do nothing
   }
   private void postOrder(Node n)
   {
       if(n != null)
       {
           postOrder(n.getLeft());
           postOrder(n.getRight());
           System.out.print(n.getData() + " ");
       }
       // else do nothing
   }
   private void inOrder(Node n)
   {
       if(n != null)
       {
           inOrder(n.getLeft());
           System.out.print(n.getData() + " ");
           inOrder(n.getRight());
       }
       // else do nothing
   }
   
   
   private class Node
   {
       private int data;
       private Node left, right;

       public Node(int data, Node left, Node right)
       {
         this.data = data;
         this.left = left;
         this.right = right;
       }

       public int getData()
       {
           return data;
       }
       
       public Node getLeft()
       {
           return left;
       }
       
       public Node getRight()
       {
           return right;
       }
       
       public int setData(int newData)
       {
           int old = data;
           data = newData;
           return old;
       }
       
       public Node setLeft(Node newLeft)
       {
           Node old = left;
           left = newLeft;
           return old;
       }
       
       public Node setRight(Node newRight)
       {
           Node old = right;
           right = newRight;
           return old;
       }
   }
   
}
