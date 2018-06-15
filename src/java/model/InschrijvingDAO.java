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

/**
 *
 * @author dw018
 */
public class InschrijvingDAO {

    public int geefInschrijvingID(int gebruikerID, int moduleID, int semesterID, int schooljaarID) {

        int inschrijvingID = 0;
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select inschrijvingID from inschrijvingen where gebruikerID=? and moduleID=? and schooljaarID=? and semesterID= ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, gebruikerID);
            ps.setInt(2, moduleID);
            ps.setInt(3, schooljaarID);
            ps.setInt(4, semesterID);

            rs = ps.executeQuery();

            while (rs.next()) {
                inschrijvingID = rs.getInt("inschrijvingID");
            }
        } catch (SQLException e) {
           System.out.println(e.getErrorCode());
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }

        return inschrijvingID;

    }
  /* Sluit enkele variabelen en zet ze op null */
    private void sluitVariabelen(ResultSet rs, Statement statement, PreparedStatement ps, Connection currentCon) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                rs = null;
            } catch (Exception e) {
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                statement = null;
            } catch (Exception e) {
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                ps = null;
            } catch (Exception e) {
            }
        }
        if (currentCon != null) {
            try {
                currentCon.close();
            } catch (Exception e) {
            }
            try {
                currentCon = null;
            } catch (Exception e) {
            }
        }
    }

    
}


