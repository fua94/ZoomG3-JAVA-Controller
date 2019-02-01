package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Debug.Log;

public class EffectDB {
	
	public static String get(Connection c, int e){
		String r = "";
        ResultSet rs = null;
        
        try {
        	PreparedStatement st = c.prepareStatement("SELECT Id, Name FROM Effects WHERE Id =" + e);
        	rs = st.executeQuery();
            while (rs.next()) {
                r = rs.getString(2);
            }
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect name because:"+ex.getMessage());
        }
        return r;
	}

	public static String[] getList(Connection c, int id){
		String[] r = null;
		int l = 0, i = 0;
		
        ResultSet rs = null;
        PreparedStatement st = null;
        
        try {
        	st = c.prepareStatement("SELECT COUNT(Name) as length FROM Effects WHERE Type="+id);
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                l = rs.getInt("length");
            
            r = new String[l];
            
        	st = c.prepareStatement("SELECT Id, Name, Type FROM Effects WHERE Type="+id);
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                r[i++] = rs.getString(2);
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect list because:"+ex.getMessage());
        }
        return r;
	}
	
	public static String[] getTypeList(Connection c){
		String[] r = null;
		int l = 0, i = 0;
		
        ResultSet rs = null;
        PreparedStatement st = null;
        
        try {
        	st = c.prepareStatement("SELECT COUNT(Id) as length FROM EffectsType");
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                l = rs.getInt("length");
            
            r = new String[l];
            
        	st = c.prepareStatement("SELECT Type FROM Effectstype");
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                r[i++] = rs.getString(1);
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect list type because:"+ex.getMessage());
        }
        return r;
	}
	
	public static int getEffectId(Connection c, String n){
		int r = 0;
        ResultSet rs = null;
        
        try {
        	PreparedStatement st = c.prepareStatement("SELECT Id, Name FROM Effects WHERE Name ='" + n +"'");
        	rs = st.executeQuery();
            while (rs.next())
                r = rs.getInt(1);
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect id because:"+ex.getMessage());
        }
        return r;
	}
	
	public static String getEffectType(Connection c, int id){
		int t = 0;
		String r = "";
        ResultSet rs = null;
        PreparedStatement st = null;
        
        try {
        	st = c.prepareStatement("SELECT Type FROM Effects WHERE Id =" + id );
        	rs = st.executeQuery();
            while (rs.next())
                t = rs.getInt(1);
            
            st = c.prepareStatement("SELECT Type FROM EffectsType WHERE Id =" + t );
        	rs = st.executeQuery();
            while (rs.next())
                r = rs.getString(1);
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect id because:"+ex.getMessage());
        }
        return r;
	}
	
}
