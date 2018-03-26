package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Keanu hier komen sql query's
 */
public class ScoreDAO {

    public ArrayList<Score> typeScoreLaden() {
        ArrayList<Score> beoordelingssoorten = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM beoordelingssoorten";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Score score = new Score();
                score.setBeoordelingssoortID(rs.getInt("BeoordelingssoortID"));
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
    
    public void typeScoreAanpassen(int beoordelingssoortID, Score score) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE beoordelingssoorten(beoordelingssoortID, naam, beschrijving, waarde) VALUES(?,?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "naam");
            ps.setString(2, "beschrijving");
            ps.setString(3, "waarde");
            ps.executeQuery();

        } catch (SQLException ex) {

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
                } catch (SQLException e) {

                }
                ps = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (SQLException e) {

                }

                currentCon = null;
            }
        }

    }

    public void schooljarenLaden(Gebruiker gebruiker) {
        throw new UnsupportedOperationException("Schrijf code.");
    }
    
    public void scoreAanmaken(Gebruiker gebruiker) {
        throw new UnsupportedOperationException("Schrijf code.");
    }

    public void scoreVerwijderen(Gebruiker gebruiker) {
        throw new UnsupportedOperationException("Schrijf code.");
    }
}
