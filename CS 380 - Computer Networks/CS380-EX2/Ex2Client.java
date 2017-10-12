
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import java.util.zip.CRC32;

public final class Ex2Client {

    public static void main(String[] args) throws Exception {
		
		try (Socket socket = new Socket("18.221.102.182", 38102)) {
			
			//display that server connection was successful
			String address = socket.getInetAddress().getHostAddress();
			System.out.printf("Connected to: %s%n", address);
			
			//recieves bytes
            InputStream is = socket.getInputStream();
			
			System.out.print("Received bytes:");
			byte[] bytesTranslated = new byte[100];
			
			for(int i = 0; i < 100; i++)
			{
				if(i%10 == 0)
				{
					System.out.println();
					System.out.print("   ");
				}
				int halfByte1 = is.read();
				int halfByte2 = is.read();
				halfByte1 = halfByte1 << 4;
				bytesTranslated[i] = (byte)(halfByte1 ^ halfByte2);
				System.out.print(String.format("%02X", bytesTranslated[i]));
			}
			
			//create CRC-32 value
			CRC32 crc32 = new CRC32();
			crc32.update(bytesTranslated);
			
			Long crcVal = new Long(crc32.getValue());
			byte[] crcValBytes = new byte[4];
			Integer copy = new Integer(crcVal.intValue());
			
			//take the long and make it into four bytes
			for(int i = 3; i >= 0; i--)
			{	
				crcValBytes[i] = copy.byteValue();
				copy = copy >> 8;
			}
			
			System.out.println("\nGenerated CRC32: " + String.format("%08X", crcVal.intValue()) +".");
			
			//sends bytes to server
			OutputStream os = socket.getOutputStream();
			os.write(crcValBytes);
			
			//receive if crc is correct
			if(is.read() == 1)
				System.out.println("Response good");
			else
				System.out.println("Response bad");
			System.out.println("Disconnected from server.");
			is.close();
        }
	}
}