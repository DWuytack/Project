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
public class EvaluatieFormulierDAO {

    public void bewaarFormulier(EvaluatieFormulier formulier) {

        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO evaluatieformulieren(inschrijvingID, lesnrID, datum) VALUES(?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, formulier.getInschrijvingID());
            ps.setInt(2, formulier.getLesnrID());
            ps.setString(3, formulier.getDatum());
            ps.executeUpdate();

        } catch (SQLException ex) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
    }

    public int laadformulierID(EvaluatieFormulier formulier) {

        int evaluatieFormulierID = 0;
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select evaluatieformID from evaluatieformulieren where inschrijvingID=? and lesnrID=? and datum=?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, formulier.getInschrijvingID());
            ps.setInt(2, formulier.getLesnrID());
            ps.setString(3, formulier.getDatum());
            rs = ps.executeQuery();

            while (rs.next()) {
                evaluatieFormulierID = rs.getInt("evaluatieformID");
            }
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }

        return evaluatieFormulierID;

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
