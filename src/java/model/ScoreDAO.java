package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ScoreDAO(Score Data Access Object) is een klasse voor alle handelingen in de
 * database betreffende Scores.
 *
 * @author gil-_
 */
public class ScoreDAO {
    
    public ArrayList<Score> klassikaleScore(int schooljaarID, int semesterID, int moduleID) {
        ArrayList<Score> cursistenScores = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT gebruikers.voornaam, gebruikers.achternaam, AVG(beoordelingssoorten.waarde) as score FROM evaluatieformulieren\n"
                + "INNER JOIN inschrijvingen ON evaluatieformulieren.inschrijvingID = inschrijvingen.inschrijvingID\n"
                + "INNER JOIN gebruikers ON inschrijvingen.gebruikerID = gebruikers.gebruikerID\n"
                + "INNER JOIN evalform_scores ON evaluatieformulieren.evaluatieformID = evalform_scores.evaluatieformID\n"
                + "INNER JOIN beoordelingssoorten ON evalform_scores.beoordelingssoortID = beoordelingssoorten.beoordelingssoortID\n"
                + "WHERE inschrijvingen.moduleID=? "
                + "AND inschrijvingen.semesterID=? "
                + "AND inschrijvingen.schooljaarID=? "
                + "GROUP BY gebruikers.gebruikerID;";
        
        try {
            //connectie met de database
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
                score.setScore(rs.getDouble("score"));
                cursistenScores.add(score);
                
            }
        } catch (SQLException e) {
            
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return cursistenScores;
    }
    
    public ArrayList<ScoreOverzicht> geefScoresVoorInschrijvingID(int inschrijvingID, int moduleID) {
        
        ArrayList<ScoreOverzicht> scores = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        
        String sql = "CREATE TEMPORARY TABLE temp1 ENGINE=MEMORY  AS (select evalform_scores.taakID, evalform_scores.doelstellingID,"
                + " evalform_scores.beoordelingssoortID, beoordelingssoorten.naam,beoordelingssoorten.waarde  from evalform_scores\n"
                + " inner join beoordelingssoorten on beoordelingssoorten.beoordelingssoortID=evalform_scores.beoordelingssoortID\n"
                + " where evalform_scores.evaluatieformID IN\n"
                + " (select evaluatieformulieren.evaluatieformID from evaluatieformulieren \n"
                + " where evaluatieformulieren.inschrijvingID=?)); \n";
        
        String sql2 = "select doelstellingen.doelstellingID, doelstellingen.naam as doelstellingnaam,doelstellingen.kerndoelstelling, doelstellingen_taken.taakID, taken.naam as taaknaam,temp1.naam as beoordelingsnaam,temp1.waarde from doelstellingen\n"
                + " left join doelstellingen_taken on doelstellingen_taken.doelstellingID = doelstellingen.doelstellingID\n"
                + " left join taken on taken.taakID = doelstellingen_taken.taakID\n"
                + " left join temp1 on temp1.doelstellingID = doelstellingen.doelstellingID and temp1.taakID=doelstellingen_taken.taakID\n"
                + " where doelstellingen.doelstellingID IN\n"
                + " (select doelstellingID from doelstellingen_modules\n"
                + " where moduleID=?)\n"
                + " order by doelstellingen.doelstellingID";
        
        try {
            //connectie met de database
            currentCon = ConnectionManager.getConnection();
            
            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, inschrijvingID);
            ps.executeQuery();
            
            ps2 = currentCon.prepareStatement(sql2);
            ps2.setInt(1, moduleID);
            rs = ps2.executeQuery();
            
            while (rs.next()) {
                ScoreOverzicht score = new ScoreOverzicht();
                score.setDoelstellingID(rs.getInt("doelstellingID"));
                  score.setTaakID(rs.getInt("taakID"));
                score.setTaaknaam(rs.getString("taaknaam"));
                score.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
                score.setScore(rs.getString("beoordelingsnaam"));
                score.setWaarde(rs.getDouble("waarde"));
                  score.setDoelstellingnaam(rs.getString("doelstellingnaam"));
            
                scores.add(score);
            }            
            
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return scores;
    }
}
