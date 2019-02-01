package PedalController;

import java.util.LinkedList;
import javax.swing.JTextField;

import MIDIDecoders.EffectDecoder;
import MIDIDecoders.PatchDecoder;
import MainController.Global;

public class ZoomG3Unit {
	private static PedalController pedal;
	
	private static int patchNumber;
	private static String patchName;
	private static int[] effectId;
	private static boolean[] effectState;
	private static JTextField[] displays;
	
	private static LinkedList<String> patchList;
	private static boolean patchFlag;
	
	private static boolean live;
	
	public ZoomG3Unit(JTextField[] d){
		effectId = new int[6];
		effectState = new boolean[6];
		patchList = new LinkedList<String>();
		displays = d;
		pedal = new PedalController(Global.MIDIname);
		pedal.getActualPatchNumber();
		patchFlag = false;
		live = false;
	}
	
	//LISTENER
	public static void decode(byte[] m){	
		switch(m.length){
			case 110: {
				if (m[4] == 0x28){
					patchName = PatchDecoder.name(m, false);
					effectId = EffectDecoder.id(m);
					effectState = EffectDecoder.state(m);
				}
			}break;
			case 120: {
				if (m[4] == 0x08)
					verifyPatchListSize(PatchDecoder.name(m, true));
			}break;
			case 2: {
				if(m[0] == -64){
					patchNumber = PatchDecoder.number(m);
					pedal.getActualPatchInfo();
				}
			}break;	
			case 10: {
				if(m[6] == 0x00)
					effectState[EffectDecoder.off(m)] = false;
			}break;	
			case 11: {//f0 52 00 5a 17 45 00 00 00 00 f7
				Global.disconnected = false;
			}break;	
		}
		if(live)
			setDisplays();
	}
	
	//INTERNAL CONTROL
	public void setMode(boolean m){
		live = m;
		
		if(m)
			pedal.getActualPatchNumber();		
	}
	
	public String[] getPatchList(){
		updatePatchList();
		
		return PatchController.list(patchList);
	}
	
	//PEDAL CONTROL
	private static void setDisplays(){
		PatchController.display(patchNumber, patchName, displays[0]);
		EffectController.displays(effectId, effectState, displays);
	}
	
	private static void addPatchName(String p){
		if(p != null)
			if(patchList.size() <= 100)
				patchList.add(p);
	}
	
	private static void verifyPatchListSize(String p){
		if(p.compareTo("          ") == 0)
			patchFlag = true;
		else
			addPatchName(p);
	}
	
	public void updatePatchList(){
		patchList = new LinkedList<String>();
		patchFlag = false;
		
		for(int i=0; i<100; i++){
			Global.delay(Global.patchDelay);
			pedal.getSpecificPatch(i);	
			if(patchFlag)
				break;
		}
	}
	
	public void turnOff(){
		pedal.stop();
	}
	
	//PATCH CONTOLLER
	public void nextPatch(){
		PatchController.next(pedal, patchNumber);
	}
	
	public void beforePatch(){
		PatchController.before(pedal, patchNumber);
	}
	
	public void goToPatch(){
		updatePatchList();
		PatchController.to(pedal, patchList);
	}
	
	//EFFECT CONTOLLER
	public void setEffectState(int fs){
		EffectController.state(pedal, fs, displays);
	}
	
	public void setEffect(int fs){
		EffectController.assign(pedal, fs);
	}
	
	//GETTERS
	public PedalController getController(){
		return pedal;
	}
	
	public int getEffectId(int i){
		return effectId[i];
	}
}
