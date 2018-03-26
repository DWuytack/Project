/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    
     public ArrayList<Module> modulesLaden( int bladz) {
         
        ArrayList<Module> modules = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM Modules;";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                Module module = new Module();
                module.setmoduleID(rs.getInt("moduleID"));
                module.setopleidingID(rs.getInt("opleidingID"));
                module.setnaam(rs.getString("naam"));
              
                
                modules.add(module);
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
        return modules;
    }

    public int geefAantalGebruikers() {
        
         Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;
        int aantalModules = 0;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select * from Modules";
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);
            while (rs.next()) {
                aantalModules++;
                rs.getString(2);
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
        return aantalModules;
        
    }

}
