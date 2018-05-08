package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * OpleidingDAO(Opleiding Data Access Object) is een klasse voor alle
 * handelingen in de database betreffend Opleidingen.
 *
 * @author Ewout Phlips
 */
public class OpleidingDAO {

    public void opleidingToevoegen(Opleiding opleiding) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO opleidingen (opleiding.naam) VALUES (?)";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, opleiding.getNaam());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void opleidingenToevoegen(ArrayList<Opleiding> opleidingen) {
        opleidingen.forEach((opleiding) -> this.opleidingToevoegen(opleiding));
    }

    public void opleidingAanpassen(Opleiding opleiding) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "UPDATE opleidingen SET naam = ? WHERE opleidingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, opleiding.getNaam());
            ps.setInt(2, opleiding.getOpleidingID());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void opleidingenAanpassen(ArrayList<Opleiding> opleidingen) {
        opleidingen.forEach((opleiding) -> this.opleidingAanpassen(opleiding));
    }

    public void opleidingVerwijderen(Opleiding opleiding) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM opleidingen WHERE opleidingen.opleidingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, opleiding.getOpleidingID());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void opleidingenVerwijderen(ArrayList<Opleiding> opleidingen) {
        opleidingen.forEach((opleiding) -> this.opleidingVerwijderen(opleiding));
    }

    public Opleiding opleidingLaden(String opleidingNaam) {
        Opleiding opleiding = new Opleiding();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT opleidingen.* FROM opleidingen WHERE opleidingen.naam = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, opleidingNaam);

            rs = ps.executeQuery();
            while (rs.next()) {
                opleiding.setOpleidingID(rs.getInt("opleidingID"));
                opleiding.setNaam(rs.getString("naam"));
            }

        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return opleiding;
    }

    public Opleiding opleidingLaden(int opleidingID) {
        Opleiding opleiding = new Opleiding();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT opleidingen.* FROM opleidingen WHERE opleidingen.opleidingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, opleidingID);

            rs = ps.executeQuery();
            while (rs.next()) {
                opleiding.setOpleidingID(rs.getInt("opleidingID"));
                opleiding.setNaam(rs.getString("naam"));
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return opleiding;
    }

    public ArrayList<Opleiding> opleidingenLaden() {
        ArrayList<Opleiding> opleidingen = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT opleidingen.* FROM opleidingen";

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Opleiding opleiding = new Opleiding();
                opleiding.setOpleidingID(rs.getInt("opleidingID"));
                opleiding.setNaam(rs.getString("naam"));
                opleidingen.add(opleiding);
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(statement, rs, currentCon);
        }
        return opleidingen;
    }

    public ArrayList<Opleiding> opleidingenLaden(int studiegebiedID) {
        ArrayList<Opleiding> opleidingen = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT opleidingen.* FROM opleidingen "
                + "INNER JOIN studiegebieden_opleidingen ON opleidingen.opleidingID = studiegebieden_opleidingen.opleidingID "
                + "WHERE studiegebieden_opleidingen.studiegebiedID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, studiegebiedID);

            rs = ps.executeQuery();

            while (rs.next()) {
                Opleiding opleiding = new Opleiding();
                opleiding.setOpleidingID(rs.getInt("opleidingID"));
                opleiding.setNaam(rs.getString("naam"));
                opleidingen.add(opleiding);
            }
        } catch (Exception e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return opleidingen;
    }

    public String geefOpleidingNaam(int opleidingID) {
        return opleidingLaden(opleidingID).getNaam();
    }

    public int geefOpleidingID(String opleidingNaam) {
        return opleidingLaden(opleidingNaam).getOpleidingID();
    }

    public int geefAantalOpleidingen() {
        return opleidingenLaden().size();
    }

    public int geefAantalOpleidingen(int studiegebiedID) {
        return opleidingenLaden(studiegebiedID).size();
    }

}
