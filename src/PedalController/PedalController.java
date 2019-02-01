package PedalController;

import MIDIController.MIDIReconizer;
import MIDIGenerators.EffectSender;
import MIDIGenerators.PatchSender;
import MIDIGenerators.TempoSender;

public class PedalController {
	private MIDIReconizer r;
	private boolean isStarted;
	private String MIDIDevice;
	
	public PedalController(String n){
		MIDIDevice = n;
		start();
	}
	
	public void start(){
		r = new MIDIReconizer(MIDIDevice);
 		r.initTransmition();
 		isStarted = true;
 		listen();
	}
	
	//EFECT SENDER	
	public void setEffectState(int e, boolean s){
		sendMesagge(EffectSender.state(e, s));
	}
	
	public void effectAssign(int fs, int e){
		sendMesagge(EffectSender.assign(fs, e));
	}
	
	public void setEffectParameter(int fs, int p, int c){
		sendMesagge(EffectSender.parameter(fs, p, c));
	}
	
	//PATCH SENDER
	public void setPatchLevel(int v){
		sendMesagge(PatchSender.volume(v));	
	}
	
	public void toPatch(int p){
		if(isStarted)
			r.sendMIDI(PatchSender.toPatch(p));	
	}
	
	public void setPatchName(String n){
		for(int i = 0; i<n.length(); i++)
			sendMesagge(PatchSender.name(n.charAt(i), i));
		
		for(int i = n.length(); i<10; i++)
			sendMesagge(PatchSender.name(' ', i));
	}
	
	//TEMPO SENDER
	public void setTempo(int t){
		sendMesagge(TempoSender.tempo(t));
	}
	
	//READ METHODS
	public void getActualPatchInfo(){
		//F0 52 00 5A 29 F7
		byte[] actual ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x29, (byte)0xF7				
		};
		sendMesagge(actual);
	}
	
	public void getActualPatchNumber(){
		//F0 52 00 5A 33 F7
		byte[] patch ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x33, (byte)0xF7				
		};
		sendMesagge(patch);
	}
	
	public void getSpecificPatch(int p){
		sendMesagge(PatchSender.specific(p));
	}
	
	public void stop(){
		//F0 52 00 5A 51 F7
		byte[] close ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x51, (byte)0xF7				
		};
		sendMesagge(close);
		isStarted = false;
 		r.closeAllConections();
	}
	
	public void listen(){
		//F0 52 00 5A 50 F7
		byte[] listen ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x50, (byte)0xF7				
		};
 		sendMesagge(listen);
	}
	
	public void waiting(){
		//F0 52 00 5A 16 F7
		byte[] waiting ={
				(byte)0xF0, (byte)0x52, (byte)0x00, 
				(byte)0x5A, (byte)0x16, (byte)0xF7				
		};
 		sendMesagge(waiting);
	}
	
	private void sendMesagge(byte[] m){
		if(isStarted)
			r.sendSysEx(m);
	}
	
	public MIDIReconizer getMIDIDevice(){
		return r;
	}
}
