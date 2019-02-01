package MainController;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DB.ControllerDB;
import Debug.Log;
import PedalController.ZoomG3Unit;
import PresetController.PresetController;

public class MainController {
	
	private boolean liveMode;
	private boolean presetMode;
	private ZoomG3Unit g3;
	
	private PresetController pc;
	
	public MainController(JTextField[] d, JButton[] b){
		init(d, b);
	}
	
	private void init(JTextField[] d, JButton[] b){
		Global.readConfigFile();
		Log.startDebug();
		ControllerDB.connect();
		g3 = new ZoomG3Unit(d);
		liveMode = false;
		presetMode = true;
		pc = new PresetController(d, b, g3);
		setLiveModeOff();
		Global.disconnected = false;
		new BackgroundProcess(g3, d).start();
	}
	
	//ASSIGNS
	public void normalAssign(int fs){
		if(!Global.disconnected){
			if(liveMode)
				g3.setEffectState(fs - 1);
			if(presetMode)
				pc.fsAssign(fs);
		}
	}
	
	public void normalAssign(char c){
		if(!Global.disconnected){
			if(liveMode){
				switch(c){
					case 'U': g3.nextPatch(); break;
					case 'D': g3.beforePatch(); break;
				}	
			}
			if(presetMode){
				switch(c){
					case 'U': pc.nextPreset(); break;
					case 'D': pc.beforePreset(); break;
					case 'S': pc.saveEffectStates(); break;
					case 'P': pc.changePage(); break;
				}			
			}
		}
	}
	
	public void holdAssign(int fs){
		if(!Global.disconnected){
			if(liveMode){
				if(fs != 0)
					g3.setEffect(fs);
				else{
					String r = (String) JOptionPane.showInputDialog(null, "Enter Patch Name");
					
					if(r != null)
						g3.getController().setPatchName(r);
				}
			}
			if(presetMode){
				if(fs == 0)
					pc.changePresetName();
				else
					pc.changeFSName(fs);
			}
		}
	}
	
	//GETTERS
	public PresetController getPC(){
		return pc;
	}
	
	////PEDAL////
	public ZoomG3Unit getPedal(){
		return g3;
	}
	/////////////
	
	//MENU ASSIGNS	
	public void setLiveModeOn(){
		activateLiveMode();
		g3.setMode(true);
	}
	
	public void setLiveModeOff(){
		activatePresetMode();
		pc.setPresetMode();
		g3.setMode(false);
	}
	
	public void close(){
		ControllerDB.close();
		g3.turnOff();
		Log.stopDebug();
		System.exit(0);
	}
	
	//INTERNAL METHODS
	private void activateLiveMode(){
		liveMode = true;
		presetMode = false;
	}
	
	private void activatePresetMode(){
		presetMode = true;
		liveMode = false;
	}
}
