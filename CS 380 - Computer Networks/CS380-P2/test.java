
public class test
{
	//class to test how bytes longs and others work 
	public static void main(String[] args)
	{
		int i = 0xFF; // 255
		System.out.println(i);
		i = i << 1;	//510
		System.out.println(i);
		/*
		byte b1 = 0x05;
		byte b2 = 0x0A;
		byte b3 = 0x5A;
		
		byte temp = b1;
		temp = (byte)(temp << 4);
		
		byte b4 = (byte)(temp ^ b2);
		
		System.out.println("b1: " + String.format("%02X",b1));
		System.out.println("b2: " + String.format("%02X",b2));
		System.out.println("b3: " + String.format("0x%02X",b3));
		System.out.println("b4: " + String.format("0x%02X",b4));
	*/
	/*
		long l = 0XFA128A79;
		byte lB = ((Long)l).byteValue();
		System.out.println("l: " + String.format("%08X", l));
		System.out.println("l's byte value: " + String.format("%08X", lB));
	*/	/* test output
		l: FFFFFFFFFA128A79
		l's byte value: 00000079
		*/
	}
}