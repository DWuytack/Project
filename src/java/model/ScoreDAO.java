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

    public ArrayList<ScoreOverzicht> geefScoresVoorInschrijvingID(int inschrijvingID) {

        ArrayList<ScoreOverzicht> scores = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select taakID,doelstellingID, beoordelingssoorten.naam as score,beoordelingssoorten.waarde \n"
                + " from evalform_scores \n"
                + " inner join beoordelingssoorten on evalform_scores.beoordelingssoortID = beoordelingssoorten.beoordelingssoortID\n"
                + " where\n"
                + " evalform_scores.evaluatieformID IN\n"
                + " (select evaluatieformID from evaluatieformulieren\n"
                + " where inschrijvingID=?)";

        try {
            //connectie met de database
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, inschrijvingID);

            rs = ps.executeQuery();

            while (rs.next()) {
                ScoreOverzicht scoreOverzicht = new ScoreOverzicht();
                scoreOverzicht.setTaakID(rs.getInt("taakID"));
                scoreOverzicht.setDoelstellingID(rs.getInt("doelstellingID"));
                scoreOverzicht.setWaarde(rs.getDouble("waarde"));
                scores.add(scoreOverzicht);

            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return scores;
    }
}
