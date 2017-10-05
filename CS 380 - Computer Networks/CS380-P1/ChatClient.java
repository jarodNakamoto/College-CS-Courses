
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public final class ChatClient {

	public static volatile Socket socket;
	public final static int THREAD_SLEEP = 1000;
	
    public static void main(String[] args) throws Exception {
		
		Runnable MessageHandler = () ->  {
			Random random = new Random();
			try
			{
				//recieves messages
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				//System.out.println("After Initializations");
				
				//receive server message
				String str;
				while(true)
				{
					str = br.readLine();
					//stops thread if receives null from server
					if(str.equals(null))
					{
						System.out.println("Disconnected from server");
						return;
					}
					//prints server message to console
					System.out.println(str);
					Thread.sleep(random.nextInt(THREAD_SLEEP));
				}
			}
			catch(Exception e)
			{
				System.out.println("Receiver Thread: " + e.toString());
				try{
					socket.close();
				}
				catch(Exception er)
				{
					System.out.println("Socket failed to close");
				}
				//System.out.println("Socket: " + socket.toString()+socket.isConnected()+socket.isClosed());
				return;
			}	
		};
		
		Runnable MessageSender = () ->  {
			Random random = new Random();
			try
			{
				//sends stuff to server
				OutputStream os = socket.getOutputStream();
				PrintStream out = new PrintStream(os, true, "UTF-8");
				String address = socket.getInetAddress().getHostAddress();
				
				//User input messages
				Scanner sc = new Scanner(System.in);
				String input = "";
				
				while(!socket.isClosed())
				{
					//get user input
					//System.out.println("Sender Thread still running");
					input = sc.nextLine();
					//stop if they type exit
					if(input.equalsIgnoreCase("exit"))
					{
						socket.close();
						return;
					}
					
					//send to server
					out.println(input);
					Thread.sleep(random.nextInt(THREAD_SLEEP));
				}
			}
			catch(Exception e)
			{
				System.out.println("Sender Thread: " + e.toString());
				return;
			}	
		};
		
		System.out.println("Your first message should be your username.");

		try 
		{
			//connect to the server
			socket = new Socket("18.221.102.182", 38001);
			Thread messageReceiver = new Thread(MessageHandler);
			Thread messageSender = new Thread(MessageSender);
			
			messageSender.start();
			messageReceiver.start();
		}
		catch(Exception e)
		{
			System.out.println("Main Thread: " + e.toString());
		}
    }
}