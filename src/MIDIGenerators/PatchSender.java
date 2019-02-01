package MIDIGenerators;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

import Debug.Log;

public class PatchSender {
	
	public static byte[] volume(int v){
		//F0 52 00 5A 31 06 02 70(vol) 00 F7
		byte[] m ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x31, (byte)0x06,
				(byte)0x02, (byte)v, (byte)0x00,
				(byte)0xF7
		};
		
		return m;
	}
	
	public static byte[] name(char l, int i){
		//F0 52 00 5A 31 07 01(POSICION) 43(CHAR) 00 F7
		byte[] m ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x31, (byte)0x07,
				(byte)i, (byte)l, (byte)0x00,
				(byte)0xF7
		};
		
		return m;
	}
	
	public static byte[] specific(int p){
		//F0 52 00 5A 09 00 00 00(patch) F7
		byte[] m ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x09, (byte)0x00,
				(byte)0x00, (byte)p, (byte)0xF7
		};
		return m;
	}
	
	public static MidiMessage toPatch(int p){
		MidiMessage m = null;

		try {
			m = new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, p, 0);
		} catch (InvalidMidiDataException e) {
			Log.continueDebug("INVALID MIDI DATA: I couldn't send midi message");
		}
		return m;
	}
}
