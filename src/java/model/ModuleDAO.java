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
public class ModuleDAO {
    
     private void ModuleAanmaken(Module module) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "INSERT INTO Modules "
                + "(moduleID,opleidingID,naam) VALUES (?,?,?)";

        //opslaan in database
        try {
            PreparedStatement statement = connectie.prepareStatement(sql);
            statement.setInt(1, module.getmoduleID());
            statement.setInt(2, module.getopleidingID());
            statement.setString(3, module.getnaam());
            statement.execute();
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }
    }

    private void ModuleAanpassen(Module module) {

        //connectie maken met database
        Connection connectie = null;
        connectie = ConnectionManager.getConnection();

        //insertquery
        String sql = "UPDATE Modules "
                + " set naam =  " + module.getnaam() + ", opleidingID= "
                + module.getopleidingID() + " where moduleID= "
                + module.getmoduleID();

        //opslaan in database
        try {
            Statement statement = connectie.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }

    }

    private void ModuleVerwijderen(Module module) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "DELETE FROM Modules "
                + " where moduleID= " + module.getmoduleID();

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