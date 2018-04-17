package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *  Deze klasse bevat alle uitwisseling met de database
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
}
