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
                = "select gebruikers.*, rollen.rol from gebruikers"
                + " inner join rollen on gebruikers.rolID= rollen.rolID "
                + "where login='" + login + "' AND wachtwoord=md5('" + paswoord + "') order by gebruikers.achternaam;";
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
                String geboorteDatum = rs.getString("geboorteDatum");
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
            sluitVariabelen(rs, null, null, currentCon);
        }

        return gebruiker;

    }

    public ArrayList<Gebruiker> cursistenLaden(int bladz) {
        ArrayList<Gebruiker> cursistenLijst = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select gebruikerID, voornaam, achternaam, login, email, geboortedatum, rol from gebruikers inner join rollen  on gebruikers.rolID= rollen.rolID order by achternaam";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);
            int recordStart = (bladz * Instellingen.AANTAL_RECORDS_PER_PAGE) - (Instellingen.AANTAL_RECORDS_PER_PAGE - 1);
            int recordEinde = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
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
                    gebruiker.setGeboorteDatum(rs.getString("geboortedatum"));
                    gebruiker.setEmail(rs.getString("email"));
                    cursistenLijst.add(gebruiker);

                }
            }
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, statement, null, currentCon);

        }
        return cursistenLijst;
    }

    public ArrayList<Gebruiker> cursistenLaden1() {
        ArrayList<Gebruiker> cursistenLijst = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select gebruikerID, voornaam, achternaam, login, email, geboortedatum, rol from gebruikers inner join rollen on gebruikers.rolID= rollen.rolID where gebruikers.rolID = '3'";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {

                Gebruiker gebruiker = new Gebruiker();
                gebruiker.setGebruikerID(rs.getInt("gebruikerID"));
                gebruiker.setVoorNaam(rs.getString("voornaam"));
                gebruiker.setAchternaam(rs.getString("achternaam"));
                gebruiker.setLogin(rs.getString("login"));
                gebruiker.setRol(rs.getString("rol"));
                gebruiker.setGeboorteDatum(rs.getString("geboortedatum"));
                gebruiker.setEmail(rs.getString("email"));
                cursistenLijst.add(gebruiker);

            }
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, statement, null, currentCon);

        }
        return cursistenLijst;
    }

    public ArrayList<Gebruiker> gebruikersLaden(int bladz) {

        ArrayList<Gebruiker> gebruikers = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select gebruikerID, voornaam, achternaam, login, email, geboortedatum, rol from gebruikers inner join rollen  on gebruikers.rolID= rollen.rolID order by achternaam";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);
            int recordStart = (bladz * Instellingen.AANTAL_RECORDS_PER_PAGE) - (Instellingen.AANTAL_RECORDS_PER_PAGE - 1);
            int recordEinde = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
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
                    gebruiker.setGeboorteDatum(rs.getString("geboortedatum"));
                    gebruiker.setEmail(rs.getString("email"));
                    gebruikers.add(gebruiker);
                }
            }

        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, statement, null, currentCon);

        }
        return gebruikers;
    }

    public int geefAantalGebruikers() {

        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;
        int aantalGebruikers = 0;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT COUNT(*) FROM gebruikers";
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);
            while (rs.next()) {
                aantalGebruikers = rs.getInt(1);
            }

        } catch (SQLException e) {
        } finally {
            sluitVariabelen(rs, statement, null, currentCon);

        }
        return aantalGebruikers;
    }
//oi ewout kan je ervoor zorgen dat er bij gebruikerservlet een case us voor gebruikerAanmaken?

    public void gebruikerAanmaken(Gebruiker gebruiker) {
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO gebruikers(voornaam, achternaam, rolID , geboortedatum, email, login, wachtwoord) VALUES(?,?,?,?,?,?,MD5(?))";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, gebruiker.getVoorNaam());
            ps.setString(2, gebruiker.getAchternaam());
            ps.setString(3, gebruiker.getRol());
            ps.setString(4, gebruiker.getGeboorteDatum());
            ps.setString(5, gebruiker.getEmail());
            ps.setString(6, gebruiker.getLogin());
            ps.setString(7, gebruiker.getPaswoord());
            ps.executeUpdate();

        } catch (SQLException ex) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);

        }
    }

    public void gebruikerAanpassen(int gebruikerID, Gebruiker gebruiker) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE gebruikers set gebruikers.voornaam=?,"
                + "gebruikers.achternaam=?, gebruikers.login=?, "
                + "gebruikers.rolID=?, gebruikers.email=?,"
                + "gebruikers.geboortedatum=? where gebruikers.gebruikerID=?";

        int rolId = 0;
        if (gebruiker.getRol().equals("admin")) {
            rolId = 1;
        }
        if (gebruiker.getRol().equals("leerkracht")) {
            rolId = 2;
        }
        if (gebruiker.getRol().equals("cursist")) {
            rolId = 3;
        }
        if (gebruiker.getRol().equals("secretariaat")) {
            rolId = 4;
        }

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setInt(4, rolId);
            ps.setString(3, gebruiker.getLogin());
            ps.setString(1, gebruiker.getVoorNaam());
            ps.setString(2, gebruiker.getAchternaam());
            ps.setString(6, gebruiker.getGeboorteDatum());
            ps.setString(5, gebruiker.getEmail());
            ps.setInt(7, gebruikerID);

            ps.executeUpdate();

        } catch (SQLException ex) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }

    }

    public ArrayList<Gebruiker> gebruikersZoeken(String zoekterm, int bladz) {

        ArrayList<Gebruiker> gebruikers = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int recordStart = (bladz * Instellingen.AANTAL_RECORDS_PER_PAGE) - (Instellingen.AANTAL_RECORDS_PER_PAGE - 1);
        int recordEinde = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
        int recordTeller = 0;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM gebruikers INNER JOIN rollen on gebruikers.rolID = rollen.rolID WHERE gebruikers.voornaam LIKE ? OR gebruikers.achternaam LIKE ? order by achternaam";

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, "%" + zoekterm + "%");
            ps.setString(2, "%" + zoekterm + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                recordTeller++;

                if (recordTeller >= recordStart && recordTeller <= recordEinde) {
                    Gebruiker gebruiker = new Gebruiker();
                    gebruiker.setGebruikerID(rs.getInt("gebruikerID"));
                    gebruiker.setVoorNaam(rs.getString("voornaam"));
                    gebruiker.setAchternaam(rs.getString("achternaam"));
                    gebruiker.setLogin(rs.getString("login"));
                    gebruiker.setRol(rs.getString("rol"));
                    gebruiker.setGeboorteDatum(rs.getString("geboortedatum"));
                    gebruiker.setEmail(rs.getString("email"));
                    gebruikers.add(gebruiker);
                }
            }
        } catch (Exception e) {
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return gebruikers;
    }

    /* Sluit enkele variabelen en zet ze op null */
    private void sluitVariabelen(ResultSet rs, Statement statement, PreparedStatement ps, Connection currentCon) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                rs = null;
            } catch (Exception e) {
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                statement = null;
            } catch (Exception e) {
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                ps = null;
            } catch (Exception e) {
            }
        }
        if (currentCon != null) {
            try {
                currentCon.close();
            } catch (Exception e) {
            }
            try {
                currentCon = null;
            } catch (Exception e) {
            }
        }
    }

    public void gebruikerVerwijderen(int gebruikerID) {

        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE from gebruikers where gebruikers.gebruikerID = ?";
        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setInt(1, gebruikerID);
            ps.executeQuery();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        } finally {

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

    public ArrayList<Gebruiker> gebruikersLaden(int schooljaarID, int semesterID, int moduleID) {
        ArrayList<Gebruiker> gebruikers = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * from gebruikers"
                + " INNER JOIN inschrijvingen on gebruikers.gebruikerID = inschrijvingen.gebruikerID"
                + " WHERE inschrijvingen.moduleID=?"
                + " AND inschrijvingen.semesterID=?"
                + " AND inschrijvingen.schooljaarID=?";
        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, moduleID);
            ps.setInt(2, semesterID);
            ps.setInt(3, schooljaarID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Gebruiker gebruiker = new Gebruiker();
                gebruiker.setNaam(rs.getString("voornaam") + " " + rs.getString("achternaam"));
                gebruikers.add(gebruiker);
            }
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return gebruikers;
    }

}
