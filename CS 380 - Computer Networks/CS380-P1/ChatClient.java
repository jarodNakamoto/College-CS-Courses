
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
				
				//receive server message
				System.out.println(""+br.readLine());
				
				Thread.sleep(random.nextInt(THREAD_SLEEP));
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
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
				
				//client
				//get user input
				input = sc.nextLine();
				//stop if they type exit
				if(input.equalsIgnoreCase("exit"))
				{
					out.println("exit");
					socket.close();
					Thread.sleep(100 * THREAD_SLEEP);
				}
				
				//send to server
				out.println(input);
				Thread.sleep(random.nextInt(THREAD_SLEEP));
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
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
			Random random = new Random();
				
			while(true)
			{
				Thread.sleep(random.nextInt(THREAD_SLEEP));
				//System.out.println("test");
				messageSender.run();
				messageReceiver.run();
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
    }
}