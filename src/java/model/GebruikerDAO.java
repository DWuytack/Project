/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Dirk
 */
public class GebruikerDAO {

    static Connection currentCon = null;
    static ResultSet rs = null;

    public static Gebruiker login(Gebruiker gebruiker) {

        //preparing some objects for connection 
        Statement stmt = null;

        String login = gebruiker.getLogin();
        String paswoord = gebruiker.getPaswoord();

        String searchQuery
                = "select Gebruiker.*, Rol.rol from Gebruiker"
                + " inner join Rol on Gebruiker.rolID= Rol.rolID "
                + "where login='" + login + "' AND wachtwoord=md5('" + paswoord + "')";
        try {
            //connectie met onze database
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // Bestaaat de gebruiker in de database?
            if (!more) {
                //gebruiker bestaat niet
                gebruiker.setGeldig(false);
            } else if (more) {
                //gebruiker bestaat
                int gebruikerID = rs.getInt("gebruikerID");
                String voornaam = rs.getString("voornaam");
                String achternaam = rs.getString("achternaam");
                String rol = rs.getString("rol");
                Date geboorteDatum = rs.getDate("geboorteDatum");
                String email = rs.getString("email");

                gebruiker.setVoorNaam(voornaam);
                gebruiker.setAchternaam(achternaam);
                gebruiker.setGebruikerID(gebruikerID);
                gebruiker.setRol(rol);
                gebruiker.setGeboorteDatum(geboorteDatum);
                gebruiker.setEmail(email);
                gebruiker.setGeldig(true);
            }
        } catch (Exception ex) {
            System.out.println("Inloggen niet gelukt! : Volgende fout heeft zich voorgedaan: " + ex);
        } //some exception handling
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    System.out.println("Log In failed: An Exception has occurred! " + e);
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    System.out.println("Log In failed: An Exception has occurred! " + e);
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    System.out.println("Log In failed: An Exception has occurred! " + e);
                }

                currentCon = null;
            }
        }

        return gebruiker;

    }
}
