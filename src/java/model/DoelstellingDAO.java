package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DoelstellingDAO(Doelstelling Data Access Object) is een klasse voor alle
 * handelingen in de database betreffend Doelstellingen.
 *
 * @author Ewout Phlips
 */
public class DoelstellingDAO {

    public ArrayList<Doelstelling> laadDoelstellingen(int taakID) {
        ArrayList<Doelstelling> doelstellingen = new ArrayList();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from doelstellingen "
                + " inner join doelstellingen_taken on doelstellingen_taken.doelstellingID=doelstellingen.doelstellingID "
                + " where doelstellingen_taken.taakID=?;";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, taakID);

            rs = ps.executeQuery();
            while (rs.next()) {
                Doelstelling doelstelling = new Doelstelling();
                doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                doelstelling.setNaam(rs.getString("naam"));
                doelstelling.setBeschrijving(rs.getString("beschrijving"));
                doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
                doelstellingen.add(doelstelling);
            }

        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }

        return doelstellingen;
    }

    public void doelstellingToevoegen(Doelstelling doelstelling) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO doelstellingen (doelstellingen.naam, doelstellingen.beschrijving, doelstellingen.kerndoelstelling) VALUES (?, ?, ?)";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, doelstelling.getNaam());
            ps.setString(2, doelstelling.getBeschrijving());
            ps.setBoolean(3, doelstelling.getKerndoelstelling());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void doelstellingenToevoegen(ArrayList<Doelstelling> doelstellingen) {
        doelstellingen.forEach((doelstelling) -> this.doelstellingToevoegen(doelstelling));
    }

    public void doelstellingAanpassen(Doelstelling doelstelling) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "UPDATE doelstellingen SET naam = ?, beschrijving = ?, kerndoelstelling = ? WHERE doelstellingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, doelstelling.getNaam());
            ps.setString(2, doelstelling.getBeschrijving());
            ps.setBoolean(3, doelstelling.getKerndoelstelling());
            ps.setInt(4, doelstelling.getDoelstellingID());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void doelstellingenAanpassen(ArrayList<Doelstelling> doelstellingen) {
        doelstellingen.forEach((doelstelling) -> this.doelstellingAanpassen(doelstelling));
    }

    public void doelstellingVerwijderen(Doelstelling doelstelling) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM doelstellingen WHERE doelstellingen.doelstellingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, doelstelling.getDoelstellingID());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void doelstellingenVerwijderen(ArrayList<Doelstelling> doelstellingen) {
        doelstellingen.forEach((doelstelling) -> this.doelstellingVerwijderen(doelstelling));
    }

    public Doelstelling doelstellingLaden(String doelstellingNaam) {
        Doelstelling doelstelling = new Doelstelling();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT doelstellingen.* FROM doelstellingen WHERE doelstellingen.naam = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, doelstellingNaam);

            rs = ps.executeQuery();
            while (rs.next()) {
                doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                doelstelling.setNaam(rs.getString("naam"));
                doelstelling.setBeschrijving(rs.getString("beschrijving"));
                doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
            }

        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }

        return doelstelling;
    }

    public Doelstelling doelstellingLaden(int doelstellingID) {
        Doelstelling doelstelling = new Doelstelling();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT doelstellingen.* FROM doelstellingen WHERE doelstellingen.doelstellingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, doelstellingID);

            rs = ps.executeQuery();
            while (rs.next()) {
                doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                doelstelling.setNaam(rs.getString("naam"));
                doelstelling.setBeschrijving(rs.getString("beschrijving"));
                doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
            }

        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }

        return doelstelling;
    }

    public ArrayList<Doelstelling> doelstellingenLaden() {
        ArrayList<Doelstelling> doelstellingen = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT doelstellingen.* FROM doelstellingen";

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Doelstelling doelstelling = new Doelstelling();
                doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                doelstelling.setNaam(rs.getString("naam"));
                doelstelling.setBeschrijving(rs.getString("beschrijving"));
                doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
                doelstellingen.add(doelstelling);
            }

        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(statement, rs, currentCon);
        }

        return doelstellingen;
    }

    public ArrayList<ScoreOverzicht> doelstellingenLadenModule(int moduleID) {
        ArrayList<ScoreOverzicht> overzicht = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from doelstellingen \n" +
            " where doelstellingID in\n" +
            " (select doelstellingID from doelstellingen_modules\n" +
            " where moduleID=?"
                + " order by doelstellingID);";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, moduleID);

            rs = ps.executeQuery();

            while (rs.next()) {
                ScoreOverzicht scoreOverzicht = new ScoreOverzicht();
                scoreOverzicht.setDoelstellingnaam(rs.getString("naam"));
                scoreOverzicht.setDoelstellingID(rs.getInt("doelstellingID"));
                scoreOverzicht.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
                overzicht.add(scoreOverzicht);
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }

        return overzicht;
    }

    public String geefDoelstellingNaam(int doelstellingID) {
        return doelstellingLaden(doelstellingID).getNaam();
    }

    public int geefDoelstellingID(String doelstellingNaam) {
        return doelstellingLaden(doelstellingNaam).getDoelstellingID();
    }

    public int geefAantalDoelstellingen() {
        return doelstellingenLaden().size();
    }

    public int geefAantalDoelstellingen(int moduleID) {
        return doelstellingenLadenModule(moduleID).size();
    }
}
