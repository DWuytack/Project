package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Aaron Deze klasse is verantwoordelijk voor het ophalen, wijzigen en
 * verwijderen van taken in de database.
 */
public class TaakDAO {

    private void TaakAanmaken(Taak taak) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "INSERT INTO Taken "
                + "(naam,beschrijving) VALUES (?,?)";

        //opslaan in database
        try {
            PreparedStatement statement = connectie.prepareStatement(sql);
            statement.setString(1, taak.getNaam());
            statement.setString(2, taak.getBeschrijving());
            statement.execute();
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }
    }

    private void TaakAanpassen(Taak taak) {

        //connectie maken met database
        Connection connectie = null;
        connectie = ConnectionManager.getConnection();

        //insertquery
        String sql = "UPDATE Taken "
                + " set naam =  " + taak.getNaam() + ", beschrijving= "
                + taak.getBeschrijving() + " where taakID= "
                + taak.getTaakID();

        //opslaan in database
        try {
            Statement statement = connectie.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }

    }

    private void TaakVerwijderen(Taak taak) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "DELETE FROM Taken "
                + " where taakID= " + taak.getTaakID();

        //opslaan in database
        try {
            PreparedStatement statement = connectie.prepareStatement(sql);
            statement.execute();
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }

    }
    
        public ArrayList<Taak> takenLaden() {
        Statement stmt;
        
        Connection currentCon;
        ResultSet rs;
        
        ArrayList<Taak> taken = new ArrayList<>();
        
        String selectTaken = "SELECT * FROM Taken;";
        
        try {
            currentCon = ConnectionManager.getConnection();
            stmt =  currentCon.createStatement();
            rs = stmt.executeQuery(selectTaken);
            
            while(rs.next()) {
                Taak taak = new Taak();
                taak.setTaakID(rs.getInt("taakID"));
                taak.setNaam(rs.getString("naam"));
                taak.setBeschrijving(rs.getString("beschrijving"));
                
                taken.add(taak);
            }
            
            currentCon.close();
            
            return taken;
            
        } catch(Exception e) {
            System.out.println("Error - Kon geen taken laden.");
            return null;
        }
    }
    
}
