package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Deze klasse bevat alle uitwisseling met de database
 *
 * @author gil-_
 */
public class SemesterDAO {

    public ArrayList<Semester> semestersLaden() {

        ArrayList<Semester> semesters = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM semesters;";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Semester semester = new Semester();
                semester.setSemsterID(rs.getInt("semesterID"));
                semester.setSemester(rs.getString("semester"));
                semesters.add(semester);
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
        return semesters;
    }

    public int laadSemesterID(String semesterNaam) {

        int semesterID = 0;
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT semesters.semesterID, semesters.semester from semesters"
            + "WHERE semester = ?";

            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setString(1, semesterNaam);
            rs = ps.executeQuery();

            while (rs.next()) {

                semesterID = rs.getInt(semesterID);

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
        return semesterID;
    }

}
