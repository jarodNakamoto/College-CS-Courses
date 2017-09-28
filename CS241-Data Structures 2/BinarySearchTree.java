// Jarod Nakamoto
// CS 241

import java.util.*;

public class BinarySearchTree
{
   private Node root;
   
   public BinarySearchTree()
   {
       root = null;
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
   
    private void delete(int value)
   {
       if(root == null)
       {
          System.out.println("Root is null cannot delete anything");
       }
       else
       {
           Node temp = root;
           Node next = root;
           while(next != null)
           {   
               if(temp.getData() == value)
               {
                   // value is equal to node data
                   Node replacement = temp.getRight();
                   if(replacement == null)
                   {
                       // nothing in right subtree 
                       // try to replace spot with left subtree
                       replacement = temp.getLeft();
                       if(replacement != null)
                       {
                           temp.setData(replacement.getData());
                           temp.setLeft(replacement.getLeft());
                           temp.setRight(replacement.getRight());
                       }
                   }
                   else
                   {
                   }
                   return;
               }
               else if(next.getData() < value)
               {
                   //value is greater than node data
                   //look in right subtree
                   next = temp.getRight();
               }
               else
               {
                   //value is less than node data
                   // look in left subtree
                   next = temp.getLeft();
               }
               temp = next;
           }
           
           // place we need to go DNE
           System.out.println("Value not in tree");
       }
   }
   
   private void Print()
   {
       if(root == null)
       {
            System.out.println("Root is null");
       }
       else
       {
            preOrder(root);
            inOrder(root);
            postOrder(root);
       }
   }
   
   public static void main(String[] args)
   {
       
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
