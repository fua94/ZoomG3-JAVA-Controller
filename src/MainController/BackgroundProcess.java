package MainController;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import PedalController.ZoomG3Unit;

public class BackgroundProcess extends Thread{
	private ZoomG3Unit g3;
	private JTextField[] displays;
	
	public BackgroundProcess(ZoomG3Unit g3, JTextField[] d){
		this.g3 = g3;
		displays = d;
	}
	
	public void run(){
		while(true){
			Global.delay(Global.detectDelay);
			g3.getController().listen();
			g3.getController().waiting();
			if(Global.disconnected){
				JOptionPane.showMessageDialog(null, "Pedal disconnected!!! Connect and Refresh", "ERROR", JOptionPane.ERROR_MESSAGE);
				Global.clearDisplays(displays);
				break;
			}
		}
	}
}
