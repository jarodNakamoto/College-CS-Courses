import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
			//server runs indefinitely
            while (true) {
				//establish connection
                try (Socket socket = serverSocket.accept()) {
					
					//creates the runnable for client
					Runnable client = () -> {
						Random random = new Random();
						try{
							System.out.println("Inside Runnable");	
							//message receivers
							InputStream is = socket.getInputStream();
							
							System.out.println("after first call to socket");
							
							InputStreamReader isr = new InputStreamReader(is, "UTF-8");
							//recieves messages
							BufferedReader br = new BufferedReader(isr);
							String address = socket.getInetAddress().getHostAddress();
							//prints to console client joined
							System.out.printf("Client connected: %s%n", address);
							OutputStream os = socket.getOutputStream();
							//sends messages to client
							PrintStream out = new PrintStream(os, true, "UTF-8");
							
							System.out.println("After Initializations");	
							
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
					};
					
					//creates thread objects to execute Runnable
					Thread client1 = new Thread(client());
					//Thread client2 = new Thread(client);
					
					//starts threads. Main thread countinues
					client1.start();
					//client2.start();
                }
				
				Thread.sleep(1000);
            }
        }
    }
}