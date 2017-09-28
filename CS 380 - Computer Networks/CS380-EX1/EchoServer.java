import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;

public final class EchoServer {

	public static volatile Socket socket;

    public static void main(String[] args) throws Exception {
        Runnable clientHandler = () -> { 
                
					String address = socket.getInetAddress().getHostAddress();
					//prints to console client joined
					System.out.printf("Client connected: %s%n", address);
					
					//creates the runnable for client
					//new RequestHandler(socket);
					//Random random = new Random();
					try{
						//message receivers
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is, "UTF-8");
						//recieves messages
						BufferedReader br = new BufferedReader(isr);
						OutputStream os = socket.getOutputStream();
						//sends messages to client
						PrintStream out = new PrintStream(os, true, "UTF-8");
						
						//while connected to client
						while(!socket.isClosed())
						{
							String msg = br.readLine();
							if(msg.equals("exit"))
								socket.close();
							else
								out.println(msg);
							/*
							try{
								Thread.sleep(1000);
								//Thread.sleep(random.nextInt(1000));
							}
							catch(InterruptedException e){
								return;
							}*/		
						}
						//prints to console client left
						System.out.printf("Client disconnected: %s%n", address);
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
				
				
                };try (ServerSocket serverSocket = new ServerSocket(22222)) {
			//server runs indefinitely
            while (true) {
				//establish connection
				try {
					socket = serverSocket.accept();
				}
				catch(IOException ioE)
				{
					System.out.println(ioE.toString());
				}
				//creates thread objects to execute Runnable
				//starts threads. Main thread countinues
				Thread client1 = new Thread(clientHandler);
				//Thread client2 = new Thread(clientHandler);
				client1.start();
				//client2.start();
				//Thread.sleep(1000);
            }
			
					
        }
    }
}