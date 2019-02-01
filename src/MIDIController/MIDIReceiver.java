package MIDIController;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.SysexMessage;

import Debug.Log;

public class MIDIReceiver {
	private MidiDevice rx;
	private Receiver r;
	private String name;
	
	public MIDIReceiver(String n) {
		name = n;
		setSender();
	}

	private void setSender(){
		rx = null;
		r = null;
		
		MidiDevice.Info[] allDeviceInfos = MidiSystem.getMidiDeviceInfo();
	    MidiDevice d = null;
	    
	    for (MidiDevice.Info info : allDeviceInfos){
			if (rx == null && info.getName().compareTo(name) == 0){
		        try{
			        d = MidiSystem.getMidiDevice(info);
			        d.open();
			        r = d.getReceiver();
		        	rx = d;
		        }
		        catch(MidiUnavailableException e)
		        {
		        	d.close();
		        }
			}	
	    }
	}
	
	private boolean isConnected(){
		boolean r = false;
		MidiDevice.Info[] allDeviceInfos = MidiSystem.getMidiDeviceInfo();

	    for (MidiDevice.Info info : allDeviceInfos){
			if (info.getName().compareTo(name) == 0)
		        r = true;
	    }
	    
	    return r;
	}
	
	public boolean sendMessage(byte data[]){
		boolean ret = false;
		
		if ( rx != null ){
			try{
				rx.open();
				SysexMessage sm = new SysexMessage();
				sm.setMessage(data, data.length );
				if(isConnected()){
					r.send( sm, -1 );	
					ret = true;
				}
	        }
	        catch( MidiUnavailableException e )
	        {
	        	rx.close();
	        	Log.continueDebug("MIDI NOT FOUND: I couldn't send sysex message");
	        }
			catch( InvalidMidiDataException e )
	        {
	        	rx.close();
	        	Log.continueDebug("INVALID MIDI DATA: I couldn't send sysex message");
	        }
		}
		return ret;
	}
	
	public boolean sendMessage(MidiMessage m){
		boolean ret = false;
		
		if ( rx != null ){
			try{
				rx.open();	
				r.send( m, -1 );	
				ret = true;
	        }
	        catch( MidiUnavailableException e )
	        {
	        	rx.close();
	        	Log.continueDebug("MIDI NOT FOUND: I couldn't send midi message");
	        }
		}
		return ret;
	}
	
	public void close(){
		rx.close();
		r.close();
	}
}
