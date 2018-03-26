/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */  
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Keanu hier komen sql query's
 */
public class ScoreDAO {

    public static ArrayList<Score> scoreLaden() {
        ArrayList<Score> beoordelingssoorten = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM beoordelingssoorten ";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Score score = new Score();
                score.setNaam(rs.getString("naam"));
                score.setBeschrijving(rs.getString("beschrijving"));
                score.setWaarde(rs.getInt("waarde"));


                beoordelingssoorten.add(score);
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
        return beoordelingssoorten;
    }

      private void schooljarenLaden(Gebruiker gebruiker) {
        throw new UnsupportedOperationException("Schrijf code.");
    }
    
    private void scoreAanmaken(Gebruiker gebruiker) {
        throw new UnsupportedOperationException("Schrijf code.");
    }

    private void scoreAanpassen(Gebruiker gebruiker) {
        throw new UnsupportedOperationException("Schrijf code.");
    }

    private void scoreVerwijderen(Gebruiker gebruiker) {
        throw new UnsupportedOperationException("Schrijf code.");
    }
}
