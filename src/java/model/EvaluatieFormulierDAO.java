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

        String sql = "INSERT INTO evaluatieformulieren(evaluatieformNaam,inschrijvingID,moduleID,lesnrID,schooljaarID,semesterID) VALUES(?,?,?,?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setString(1, formulier.getNaam());
            ps.setInt(2, formulier.getInschrijvingID());
            ps.setInt(3, formulier.getModuleID());
            ps.setInt(4, formulier.getLesnrID());
            ps.setInt(5, formulier.getSchooljaarID());
            ps.setInt(6, formulier.getSemesterID());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
    }

    public int laadformulierID(EvaluatieFormulier formulier) {

        int evaluatieFormulierID = 0;
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select evaluatieformID from evaluatieformulieren where moduleID=? and inschrijvingID=? and lesnrID=? and schooljaarID=? and semesterID=?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, formulier.getModuleID());
            ps.setInt(2, formulier.getInschrijvingID());
            ps.setInt(3, formulier.getLesnrID());
            ps.setInt(4, formulier.getSchooljaarID());
            ps.setInt(5, formulier.getSemesterID());
            rs = ps.executeQuery();

            while (rs.next()) {
                evaluatieFormulierID = rs.getInt("evaluatieformID");
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
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

    public void saveDoelstellingScore(int evaluatieFormID, int taakID, int doelstellingID, int scoreID) {

        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into evalform_scores (evaluatieFormID, taakID, doelstellingID,beoordelingssoortID) VALUES(?,?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, evaluatieFormID);
            ps.setInt(2, taakID);
            ps.setInt(3, doelstellingID);
            ps.setInt(4, scoreID);
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getErrorCode());

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
    }

    public int checkFormulier(String formuliernaam) {

        int evaluatieFormulierID = 0;
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select evaluatieformID from evaluatieformulieren where evaluatieformNaam=?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, formuliernaam);
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

    public void saveComment(int evaluatieFormID, int taakID, String commentaar) {

        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into evalform_comments (evaluatieFormID, taakID, commentaar) VALUES(?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, evaluatieFormID);
            ps.setInt(2, taakID);
            ps.setString(3, commentaar);
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getErrorCode());

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }

    }

    public void wisFormulier(int evaluatieFormulierID) {

        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from evaluatieformulieren where evaluatieformID=?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, evaluatieFormulierID);
            rs = ps.executeQuery();

        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }

        ps = null;
        rs = null;
        sql = "delete from evalform_scores where evaluatieformID=?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, evaluatieFormulierID);
            rs = ps.executeQuery();

        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        
         ps = null;
        rs = null;
        sql = "delete from evalform_comments where evaluatieformID=?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, evaluatieFormulierID);
            rs = ps.executeQuery();

        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }

    }

}
