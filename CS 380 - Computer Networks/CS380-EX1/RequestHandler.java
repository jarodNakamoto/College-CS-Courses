
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.Random;

public class RequestHandler implements Runnable {
	private final Socket socket;
	ServerSocket serverSocket = null;
	
		public RequestHandler(Socket socket){
			this.socket = socket;
		}
		
		@Override
		public void run() {
			Random random = new Random();
			try{
				//message receivers
				InputStream is = socket.getInputStream();
				
				//System.out.println("after first call to socket");
				
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				//recieves messages
				BufferedReader br = new BufferedReader(isr);
				String address = socket.getInetAddress().getHostAddress();
				//prints to console client joined
				System.out.printf("Client connected: %s%n", address);
				OutputStream os = socket.getOutputStream();
				//sends messages to client
				PrintStream out = new PrintStream(os, true, "UTF-8");
				
				//System.out.println("After Initializations");	
				
				//while connected to client
				while(!socket.isClosed())
				{
					String msg = br.readLine();
					if(msg.equals("exit"))
						socket.close();
					else
						out.println(msg);
					
					try{
						Thread.sleep(random.nextInt(1000));
					}
					catch(InterruptedException e){
						return;
					}
					
				}
				//prints to console client left
				System.out.printf("Client disconnected: %s%n", address);
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
}