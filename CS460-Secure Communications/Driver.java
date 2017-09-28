
public class Driver
{
    public static void main(String[] args)
    {
        String cipherText = "toydovhqblktqhtggecgacwoadlapqacpovafsmoftosnaw";
        
        int i = 11;
        //for(int i = 2; i <= 15; i++)
        //{
            System.out.println("Key length " + i);
            System.out.println();
            for(int k = 0; k < i; k++)
            {
                System.out.println("Sequence " + (k+1) + ": ");
                
                 for(int l = 0; l < 26; l++)
                 {
                     System.out.print("Key: " + l + "   ");
                     for(int j = k; j < cipherText.length();j+=i)
                     {
                     char ch = cipherText.charAt(j);
                     char cha = (char)((int)ch+l); 
                     if(cha <= 'z')
                        System.out.print(cha);
                     else
                        System.out.print((char)((int)ch+l -26));
                    }
                    System.out.println();
                 }
                System.out.println();
            } 
            System.out.println();
            System.out.println();
        //}
    }
}
