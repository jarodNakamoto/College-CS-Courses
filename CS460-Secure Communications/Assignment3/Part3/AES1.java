import java.security.*;
import javax.crypto.*;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

// encrypt and decrypt using the AES private key algorithm
public class AES1 
{
   public static void main (String[] args) throws Exception 
   {
      File input = new File("plaintext.txt"); 
      File cipherOutput = new File("ciphertext.txt");
      File decryptOutput = new File("decryptedtext.txt");
       
      //get the contents of the file as a string
      String str = "";
      try
      {
         Scanner sc = new Scanner(input,"UTF8");
         while(sc.hasNextLine())
         {
             str += sc.nextLine();
         }
      }
      catch(Exception e)
      {
          System.out.println("plaintext.txt Not Found Error");
      } 
      
      // check str length
      if (str.length() == 0) 
      { 
         System.err.println("Usage: java AES text");
         System.exit(1);
      }
      //convert to plaintext
      byte[] plainText = str.getBytes("UTF8");
      
      // get a AES private key
      System.out.println( "\nStart generating AES key" );
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(128);
      Key key = keyGen.generateKey();
      System.out.println( "Finish generating AES key" );

      // get a AES cipher object and print the provider
      Cipher cipher = Cipher.getInstance("AES");

      // encrypt using the key and the plaintext
      System.out.println( "\nStart encryption" );
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] cipherText = cipher.doFinal(plainText);
      System.out.println( "Finish encryption" );
      //writes to cipher output file
      try
      {
         PrintWriter writer = new PrintWriter(cipherOutput, "UTF-8");
         //writer.println( new String(cipherText, "UTF8") );
         writer.println(cipherText);
         writer.close();
         System.out.println();
      }
      catch(Exception e)
      {
          System.out.println("Cipher Output File Error");
      } 
      
      
      
      
      System.out.println( new String(cipherText, "UTF8") );
      
      //get cipher text
      /*
      str = "";
      try
      {
         Scanner sc = new Scanner(cipherOutput,"UTF8");
         while(sc.hasNextLine())
         {
             str += sc.nextLine();
         }
      }
      catch(Exception e)
      {
          System.out.println("ciphertext.txt Not Found Error");
      } 
      System.out.println(str);
      */
      // decrypt the ciphertext using the same key
      System.out.println( "\nStart decryption" );
      //cipherText = str.getBytes("UTF8");
      cipherText = Files.readAllBytes(cipherOutput.toPath());
      //cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      
      cipher.init(Cipher.DECRYPT_MODE, key);

      byte[] newPlainText = cipher.doFinal(cipherText);
      System.out.println( "Finish decryption" );
      //writes to decrypted output file
      try
      {
          
          
          
          
         System.out.println( new String(newPlainText, "UTF8") );
         
         
         PrintWriter writer = new PrintWriter(decryptOutput, "UTF-8");
         writer.println( new String(newPlainText, "UTF8") );
         writer.close();
         System.out.println();
      }
      catch(Exception e)
      {
          System.out.println("Plaintext Output File Error");
      } 
    }
}
