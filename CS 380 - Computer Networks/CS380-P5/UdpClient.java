import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.math.BigInteger;

public class UdpClient {

  public static void main(String[] args) throws Exception {
    try {
      Socket socket = new Socket("18.221.102.182", 38005);
      System.out.println("Connected to server.");

      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
	  
	  
	  int minsize = 20;
	  int numPackets = 12;
	  
	  //handshake
	  byte[] packet = new byte[minsize];
	  byte[] data = {new Integer(0xDE).byteValue(),new Integer(0xAD).byteValue(),new Integer(0xBE).byteValue(),new Integer(0xEF).byteValue()};
	  
	  //TCP is six
	  setUpIpv4Header(packet, 6, 0);
	  os.write(packet);
	  os.write(data);
	  
	  byte[] serverResponse = new byte[4];
	  
	  for(int i = 0; i < serverResponse.length; i++){
		serverResponse[i] = (byte)(is.read());
	  }
	  
	  String response = serverByteResponseToString(serverResponse);
	  System.out.println("Handshake response: " + response);
	  if(!response.equals("0xCAFEBABE")) {
          return;
      }
	  
	  int portNumberHalf = is.read();
	  int portNumberHalf2 = is.read();
	  int portNumber = 0;
	  portNumber = portNumber << 8;
	  portNumber = portNumber ^ portNumberHalf2;
	 
	  System.out.println("Port Number Received: " + portNumber);
	  System.out.println();
	  
	  //connect to the new port for udp
	  socket = new Socket("18.221.102.182", portNumber);
	  is = socket.getInputStream();
      os = socket.getOutputStream();
	  
	  int[] rttTimes = new int[numPackets];
	  
      for(int i = 2; i <= Math.pow(2,numPackets); i*=2) {
        System.out.println("Sending packet with " + i + " bytes of data");
		int size = minsize+i+8;
        packet = new byte[size];
        data = new byte[i];
		fillWithRandomData(data);
		//UDP is 17
		fillIPv4Packet(packet,data, 17, portNumber);
		
		for(int j = 0; j < packet.length; j++){
				os.write(packet[j]);
		}
		
		
		is.read(serverResponse);
        response = serverByteResponseToString(serverResponse);
        System.out.println(response);
		
        if(!response.equals("0xCAFEBABE")) {
          break;
        }
		System.out.println();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void fillWithRandomData(byte[] data){
	Random dataGen = new Random();
	dataGen.nextBytes(data);
  }
  
  private static void fillIPv4Packet(byte[] packet, byte[] data, int protocol, int destPort){
	   setUpIpv4Header(packet, protocol, data.length);
		
        //IPv4 data: implement using 8 byte UDP header 
		//if applicable and random data
		int startOfData = 20;
		//using udp protocol
		if(protocol == 17){
			//pretend source port is 0
			//takes 2 bytes in array 
			//destination port is placed after source port
			splitAndAddToByteArr(destPort, 2, packet, 22);
			//length of UDP header and data
			splitAndAddToByteArr(8 + data.length, 2, packet, 24);
			//checksum of psuedoheader //happens later
			
			//udp header takes up 8 bytes
			startOfData += 8;
		}
		for(int i = 0; i < data.length; i++){
			packet[startOfData + i] = data[i];
		}
		
		//if udp, do checksum on header, data, psuedoheader "includes Ipv4 header"
		if(protocol == 17){
			
			byte[] psuedoHeader = new byte[20 + data.length];
			System.arraycopy(packet, 12, psuedoHeader, 0, 8);
			psuedoHeader[9] = (byte)17;
			splitAndAddToByteArr(17, 2, psuedoHeader, 10);
			System.arraycopy(data, 0, psuedoHeader, 20, data.length);
			//add UDP checksum to packet
			int chksum = (int)(checksum(psuedoHeader));
			splitAndAddToByteArr(chksum, 2, packet, 10);
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
		//134.71.249.45
		String sourceAddr = "10000110010001111111100100101101";
		int srcAddr = (new BigInteger(sourceAddr, 2)).intValue();
		splitAndAddToByteArr(srcAddr, 4, packet, 12);
		
		//destaddr: implement using IP address of server
		//18.221.102.182
		String destAddr =   "00010010110111010110011010110110";
        int dstAddr = Integer.parseInt(destAddr, 2);
		splitAndAddToByteArr(dstAddr, 4, packet, 16);
		
		//options/pad: ignore, dont even put in packet
	
		//add real checksum on header to packet
		int chksum = (int)(checksum(packet));
		splitAndAddToByteArr(chksum, 2, packet, 10);	
  }
  
  private static String serverByteResponseToString(byte[] serverResponse){
	String response = "0x";
		for(int k = 0; k < serverResponse.length; k++){
			response += String.format("%02X", serverResponse[k]);
		}
	return response;
  }
  
  private static short checksum(byte[] b) {
    //if the array length is odd
    if((b.length % 2) != 0) {
      byte[] bOdd = new byte[b.length+1];
      System.arraycopy(b, 0, bOdd, 0, b.length);
      bOdd[bOdd.length-1] = 0;
      b = bOdd;
    }
    int sum = 0;
    for (int i = 0; (i + 1) < b.length; i += 2) {
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
}

