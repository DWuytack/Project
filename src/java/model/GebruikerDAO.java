package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Deze klasse is verantwoordelijk voor alle gebruikersbewerkingen in de
 * database.
 *
 * @author Gil
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
                + "where login='" + login + "' AND wachtwoord=md5('" + paswoord + "') order by Gebruiker.achternaam;";
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

                gebruiker.setGebruikerID(gebruikerID);
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

        ArrayList<Gebruiker> cursisten = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM Gebruiker WHERE Gebruiker.rolID = 3";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Gebruiker gebruiker = new Gebruiker();
                gebruiker.setVoorNaam(rs.getString("voornaam"));
                gebruiker.setAchternaam(rs.getString("achternaam"));
                gebruiker.setGeboorteDatum(rs.getDate("geboortedatum"));
                gebruiker.setEmail(rs.getString("email"));

                cursisten.add(gebruiker);
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
        return cursisten;
    }

    public void cursistAanmaken(Gebruiker gebruiker) {
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

    public void cursistAanpassen(Gebruiker gebruiker) {
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

    }

    public void cursistVerwijderen(Gebruiker gebruiker) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE g FROM gebruiker g inner join rol on g.rolID = rol.rolID WHERE rol = 'cursist' AND voornaam = ? AND achternaam = ?;";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

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

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (SQLException e) {

                }

                currentCon = null;
            }
        }

    }

    public ArrayList<Gebruiker> gebruikersLaden(int bladz, int aantalPerBlz) {
       
        ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select gebruikerID,voornaam, achternaam, login,  email, geboortedatum, rol from Gebruiker inner join Rol  on Gebruiker.rolID= Rol.rolID order by achternaam";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);
            int recordStart = (bladz * aantalPerBlz) - aantalPerBlz-1;
            int recordEinde = bladz * aantalPerBlz;
            int recordTeller = 0;

            while (rs.next()) {
                recordTeller++;
                if (recordTeller >= recordStart && recordTeller <= recordEinde) {
                    Gebruiker gebruiker = new Gebruiker();
                    gebruiker.setGebruikerID(rs.getInt("gebruikerID"));
                    gebruiker.setVoorNaam(rs.getString("voornaam"));
                    gebruiker.setAchternaam(rs.getString("achternaam"));
                    gebruiker.setLogin(rs.getString("login"));
                    gebruiker.setRol(rs.getString("rol"));
                    gebruiker.setGeboorteDatum(rs.getDate("geboortedatum"));
                    gebruiker.setEmail(rs.getString("email"));
                    gebruikers.add(gebruiker);
                }
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
        return gebruikers;
    }

    public void gebruikerAanmaken() {
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO Gebruiker(voornaam, achternaam, rolID , geboortedatum, email, login, wachtwoord) VALUES(?,?,?,?,?,?,MD5(?))";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "voornaam");
            ps.setString(2, "achternaam");
            ps.setString(3, "rol");
            ps.setString(4, "geboortedatum");
            ps.setString(5, "email");
            ps.setString(6, "login");
            ps.setString(7, "wachtwoord");
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

    public void gebruikerAanpassen(Gebruiker gebruiker) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE gebruiker(rolID, voornaam, achternaam, geboortedatum, email) VALUES(?,?,?,?,?)";

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

    }

    public void gebruikerVerwijderen(Gebruiker gebruiker) {

    }

    public void gebruikerVerwijderen(int gebruikerID) {

        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE g FROM gebruiker g inner join rol on g.rolID = rol.rolID WHERE rol = 'leerkracht' AND voornaam = ? AND achternaam = ?;";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

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

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (SQLException e) {

                }

                currentCon = null;
            }
        }
    }
}
