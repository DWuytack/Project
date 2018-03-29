package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasse Doelstelling DataBase Access Object: Alles handeling in verband met
 * doelstellingen in de Database.
 *
 * @author Ewout Phlips
 */
public class DoelstellingDAO {

    public ArrayList<Doelstelling> doelstellingenLaden(int moduleID) {

        ArrayList<Doelstelling> doelstellingen = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from doelstellingen\n"
                    + "inner join modules on doelstellingen.moduleID=modules.moduleID\n"
                    + "where doelstellingen.moduleID=?";
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setInt(1, moduleID);
            ps.executeQuery(sql);

            while (rs.next()) {

                Doelstelling doelstelling = new Doelstelling();
                doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                doelstelling.setNaam(rs.getString("naam"));
                doelstelling.setBeschrijving(rs.getString("beschrijving"));
                doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
                doelstellingen.add(doelstelling);
            }

        } catch (Exception e) {
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return doelstellingen;
    }

    //Laad alle Doelstellingen uit de Database.
    public ArrayList<Doelstelling> doelstellingenLaden(int bladz, int moduleID) {

        ArrayList<Doelstelling> doelstellingen = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM doelstellingen";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);
            int recordStart = (bladz * Instellingen.AANTAL_RECORDS_PER_PAGE) - (Instellingen.AANTAL_RECORDS_PER_PAGE - 1);
            int recordEinde = bladz * Instellingen.AANTAL_RECORDS_PER_PAGE;
            int recordTeller = 0;

            while (rs.next()) {
                recordTeller++;

                if (recordTeller >= recordStart && recordTeller <= recordEinde) {
                    Doelstelling doelstelling = new Doelstelling();
                    doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                    doelstelling.setNaam(rs.getString("naam"));
                    doelstelling.setBeschrijving(rs.getString("beschrijving"));
                    doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
                    doelstellingen.add(doelstelling);
                }
            }

        } catch (Exception e) {
        } finally {
            sluitVariabelen(rs, statement, null, currentCon);
        }
        return doelstellingen;
    }

    /* Geeft het aantal Doelstelligen weer. */
    public int geefAantalDoelstellingen() {

        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;
        int aantalDoelstellingen = 0;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT COUNT(*) FROM doelstellingen";
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);
            while (rs.next()) {
                aantalDoelstellingen = rs.getInt(1);
            }

        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, statement, null, currentCon);
        }
        return aantalDoelstellingen;
    }

    /* Creër een nieuwe doelstelling. */
    public void doelstellingAanmaken(Doelstelling doelstelling) {
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO doelstellingen (naam, beschrijving, kerndoelstelling)\n"
                + "VALUES (?, ?, ?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "naam");
            ps.setString(2, "beschrijving");
            if (doelstelling.getKerndoelstelling()) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 0);
            }
            ps.executeQuery();

        } catch (SQLException ex) {
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
    }

    /* Pas een doelstelling aan. */
    public void doelstellingAanpassen(Doelstelling doelstelling) {

        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE doelstellingen SET naam = ?, beschrijving = ?, kerndoelstelling = ? WHERE doelstellingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, doelstelling.getNaam());
            ps.setString(2, doelstelling.getBeschrijving());
            if (doelstelling.getKerndoelstelling()) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 0);
            }
            ps.setInt(4, doelstelling.getDoelstellingID());
            ps.executeQuery(sql);

        } catch (SQLException ex) {
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
    }

    /* Verwijdert een doelstelling. */
    public void doelstellingVerwijderen(int doelstellingID) {

        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE FROM Doelstellingen WHERE Doelstellingen.doelstellingID = ?";
        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setInt(1, doelstellingID);
            ps.executeQuery();

        } catch (SQLException ex) {
        } finally {
            sluitVariabelen(null, null, ps, currentCon);
        }
    }

    /* Geeft doelstellingen weer waar een bepaalde string in zit. */
    public ArrayList<Doelstelling> doelstellingenZoeken(String zoekterm) {

        ArrayList<Doelstelling> doelstellingen = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM doelstellingen WHERE doelstellingen.naam LIKE ? OR doelstellingen.beschrijving LIKE ?";

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, "%" + zoekterm + "%");
            ps.setString(2, "%" + zoekterm + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Doelstelling doelstelling = new Doelstelling();
                doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                doelstelling.setNaam(rs.getString("naam"));
                doelstelling.setBeschrijving(rs.getString("beschrijving"));
                doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
                doelstellingen.add(doelstelling);
            }
        } catch (Exception e) {
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return doelstellingen;
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
}
