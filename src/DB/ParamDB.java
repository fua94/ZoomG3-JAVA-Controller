package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Debug.Log;

public class ParamDB {
	public static String[] getParamList(Connection c, int e){
		String[] r = null;
		int l = 0, i = 0;
		
        ResultSet rs = null;
        PreparedStatement st = null;
        
        try {           
        	st = c.prepareStatement("SELECT COUNT(Param) as length "
        			+ "FROM EffectsParam "
        			+ "WHERE EffectId = " + e);
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                l = rs.getInt("length");
            
            r = new String[l];
            
        	st = c.prepareStatement("SELECT Param "
        			+ "FROM EffectsParam "
        			+ "WHERE EffectId = " + e);
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                r[i++] = rs.getString(1);
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect list type because:"+ex.getMessage());
        }
        return r;
	}
	
	public static int getEffectParamId(Connection c, String n, int e){
		int r = 0;
        ResultSet rs = null;
        
        try {
        	PreparedStatement st = c.prepareStatement("SELECT Id, Param "
        			+ "FROM EffectsParam "
        			+ "WHERE Param ='" + n +"' "
        					+ "AND EffectId =" + e);
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                r = rs.getInt(1);
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect id because:"+ex.getMessage());
        }
        return r;
	}
	
	public static int[] getEffectParamMaxDef(Connection c, int p){
		int[] r = new int[2];
        ResultSet rs = null;
        
        try {
        	PreparedStatement st = c.prepareStatement("SELECT Max, Def "
        			+ "FROM EffectsParam "
        			+ "WHERE Id =" + p);
        	rs = st.executeQuery();
        	
            while (rs.next()){ 
                r[0] = rs.getInt(1);
                r[1] = rs.getInt(2);
            }
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect id because:"+ex.getMessage());
        }
        return r;
	}
	
	public static String[] getValueList(Connection c, int p){
		String[] r = null;
		int l = 0, i = 0;
		
        ResultSet rs = null;
        PreparedStatement st = null;
        
        try {           
        	st = c.prepareStatement("SELECT COUNT(Label) as length "
        			+ "FROM ParamsLabel "
        			+ "WHERE ParamId = " + p);
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                l = rs.getInt("length");
            
            r = new String[l];
            
        	st = c.prepareStatement("SELECT Label "
        			+ "FROM ParamsLabel "
        			+ "WHERE ParamId = " + p);
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                r[i++] = rs.getString(1);
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get effect list type because:"+ex.getMessage());
        }
        return r;
	}
}
