
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;
import java.io.IOException;

public final class test {

    public static void main(String[] args) throws Exception {
		
			String myData = "5ADC02B4D34929690976B733924D86191EAF4FB8EF4482274277E3D34FF59CA8795D87A642284D";
			String dataSet = "5ADC02B4D34929690976B733924D86191EAF4FB8EF4482274277E3D34FF59CA8795D87A642284D";
		
		
			System.out.println("Data set is same: " + myData.equals(dataSet));
			
			System.out.print("Received bytes:");
			//4) receive the rest of bytes and store in array
			byte[] bytesReceived = {(byte)0x5A, (byte)0xDC, (byte)0x02, (byte)0xB4, (byte)0xD3, (byte)0x49, (byte)0x29, (byte)0x69, (byte)0x09, (byte)0x76, 
									(byte)0xB7, (byte)0x33, (byte)0x92, (byte)0x4D, (byte)0x86, (byte)0x19, (byte)0x1E, (byte)0xAF, (byte)0x4F, (byte)0xB8,
									(byte)0xEF, (byte)0x44, (byte)0x82, (byte)0x27, (byte)0x42, (byte)0x77,	(byte)0xE3, (byte)0xD3, (byte)0x4F, (byte)0xF5,
									(byte)0x9C, (byte)0xA8, (byte)0x79, (byte)0x5D, (byte)0x87, (byte)0xA6, (byte)0x42, (byte)0x28, (byte)0x4D};
			
			for(int i = 0; i < bytesReceived.length; i++){
				if(i%10 == 0)
				{
					System.out.println();
					System.out.print("   ");
				}
				
				System.out.print(String.format("%02X", bytesReceived[i]));
			}
			System.out.println();
			
			//6) take array and pass it into checksum
			short checkSum = checksum(bytesReceived);
			
			
			//7) send checksum as sequence of bytes to server
			Short val = new Short(checkSum);
			byte[] byteArr = new byte[2];
			Integer copy = new Integer(val.intValue());
			
			//take the value and make it into two bytes
			for(int i = byteArr.length-1; i >= 0; i--){	
				byteArr[i] = copy.byteValue();
				//
				//System.out.println("byte " + i + " " + String.format("0x%02X", byteArr[i]));
				//
				copy = copy >> 8;
			}
			
			
			System.out.println("\nChecksum calculated: " + String.format("0x%04X", val.shortValue()) +". " + Long.toBinaryString((val.shortValue())));
			System.out.println("Checksum that is right: 0x49C2  " + Long.toBinaryString((long)0x49C2));
			
        
	}
	
	
	//5) write checksum
	public static short checksum(byte[] b){
		long sum = 0;
		System.out.println("sum: " + Long.toBinaryString(sum));
		
		//while(count--) 
		for(int i = 0; i < b.length; i++){
			//sum += *buf++; add the value at refrence and then increment
			int b1 = b[i];		
			if(b1 < 0)
				b1 = b1 ^ 0xFFFFFF00;
			i++;
			int b2 = 0x00;
			if(i < b.length)
			{
				b2 = b[i];
				if(b2 < 0)
					b2 = b2 ^ 0xFFFFFF00;
				System.out.println("b1: " + b1 +" " + String.format("0x%02X",b1) + " ; " + "b2: " + b2 +" " + String.format("0x%02X",b2));
			}
			b1 = b1 << 8;
		

			//System.out.println("b1: " + String.format("0x%02X",b1) + " ; " + "b2: " + String.format("0x%02X",b2));
		
			int add = (b1 ^ b2);
		
		
			System.out.println("add: " + add + " , " + String.format("0x%04X",add));
			
			sum += add;
			
			//
			System.out.println("sum: " + sum);
			System.out.println("sum: " + Long.toBinaryString(sum));
			//
			
			
			//if (sum & 0xFFFF0000)
			if((sum & 0xFFFF0000) != 0x00000000){
				System.out.println("carry occurred. so wrap around");
				/*carry occurred. so wrap around */
				sum = sum & 0xFFFF;
				//System.out.println("sum: " + Long.toBinaryString(sum));
				sum++;
				
				System.out.println("sum: " + Long.toBinaryString(sum));
			}
			System.out.println();
		}
		
		//return the bit wise inverse of (sum & 0xFFFF)
		//ones complement and return right most 16 bits
		short val = (short)(~(sum & 0XFFFF));
		return val; //(short)(val ^ 0xFFFF0000);
		//my val: 1001011001110101
		//rs val: 100100111000010
	}
}