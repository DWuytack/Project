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
 * @author Aaron
 */
public class CursistenInschrijvenDAO {

    public ArrayList<CursistenInschrijven> cursistenLaden() {

        ArrayList<CursistenInschrijven> cursisten = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select gebruikerID, voornaam, achternaam, login, email, geboortedatum, rol from gebruikers inner join rollen  on gebruikers.rolID= rollen.rolID order by achternaam";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                CursistenInschrijven cursist = new CursistenInschrijven();
                cursist.setGebruikerID(rs.getInt("gebruikerID"));
                cursist.setVoorNaam(rs.getString("voornaam"));
                cursist.setAchternaam(rs.getString("achternaam"));
                cursist.setEmail(rs.getString("email"));
                cursisten.add(cursist);

            }

        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, statement, null, currentCon);

        }
        return cursisten;
    }

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

    public ArrayList<CursistenInschrijven> cursistenZoeken(String zoekterm) {

        ArrayList<CursistenInschrijven> cursisten = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM gebruikers INNER JOIN rollen on gebruikers.rolID = rollen.rolID WHERE gebruikers.voornaam LIKE ? OR gebruikers.achternaam LIKE ? order by achternaam";

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, "%" + zoekterm + "%");
            ps.setString(2, "%" + zoekterm + "%");

            rs = ps.executeQuery();

            while (rs.next()) {

                CursistenInschrijven cursist = new CursistenInschrijven();
                cursist.setGebruikerID(rs.getInt("gebruikerID"));
                cursist.setVoorNaam(rs.getString("voornaam"));
                cursist.setAchternaam(rs.getString("achternaam"));
                cursist.setEmail(rs.getString("email"));
                cursisten.add(cursist);

            }
        } catch (Exception e) {
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return cursisten;
    }

}
