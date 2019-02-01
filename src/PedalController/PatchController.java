package PedalController;

import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PatchController {
	
	private static String setId(int n){
		return String.valueOf((char)(65 + n / 10)) + n % 10;
	}
	
	public static void display(int n, String p, JTextField d){
		d.setText(setId(n) + ": " + p);
	}
	
	public static void next(PedalController p, int n){
		p.getActualPatchNumber();
		
		if(n < 99){
			p.toPatch(n + 1);
			p.getActualPatchNumber();			
		}
		else{
			p.toPatch(0);
			p.getActualPatchNumber();
		}
	}
	
	public static void before(PedalController p, int n){
		p.getActualPatchNumber();
		
		if(n > 0){
			p.toPatch(n - 1);
			p.getActualPatchNumber();
		}
		else{
			p.toPatch(99);
			p.getActualPatchNumber();
		}
	}
	
	public static void to(PedalController p, LinkedList<String> l){	
		String[] patchs = new String[l.size()];
		
		int i;
		
		for(i=0; i<patchs.length; i++)
			patchs[i] = setId(i)+": "+l.get(i);
			
		String r = (String) JOptionPane.showInputDialog(null, "Select a Patch:", "Patch List", 
				JOptionPane.DEFAULT_OPTION, null, patchs, patchs[0]);
		
		if(r!=null){
			for(i=0; i<patchs.length; i++)
				if(patchs[i].compareTo(r)==0)
					break;
		
			p.toPatch(i);
			p.getActualPatchNumber();
		}
	}
	
	public static String[] list(LinkedList<String> l){
		String[] r = new String[l.size()];
		
		for(int i=0; i<r.length; i++)
			r[i] = setId(i)+": "+l.get(i);
		
		return r;
	}
}
