package MIDIController;

import javax.sound.midi.MidiMessage;
import javax.swing.JOptionPane;

import Debug.Log;
import MainController.Global;

public class MIDIReconizer {
	private MIDISender tx;
	private MIDIReceiver rx;
	
	public MIDIReconizer(String n){
		rx = new MIDIReceiver(n);
		tx = new MIDISender(n);
	}
	
	public void initTransmition(){
		if(!tx.initTransmition()){
			Log.continueDebug("Transmition failed...");
			JOptionPane.showMessageDialog(null, "Pedal can't be found or alredy in use by other program", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public void sendSysEx(byte data[]){
		if(!rx.sendMessage(data)){
			closeAllConections();
			Log.continueDebug("Sysex send failed...");
			Global.disconnected = true;
		}
	}
	
	public void sendMIDI(MidiMessage m){
		if(!rx.sendMessage(m))
			Log.continueDebug("MIDI send failed...");
	}
	
	public void closeAllConections(){
		rx.close();
		tx.endTransmition();
	}
}
