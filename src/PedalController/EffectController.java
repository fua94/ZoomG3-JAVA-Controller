package PedalController;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DB.ControllerDB;

public class EffectController {
	
	public static void displays(int[] id, boolean[] s, JTextField[] d){
		for(int i = 0; i<6; i++){
			d[i + 1].setText(ControllerDB.getEffect(id[i]));
			if(s[i])
				d[i + 1].setBackground(Color.red);
			else
				d[i + 1].setBackground(Color.white);
		}	
	}
	
	public static void state(PedalController p, int fs, JTextField[] d){
		if(d[fs + 1].getBackground() == Color.red)
			p.setEffectState(fs, false);
		else
			p.setEffectState(fs, true);
		
		p.getActualPatchInfo();
	}
	
	public static void assign(PedalController p, int fs){
		String[] types = ControllerDB.getEffectTypeList();
		
		String r = (String) JOptionPane.showInputDialog(null, "Select a Effect Type:", "Effect Type List", 
				JOptionPane.DEFAULT_OPTION, null, types, types[0]);
		int i;
		
		if(r != null && r.compareTo("THRU") != 0){
			for(i=0; i<types.length; i++)
				if(types[i].compareTo(r) == 0)
					break;
		
			String[] effects = ControllerDB.getEffectList(i);
		
			r = (String) JOptionPane.showInputDialog(null, "Select a Effect:", "Effect List", 
					JOptionPane.DEFAULT_OPTION, null, effects, effects[0]);
			
			if(r != null){
				i = ControllerDB.getEffectId(r);
		
				p.effectAssign(fs - 1, i);
				p.getActualPatchInfo();
			}
		}
		
		if(r.compareTo("THRU") == 0){
			p.effectAssign(fs - 1, 107);
			p.getActualPatchInfo();	
		}
	}
}
