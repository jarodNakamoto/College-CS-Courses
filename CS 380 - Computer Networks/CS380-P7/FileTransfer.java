import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class FileTransfer{
	public static void main(String[] args){
		String mode = args[0]; 
		
		if(mode.equals("makekeys")){
			makeKeys();
		}
		else if(mode.equals("server")){
			serverMode(args[1], args[2]);
		}
		else if(mode.equals("client")){
			clientMode(args[1], args[2], args[3]);
		}
		else{
		
		}
	}
	
	private static void makeKeys(){
		try {
			KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
			gen.initialize(4096); // you can use 2048 for faster key generation
			KeyPair keyPair = gen.genKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("public.bin")))) {
				oos.writeObject(publicKey);
			}
			try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("private.bin")))) {
				oos.writeObject(privateKey);
			}
			} 
		catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static void serverMode(String fileName, String port){
		Socket socket;
		int portNumber = Integer.parseInt(port);
		
		Runnable clientHandler = () -> { 
			String address = socket.getInetAddress().getHostAddress();
			System.out.printf("Client connected: %s%n", address);
			
			try{
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				OutputStream os = socket.getOutputStream();
				PrintStream out = new PrintStream(os, true, "UTF-8");
				
				while(!socket.isClosed()){
					String msg = br.readLine();
					if(msg.equals("exit"))
						socket.close();
					else
						out.println(msg);
				}
				System.out.printf("Client disconnected: %s%n", address);
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
	    };
		try{
			ServerSocket serverSocket = new ServerSocket(portNumber);
		    while (true) {
				try {
					socket = serverSocket.accept();
					Thread client1 = new Thread(clientHandler);
					client1.start();
				}
				catch(IOException ioE){
					System.out.println(ioE.toString());
				}
		    }
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	private static void clientMode(String fileName, String hostName, String port){
		 try{
			int portNumber = Integer.parseInt(port);
			Socket socket = new Socket(hostName, portNumber);
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			//recieves messages
            BufferedReader br = new BufferedReader(isr);
			
			//generate AES session key
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("AES");
            keyGen.initialize(256);
            KeyPair keyPair = keyGen.generateKeyPair();
            
            //encrypt session key using servers public key
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.WRAP_MODE, keyPair.getPrivate());
            
			//sends stuff to server
			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os, true, "UTF-8");
			String address = socket.getInetAddress().getHostAddress();

			System.out.printf("Connected to server: %s%n", address);
			
			//User input messages
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter path: ");
			String input = sc.nextLine();
			System.out.print("Enter chunk size [1024]: ");
			int chunkSize = sc.nextInt();
			
			System.out.println("Sending " + +". File Size: " + + ".");
			
			
        }
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
}