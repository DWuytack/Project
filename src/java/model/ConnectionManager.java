/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dirk
 */
public class ConnectionManager {
  static Connection con;

    public static Connection getConnection() {

        try {

            Class.forName("org.mariadb.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mariadb://172.16.20.193:3306/project", "root", "12345");
            con = DriverManager.getConnection("jdbc:mariadb://192.168.10.250:3307/project", "project", "Testing100");
            //con = DriverManager.getConnection("jdbc:mariadb://localhost:3307/project", "project", "12345");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;

    }
}
