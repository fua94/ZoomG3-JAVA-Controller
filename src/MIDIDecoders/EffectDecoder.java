package MIDIDecoders;

public class EffectDecoder {
	public static int param(byte[] d){
		return d[6];
	}
	
	public static int off(byte[] d){
		return d[5];
	}
	
	private static int individually(byte[] d, int i){
		int[] fs = new int[6];
		
		fs[0] = ((d[5] & 64) << 1) + d[6];
		fs[1] = ((d[13] & 2) << 6) + d[19];
		fs[2] = ((d[29] & 8) << 4) + d[33];
		fs[3] = ((d[45] & 32) << 2) + d[47];
		fs[4] = ((d[53] & 1) << 7) + d[60];
		fs[5] = ((d[69] & 4) << 5) + d[74];
		
		return fs[i];
	}
	
	public static int[] id(byte[] d){
		int[] n = new int[6];
		
		for(int i = 0; i < n.length; i++)
			n[i] = individually(d, i) >> 1;
		
		return n;
	}
	
	public static boolean[] state(byte[] d){
		boolean[] s = new boolean[6];
		
		for(int i = 0; i < s.length; i++)
			if((individually(d, i) & 1) == 1)
				s[i] = true;
			else
				s[i] = false;
		
		return s;
	}
}
