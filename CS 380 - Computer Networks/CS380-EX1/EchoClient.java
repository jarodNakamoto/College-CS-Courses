
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			//recieves messages
            BufferedReader br = new BufferedReader(isr);
			
			//my changes start here
			//sends stuff to server
			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os, true, "UTF-8");
			String address = socket.getInetAddress().getHostAddress();
			
			//User input messages
			Scanner sc = new Scanner(System.in);
			String input = "";
			
			do
			{	
				//client
				System.out.print("Client> ");
				//get user input
				input = sc.nextLine();
				
				if(input.equalsIgnoreCase("exit"))
				{
					out.println("exit");
					socket.close();
					break;
				}
				
				//send to server
				out.println(input);
				
				//System.out.println("B4 Receiving from Server");
				//receive server message
				System.out.println("Server> " + br.readLine());
			}while(!input.equalsIgnoreCase("exit"));
        }
    }
}