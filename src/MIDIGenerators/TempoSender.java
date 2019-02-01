package MIDIGenerators;

public class TempoSender {
	
	public static byte[] tempo(int t){
		//F0 52 00 5A 31 06 08 28(first) 00(last) F7
		byte[] m ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x31, (byte)0x06,
				(byte)0x08, (byte)(t % 128), (byte)(t / 128),
				(byte)0xF7
		};
		return m;
	}
}
