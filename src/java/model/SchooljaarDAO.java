package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * SchooljaarDAO(Schooljaar Data Access Object) is een klasse voor alle
 * handelingen in de database betreffend Schooljaren.
 *
 * @author Ewout Phlips
 */
public class SchooljaarDAO {

    public Schooljaar schooljaarLaden(String schooljaarNaam) {
        Schooljaar schooljaar = new Schooljaar();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT schooljaren.* FROM schooljaren WHERE schooljaren.schooljaar = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, schooljaarNaam);

            rs = ps.executeQuery();
            while (rs.next()) {
                schooljaar.setSchooljaarID(rs.getInt("schooljaarID"));
                schooljaar.setSchooljaar(rs.getString("schooljaar"));
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return schooljaar;
    }

    public Schooljaar schooljaarLaden(int schooljaarID) {
        Schooljaar schooljaar = new Schooljaar();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT schooljaren.* FROM schooljaren WHERE schooljaren.schooljaarID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, schooljaarID);

            rs = ps.executeQuery();
            while (rs.next()) {
                schooljaar.setSchooljaarID(rs.getInt("schooljaarID"));
                schooljaar.setSchooljaar(rs.getString("schooljaar"));
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return schooljaar;
    }

    public ArrayList<Schooljaar> schooljarenLaden() {
        ArrayList<Schooljaar> schooljaren = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT schooljaren.* FROM schooljaren";

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Schooljaar schooljaar = new Schooljaar();
                schooljaar.setSchooljaarID(rs.getInt("opleidingID"));
                schooljaar.setSchooljaar(rs.getString("schooljaar"));
                schooljaren.add(schooljaar);
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(statement, rs, currentCon);
        }
        return schooljaren;
    }

    public String geefSchooljaarNaam(int schooljaarID) {
        return schooljaarLaden(schooljaarID).getSchooljaar();
    }

    public int geefSchooljaarID(String schooljaarNaam) {
        return schooljaarLaden(schooljaarNaam).getSchooljaarID();
    }
}
