package PresetController;

import MainController.Global;
import Pedal.Patch;

public class Preset {
	private int id;
	private String name;
	private String[] fsNames;
	private Patch[] fsPatchs;
	
	public Preset(int n){
		id = n;
		fsNames = new String[Global.fsCountTotal];
		fsPatchs = new Patch[Global.fsCountTotal];
		
		name = "New Preset";
		
		for(int i = 0; i<Global.fsCountTotal; i++){
			fsNames[i] = " - ";
			fsPatchs[i] = new Patch(-1);
		}
	}
	
	//GETTERS
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getFSName(int i){
		return fsNames[i];
	}
	
	public int getFSPatchNumber(int i){
		return fsPatchs[i].getNumber();
	}
	
	public boolean getFSPatchEffect(int fs, int e){
		return fsPatchs[fs].getEffects(e).getState();
	}
	
	//SETTERS
	public void setName(String n){
		name = n;
	}
	
	public void setFSName(int i, String n){
		fsNames[i] = n;
	}
	
	public void setPatchNumber(int i, int p){
		fsPatchs[i] = new Patch(p);
	}
	
	public void setEffectState(int fs, int e, boolean s){
		fsPatchs[fs].setState(e, s);
	}
}
