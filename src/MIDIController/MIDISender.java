package MIDIController;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;

import Debug.Log;
import PedalController.ZoomG3Unit;

public class MIDISender {
	private MidiDevice tx;
	private String name;
	
	public MIDISender(String n) {
		name = n;
		setReceiver();
	}
	
	static class customReciever implements Receiver {
		private String name;
		
		public customReciever(String n){
			name = n;
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
		
		@Override
		public void send(MidiMessage msg, long time) {
			if(isConnected())
				ZoomG3Unit.decode(msg.getMessage());
		}

		@Override
		public void close() {}
	}
	
	private void setReceiver(){
		tx = null;
		
		MidiDevice.Info[] allDeviceInfos = MidiSystem.getMidiDeviceInfo();
	    MidiDevice d = null;
	    
	    for (MidiDevice.Info info : allDeviceInfos){
			if (tx == null && info.getName().compareTo(name) == 0){
		        try{
			        d = MidiSystem.getMidiDevice(info);
			        d.open();
		        	tx = d;
		        }
		        catch(MidiUnavailableException e)
		        {
		        	d.close();
		        }
			}	
	    }
	}
	
	public boolean initTransmition(){
		boolean ret = false;
		
		if ( tx != null ){
			try{				
				tx.getTransmitter().setReceiver(new customReciever(name));
				ret = true;
	        }
	        catch( MidiUnavailableException e )
	        {
	        	tx.close();
	        	Log.continueDebug("MIDI not found: I couldn't initialize data transmition");
			}
		}
		return ret;
	}
	
	public void endTransmition(){
		tx.close();
	}
}
