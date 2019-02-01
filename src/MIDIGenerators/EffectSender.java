package MIDIGenerators;

public class EffectSender {
	
	public static byte[] state(int fs, boolean s){	
		int state;
		
		if(!s)
			state = 0;
		else
			state = 1;
		
		byte[] m ={
				//F0 52 00 5A 31 00(FS) 00 01(State) 00 F7
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x31, (byte)fs,
				(byte)0x00, (byte)state, (byte)0x00,
				(byte)0xF7
		};
		return m;
	}
	
	public static byte[] assign(int fs, int e){
		//F0 52 00 5A 31 00(FS) 01 6B(EFECTO) 00 F7
		byte[] m ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x31, (byte)fs,
				(byte)0x01, (byte)e, (byte)0x00,
				(byte)0xF7
		};
		return m;
	}
	
	public static byte[] parameter(int fs, int p, int c){
		//F0 52 00 5A 31 01(FS) 02(PAR) 01(CANT) 00(CANT 0-1) F7
		//CANT: 7f 00 = 127, 00 01 = 128
		byte[] m ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x31, (byte)fs,
				(byte)p, (byte)(c % 128), (byte)(c / 128),
				(byte)0xF7
		};
		return m;
	}
}
