package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Studiegebied;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ewout Phlips
 */
public class StudiegebiedDAO {

    public ArrayList<Studiegebied> studiegebiedenLaden() {

        ArrayList<Studiegebied> studiegebieden = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select * from studiegebieden";

            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Studiegebied studiegebied = new Studiegebied();
                studiegebied.setStudiegebiedID(rs.getInt("studiegebiedID"));
                studiegebied.setNaam(rs.getString("naam"));
                studiegebieden.add(studiegebied);
            }

        } catch (Exception e) {
        } finally {
            sluitVariabelen(rs, statement, null, currentCon);
        }
        return studiegebieden;
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



