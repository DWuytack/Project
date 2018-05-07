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
 * @author CURSIST
 */
public class SchooljarenDAO {

    public ArrayList<Schooljaar> schooljarenLaden() {

        ArrayList<Schooljaar> schooljaren = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM schooljaren;";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Schooljaar schooljaar = new Schooljaar();
                schooljaar.setSchooljaarID(rs.getInt("schooljaarID"));
                schooljaar.setSchooljaar(rs.getString("schooljaar"));
                schooljaren.add(schooljaar);
            }
        } catch (SQLException e) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {

                }

                statement = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {

                }

                currentCon = null;
            }

        }
        return schooljaren;
    }

    public int laadSchooljaarID(String schooljaarNaam) {

        int schooljaarID = 0;
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT schooljaren.schooljaarID, schooljaren.schooljaar FROM schooljaren"
                    + "where schooljaar = ?";

            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setString(1, schooljaarNaam);
            rs = ps.executeQuery();

            while (rs.next()) {

                schooljaarID = rs.getInt(schooljaarID);

            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {

                }

                ps = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {

                }

                currentCon = null;
            }

        }
        return schooljaarID;
    }

}
