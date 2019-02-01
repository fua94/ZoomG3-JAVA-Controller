package MIDIDecoders;

public class PatchDecoder {
	public static String name(byte[] d, boolean e){
		int i;
		String r="";
		
		for (i = 0; i < 4; i++)
			if(!e) r += (char) d[i + 0x61];
			else r += (char) d[i + 0x66];
		
		for (i = 4; i < 10; i++)
			if(!e) r += (char) d[i + 0x62];
			else r += (char) d[i + 0x67];

		return r;
	}
	
	public static int number(byte[] d){
		return d[1];
	}
}
