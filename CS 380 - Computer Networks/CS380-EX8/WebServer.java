import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;


public class WebServer{

	public static volatile Socket socket;
	public static volatile OutputStream output;
	public static volatile InputStream input;
	
	public static void main(String[] args) throws IOException{
		Runnable clientHandler = () -> { 
			try{
				InputStream sis = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(sis));
				String request = br.readLine(); //receives "GET website_name.html HTTP/1.1"
				String[] requestParam = request.split(" ");
				String path = requestParam[1];
				
				PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
				File file = new File("www/" + path);
				boolean fileExists = file.exists();
				if(!fileExists){
					file = new File("www/404.html");
				}
				FileReader fr = new FileReader(file);
				BufferedReader bfr = new BufferedReader(fr);
				
				String fileStatus = "HTTP/1.1 ";
				if(fileExists){
					fileStatus += "200 OK";
				}
				else{
					fileStatus += "404 Not Found";
				}
				out.println(fileStatus);
				System.out.println(fileStatus);
				
				out.println("Content-type: text/html");
				System.out.println("Content-type: text/html");
				
				String length = "Content-length: " + file.length() + "\n";
				System.out.println(length);
				out.println(length);
				
				
				//send file content to client
				System.out.println("File: " + file.toString());
				String line;
				while((line = bfr.readLine()) != null){
					System.out.println(line);
					out.println(line);
				}
				
				bfr.close();
			
				br.close();
				out.close();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		};
				
		try(ServerSocket serverSocket = new ServerSocket(8080)){
			while (true) {
				try {
					socket = serverSocket.accept();
					//
					System.out.println(socket.toString());
					//
				}
				catch(IOException ioE){
					System.out.println(ioE.toString());
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
				
				Thread client1 = new Thread(clientHandler);
				client1.start();
				try{
				    Thread.sleep(1000);
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
				System.out.println();
            }
		}
	}
}