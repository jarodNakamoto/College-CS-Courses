import java.io.*;
import java.net.Socket;
import java.math.BigInteger;
import java.util.Random;

public class Ipv4ClientTest {

  public static void main(String[] args) throws Exception {
    try {
      Socket socket = new Socket("18.221.102.182", 38005);
      System.out.println("Connected to server.");

      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is, "UTF-8");
      BufferedReader br = new BufferedReader(isr);
	  
      OutputStream os = socket.getOutputStream();
	  /*
      //for(int i = 2; i <= Math.pow(2,12); i*=2) {
	  for(int i = 2; i <= 2; i*=2) {
        System.out.println("Data length: " + i);
		int size = 20+i;
        byte[] header = new byte[size];
        
		Random dataGen = new Random();
		dataGen.nextBytes(header);
		setUpIpv4Header(header, 6, i);
		
        //data: implement using 0's or random data
		int data = 0;
		
		for(int j = 0; j < header.length; j++){
				os.write(header[j]);
				System.out.println(String.format("0x%02X", header[j]));
		}
		
        String response = br.readLine();
        System.out.println(response);
        if(!response.equals("good")) {
          break;
        }
		System.out.println();
      }
	  */
	  int i = 4;
	  System.out.println("Data length: " + i);
		int size = 20+i;
        byte[] header = new byte[size];
        
		//Random dataGen = new Random();
		//dataGen.nextBytes(header);
		setUpIpv4Header(header, 8, i);
		splitAndAddToByteArr(0xDEADBEEF, 4, header, 20);
		
        //data: implement using 0's or random data
		int data = 0;
		
		for(int j = 0; j < header.length; j++){
				os.write(header[j]);
				System.out.println(String.format("0x%02X", header[j]));
		}
		/*
        String response = br.readLine();
        System.out.println(response);
		*/
		byte[] serverResponse = new byte[4];
	  
		for(i = 0; i < serverResponse.length; i++){
			serverResponse[i] = (byte)(is.read());
			System.out.print(String.format("%02X", serverResponse[i]));
		}
		System.out.println();
		
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static short checksum(byte[] b, int length) {
    //if the array length is odd
    if((b.length % 2) != 0) {
      byte[] bOdd = new byte[b.length+1];
      System.arraycopy(b, 0, bOdd, 0, b.length);
      bOdd[bOdd.length-1] = 0;
      b = bOdd;
    }
    int sum = 0;
    for (int i = 0; (i + 1) < length; i += 2) {
      int first = b[i];
      if (first < 0) {
        first ^= 0xFFFFFF00;
      }
      int second = b[i+1];
      if (second < 0) {
        second^= 0xFFFFFF00;
      }
      first <<= 8;
      sum += (first ^ second);
      // overflow detection
      if ((sum & 0xFFFF0000) != 0) {
        /*carry occurred, so wrap around */
        sum &= 0xFFFF;
        sum++;
      }
    }
    return (short)(~(sum & 0xFFFF));
  }
  private static int shiftAndMerge(int s1, int s2, int shiftAmount){
	s1 = s1 << shiftAmount;
	int thingy = s1 ^ s2;
	//System.out.println("thingy: " + String.format("0x%04X", thingy));
	return thingy;
  }
  
  private static void splitAndAddToByteArr(int split, int numSplits, byte[] b, int index){
	  
	  for(int i = 1; i <= numSplits; i++){
		if(numSplits + index -i >= b.length)
			return;
		b[numSplits + index - i] = new Integer(split).byteValue();
		split = split >> 8;
	  }
  }
  
  private static void setUpIpv4Header(byte[] packet, int protocol, int dataLength){
  //version: implement
        int version = 4;
        //HLen: implement
        int hLen = 5;
		int merged = shiftAndMerge(version,hLen,4);
		packet[0] = (new Integer(merged)).byteValue();
        //TOS: do not implement
		int tos = 0;
		packet[1] = (new Integer(tos)).byteValue();
        
		//length: implement
        int totalLength = 20 + dataLength;
		splitAndAddToByteArr(totalLength, 2, packet, 2);
		
		//ident: do not implement
		int ident = 0;
		splitAndAddToByteArr(ident, 2, packet, 4);
        
		//flags: implement assuming no fragmentation
        //String flag = "010";
		int flag = 2;
        //offset: do not implement
		int offset = 0;
		merged =  shiftAndMerge(flag,offset,13);
		splitAndAddToByteArr(merged, 2, packet, 6);
		
        //TTL: implement assuming every packet has a TTL of 50
        int ttl = 50;
        packet[8] = (new Integer(ttl)).byteValue();
		
		//protocol: implement 
        packet[9] = (new Integer(protocol)).byteValue();
		
		//checksum: implement
		packet[10] = 0;
		packet[11] = 0;
		
        //sourceaddr: implement using IP address of choice
		//192.168.56.1
		String sourceAddr = "11000000101010000011100000000001";
		int srcAddr = (new BigInteger(sourceAddr, 2)).intValue();
		splitAndAddToByteArr(srcAddr, 4, packet, 12);
		
		//destaddr: implement using IP address of server
		//18.221.102.182
		String destAddr =   "00010010110111010110011010110110";
        int dstAddr = Integer.parseInt(destAddr, 2);
		splitAndAddToByteArr(dstAddr, 4, packet, 16);
		
		//options/pad: ignore, dont even put in packet
	
		//add real checksum on header to packet
		int chksum = (int)(checksum(packet, 20));
		splitAndAddToByteArr(chksum, 2, packet, 10);	
  }
}

