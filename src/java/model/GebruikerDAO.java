/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dirk
 */
public class GebruikerDAO {

    public Gebruiker login(Gebruiker gebruiker) {
        Connection currentCon = null;
        ResultSet rs = null;

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
        } catch (SQLException ex) {
            System.out.println("Inloggen niet gelukt! : Volgende fout heeft zich voorgedaan: " + ex);
        } //some exception handling
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    
                }

                currentCon = null;
            }
        }

        return gebruiker;

    }

     public ArrayList<Gebruiker> cursistenLaden() {
      
         
         
         
         return null;
     }
    
    
    
    public void gebruikerAanmaken(Gebruiker gebruiker) {

        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO gebruiker(rolID, voornaam, achternaam, geboortedatum, email, login, paswoord) VALUES(3,?,?,?,?,?,MD5(?));";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "voornaam");
            ps.setString(2, "achternaam");
            ps.setString(3, "geboortedatum");
            ps.setString(4, "email");
            ps.setString(5, "login");
            ps.setString(6, "paswoord");
            ps.executeQuery(sql);

            

        } catch (SQLException ex) {
            
            
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
                } catch (SQLException e) {
                    
                }

                currentCon = null;
            }
        }
    }

    public Gebruiker cursistVerwijderen(Gebruiker gebruiker) {
        Connection connectie = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE g FROM gebruiker g inner join rol on g.rolID = rol.rolID WHERE rol = 'cursist' AND voornaam = ? AND achternaam = ?;";

        try {
            connectie = ConnectionManager.getConnection();
            ps = connectie.prepareStatement(sql);

            ps.setString(1, "voornaam");
            ps.setString(2, "achternaam");
            ps.executeQuery(sql);

           

        } catch (SQLException ex) {
            
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
                } catch (SQLException e) {
                   
                }
                ps = null;
            }

            if (connectie != null) {
                try {
                    connectie.close();
                } catch (SQLException e) {
                    
                }

                connectie = null;
            }
        }
        return gebruiker;
    }

    public Gebruiker cursistAanpassen(Gebruiker gebruiker) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE gebruiker(rolID, voornaam, achternaam, geboortedatum, email) VALUES(3,?,?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "voornaam");
            ps.setString(2, "achternaam");
            ps.setString(3, "geboorteDatum");
            ps.setString(4, "email");
            ps.executeQuery(sql);

            

        } catch (SQLException ex) {
            
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
                } catch (SQLException e) {
                    
                }
                ps = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (SQLException e) {
                    
                }

                currentCon = null;
            }
        }
        return gebruiker;
    }

    public ArrayList<Gebruiker> cursistenLaden() {

        ArrayList<Gebruiker> gebruiker = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String s = "SELECT * FROM gebruiker";

            rs = statement.executeQuery(s);

            while (rs.next()) {
                rs.getString(3);
                rs.getArray(4);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    
                }
                rs = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                    
                }

                currentCon = null;
            }
        }
        return gebruiker;
    }
}
