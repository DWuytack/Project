package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ScoreDAO(Score Data Access Object) is een klasse voor alle handelingen in de
 * database betreffend Scores.
 *
 * @author gil-_
 */
// Laden van score met query
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

    public ArrayList<Score> cursistScore(int moduleID, int gebruikerID) {
        ArrayList<Score> cursistScore = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT doelstellingen.naam, avg(beoordelingssoorten.waarde) AS score FROM doelstellingen_taken "
                + "INNER JOIN doelstellingen ON doelstellingen_taken.doelstellingID = doelstellingen.doelstellingID "
                + "INNER JOIN taken ON doelstellingen_taken.taakID = taken.taakID "
                + "LEFT JOIN evalform_scores ON taken.taakID = evalform_scores.taakID "
                + "INNER JOIN beoordelingssoorten ON evalform_scores.beoordelingssoortID = beoordelingssoorten.beoordelingssoortID "
                + "INNER JOIN evaluatieformulieren ON evalform_scores.evaluatieformID = evaluatieformulieren.evaluatieformID "
                + "INNER JOIN inschrijvingen ON evaluatieformulieren.inschrijvingID = inschrijvingen.inschrijvingID "
                + "INNER JOIN gebruikers ON inschrijvingen.gebruikerID = gebruikers.gebruikerID "
                + "WHERE gebruikers.gebruikerID = ? AND modules.moduleID = ? "
                + "GROUP BY doelstellingen.naam";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, gebruikerID);
            ps.setInt(2, moduleID);

            rs = ps.executeQuery();

            while (rs.next()) {
                Score score = new Score();
                score.setVoornaam(rs.getString("naam"));
                score.setScore(rs.getDouble("score"));
                cursistScore.add(score);

            }
        } catch (SQLException e) {

        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return cursistScore;
    }
}
