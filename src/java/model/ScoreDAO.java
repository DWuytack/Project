package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ScoreDAO(Score Data Access Object) is een klasse voor alle
 * handelingen in de database betreffend Scores.
 * 
 * @author gil-_
 */
public class ScoreDAO {
    
    public ArrayList<String> klassikaleScore(int schooljaarID, int semesterID, int moduleID) {
        ArrayList<String> cursistenScore = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT gebruikers.voornaam, gebruikers.achternaam, avg(beoordelingssoorten.waarde) as totaal_score from doelstellingen_inschrijvingen\n" +
        "INNER JOIN beoordelingssoorten ON doelstellingen_inschrijvingen.beoordelingssoortID = beoordelingssoorten.beoordelingssoortID\n" +
        "INNER JOIN inschrijvingen ON doelstellingen_inschrijvingen.inschrijvingID = inschrijvingen.inschrijvingID\n" +
        "INNER JOIN gebruikers ON inschrijvingen.gebruikerID = gebruikers.gebruikerID\n" +
        "WHERE inschrijvingen.moduleID=?\n" +
        "AND inschrijvingen.semesterID=?\n" +
        "AND inschrijvingen.schooljaarID=?\n" +
        "GROUP BY gebruikers.gebruikerID;";
        
        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, moduleID);
            ps.setInt(2, semesterID);
            ps.setInt(3, schooljaarID);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                cursistenScore.add(rs.getString("voornaam") + " " + rs.getString("achternaam") + " " + rs.getString("totaal_score"));
                
            }
        } catch (SQLException e) {

        } finally {
            Utilities.sluitVariabelen(ps, rs , currentCon);
        }
        return cursistenScore;
    }
}
