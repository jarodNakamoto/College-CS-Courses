import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
			//server runs indefinitely
            while (true) {
				//establish connection
                try (Socket socket = serverSocket.accept()) {
					
					//message receivers
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is, "UTF-8");
					//recieves messages
					BufferedReader br = new BufferedReader(isr);
					String address = socket.getInetAddress().getHostAddress();
					//prints to console client joined
                    System.out.printf("Client connected: %s%n", address);
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
					}
					
					
					//prints to console client left
                    System.out.printf("Client disconnected: %s%n", address);
                }
            }
        }
    }
}