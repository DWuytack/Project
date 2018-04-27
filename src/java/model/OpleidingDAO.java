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
 * @author Davino & Ewout Phlips
 */
public class OpleidingDAO {

    public void opleidingToevoegen(Opleiding opleiding) {

        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO opleidingen "
                + "(naam) VALUES (?);";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setString(1, opleiding.getNaam());
            ps.execute();

        } catch (SQLException e) {

        } finally {
            sluitVariabelen(null, null, ps, currentCon);
        }
    }

    public void opleidingAanpassen(Opleiding opleiding) {

        Connection currentCon = null;
        Statement statement = null;

        String sql = "UPDATE opleidingen "
                + " SET naam =  " + opleiding.getNaam()
                + " WHERE opleidingID= "
                + opleiding.getOpleidingID();

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(null, statement, null, currentCon);
        }
    }

    public void opleidingVerwijderen(Opleiding opleiding) {

        Connection currentCon = null;
        Statement statement = null;

        String sql = "DELETE FROM opleidingen "
                + " WHERE opleidingID= " + opleiding.getOpleidingID();

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(null, statement, null, currentCon);
        }
    }

    public Opleiding opleidingLaden(int opleidingID) {

        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM opleidingen WHERE opleidingen.opleidingID = ?;";
        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, opleidingID);
            rs = ps.executeQuery();

            Opleiding opleiding = new Opleiding();
            opleiding.setOpleidingID(rs.getInt("opleidingID"));
            opleiding.setNaam(rs.getString("naam"));

            return opleiding;
        } catch (Exception e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return null;
    }

    public ArrayList<Opleiding> opleidingenLaden() {

        ArrayList<Opleiding> opleidingen = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM opleidingen;";
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
        return opleidingen;
    }

    public ArrayList<Opleiding> opleidingenLaden(int studiegebiedID) {

        ArrayList<Opleiding> opleidingen = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT studiegebieden_opleidingen.opleidingID, opleidingen.naam FROM studiegebieden_opleidingen Inner join opleidingen on studiegebieden_opleidingen.opleidingID = opleidingen.opleidingID Where studiegebiedID = ? ";
            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, studiegebiedID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Opleiding opleiding = new Opleiding();
                opleiding.setOpleidingID(rs.getInt("opleidingID"));
                opleiding.setNaam(rs.getString("naam"));

                opleidingen.add(opleiding);
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
                } catch (Exception e) {

                }

                currentCon = null;
            }

        }
        return opleidingen;
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
