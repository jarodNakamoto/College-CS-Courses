import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public final class EchoServer {

	public static volatile Socket socket;

    public static void main(String[] args) throws Exception {
		
        Runnable clientHandler = () -> { 
					String address = socket.getInetAddress().getHostAddress();
					System.out.printf("Client connected: %s%n", address);
					
					try{
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is, "UTF-8");
						BufferedReader br = new BufferedReader(isr);
						OutputStream os = socket.getOutputStream();
						PrintStream out = new PrintStream(os, true, "UTF-8");
						
						while(!socket.isClosed())
						{
							String msg = br.readLine();
							if(msg.equals("exit"))
								socket.close();
							else
								out.println(msg);
						}
						System.out.printf("Client disconnected: %s%n", address);
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
                };
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
				try {
					socket = serverSocket.accept();
				}
				catch(IOException ioE)
				{
					System.out.println(ioE.toString());
				}
				Thread client1 = new Thread(clientHandler);
				client1.start();
				Thread.sleep(1000);
            }
        }
    }
}