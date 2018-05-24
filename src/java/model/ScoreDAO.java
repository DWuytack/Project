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
    
    public ArrayList<Score> klassikaleScore(int schooljaarID, int semesterID, int moduleID) {
        ArrayList<Score> cursistenScores = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT gebruikers.voornaam, gebruikers.achternaam, avg(beoordelingssoorten.waarde) as waarde from doelstellingen_inschrijvingen " +
        "INNER JOIN beoordelingssoorten ON doelstellingen_inschrijvingen.beoordelingssoortID = beoordelingssoorten.beoordelingssoortID " +
        "INNER JOIN inschrijvingen ON doelstellingen_inschrijvingen.inschrijvingID = inschrijvingen.inschrijvingID " +
        "INNER JOIN gebruikers ON inschrijvingen.gebruikerID = gebruikers.gebruikerID " +
        "WHERE inschrijvingen.moduleID=? " +
        "AND inschrijvingen.semesterID=? " +
        "AND inschrijvingen.schooljaarID=? " +
        "GROUP BY gebruikers.gebruikerID;";
        
        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, moduleID);
            ps.setInt(2, semesterID);
            ps.setInt(3, schooljaarID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Score score = new Score();
                score.setVoornaam(rs.getString("voornaam"));
                score.setAchternaam(rs.getString("achternaam"));
                score.setWaarde(rs.getDouble("waarde"));
                cursistenScores.add(score);
                               
            }
        } catch (SQLException e) {

        } finally {
            Utilities.sluitVariabelen(ps, rs , currentCon);
        }
        return cursistenScores;
    }
}
