package PresetController;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DB.ControllerDB;
import PedalController.ZoomG3Unit;
import MainController.Global;

public class PresetController {	
	private JTextField[] displays;
	private JButton[] buttons;
	
	private boolean presetMode;
	private boolean chooseMode;
	private boolean effectsMode;
	
	private boolean editPreset;
	
	private ZoomG3Unit pedal;
	private Preset preset;

	private String[] patchList;
	private int actualPreset;
	private int presetCount;
	
	private int actualFS;
	private boolean pageChanged;
	
	//CONSTRUCTOR
	public PresetController(JTextField[] d,  JButton[] b, ZoomG3Unit p){
		init(d, b, p);
	}
	
	private void init(JTextField[] d,  JButton[] b, ZoomG3Unit p){
		displays = d;
		buttons = b;
		pedal = p;	
		editPreset = false;
		pageChanged = false;
	}
	
	//DISPLAYS METHODS
	private void updateDisplays(){
		int i;
		displays[0].setText((preset.getId() + 1)+" - "+preset.getName());
		
		for(i = 0; i<Global.fsCount; i++){
			if(!pageChanged){
				displays[i + 1].setText(preset.getFSName(i));
				buttons[i].setText("" + (i + 1));
			}			
			else{
				displays[i + 1].setText(preset.getFSName(i + 6));
				buttons[i].setText("" + (i + 7));
			}
			
			displays[i + 1].setBackground(Color.white);
		}
	}
	
	private void updateEffectDisplays(){
		Global.delay(Global.effectDelay);
		
		for(int i = 0; i<Global.fsCount; i++){
			displays[i + 1].setText(ControllerDB.getEffect(pedal.getEffectId(i)));			
			displays[i + 1].setBackground(Color.white);
			pedal.getController().setEffectState(i + 1, false);
		}
		
		if(!pageChanged)
			displays[actualFS + 1].setBackground(Color.red);
		else
			displays[(actualFS - 6) + 1].setBackground(Color.red);
	}
	
	private void setActualDisplay(int fs){
		for(int i = 0; i<Global.fsCount; i++)
			displays[i + 1].setBackground(Color.white);
		
		if(!pageChanged && actualFS <= 6){
			if(!(displays[fs].getText().compareTo(" - ") == 0))
				displays[fs].setBackground(Color.red);
		}
		
		if(pageChanged && actualFS > 6){
			if(!(displays[fs - 6].getText().compareTo(" - ") == 0))
			displays[fs - 6].setBackground(Color.red);
		}
	}
	
	//COMMON METHODS
	private void selectPatch(int fs){
		int i;
		String r = (String) JOptionPane.showInputDialog(null, 
				"Select patch", "Patch List", JOptionPane.DEFAULT_OPTION, null, patchList, patchList[0]);
		
		if(r != null){
			for(i=0; i<patchList.length; i++)
				if(patchList[i].compareTo(r)==0)
					break;
		
			preset.setPatchNumber(fs, i);	
			pedal.getController().toPatch(i);
			pedal.getController().getActualPatchNumber();
			
			actualFS = fs;

			updateEffectDisplays();
			activeEffectsMode();
		}
	}
	
	//CHOOSE MODE
	private void chooseFS(int fs){
		if(pageChanged)
			fs += 6;
		
		updateDisplays();
		String n;
		
		if(!editPreset){
			n = (String) JOptionPane.showInputDialog(null, "Enter FS" + fs + " Name");
			
			if(n!=null)
				preset.setFSName(fs - 1, n);
		}

		selectPatch(fs - 1);
	}
	
	//EFFECTS MODE
	private void setEffectsFS(int fs){			
		if(displays[fs].getBackground()==Color.red){
			displays[fs].setBackground(Color.white);
			pedal.getController().setEffectState(fs - 1, false);
		}
		else{
			displays[fs].setBackground(Color.red);
			pedal.getController().setEffectState(fs - 1, true);
		}
	}
	
	public void saveEffectStates(){
		if(effectsMode){
			for(int i = 0; i<Global.fsCount; i++){
				if(displays[i + 1].getBackground() == Color.red)
					preset.setEffectState(actualFS, i, true);
				else
					preset.setEffectState(actualFS, i, false);
			}
		
			activeChooseMode();
			updateDisplays();
		}
	}
	
	//ASSINGS METHODS
	private void setPresetFS(int fs){		
		if(pageChanged)
			fs += 6;
		
		actualFS = fs;
		
		int patch = preset.getFSPatchNumber(fs - 1);
		if(patch != -1){
			pedal.getController().toPatch(patch);
		
			for(int i=0; i<Global.fsCount; i++)
				pedal.getController().setEffectState(i, preset.getFSPatchEffect(fs - 1, i));
			
			setActualDisplay(fs);
		}
	}
	
	public void fsAssign(int fs){
		if(chooseMode)
			chooseFS(fs);
		if(presetMode)
			setPresetFS(fs);
		if(effectsMode)
			setEffectsFS(fs);
	}
	
	//MENU METHODS
	public void setPresetMode(){
		presetCount = ControllerDB.getPresetCount();
		preset = ControllerDB.readPreset(0);
		actualPreset = 0;
		activePresetMode();
		updateDisplays();
		setPresetFS(1);
	}
	
	public void newPreset(){
		patchList = pedal.getPatchList();
		preset = new Preset(presetCount);
		
		String n = (String) JOptionPane.showInputDialog(null, "Enter Preset Name:");
		
		if(n != null)
			preset.setName(n);
		
		updateDisplays();
		activeChooseMode();
	}
	
	public void editPreset(){
		editPreset = true;
		patchList = pedal.getPatchList();
		
		updateDisplays();
		activeChooseMode();
	}
	
	public void savePreset(){
		if(chooseMode){
			if(!editPreset){
				ControllerDB.writePreset(preset);
				setPresetFS(1);
				activePresetMode();
				actualPreset = presetCount;
				presetCount++;
			}
			else{
				ControllerDB.editPreset(preset);
				setPresetFS(1);
				activePresetMode();
				editPreset = false;
			}
		}
	}
	
	public void nextPreset(){
		if(presetMode){
			if(actualPreset < presetCount - 1)
				preset = ControllerDB.readPreset(++actualPreset);	
			else{
				preset = ControllerDB.readPreset(0);
				actualPreset = 0;
			}
		
			setPreset();
		}
	}
	
	public void beforePreset(){
		if(presetMode){
			if(actualPreset > 0)
				preset = ControllerDB.readPreset(--actualPreset);
			else{
				preset = ControllerDB.readPreset(presetCount - 1);
				actualPreset = presetCount - 1;
			}
			
			setPreset();
		}
	}
	
	public void goToPreset(){
		String[] presets = ControllerDB.getPresetList();
		int i;
		
		if(presetMode && presets.length!=0){
			String r = (String) JOptionPane.showInputDialog(null, "Select a Preset:", "Preset List", 
					JOptionPane.DEFAULT_OPTION, null, presets, presets[0]);
			
			if(r!=null){
				for(i=0; i<presets.length; i++)
					if(presets[i].compareTo(r)==0)
						break;
			
				preset = ControllerDB.readPreset(i);
				actualPreset = i;
				setPreset();
			}
		}
	}
	
	public void deletePreset(){
		int actual = actualPreset;
		int i;
		
		if(presetMode){
			ControllerDB.deletePreset(actualPreset--);
			presetCount--;
			
			if(actual == 0){
				preset = ControllerDB.readPreset(0);
				actualPreset = 0;
			}
			else
				preset = ControllerDB.readPreset(actualPreset);
				
			if(actual < presetCount)
				for(i = actual; i<presetCount; i++)
					ControllerDB.updatePresetId(i + 1, i);
				
			setPreset();
		}
	}
	
	private void setPreset(){
		updateDisplays();
		activePresetMode();
		setPresetFS(1);
	}
	
	public void changePage(){
		if(presetMode || chooseMode){
			if(!pageChanged)
				pageChanged = true;
			else
				pageChanged = false;
		
			updateDisplays();
			
			if(presetMode)
				setActualDisplay(actualFS);
		}
	}
	
	public void changePresetName(){
		if(editPreset){
			String r = (String) JOptionPane.showInputDialog(null, "Enter Preset Name");
		
			if(r!=null)
				preset.setName(r);
		
			updateDisplays();
		}
	}
	
	public void changeFSName(int fs){
		if(editPreset){
			if(pageChanged)
				fs += 6;
			
			String n = (String) JOptionPane.showInputDialog(null, "Enter FS" + fs + " Name");
			
			if(n!=null)
				preset.setFSName(fs - 1, n);
			
			updateDisplays();
		}
	}
	
	//INTERNAL METHODS
	private void activePresetMode(){
		presetMode = true;
		chooseMode = false;
		effectsMode = false;
	}
	
	private void activeChooseMode(){
		chooseMode = true;
		presetMode = false;
		effectsMode = false;
	}
	
	private void activeEffectsMode(){	
		effectsMode = true;
		chooseMode = false;
		presetMode = false;
	}
}
