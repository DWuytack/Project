/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Davino
 */
public class OpleidingDAO {
    
     private void OpleidingToevoegen(Opleiding opleiding) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "INSERT INTO Opleidingen "
                + "(opleidingID,naam) VALUES (?,?)";

        //opslaan in database
        try {
            PreparedStatement statement = connectie.prepareStatement(sql);
            statement.setInt(1, opleiding.getopleidingID());
            statement.setString(2, opleiding.getnaam());
            statement.execute();
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }
    }

    private void OpleidingAanpassen(Opleiding opleiding) {

        //connectie maken met database
        Connection connectie = null;
        connectie = ConnectionManager.getConnection();

        //insertquery
        String sql = "UPDATE Opleidingen "
                + " set naam =  " + opleiding.getnaam() 
                + " where opleidingID= "
                + opleiding.getopleidingID();

        //opslaan in database
        try {
            Statement statement = connectie.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }

    }

    private void OpleidingVerwijderen(Opleiding opleiding) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "DELETE FROM Opleidingen "
                + " where opleidingID= " + opleiding.getopleidingID();

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
