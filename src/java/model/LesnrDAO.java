/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author dw018
 */
public class LesnrDAO {
    
     public ArrayList<Lesnr> lesnrsLaden() {
         
        ArrayList<Lesnr> lesnrs = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select * from lesnrs";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);
      
            while (rs.next()) {
                    Lesnr lesnr = new Lesnr();
                    lesnr.setLesID(rs.getInt("lesnrID"));  
                    lesnr.setLesnr(rs.getInt("lesnr"));
                    lesnrs.add(lesnr); 
            }
        } catch (SQLException e) {

        } finally {
            Utilities.sluitVariabelen(statement, rs, currentCon);

        }
        return lesnrs;
    }
   
    
}
