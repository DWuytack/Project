package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
}
