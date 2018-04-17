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
    
     public ArrayList<Opleiding> opleidingenLaden() {
         
        ArrayList<Opleiding> opleidingen = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM Opleidingen;";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                Opleiding opleiding = new Opleiding();
                opleiding.setopleidingID(rs.getInt("opleidingID"));
                opleiding.setnaam(rs.getString("naam"));
              
                
                opleidingen.add(opleiding);
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
        return opleidingen;
    }
    
    public ArrayList<Opleiding> opleidingenLaden(int studiegebiedID) {
         
        ArrayList<Opleiding> opleidingen = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT studiegebieden_opleidingen.opleidingID, opleidingen.naam FROM studiegebieden_opleidingen Inner join opleidingen on studiegebieden_opleidingen.opleidingID = opleidingen.opleidingID Where studiegebiedID = ? ";
            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, studiegebiedID);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Opleiding opleiding = new Opleiding();
                opleiding.setopleidingID(rs.getInt("opleidingID"));
                opleiding.setnaam(rs.getString("naam"));
              
                
                opleidingen.add(opleiding);
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
        return opleidingen;
    }
}
