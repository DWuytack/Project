package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * StudiegebiedDAO(Studiegebied Data Access Object) is een klasse voor alle
 * handelingen in de database betreffend Studiegebieden.
 *
 * @author Ewout Phlips
 */
public class StudiegebiedDAO {

    public void studiegebiedToevoegen(Studiegebied studiegebied) {

        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO studiegebieden "
                + "(naam, beschrijving) VALUES (?,?);";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setString(1, studiegebied.getNaam());
            ps.setString(2, studiegebied.getBeschrijving());
            ps.execute();

        } catch (SQLException e) {

        } finally {
            sluitVariabelen(null, null, ps, currentCon);
        }
    }

    public void studiegebiedAanpassen(Studiegebied studiegebied) {

        Connection currentCon = null;
        Statement statement = null;

        String sql = "UPDATE studiegebieden "
                + " SET naam =  " + studiegebied.getNaam() + ", "
                + " beschrijving = " + studiegebied.getBeschrijving()
                + " WHERE studiegebiedID= "
                + studiegebied.getStudiegebiedID();

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(null, statement, null, currentCon);
        }
    }

    public void studiegebiedVerwijderen(Studiegebied studiegebied) {

        Connection currentCon = null;
        Statement statement = null;

        String sql = "DELETE FROM studiegebieden "
                + " WHERE studiegebiedID= " + studiegebied.getStudiegebiedID();

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(null, statement, null, currentCon);
        }
    }

    public Studiegebied studiegebiedLaden(int studiegebiedID) {

        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM studiegebieden WHERE studiegebieden.studiegebiedID = ?;";
        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, studiegebiedID);
            rs = ps.executeQuery();

            Studiegebied studiegebied = new Studiegebied();
            studiegebied.setStudiegebiedID(rs.getInt("studiegebiedID"));
            studiegebied.setNaam(rs.getString("naam"));
            studiegebied.setBeschrijving(rs.getString("beschrijving"));

            return studiegebied;
        } catch (Exception e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return null;
    }

    public ArrayList<Studiegebied> studiegebiedenLaden() {

        ArrayList<Studiegebied> studiegebieden = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select * from studiegebieden";

            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Studiegebied studiegebied = new Studiegebied();
                studiegebied.setStudiegebiedID(rs.getInt("studiegebiedID"));
                studiegebied.setNaam(rs.getString("naam"));
                studiegebied.setBeschrijving("beschrijving");
                studiegebieden.add(studiegebied);
            }

        } catch (Exception e) {
        } finally {
            sluitVariabelen(rs, statement, null, currentCon);
        }
        return studiegebieden;
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

    public int geefStudieGebied(String studiegebied) {
        return 1;
    }
}
