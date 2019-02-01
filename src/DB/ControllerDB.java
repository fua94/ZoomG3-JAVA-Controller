package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Debug.Log;
import MainController.Global;
import PresetController.Preset;

public class ControllerDB {
	
	private static Connection c;
	
	public static void connect(){
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + Global.dbName);
		}catch (SQLException ex) {
			Log.continueDebug("I couldn't connect to db: "+ex.getMessage());
		}
	}
	
	public static void close(){
		try {
			c.close();
		} catch (SQLException ex) {
			Log.continueDebug("I couldn't close db: "+ex.getMessage());
		}
	}
	
	//EFFECTS
	public static String getEffect(int e){
		return EffectDB.get(c, e);
    }
	
	public static String[] getEffectList(int e){
		return EffectDB.getList(c, e);
    }
	
	public static String[] getEffectTypeList(){
		return EffectDB.getTypeList(c);
    }
	
	public static int getEffectId(String n){
		return EffectDB.getEffectId(c, n);
    }
	
	public static String getEffectType(int id){
		return EffectDB.getEffectType(c, id);
    }
	
	//PARAMS
	public static String[] getParamList(int e){
		return ParamDB.getParamList(c, e);
    }
	
	public static int getEffectParamId(String n, int e){
		return ParamDB.getEffectParamId(c, n, e);
	}
	
	public static int[] getEffectParamMaxDef(int p){
		return ParamDB.getEffectParamMaxDef(c, p);
	}
	
	public static String[] getValueList(int p){
		return ParamDB.getValueList(c, p);
	}
	
	//PRESETS
	public static void writePreset(Preset p){
		PresetDB.write(c, p);
	}
	
	public static Preset readPreset(int id){
		return PresetDB.read(c, id);
	}
	
	public static int getPresetCount(){
		return PresetDB.count(c);
	}
	
	public static String[] getPresetList(){
		return PresetDB.list(c);
	}
	
	public static void deletePreset(int id){
		PresetDB.delete(c, id);
	}
	
	public static void updatePresetId(int b, int n){
		PresetDB.updateId(c, b, n);
	}
	
	public static void editPreset(Preset p){
		PresetDB.edit(c, p);
	}
}
