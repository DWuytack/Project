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
    
    //laadt alle verschillende type scores.
    public ArrayList<Score> typeScoreLaden() {
        ArrayList<Score> typeScores = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT beoordelingssoortID, naam, beschrijving, waarde FROM Beoordelingssoorten";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Score typeScore = new Score();
                typeScore.setBeoordelingssoortID(rs.getInt("beoordelingssoortID"));
                typeScore.setNaam(rs.getString("naam"));
                typeScore.setBeschrijving(rs.getString("beschrijving"));
                typeScore.setWaarde(rs.getInt("waarde"));
                
                typeScores.add(typeScore);
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
        return typeScores;
    }

    // methode die het type score aanpast
    public void typeScoreAanpassen(int beoordelingssoortID, Score typeScore) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "UPDATE Beoordelingssoorten SET Beoordelingssoorten.naam = ? , Beoordelingssoorten.beschrijving = ? ,"
                    + " Beoordelingssoorten.waarde = ? WHERE Beoordelingssoorten.beoordelingssoortID = ?";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            
            ps.setString(1, typeScore.getNaam());
            ps.setString(2, typeScore.getBeschrijving());
            ps.setInt(3, typeScore.getWaarde());
            ps.setInt(4, beoordelingssoortID);
            ps.executeUpdate();

        } catch (SQLException ex) {

        } finally {            
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
    
    // methode die een type score verwijderd
    public void typeScoreVerwijderen(int beoordelingssoortID) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM Beoordelingssoorten WHERE Beoordelingssoorten.beoordelingssoortID = ?";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            
            ps.setInt(1, beoordelingssoortID);
            ps.executeUpdate();
        
        } catch (SQLException ex) {

        } finally {
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
    // methode die een type score toevoegd
    public void typeScoreToevoegen() {
        Connection currentCon = null;        
        PreparedStatement ps = null;

        String sql = "INSERT INTO Beoordelingssoorten(naam, beschrijving, waarde) VALUES(?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            
            ps.setString(1, "naam");
            ps.setString(2, "beschrijving");
            ps.setString(3, "waarde");
            ps.executeQuery(sql);
        
        } catch (SQLException ex) {

        } finally {
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
