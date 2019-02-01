package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Debug.Log;
import MainController.Global;
import PresetController.Preset;

public class PresetDB {	
	public static void write(Connection c, Preset p){
		PreparedStatement st = null;
		PreparedStatement ste = null;
		int patch;
		
		try {
			st = c.prepareStatement("INSERT INTO Presets (Id,Name) values (?,?)");
			st.setInt(1, p.getId());
			st.setString(2, p.getName());
			st.execute();
			
			for(int i=0; i<Global.fsCountTotal; i++){
				st = c.prepareStatement("INSERT INTO PresetFSPatchs (PresetId,FS,FSName,FSPatch) values (?,?,?,?)");
				st.setInt(1, p.getId());
				st.setInt(2, i + 1);
				st.setString(3, p.getFSName(i));
				patch = p.getFSPatchNumber(i);
				st.setInt(4, patch);
				if(patch != -1){
					for(int j=0; j<Global.fsCount; j++){
						ste = c.prepareStatement("INSERT INTO PresetFSEffects (PresetId,FSId,FSPedal,State) values (?,?,?,?)");
						ste.setInt(1, p.getId());
						ste.setInt(2, i + 1);
						ste.setInt(3, j + 1);
						if(p.getFSPatchEffect(i, j))
							ste.setInt(4, 1);
						else
							ste.setInt(4, 0);
						ste.execute();
					}
				}
				st.execute();
			}
		} catch (SQLException ex) {
			Log.continueDebug("I couldn't write preset because:"+ex.getMessage());
		}
	}
	
	public static Preset read(Connection c, int id){
		Preset p = new Preset(id);
		p.setName("N/A");
		
		PreparedStatement st = null;
		PreparedStatement ste = null;
        ResultSet rs = null;
        ResultSet rse = null;
        int fs;
        int i;
        int patch;
        
        try {
        	st = c.prepareStatement("SELECT Id,Name FROM Presets WHERE Id="+id);
        	rs = st.executeQuery();
        	
            while (rs.next()) 
            	p.setName(rs.getString(2));
            
        	st = c.prepareStatement("SELECT FS,FSName,FSPatch,PresetId FROM PresetFSPatchs WHERE PresetId="+id);
        	rs = st.executeQuery();
        	
            while (rs.next()){
            	fs = rs.getInt(1) - 1;
            	p.setFSName(fs, rs.getString(2));
            	patch = rs.getInt(3);
            	p.setPatchNumber(fs, patch);
            	if(patch != -1){
            		ste = c.prepareStatement("SELECT PresetId,FSId,FSPedal,State FROM PresetFSEffects "
            				+ "WHERE PresetId="+id+" and FSId="+(fs+1));
            		rse = ste.executeQuery();
            		i = 0;
            		while(rse.next()){
            			if(rse.getInt(4) == 1)
            				p.setEffectState(fs, i++, true);
            			else
            				p.setEffectState(fs, i++, false);
            		}
                }
            } 
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't read preset because:"+ex.getMessage());
        }
		return p;
	}

	public static int count(Connection c){
		PreparedStatement st = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
        	st = c.prepareStatement("SELECT COUNT(Id) as pcount FROM Presets");
        	rs = st.executeQuery();
        	
            while (rs.next()) 
            	count = rs.getInt("pcount");
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't count presets because:"+ex.getMessage());
        }
        
		return count;
	}
	
	public static String[] list(Connection c){
		String[] r = null;
		int l = 0, i = 0;
		
        ResultSet rs = null;
        PreparedStatement st = null;
        
        try {
        	st = c.prepareStatement("SELECT COUNT(Id) as length FROM Presets");
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                l = rs.getInt("length");
            
            r = new String[l];
            
        	st = c.prepareStatement("SELECT Id, Name FROM Presets");
        	rs = st.executeQuery();
        	
            while (rs.next()) 
                r[i++] = i+" - "+rs.getString(2);
            
        } catch (SQLException ex) {
        	Log.continueDebug("I couldn't get preset list because:"+ex.getMessage());
        }
        return r;
	}
	
	public static void delete(Connection c, int id){
		PreparedStatement st = null;
		
		try {
			st = c.prepareStatement("DELETE FROM Presets WHERE Id="+id);
			st.executeUpdate();
			st = c.prepareStatement("DELETE FROM PresetFSPatchs WHERE PresetId="+id);
			st.executeUpdate();
			st = c.prepareStatement("DELETE FROM PresetFSEffects WHERE PresetId="+id);
			st.executeUpdate();
		} catch (SQLException ex) {
			Log.continueDebug("I couldn't delete preset because:"+ex.getMessage());
		}
	}
	
	public static void updateId(Connection c, int b, int n){
		PreparedStatement st = null;
		
		try {
			st = c.prepareStatement("UPDATE Presets SET Id = ? WHERE Id =" + b);
			st.setInt(1, n);
			st.executeUpdate();
			st = c.prepareStatement("UPDATE PresetFSPatchs SET PresetId = ? WHERE PresetId =" + b);
			st.setInt(1, n);
			st.executeUpdate();
			st = c.prepareStatement("UPDATE PresetFSEffects SET PresetId = ? WHERE PresetId =" + b);
			st.setInt(1, n);
			st.executeUpdate();
		} catch (SQLException ex) {
			Log.continueDebug("I couldn't update id preset because:"+ex.getMessage());
		}
	}
	
	public static void edit(Connection c, Preset p){
		PreparedStatement st = null;
		PreparedStatement ste = null;
		int patch;
		
		try {
			st = c.prepareStatement("UPDATE Presets SET Name=? WHERE Id="+p.getId());
			st.setString(1, p.getName());
			st.executeUpdate();
			
			st = c.prepareStatement("DELETE FROM PresetFSEffects WHERE PresetId="+p.getId());
			st.executeUpdate();
			
			for(int i=0; i<Global.fsCountTotal; i++){
				st = c.prepareStatement("UPDATE PresetFSPatchs SET FSName=?, FSPatch=? "
						+ "WHERE PresetId="+p.getId()+" AND FS="+(i + 1));
				st.setString(1, p.getFSName(i));
				patch = p.getFSPatchNumber(i);
				st.setInt(2, patch);
				if(patch != -1){
					for(int j=0; j<Global.fsCount; j++){
						ste = c.prepareStatement("INSERT INTO PresetFSEffects (PresetId,FSId,FSPedal,State) values (?,?,?,?)");
						ste.setInt(1, p.getId());
						ste.setInt(2, i + 1);
						ste.setInt(3, j + 1);
						if(p.getFSPatchEffect(i, j))
							ste.setInt(4, 1);
						else
							ste.setInt(4, 0);
						ste.execute();
					}
				}
				st.executeUpdate();
			}
		} catch (SQLException ex) {
			Log.continueDebug("I couldn't edit preset because:"+ex.getMessage());
		}
	}
	
}
