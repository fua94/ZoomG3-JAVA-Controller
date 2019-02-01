package Debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import MainController.Global;

public class Log {	
	private static String getDate(){
        Calendar now = Calendar.getInstance();
		String d = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		String m = String.valueOf(now.get(Calendar.MONTH)+1);
		
		if(m.length() == 1)
			m = "0" + m;
		if(d.length() == 1)
			d = "0" + d;
		
		return d + "/" + m  +"/" + String.valueOf(now.get(Calendar.YEAR));
	}
	
	private static String getTime(){
        Calendar now = Calendar.getInstance();
		String s = String.valueOf(now.get(Calendar.SECOND));
		String m = String.valueOf(now.get(Calendar.MINUTE));
		String h = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
		
		if(s.length() == 1)
			s = "0" + s;
		if(m.length() == 1)
			m = "0" + m;
		if(h.length() == 1)
			h = "0" + h;
		
		return h + ":" + m + ":" + s;
	}
	
	public static void startDebug(){
		FileWriter file = null;
		BufferedWriter writer = null;
		
		if (!(new File(Global.debugName).exists()))
			try {
				file = new FileWriter(new File(Global.debugName));
			} catch (IOException e) {
				System.out.println("IO Exception error...");
			}
 
		try {
			file = new FileWriter(new File(Global.debugName));
			writer = new BufferedWriter(file);
			writer.write("This debug was created: "+getDate()+" at "+ getTime());
			writer.newLine();
			writer.newLine();
			writer.close();
			file.close();
		} catch (IOException e) {
			System.out.println("IO Exception error...");
		}
	}
	
	public static void continueDebug(String s){
		FileWriter file = null;
		BufferedWriter writer = null;
 
		try {
			file = new FileWriter(new File(Global.debugName), true);
			writer = new BufferedWriter(file);
			writer.append(getTime()+" > "+s);
			writer.newLine();
			writer.close();
			file.close();
		} catch (IOException e) {
			System.out.println("IO Exception error...");
		}
	}
	
	public static void stopDebug(){
		FileWriter file = null;
		BufferedWriter writer = null;
 
		try {
			file = new FileWriter(new File(Global.debugName), true);
			writer = new BufferedWriter(file);
			writer.newLine();
			writer.append("This debug was stopped: "+getDate()+" at "+ getTime());
			writer.close();
			file.close();
		} catch (IOException e) {
			System.out.println("IO Exception error...");
		}
	}
}