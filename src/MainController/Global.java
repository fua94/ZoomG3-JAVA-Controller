package MainController;

import java.awt.Color;
import java.io.FileReader;
import java.util.Properties;

import javax.swing.JTextField;

public class Global {
    private static final String configFile = "config.ini";
	private static Properties prop; 
	public static byte fsCount;
	public static byte fsCountTotal;
	public static String MIDIname;
	public static String dbName;
	public static String debugName;
	public static int patchDelay;
	public static int effectDelay;
	public static int detectDelay;
	public static boolean disconnected;
	
	public static void delay(int t){
		try{ Thread.sleep(t); }catch(InterruptedException e){}
	}
	
	public static void readConfigFile(){
		prop = new Properties();
		
		try {
			prop.load(new FileReader(configFile));
		} catch (Exception e) {}
		
		fsCount = Byte.parseByte(prop.getProperty("fsCount"));
		fsCountTotal = Byte.parseByte(prop.getProperty("fsCountTotal"));
		MIDIname = prop.getProperty("MIDIname");
		dbName = prop.getProperty("dbName");
		debugName = prop.getProperty("debugName");
		patchDelay = Integer.parseInt(prop.getProperty("patchDelay"));
		effectDelay = Integer.parseInt(prop.getProperty("effectDelay"));
		detectDelay = Integer.parseInt(prop.getProperty("detectDelay"));
	}
	
	public static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder(a.length * 2);
		int cont = 0;
		for (byte b : a){
			cont++;
			sb.append(String.format("%02x ", b & 0xff));
			if(cont >= 20){
				sb.append("\n");
				cont = 0;
			}
		}
		return sb.toString();
	}
	
	public static void clearDisplays(JTextField[] d){
		d[0].setText("Disconnected");
		for(int i = 1; i<d.length; i++){
			d[i].setText("-");
			d[i].setBackground(Color.white);
		}	
	}
}
