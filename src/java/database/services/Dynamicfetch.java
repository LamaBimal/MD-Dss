/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.services;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bimal
 */
public class Dynamicfetch {
    int disease_id;
    int symptoms_id[]={};
    Map<Integer,String>dmap = new HashMap<>();

    public int getDisease_id() {
        return disease_id;
    }

    public void setDisease_id(int disease_id) {
        this.disease_id = disease_id;
    }

    public int[] getSymptoms_id() {
        return symptoms_id;
    }

    public void setSymptoms_id(int[] symptoms_id) {
        this.symptoms_id = symptoms_id;
    }
    public Map<Integer,String> fetch_data()
    {
        
        try {
            DbConnection db = new DbConnection();
            PreparedStatement pst = db.con.prepareStatement("select symptom,id,probability from symptoms where disease_id=?");
            pst.setInt(1,disease_id);
            db.rs=pst.executeQuery();
            while(db.rs.next())
            {
                dmap.put(db.rs.getInt("id"),db.rs.getString("symptom"));
                
            }
            dmap.keySet().stream().forEach((key) -> {
                for(int i=0;i<symptoms_id.length;i++)
                {
                    if(key==symptoms_id[i])
                    {
                        dmap.remove(key);
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(Dynamicfetch.class.getName()).log(Level.SEVERE, null, ex);
        }
      return dmap;
    }
    
    
}
